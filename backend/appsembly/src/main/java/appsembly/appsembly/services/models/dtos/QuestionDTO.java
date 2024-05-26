package appsembly.appsembly.services.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class QuestionDTO {
    private UUID assemblyID;
    private UUID questionID;
    private String question;
}
