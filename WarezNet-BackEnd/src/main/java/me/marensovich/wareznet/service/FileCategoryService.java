package me.marensovich.wareznet.service;

import me.marensovich.wareznet.database.models.FileCategory;
import me.marensovich.wareznet.database.repository.FileCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FileCategoryService {

    @Autowired private FileCategoryRepository fileCategoryRepository;

    public FileCategory createdFileCategory(String name){
        FileCategory fileCategory = new FileCategory();
        fileCategory.setName(name);

        return fileCategoryRepository.save(fileCategory);
    }

    public List<FileCategory> getAllCategories(){
        return fileCategoryRepository.findAll();
    }

    public void deleteCategory(UUID uuid){
        fileCategoryRepository.deleteById(uuid);
    }

}
