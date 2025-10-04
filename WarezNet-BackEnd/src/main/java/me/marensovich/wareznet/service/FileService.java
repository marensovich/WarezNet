package me.marensovich.wareznet.service;

import me.marensovich.wareznet.database.models.FileCategory;
import me.marensovich.wareznet.database.models.FileDescription;
import me.marensovich.wareznet.database.models.FileTypes;
import me.marensovich.wareznet.database.models.Files;
import me.marensovich.wareznet.database.repository.FileCategoryRepository;
import me.marensovich.wareznet.database.repository.FileDescriptionRepository;
import me.marensovich.wareznet.database.repository.FileTypesRepository;
import me.marensovich.wareznet.database.repository.FilesRepository;
import me.marensovich.wareznet.exception.exceptions.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class responsible for file-related operations.
 *
 * <p>This service provides logic for:
 * <ul>
 *     <li>Uploading new files</li>
 *     <li>Fetching file names by IDs</li>
 *     <li>Retrieving files grouped by type</li>
 * </ul>
 *
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
@Service
public class FileService {

    @Autowired private FilesRepository filesRepository;
    @Autowired private FileDescriptionRepository fileDescriptionRepository;
    @Autowired private FileTypesRepository fileTypesRepository;
    @Autowired private FileCategoryRepository fileCategoryRepository;
    @Autowired
    private StorageService storageService;

    /**
     * Retrieves file names mapped to their IDs.
     *
     * @param IDs a comma-separated string of UUIDs
     * @return a map where keys are string representations of IDs and values are corresponding file names
     * @since v.0.1
     */
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

    /**
     * Uploads a new file and stores it in the database.
     *
     * @param typeId      the UUID of the file type
     * @param categoryId  the UUID of the file category
     * @param release     the release version/date of the file
     * @param name        the file name
     * @param description the description of the file
     * @param fileIcon    the path to the file icon
     * @return the newly created {@link Files} entity
     * @throws IllegalArgumentException if the type or category does not exist
     * @since v.0.1
     */
    public Files uploadFile(UUID typeId,
                            UUID categoryId,
                            byte[] data,
                            String release,
                            String name,
                            String description,
                            String fileIcon) {


        UUID uuid = UUID.randomUUID();

        String path = storageService.upload(uuid, data, name);

        Files newFile = new Files();
        newFile.setPath(path);
        newFile.setRelease(release);
        newFile.setId(uuid);

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

    /**
     * Retrieves all files grouped by their type.
     *
     * @return a map where keys are type names and values are lists of files belonging to that type
     * @since v.0.1
     */
    public Map<String, List<Files>> getAllFilesGroupedByType() {
        List<Files> allFiles = filesRepository.findAll();

        return allFiles.stream()
                .collect(Collectors.groupingBy(file -> file.getType().getName()));
    }

    public Files getFileByUuid(UUID uuid){
        return filesRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("File not found: " + uuid));
    }

    /**
     * Download FILE
     */
    public File downloadFile(UUID uuid) {
        Files fileEntity = filesRepository.findById(uuid).orElseThrow(
                () -> new FileNotFoundException("The requested file was not found")
        );
        byte[] data = storageService.readFile(uuid);

        try {
            File tempFile = File.createTempFile(
                    "download_", "_" + fileEntity.getDescription().getName()
            );
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(data);
            }

            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании временного файла", e);
        }
    }

}
