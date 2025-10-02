package me.marensovich.wareznet.service;

import me.marensovich.wareznet.database.models.FileCategory;
import me.marensovich.wareznet.database.repository.FileCategoryRepository;
import me.marensovich.wareznet.exception.exceptions.FileCategoryNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FileCategoryService {

    @Autowired private FileCategoryRepository fileCategoryRepository;

    public FileCategory createFileCategory(String name){
        FileCategory fileCategory = new FileCategory();
        fileCategory.setName(name);

        return fileCategoryRepository.save(fileCategory);
    }

    public List<FileCategory> getAllCategories(){
        return fileCategoryRepository.findAll();
    }

    public FileCategory getFileCategoryById(UUID id){
        return fileCategoryRepository.findById(id).orElseThrow(
                () -> new FileCategoryNotFound("File category not found with UUID: " + id)
        );
    }

    public void deleteCategory(UUID uuid){
        if (!fileCategoryRepository.existsById(uuid)) {
            throw new FileCategoryNotFound("File category not found with UUID: " + uuid);
        }
        fileCategoryRepository.deleteById(uuid);
    }

    public FileCategory updateFileCategory(UUID uuid, String name) {
        FileCategory fileCategory = getFileCategoryById(uuid);
        fileCategory.setName(name);
        return fileCategoryRepository.save(fileCategory);
    }
}
