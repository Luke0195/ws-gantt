package br.com.plannermanager.repository;

import br.com.plannermanager.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {

    @Query(value = "SELECT object FROM Group object where object.name =:name")
    Optional<Group> findGroupByName(String name);
}
