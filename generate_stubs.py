import os
import re

base_dir = "src/main/java/com/example/real_school"

# Find all empty or small files
empty_files = []
for root, dirs, files in os.walk(base_dir):
    for f in files:
        if f.endswith(".java"):
            path = os.path.join(root, f)
            if os.path.getsize(path) < 150:
                empty_files.append(path)

print("Empty files:", empty_files)

for path in empty_files:
    if path.endswith("ServiceImpl.java"):
        # This is an implementation
        interface_path = path.replace("/impl/", "/").replace("ServiceImpl.java", "Service.java")
        if not os.path.exists(interface_path):
            continue
        
        with open(interface_path, "r") as f:
            interface_code = f.read()
        
        package_match = re.search(r"package\s+(.*?);", interface_code)
        if not package_match:
            continue
        package = package_match.group(1)
        impl_package = package + ".impl"
        
        class_name = os.path.basename(path).replace(".java", "")
        interface_name = class_name.replace("Impl", "")
        
        # Extract methods
        methods = []
        for line in interface_code.split("\n"):
            line = line.strip()
            if line.endswith(";") and not line.startswith("import") and not line.startswith("package"):
                methods.append(line)
        
        impl_code = f"package {impl_package};\n\n"
        impl_code += f"import {package}.{interface_name};\n"
        impl_code += "import org.springframework.stereotype.Service;\n"
        impl_code += "import java.util.List;\n"
        impl_code += "import java.util.Collections;\n"
        # imports from interface
        for line in interface_code.split("\n"):
            if line.startswith("import "):
                impl_code += line + "\n"
        
        impl_code += f"\n@Service\npublic class {class_name} implements {interface_name} {{\n"
        
        for method in methods:
            # Parse method
            method = method.replace(";", "")
            
            # Find return type by splitting by space and taking first word before method name
            # Handle generics gracefully
            method_without_generics = re.sub(r'<[^>]+>', '', method)
            parts = method.split(" ")
            
            # This is a bit fragile, let's just use a regex
            # format: return_type methodName(args)
            match = re.search(r'(?:public\s+)?([\w<>,\[\]]+)\s+(\w+)\s*\((.*)\)', method)
            if match:
                return_type = match.group(1)
                method_name = match.group(2)
                args = match.group(3)
                
                impl_code += f"    @Override\n"
                impl_code += f"    public {return_type} {method_name}({args}) {{\n"
                if return_type == "void":
                    impl_code += "        // TODO: implement\n"
                elif return_type.startswith("List"):
                    impl_code += "        return Collections.emptyList();\n"
                elif return_type in ["int", "long", "float", "double", "boolean"]:
                    impl_code += "        return 0;\n"
                else:
                    impl_code += "        return null;\n"
                impl_code += "    }\n"
        
        impl_code += "}\n"
        
        with open(path, "w") as f:
            f.write(impl_code)
        print(f"Generated {path}")
        
    elif path.endswith("Service.java") and not "Impl" in path:
        # It's an empty interface. We need to create the Impl for it.
        with open(path, "r") as f:
            content = f.read()
        package_match = re.search(r"package\s+(.*?);", content)
        if not package_match:
            continue
        package = package_match.group(1)
        interface_name = os.path.basename(path).replace(".java", "")
        class_name = interface_name + "Impl"
        impl_dir = os.path.join(os.path.dirname(path), "impl")
        os.makedirs(impl_dir, exist_ok=True)
        impl_path = os.path.join(impl_dir, class_name + ".java")
        
        impl_package = package + ".impl"
        impl_code = f"package {impl_package};\n\n"
        impl_code += f"import {package}.{interface_name};\n"
        impl_code += "import org.springframework.stereotype.Service;\n"
        impl_code += f"\n@Service\npublic class {class_name} implements {interface_name} {{\n"
        impl_code += "}\n"
        
        with open(impl_path, "w") as f:
            f.write(impl_code)
        print(f"Generated {impl_path}")
