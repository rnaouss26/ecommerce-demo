package com.cnam.ecommerce.api;

import com.cnam.ecommerce.storage.R2StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UploadController {

    private final R2StorageService storage;

    public UploadController(R2StorageService storage) {
        this.storage = storage;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "file is required"));
        }
        String url = storage.upload(file);
        return ResponseEntity.ok(Map.of("url", url));
    }
}
