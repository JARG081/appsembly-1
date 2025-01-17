package appsembly.appsembly.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

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
    @Column(name = "question_id")
    @JoinColumn(name = "question_id")
    private UUID questionID;

    private String questionText;

    @OneToMany(mappedBy = "id.question")
    private List<VoteEntity> vote;

    @ManyToOne
    @JoinColumn(name = "assembly_id")
    @JsonManagedReference
    private AssemblyEntity assembly;

}
