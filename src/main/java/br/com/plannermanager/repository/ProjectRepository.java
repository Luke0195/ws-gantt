package br.com.plannermanager.repository;

import br.com.plannermanager.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query(value = "SELECT object FROM Project object where object.name =:name")
    Optional<Project> findByName(String name);
}
