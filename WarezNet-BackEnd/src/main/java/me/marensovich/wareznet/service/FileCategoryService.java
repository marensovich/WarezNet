package me.marensovich.wareznet.service;

import me.marensovich.wareznet.database.models.FileCategory;
import me.marensovich.wareznet.database.repository.FileCategoryRepository;
import me.marensovich.wareznet.exception.exceptions.FileCategoryNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing file categories.
 *
 * <p>This service provides logic for:
 * <ul>
 *     <li>Creating new categories</li>
 *     <li>Retrieving categories</li>
 *     <li>Updating categories</li>
 *     <li>Deleting categories</li>
 * </ul>
 *
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
@Service
public class FileCategoryService {

    @Autowired private FileCategoryRepository fileCategoryRepository;

    /**
     * Creates a new file category.
     *
     * @param name the name of the category
     * @return the created {@link FileCategory} entity
     * @since v.0.1
     */
    public FileCategory createFileCategory(String name){
        FileCategory fileCategory = new FileCategory();
        fileCategory.setName(name);

        return fileCategoryRepository.save(fileCategory);
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return list of all {@link FileCategory}
     * @since v.0.1
     */
    public List<FileCategory> getAllCategories(){
        return fileCategoryRepository.findAll();
    }

    /**
     * Retrieves a category by its UUID.
     *
     * @param id the UUID of the category
     * @return the {@link FileCategory} entity
     * @throws FileCategoryNotFound if no category with the given UUID exists
     * @since v.0.1
     */
    public FileCategory getFileCategoryById(UUID id){
        return fileCategoryRepository.findById(id).orElseThrow(
                () -> new FileCategoryNotFound("File category not found with UUID: " + id)
        );
    }

    /**
     * Deletes a category by its UUID.
     *
     * @param uuid the UUID of the category to delete
     * @throws FileCategoryNotFound if no category with the given UUID exists
     * @since v.0.1
     */
    public void deleteCategory(UUID uuid){
        if (!fileCategoryRepository.existsById(uuid)) {
            throw new FileCategoryNotFound("File category not found with UUID: " + uuid);
        }
        fileCategoryRepository.deleteById(uuid);
    }

    /**
     * Updates the name of an existing category.
     *
     * @param uuid the UUID of the category to update
     * @param name the new name of the category
     * @return the updated {@link FileCategory}
     * @since v.0.1
     */
    public FileCategory updateFileCategory(UUID uuid, String name) {
        FileCategory fileCategory = getFileCategoryById(uuid);
        fileCategory.setName(name);
        return fileCategoryRepository.save(fileCategory);
    }
}
