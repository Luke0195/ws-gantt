package br.com.plannermanager.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupRequestDto implements Serializable {

    @NotEmpty(message = "The field name must be required")
    private String name;
    @NotNull(message = "The field project_id must be required")
    @JsonProperty("project_id")
    private UUID projectId;

}
