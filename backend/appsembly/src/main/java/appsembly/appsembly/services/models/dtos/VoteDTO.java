package appsembly.appsembly.services.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class VoteDTO {
    String option;
    UUID questionID;
    String userID;
}
