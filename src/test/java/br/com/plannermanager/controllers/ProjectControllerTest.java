package br.com.plannermanager.controllers;

import br.com.plannermanager.bunisses.service.impl.ProjectServiceImpl;
import br.com.plannermanager.dto.request.ProjectRequestDto;
import br.com.plannermanager.factories.ProjectFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectServiceImpl projectService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProjectRequestDto projectRequestDto;

    @DisplayName("POST - Should return a project when valid data is provided")
    @Test
    void shouldReturnAProjectWhenValidDataIsProvided() throws Exception{
        ProjectRequestDto projectRequestDto = ProjectFactory.makeProjectDto();
        String jsonBody = objectMapper.writeValueAsString(projectRequestDto);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody));
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
