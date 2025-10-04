package me.marensovich.wareznet.controller.v1;

import me.marensovich.wareznet.database.models.Files;
import me.marensovich.wareznet.service.FileService;
import me.marensovich.wareznet.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * REST controller for handling file operations.
 *
 * <p>This controller provides endpoints for:
 * <ul>
 *     <li>Uploading files</li>
 *     <li>Retrieving all files grouped by type</li>
 * </ul>
 *
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
@RestController
@RequestMapping("/api/v1/files")
public class FilesController {

    @Autowired private FileService fileService;
    @Autowired private IconService iconService;

    /**
     * Retrieves all files grouped by their type.
     *
     * @return the response entity containing a map where keys are type names and values are lists of files
     * @since v.0.1
     */
    @GetMapping("/get")
    public ResponseEntity<?> getFile() {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getAllFilesGroupedByType());
    }

    /**
     * Uploads a new file and stores it in the database.
     *
     * @param typeId      the UUID of the file type
     * @param categoryId  the UUID of the file category
     * @param release     the release date/version of the file
     * @param name        the display name of the file
     * @param description the file description
     * @param fileIcon    the file icon image
     * @return the response entity containing the created file ID
     * @since v.0.1
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("typeId") UUID typeId,
            @RequestParam("categoryid") UUID categoryId,
            @RequestParam("release") String release,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("fileIcon") MultipartFile fileIcon,
            @RequestParam("fileData") MultipartFile fileData
    ) {
        var iconPath = iconService.uploadIcon(fileIcon);

        Files newFile;
        try {
            newFile = fileService.uploadFile(
                    typeId,
                    categoryId,
                    fileData.getBytes(),
                    release,
                    name,
                    description,
                    iconPath
            );
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.OK).body("File successfully saved " + newFile.getId());
    }

    /**
     * Downloads a file by its ID.
     *
     * @since v.0.1
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable UUID uuid) {
        //TODO: Сделать логику работы
        File file = fileService.downloadFile(uuid);
        Files fileDescription = fileService.getFileByUuid(uuid);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDescription.getDescription().getName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE)
                .body(file);
    }
}
