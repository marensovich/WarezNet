package me.marensovich.wareznet.service;

import me.marensovich.wareznet.database.repository.FileDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDescriptionService {
    @Autowired private FileDescriptionRepository fileDescriptionRepository;



}
