package me.marensovich.wareznet.database.repository;

import me.marensovich.wareznet.database.models.FileCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileCategoryRepository extends JpaRepository<FileCategory, Long> {
}
