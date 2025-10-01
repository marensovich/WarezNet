package me.marensovich.wareznet.service;


import me.marensovich.wareznet.database.repository.FileCategoryRepository;
import me.marensovich.wareznet.database.repository.FileDescriptionRepository;
import me.marensovich.wareznet.database.repository.FileTypesRepository;
import me.marensovich.wareznet.database.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type File service.
 */
@Service
public class IconService {

    @Autowired private FilesRepository filesRepository;
    @Autowired private FileDescriptionRepository fileDescriptionRepository;
    @Autowired private FileTypesRepository fileTypesRepository;
    @Autowired private FileCategoryRepository fileCategoryRepository;


    public String uploadIcon(MultipartFile file){
        return "";
    }
}
