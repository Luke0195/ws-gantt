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
public class UserRequestDto implements Serializable {
    @NotEmpty(message = "The field name must be required")
    private String name;
    @NotEmpty(message = "The field email must be required")
    private String email;
    @NotEmpty(message = "The field role must be required")
    private String role;
    @NotNull(message = "The field field group_id must be required")
    @JsonProperty("group_id")
    private UUID groupId;
}
