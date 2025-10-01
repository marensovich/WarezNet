package me.marensovich.wareznet.database.repository;

import me.marensovich.wareznet.database.models.FileDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDescriptionRepository extends JpaRepository<FileDescription, Long> {
}
