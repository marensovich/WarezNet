package me.marensovich.wareznet;

import me.marensovich.wareznet.service.storage.FileStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FileStorageTests {

    @Autowired private FileStorageService storage;

    @Test
    void testSaveAndRead() {
        byte[] data = "Hello".getBytes();
        UUID id = UUID.randomUUID();
        storage.saveFile(id, data);

        byte[] read = storage.readFile(id);
        assertArrayEquals(data, read);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();
        storage.saveFile(id, "Data".getBytes());
        storage.deleteFile(id);

        assertThrows(RuntimeException.class, () -> storage.readFile(id));
    }

}
