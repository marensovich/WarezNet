package me.marensovich.wareznet.controller.v1;

import me.marensovich.wareznet.controller.v1.requests.UpdateCategoryRequest;
import me.marensovich.wareznet.database.models.FileCategory;
import me.marensovich.wareznet.controller.v1.requests.CreateCategoryRequest;
import me.marensovich.wareznet.service.FileCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST controller for handling category operations.
 *
 * <p>This controller provides endpoints for:
 * <ul>
 *     <li>Creating categories</li>
 *     <li>Retrieving categories</li>
 *     <li>Updating categories</li>
 *     <li>Deleting categories</li>
 * </ul>
 *
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
@RestController
@RequestMapping("/api/v1/category")
public class FileCategoryController {

    @Autowired private FileCategoryService fileCategoryService;

    /**
     * Retrieves all file categories.
     *
     * @return the response entity with the list of categories
     * @since v.0.1
     */
    @GetMapping("/get")
    public ResponseEntity<List<FileCategory>> getAllFileCategories() {
        return ResponseEntity.ok(fileCategoryService.getAllCategories());
    }

    /**
     * Retrieves a file category by its ID.
     *
     * @param id the category UUID
     * @return the response entity with the category
     * @since v.0.1
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<FileCategory> getFileCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(fileCategoryService.getFileCategoryById(id));
    }

    /**
     * Creates a new file category.
     *
     * @param request the request body containing the category name
     * @return the created file category
     * @since v.0.1
     */
    @PostMapping("/create")
    public ResponseEntity<FileCategory> createFileCategory(@RequestBody CreateCategoryRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(fileCategoryService.createFileCategory(request.getName()));
    }

    /**
     * Updates an existing file category.
     *
     * @param request the request body containing the category UUID and new name
     * @return the updated category
     * @since v.0.1
     */
    @PatchMapping("/update")
    public ResponseEntity<FileCategory> updateFileCategory(@RequestBody UpdateCategoryRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileCategoryService.updateFileCategory(request.getUuid(), request.getName()));
    }

    /**
     * Deletes a file category by UUID.
     *
     * @param uuid the category UUID
     * @return a success message
     * @since v.0.1
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFileCategory(@RequestBody UUID uuid) {
        fileCategoryService.deleteCategory(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Successfully deleted"));
    }
}
