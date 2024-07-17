package br.com.plannermanager.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("dev")
class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;


    @BeforeEach
    void setupSuitTests(){
        groupRepository.deleteAll();
    }


}
