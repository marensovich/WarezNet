package me.marensovich.wareznet.controller.v1;


import me.marensovich.wareznet.database.models.Files;
import me.marensovich.wareznet.exception.exceptions.FileNotFoundException;
import me.marensovich.wareznet.service.FileService;
import me.marensovich.wareznet.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for handling file operations.
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
@RequestMapping("/api/v1/files")
public class FilesController {
    @Autowired private FileService fileService;
    @Autowired private IconService iconService;


    /**
     * Uploads a new file and stores it in the database.
     *
     * @return the response entity containing the file ID or error message
     * @since v.0.1
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("typeId") UUID typeId,
            @RequestParam("categoryid") UUID categoryId,
            @RequestParam("release") String release,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("fileIcon") MultipartFile fileIcon
    ) {
        var iconPath = iconService.uploadIcon(fileIcon);

        Files newFile = fileService.uploadFile(
                typeId,
                categoryId,
                release,
                name,
                description,
                iconPath
        );

        return ResponseEntity.status(HttpStatus.OK).body("File successfuly saved" + newFile.getId());
    }

//    /**
//     * Downloads a file by its ID.
//     *
//     * @param id the ID of the file
//     * @return the response entity with the file as a byte array
//     * @throws FileNotFoundException if the file does not exist
//     * @since v.0.1
//     */
//    @GetMapping("/download/{id}")
//    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) {
//        Optional<Files> fileOptional = filesRepository.findById(id);
//
//        if (fileOptional.isEmpty()) throw new FileNotFoundException("The requested file was not found");
//
//        Files file = fileOptional.get();
//        return ResponseEntity.status(HttpStatus.OK)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                .header(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE)
//                .body(file.getFile());
//    }
}
