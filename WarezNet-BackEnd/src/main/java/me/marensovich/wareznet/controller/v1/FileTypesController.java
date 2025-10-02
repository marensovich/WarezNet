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
 *     <li>Creating file types</li>
 *     <li>Retrieving file types</li>
 *     <li>Updating file types</li>
 *     <li>Deleting file types</li>
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

    /**
     * Retrieves all file types.
     *
     * @return the response entity with the list of file types
     * @since v.0.1
     */
    @GetMapping("/get")
    public ResponseEntity<List<FileTypes>> getAllFileCategories() {
        return ResponseEntity.ok(fileTypesService.getFileTypes());
    }

    /**
     * Retrieves a file type by ID.
     *
     * @param id the type UUID
     * @return the file type
     * @since v.0.1
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<FileTypes> getFileCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(fileTypesService.getFileType(id));
    }

    /**
     * Creates a new file type.
     *
     * @param request the request body containing the type name
     * @return the created type
     * @since v.0.1
     */
    @PostMapping("/create")
    public ResponseEntity<FileTypes> createFileCategory(@RequestBody CreateTypeRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(fileTypesService.createFileTypes(request.getName()));
    }

    /**
     * Updates an existing file type.
     *
     * @param request the request body containing the type UUID and new name
     * @return the updated type
     * @since v.0.1
     */
    @PatchMapping("/update")
    public ResponseEntity<FileTypes> updateFileCategory(@RequestBody UpdateTypeRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileTypesService.updateFileType(request.getId(), request.getName()));
    }

    /**
     * Deletes a file type by UUID.
     *
     * @param uuid the type UUID
     * @return a success message
     * @since v.0.1
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFileCategory(@RequestBody UUID uuid) {
        fileTypesService.deleteFileType(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Successfully deleted"));
    }
}
