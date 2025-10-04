package me.marensovich.wareznet.service.storage;

import java.util.UUID;

public interface FileStorageService {
    void saveFile(UUID uuid, byte[] data);
    byte[] readFile(UUID uuid);
    void deleteFile(UUID uuid);
}

