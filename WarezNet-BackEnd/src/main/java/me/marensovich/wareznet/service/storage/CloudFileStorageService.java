package me.marensovich.wareznet.service.storage;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@Profile("cloud")
public class CloudFileStorageService implements FileStorageService {

    private final S3Client s3;
    private final String bucketName = "my-app-bucket";

    public CloudFileStorageService(S3Client s3) {
        this.s3 = s3;
    }

    @Override
    public String saveFile(UUID uuid, byte[] data, String originalFilename) {
        String key = uuid.toString() + "_" + originalFilename.replace(" ", "-");

        try {
            s3.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    RequestBody.fromBytes(data)
            );
            return "s3://" + bucketName + "/" + key;
        } catch (Exception e) {
            throw new RuntimeException("Error while saving file to S3", e);
        }
    }


    @Override
    public byte[] readFile(UUID uuid) {
        var resp = s3.getObject(GetObjectRequest.builder().bucket(bucketName).key(String.valueOf(uuid)).build());
        try (InputStream in = resp) {
            return in.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file from S3", e);
        }
    }

    @Override
    public void deleteFile(UUID uuid) {
        s3.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(String.valueOf(uuid)).build());
    }
}
