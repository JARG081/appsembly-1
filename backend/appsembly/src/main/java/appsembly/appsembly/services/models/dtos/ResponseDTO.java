package appsembly.appsembly.services.models.dtos;

import lombok.Data;

@Data
public class ResponseDTO {
    private int numOfErrors;
    private String message;
}
