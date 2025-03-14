package br.com.plannermanager.repository;

import br.com.plannermanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  @Query(value = "SELECT obj FROM User obj where obj.name =:name")
  Optional<User> findUserByName(String name);
    
}
