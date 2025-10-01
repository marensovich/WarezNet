package me.marensovich.wareznet.database.repository;

import me.marensovich.wareznet.database.models.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilesRepository extends JpaRepository<Files, UUID> {
    @Query("SELECT f.path FROM Files f WHERE f.id = :id")
    String getFileNameById(@Param("id") UUID id);

}
