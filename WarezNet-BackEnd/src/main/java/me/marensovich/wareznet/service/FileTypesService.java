package me.marensovich.wareznet.service;

import me.marensovich.wareznet.database.models.FileTypes;
import me.marensovich.wareznet.database.repository.FileTypesRepository;
import me.marensovich.wareznet.exception.exceptions.FileTypeNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FileTypesService {

    @Autowired private FileTypesRepository fileTypesRepository;

    public FileTypes createFileTypes(String fileType){
        FileTypes fileTypes = new FileTypes();
        fileTypes.setName(fileType);
        return fileTypesRepository.save(fileTypes);
    }

    public List<FileTypes> getFileTypes(){
        return fileTypesRepository.findAll();
    }

    public FileTypes getFileType(UUID id){
        return fileTypesRepository.findById(id).get();
    }

    public void deleteFileType(UUID id){
        if (!fileTypesRepository.existsById(id)) {
            throw new FileTypeNotFound("File type not found with UUID: " + id);
        }
        fileTypesRepository.deleteById(id);
    }

    public FileTypes updateFileType(UUID id, String name){
        FileTypes fileTypes = fileTypesRepository.findById(id).orElseThrow(
                () -> new FileTypeNotFound("File type not found with UUID: " + id)
        );
        fileTypes.setName(name);
        return fileTypesRepository.save(fileTypes);
    }
}
