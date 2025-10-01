package me.marensovich.wareznet.database.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "files")
public class Files {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "descriptionId", nullable = false)
    private FileDescription description;

    @ManyToOne
    @JoinColumn(name = "typeId", nullable = false)
    private FileTypes type;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private FileCategory category;

    @Column(name = "`release`",  nullable = false, updatable = false)
    private String release;

    @Column(name = "timeAdded",  nullable = false,  updatable = false)
    private LocalDateTime timeAdded = LocalDateTime.now();

}
