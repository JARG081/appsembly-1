package appsembly.appsembly.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class VoteEntityPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
// import java.io.Serializable;

// public class VoteEntityId implements Serializable {
// private Long questionId;
// private Long userId;

// // Constructor, getters, setters, equals y hashCode
// }
