package com.example.real_school.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Stores uploaded files on the local filesystem.
 *
 * <p>The directory is configurable via the {@code file.upload-dir} property
 * (default {@code uploads}). NOTE: container filesystems are ephemeral —
 * on Render Free, anything written here disappears on redeploy/restart. For real
 * production persistence use an object store (Cloudinary/S3/GCS) instead. An
 * object-store integration can be added here without changing callers.
 */
@Component
public class FileUploadUtils {

    private static String uploadDir = "uploads";

    public FileUploadUtils(@Value("${file.upload-dir:uploads}") String dir) {
        if (dir != null && !dir.isBlank()) {
            uploadDir = dir;
        }
    }

    public static String saveFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        return fileName;
    }
}