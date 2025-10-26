package com.shihtzhugh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class StartupSpacesWriteTask implements CommandLineRunner {

    private final S3Client s3Client;

    @Value("${spaces.bucket}")
    private String bucket;

    StartupSpacesWriteTask(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void run(String... args) {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key("test.txt")
                        .acl("public-read")
                        .build(),
                RequestBody.fromString("Hello, DigitalOcean Spaces!"));
    }
}
