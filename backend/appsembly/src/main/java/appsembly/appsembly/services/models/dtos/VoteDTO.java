package appsembly.appsembly.services.models.dtos;

import lombok.Data;

@Data
public class VoteDTO {
    String option;
    String questionID;
    String userID;
}
