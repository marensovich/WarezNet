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
    public void saveFile(UUID uuid, byte[] data) {
        try {
            Path path = Paths.get(basePath, String.valueOf(uuid));
            Files.createDirectories(path.getParent());
            Files.write(path, data);
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
