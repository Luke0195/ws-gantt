package br.com.plannermanager.domain;

import br.com.plannermanager.domain.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="projects")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name="project_status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
