package me.marensovich.wareznet.controller.v1;


import me.marensovich.wareznet.controller.v1.requests.CreateTypeRequest;
import me.marensovich.wareznet.controller.v1.requests.UpdateTypeRequest;
import me.marensovich.wareznet.database.models.FileTypes;
import me.marensovich.wareznet.service.FileTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST controller for handling file types operations.
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
@RequestMapping("/api/v1/types")
public class FileTypesController {
    @Autowired private FileTypesService fileTypesService;


    @GetMapping("/get")
    public ResponseEntity<List<FileTypes>> getAllFileCategories() {
        return ResponseEntity.ok(fileTypesService.getFileTypes());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FileTypes> getFileCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(fileTypesService.getFileType(id));
    }

    @PostMapping("/create")
    public ResponseEntity<FileTypes> createFileCategory(@RequestBody CreateTypeRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(fileTypesService.createFileTypes(request.getName()));
    }

    @PatchMapping("/update")
    public ResponseEntity<FileTypes> updateFileCategory(@RequestBody UpdateTypeRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileTypesService.updateFileType(request.getId(), request.getName()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFileCategory(@RequestBody UUID uuid) {
        fileTypesService.deleteFileType(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Successfully deleted"));
    }

}
