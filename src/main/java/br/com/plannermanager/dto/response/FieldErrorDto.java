package br.com.plannermanager.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldErrorDto implements Serializable {
    @JsonProperty("field_name")
    private String fieldName;
    @JsonProperty("field_message")
    private String fieldMessage;
}
