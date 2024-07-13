package br.com.plannermanager.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequest implements Serializable {

    @NotEmpty(message = "The field name must be required")
    private String name;
    @NotEmpty(message = "The field description must be required")
    private String description;
    @JsonProperty("project_status")
    private String projectStatus;
}
