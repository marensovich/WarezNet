package me.marensovich.wareznet.service.storage;

import java.util.UUID;

public interface FileStorageService {
    String saveFile(UUID uuid, byte[] data, String originalFilename);
    byte[] readFile(UUID uuid);
    void deleteFile(UUID uuid);
}

