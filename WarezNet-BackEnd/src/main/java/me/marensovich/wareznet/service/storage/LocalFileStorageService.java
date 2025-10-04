package me.marensovich.wareznet.service.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
@Profile("local")
public class LocalFileStorageService implements FileStorageService {

    @Value("${storage.local.path:files}")
    private String basePath;

    @Override
    public String saveFile(UUID id, byte[] data, String originalFilename) {
        try {
            String safeName = id.toString() + "_" + originalFilename.replace(" ", "-");
            Path dir = Paths.get(basePath);
            Files.createDirectories(dir);
            Path filePath = dir.resolve(safeName);
            Files.write(filePath, data);
            return filePath.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении файла", e);
        }
    }


    @Override
    public byte[] readFile(UUID uuid) {
        try {
            return Files.readAllBytes(Paths.get(basePath, String.valueOf(uuid)));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла", e);
        }
    }

    @Override
    public void deleteFile(UUID uuid) {
        try {
            Files.deleteIfExists(Paths.get(basePath, String.valueOf(uuid)));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при удалении файла", e);
        }
    }
}
