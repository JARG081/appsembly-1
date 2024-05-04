package appsembly.appsembly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import appsembly.appsembly.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    @Query(value = "SELECT * FROM question WHERE assembly_id = :assemblyID", nativeQuery = true)
    List<QuestionEntity> findAllQuestions(String assemblyID);

}
