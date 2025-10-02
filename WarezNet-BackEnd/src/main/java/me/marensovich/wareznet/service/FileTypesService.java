package me.marensovich.wareznet.service;

import me.marensovich.wareznet.database.models.FileTypes;
import me.marensovich.wareznet.database.repository.FileTypesRepository;
import me.marensovich.wareznet.exception.exceptions.FileTypeNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing file types.
 *
 * <p>This service provides logic for:
 * <ul>
 *     <li>Creating new file types</li>
 *     <li>Retrieving file types</li>
 *     <li>Updating file types</li>
 *     <li>Deleting file types</li>
 * </ul>
 *
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
@Service
public class FileTypesService {

    @Autowired private FileTypesRepository fileTypesRepository;

    /**
     * Creates a new file type.
     *
     * @param fileType the name of the file type
     * @return the created {@link FileTypes}
     * @since v.0.1
     */
    public FileTypes createFileTypes(String fileType){
        FileTypes fileTypes = new FileTypes();
        fileTypes.setName(fileType);
        return fileTypesRepository.save(fileTypes);
    }

    /**
     * Retrieves all file types.
     *
     * @return list of {@link FileTypes}
     * @since v.0.1
     */
    public List<FileTypes> getFileTypes(){
        return fileTypesRepository.findAll();
    }

    /**
     * Retrieves a file type by UUID.
     *
     * @param id the UUID of the file type
     * @return the {@link FileTypes} entity
     * @throws java.util.NoSuchElementException if not found
     * @since v.0.1
     */
    public FileTypes getFileType(UUID id){
        return fileTypesRepository.findById(id).get();
    }

    /**
     * Deletes a file type by its UUID.
     *
     * @param id the UUID of the file type
     * @throws FileTypeNotFound if the type does not exist
     * @since v.0.1
     */
    public void deleteFileType(UUID id){
        if (!fileTypesRepository.existsById(id)) {
            throw new FileTypeNotFound("File type not found with UUID: " + id);
        }
        fileTypesRepository.deleteById(id);
    }

    /**
     * Updates the name of an existing file type.
     *
     * @param id the UUID of the file type
     * @param name the new name
     * @return the updated {@link FileTypes}
     * @throws FileTypeNotFound if the type does not exist
     * @since v.0.1
     */
    public FileTypes updateFileType(UUID id, String name){
        FileTypes fileTypes = fileTypesRepository.findById(id).orElseThrow(
                () -> new FileTypeNotFound("File type not found with UUID: " + id)
        );
        fileTypes.setName(name);
        return fileTypesRepository.save(fileTypes);
    }
}
