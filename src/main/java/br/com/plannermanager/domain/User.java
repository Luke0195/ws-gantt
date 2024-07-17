package br.com.plannermanager.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String role;
    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;
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
