package br.com.plannermanager.repository;

import br.com.plannermanager.domain.Project;
import br.com.plannermanager.dto.request.ProjectRequestDto;
import br.com.plannermanager.factories.ProjectFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@ActiveProfiles("dev")
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository  projectRepository;


    @BeforeEach
    void setupSuiteTests() throws  Exception{
        projectRepository.deleteAll();
    }

    @DisplayName("Should save a project when valid entity is provided")
    @Test
    void shouldSaveAProjectWhenValidEntityIsProvided(){
        ProjectRequestDto projectDto = ProjectFactory.makeProjectDto();
        Project project = ProjectFactory.makeProjectDtoToEntity(projectDto);
        project = projectRepository.save(project);
        Assertions.assertNotNull(project.getId());
    }

    @DisplayName("Should return a project when id is provided")
    @Test
    void shouldReturnsAProjectWhenValidIdIsProvided(){
      Optional<Project> project =  projectRepository.findById(UUID.randomUUID());
      Assertions.assertNotNull(project);
    }

}
