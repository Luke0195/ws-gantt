package br.com.plannermanager.bunisses;

import br.com.plannermanager.bunisses.exceptions.EntityAlreadyExistsException;
import br.com.plannermanager.bunisses.exceptions.EntityNotExistsException;
import br.com.plannermanager.bunisses.service.impl.ProjectServiceImpl;
import br.com.plannermanager.domain.Project;
import br.com.plannermanager.dto.request.ProjectRequestDto;
import br.com.plannermanager.dto.response.ProjectResponseDto;
import br.com.plannermanager.factories.ProjectFactory;
import br.com.plannermanager.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = ProjectServiceTest.class)
@ActiveProfiles("dev")
class ProjectServiceTest {

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    private ProjectRequestDto projectRequestDto;

    private ProjectRequestDto existingProjectDto;
    private UUID existingId;
    private UUID nonExistsId;


    @BeforeEach
    void setupTestsSuite(){
        this.projectRequestDto = ProjectFactory.makeProjectDto();
        this.existingProjectDto = ProjectFactory.makeProjectDto();
        this.existingProjectDto.setName("invalid_name");
        this.existingId = UUID.randomUUID();
        this.nonExistsId = UUID.randomUUID();
        Mockito.when(projectRepository.findByName(existingProjectDto.getName())).thenThrow(EntityAlreadyExistsException.class);
        Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(ProjectFactory.makeProjectDtoToEntity(this.projectRequestDto));
        Mockito.when(projectRepository.findByName(this.projectRequestDto.getName())).thenReturn(Optional.empty());
        Mockito.when(projectRepository.findById(this.existingId)).thenReturn(Optional.of(ProjectFactory.makeProjectDtoToEntity(this.projectRequestDto)));
    }

    @DisplayName("Should throws EntityAlreadyExists if project name already exists")
    @Test
    void shouldReturnEntityAlreadyExistsIfProjectNameAlreadyExists(){
        Assertions.assertThrows(EntityAlreadyExistsException.class, () ->{
           projectService.create(existingProjectDto);
        });
    }

    @DisplayName("Should return a ProjectResponseDto when valid data is provided")
    @Test
    void shouldReturnProjectResponseDtoWhenValidDataIsProvided(){
        ProjectResponseDto responseDto = projectService.create(this.projectRequestDto);
        Assertions.assertNotNull(responseDto);
        Assertions.assertNotNull(responseDto.getId());
        Assertions.assertNotNull(responseDto.getName());
        Assertions.assertNotNull(responseDto.getProjectStatus());
        Assertions.assertNotNull(responseDto.getDescription());
        Assertions.assertNotNull(responseDto.getCreatedAt());
    }

    @DisplayName("Should return a ProjectResponseDto when valid id is provided")
    @Test
    void shouldReturnAProjectWhenValidIdIsProvided(){
       ProjectResponseDto responseDto = projectService.findProjectById(this.existingId);
       Assertions.assertNotNull(responseDto);
       Assertions.assertNotNull(responseDto.getId());
    }

    @DisplayName("Should throws when invalid id is provided ")
    @Test
    void shouldThrowsAProjectWhenInvalidProjectIsProvided(){
        Assertions.assertThrows(EntityNotExistsException.class, () ->{
            projectService.findProjectById(this.nonExistsId);
        });

    }
}
