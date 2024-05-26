package appsembly.appsembly.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appsembly.appsembly.entities.AssemblyEntity;
import appsembly.appsembly.entities.QuestionEntity;
import appsembly.appsembly.repositories.AssemblyRepository;
import appsembly.appsembly.repositories.QuestionRepository;
// import appsembly.appsembly.repositories.UserRepository;
import appsembly.appsembly.services.IQuestionService;
import appsembly.appsembly.services.models.dtos.QuestionDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // @Autowired
    // private UserRepository userRepository;

    @Autowired
    private AssemblyRepository assemblyRepository;

    @Override
    public ResponseDTO create(QuestionDTO questionDTO) throws Exception {
        try {

            // UserEntity user =
            // userRepository.findById(Long.parseLong(questionDTO.getUserID())).get();
            System.out.println("El assemblyID es: " + questionDTO.getAssemblyID());

            AssemblyEntity assembly = assemblyRepository.findById(questionDTO.getAssemblyID()).get();

            System.out.println("El assembly es: " + assembly.getProposal());
            ResponseDTO response = new ResponseDTO();

            QuestionEntity question = new QuestionEntity();

            question.setQuestionID(questionDTO.getQuestionID());
            question.setAssembly(assembly);
            System.out.println("La pregunta es: " + questionDTO.getQuestion());
            question.setQuestionText(questionDTO.getQuestion());
            questionRepository.save(question);

            response.setMessage("Question create successfuly");
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<QuestionEntity> findAllQuestions(String assemblyID) {

        return questionRepository.findAllQuestions(assemblyID);
    }
}
