package me.marensovich.wareznet.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service for handling file icon operations.
 *
 * <p>This service is responsible for uploading and managing file icons.</p>
 *
 * <p><b>Note:</b> Currently contains a placeholder implementation for uploading icons.</p>
 *
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
@Service
public class IconService {

    //TODO: Исправить заглушку
    /**
     * Uploads an icon file.
     *
     * @param file the multipart file containing the icon
     * @return the path of the uploaded icon (currently returns empty string as placeholder)
     * @since v.0.1
     */
    public String uploadIcon(MultipartFile file){
        return "";
    }
}
