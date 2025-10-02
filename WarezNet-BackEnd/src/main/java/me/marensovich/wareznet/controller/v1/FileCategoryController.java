package me.marensovich.wareznet.controller.v1;

import me.marensovich.wareznet.database.models.FileCategory;
import me.marensovich.wareznet.database.repository.FileCategoryRepository;
import me.marensovich.wareznet.service.FileCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    @Autowired private FileCategoryRepository fileCategoryRepository;
    @Autowired private FileCategoryService fileCategoryService;


    @GetMapping("/get")
    public ResponseEntity<List<FileCategory>> getAllFileCategories() {
        return ResponseEntity.ok(fileCategoryService.getAllCategories());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FileCategory> getFileCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(fileCategoryService.getFileCategoryById(id));
    }

}
