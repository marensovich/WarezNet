package me.marensovich.wareznet.service;


import me.marensovich.wareznet.database.models.FileCategory;
import me.marensovich.wareznet.database.models.FileDescription;
import me.marensovich.wareznet.database.models.FileTypes;
import me.marensovich.wareznet.database.models.Files;
import me.marensovich.wareznet.database.repository.FileCategoryRepository;
import me.marensovich.wareznet.database.repository.FileDescriptionRepository;
import me.marensovich.wareznet.database.repository.FileTypesRepository;
import me.marensovich.wareznet.database.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * The type File service.
 */
@Service
public class FileService {

    @Autowired private FilesRepository filesRepository;
    @Autowired private FileDescriptionRepository fileDescriptionRepository;
    @Autowired private FileTypesRepository fileTypesRepository;
    @Autowired private FileCategoryRepository fileCategoryRepository;


    public Map<String, String> getFileNameWithID(String IDs) {
        Map<String, String> files = new HashMap<>();
        for (String ID : IDs.split(",")) {
            UUID uuid;
            try {
                uuid = UUID.fromString(ID.trim());
            } catch (IllegalArgumentException e) {
                continue;
            }
            String fileName = filesRepository.getFileNameById(uuid);
            if (fileName != null) {
                files.put(ID, fileName);
            }
        }
        return files;
    }

    public Files uploadFile(UUID typeId,
                            UUID categoryId,
                            String release,
                            String name,
                            String description,
                            String fileIcon) {

        //TODO: Убрать заглушку
        String path = "";

        Files newFile = new Files();
        newFile.setPath(path);
        newFile.setRelease(release);

        FileTypes type = fileTypesRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("Type not found: " + typeId));

        FileCategory category = fileCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId));

        FileDescription fileDescription = new FileDescription();
        fileDescription.setName(name);
        fileDescription.setDescription(description);
        fileDescription.setFileIcon(fileIcon);
        fileDescriptionRepository.save(fileDescription);

        newFile.setDescription(fileDescription);
        newFile.setType(type);
        newFile.setCategory(category);

        return filesRepository.save(newFile);
    }
}
