package appsembly.appsembly.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vote")
public class VoteEntity {

    @EmbeddedId
    private VoteEntityPK id;

    @Enumerated(EnumType.STRING)
    private AnswersEntity answersOptions;

}
