package com.example.real_school.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Student")
public class testController {
   @GetMapping("/test")

    public String test() {
        return "School Management API is working!";
    }
    @GetMapping("/getAllStudent")
    List<Map<String,Object>> getStudnet()
    {
        return  List.of(
            Map.of("id",1,"name","Vibol","age",20),
            Map.of("id",2,"name","Vibol","age",25),
            Map.of("id",2,"Mrr Neath","Vibol","age",24)

        );
    }
    @GetMapping
    List<Map<String,Object>>allTeacher()
    {
        return List.of(
            Map.of("id",12,"name","Bslanhoun"),
            Map.of("id",13,"name","Ounslbongort"),
            Map.of("id",14,"name","Why "),
            Map.of("id",15,"name","Heryy oun")
        );
    }
    {

    }
    @GetMapping("/hello")
    public  String name()
    {
        return "My name is Vibol";
    }
    @GetMapping("/students/{id}")
    public String studentById(@PathVariable Long id) {
        return "Student ID: " + id + "is founded";
    }
    @PostMapping("/input_data")
    public String Create(@RequestParam int id,@RequestParam String name)
    {
        return name + id;

    }
}