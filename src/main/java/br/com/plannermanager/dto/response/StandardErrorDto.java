package br.com.plannermanager.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardErrorDto implements Serializable {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
    @JsonProperty("exception_message")
    private String exceptionMessage;
    @JsonProperty("field_errors")
    private List<Object> fieldError;

}
