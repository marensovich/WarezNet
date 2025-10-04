package me.marensovich.wareznet.service;

import me.marensovich.wareznet.service.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StorageService {

    @Autowired private FileStorageService storage;

    public String upload(UUID uuid, byte[] data, String originalFilename) {
        return storage.saveFile(uuid, data, originalFilename);
    }

    public void delete(UUID uuid) {
        storage.deleteFile(uuid);
    }
    public byte[] readFile(UUID uuid) {
        return storage.readFile(uuid);
    }
}
