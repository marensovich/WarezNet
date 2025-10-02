package me.marensovich.wareznet.controller.v1;

import me.marensovich.wareznet.controller.v1.requests.UpdateCategoryRequest;
import me.marensovich.wareznet.database.models.FileCategory;
import me.marensovich.wareznet.database.repository.FileCategoryRepository;
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
 *     <li>Uploading files</li>
 *     <li>Downloading files by ID</li>
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


    @GetMapping("/get")
    public ResponseEntity<List<FileCategory>> getAllFileCategories() {
        return ResponseEntity.ok(fileCategoryService.getAllCategories());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FileCategory> getFileCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(fileCategoryService.getFileCategoryById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<FileCategory> createFileCategory(@RequestBody CreateCategoryRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(fileCategoryService.createFileCategory(request.getName()));
    }

    @PatchMapping("/update")
    public ResponseEntity<FileCategory> updateFileCategory(@RequestBody UpdateCategoryRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileCategoryService.updateFileCategory(request.getUuid(), request.getName()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFileCategory(@RequestBody UUID uuid) {
        fileCategoryService.deleteCategory(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Successfully deleted"));
    }

}
