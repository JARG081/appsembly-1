package appsembly.appsembly.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "question_id")
    private Long questionID;

    private String questionText;

    @OneToMany(mappedBy = "question")
    private List<VoteEntity> vote;

    @ManyToOne
    @JoinColumn(name = "assembly_id")
    @JsonManagedReference
    private AssemblyEntity assembly;

}
