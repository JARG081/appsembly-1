package appsembly.appsembly.services;

import java.util.List;

import appsembly.appsembly.entities.QuestionEntity;
import appsembly.appsembly.services.models.dtos.AssemblyQuestionDTO;
import appsembly.appsembly.services.models.dtos.QuestionDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

public interface IQuestionService {
    public ResponseDTO create(QuestionDTO question) throws Exception;

    public List<QuestionEntity> findAllQuestions(AssemblyQuestionDTO assemblyQuestionDTO);
}
