package br.com.plannermanager.dto.response;

import br.com.plannermanager.domain.Group;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto implements Serializable {

    private UUID id;
    private String name;
    private String role;
    private Group group;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
