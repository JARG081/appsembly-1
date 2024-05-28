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
            // Obtener la asamblea asociada usando el ID de la asamblea proporcionado en el DTO de la pregunta
            AssemblyEntity assembly = assemblyRepository.findById(questionDTO.getAssemblyID()).get();

            System.out.println("El assembly es: " + assembly.getProposal());
            // Crear un objeto de respuesta
            ResponseDTO response = new ResponseDTO();
             // Crear una nueva entidad de pregunta y llenar sus campos con los datos del DTO
            QuestionEntity question = new QuestionEntity();

            question.setQuestionID(questionDTO.getQuestionID());
            question.setAssembly(assembly);
            System.out.println("La pregunta es: " + questionDTO.getQuestion());
            question.setQuestionText(questionDTO.getQuestion());
            // Guardar la pregunta en el repositorio
            questionRepository.save(question);

            response.setMessage("Question create successfuly");
            return response;
        } catch (Exception e) {
            // Manejar cualquier excepción lanzada durante el proceso y la volver a lanzar
            throw new Exception(e.getMessage());
        }
    }
    // Implementación del método para obtener todas las preguntas de una asamblea específica
    @Override
    public List<QuestionEntity> findAllQuestions(String assemblyID) {
        // Llama al método findAllQuestions del repositorio para obtener todas las preguntas de la asamblea
        return questionRepository.findAllQuestions(assemblyID);
    }
}
