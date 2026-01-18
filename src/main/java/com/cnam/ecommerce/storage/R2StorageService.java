package com.cnam.ecommerce.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

@Service
public class R2StorageService {

    private final S3Client s3;
    private final String bucket;
    private final String publicBase;

    public R2StorageService(
            @Value("${R2_ENDPOINT}") String endpoint,
            @Value("${R2_ACCESS_KEY_ID}") String accessKey,
            @Value("${R2_SECRET_ACCESS_KEY}") String secretKey,
            @Value("${R2_BUCKET}") String bucket,
            @Value("${R2_PUBLIC_BASE}") String publicBase
    ) {
        this.bucket = bucket;
        this.publicBase = publicBase;

        this.s3 = S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey))
                )
                .region(Region.of("auto"))
                .build();
    }

    public String upload(MultipartFile file) throws Exception {
        String original = file.getOriginalFilename() == null ? "file" : file.getOriginalFilename();
        String ext = original.contains(".") ? original.substring(original.lastIndexOf(".")) : "";

        // Clean key (case-sensitive)
        String key = Instant.now().toEpochMilli() + "-" + UUID.randomUUID() + ext;

        PutObjectRequest req = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3.putObject(req, RequestBody.fromBytes(file.getBytes()));

        // Public URL via your Worker gateway
        return publicBase + "/catalog/" + key;
    }
}
