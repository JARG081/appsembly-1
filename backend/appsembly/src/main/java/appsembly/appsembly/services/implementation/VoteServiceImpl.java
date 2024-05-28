package appsembly.appsembly.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appsembly.appsembly.entities.AnswersEntity;
import appsembly.appsembly.entities.QuestionEntity;
import appsembly.appsembly.entities.UserEntity;
import appsembly.appsembly.entities.VoteEntity;
import appsembly.appsembly.entities.VoteEntityPK;
import appsembly.appsembly.repositories.QuestionRepository;
import appsembly.appsembly.repositories.UserRepository;
import appsembly.appsembly.repositories.VoteRepository;
import appsembly.appsembly.services.IVoteService;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.VoteDTO;

@Service
public class VoteServiceImpl implements IVoteService {

    @Autowired
    VoteRepository voteRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;
    // Implementación del método para registrar un voto
    @Override
    public ResponseDTO vote(VoteDTO voteDTO) {
        // Crea un objeto de respuesta
        ResponseDTO response = new ResponseDTO();
        // Crea una clave primaria compuesta para el voto
        VoteEntityPK votePK = new VoteEntityPK();
        // Crea una nueva entidad de voto
        VoteEntity newVote = new VoteEntity();
        // Verifica y establece la opción de voto (SI o NO)
        if (voteDTO.getOption() != null) {
            switch (voteDTO.getOption()) {
                case "si":
                    newVote.setAnswersOptions(AnswersEntity.SI);
                    break;
                case "no":
                    newVote.setAnswersOptions(AnswersEntity.NO);
                    break;
                default:
                    break;
            }
        }
        // Busca la pregunta y el usuario correspondientes en el repositorio
        QuestionEntity question = questionRepository.findById(voteDTO.getQuestionID()).get();
        UserEntity user = userRepository.findById(Long.parseLong(voteDTO.getUserID())).get();

        // newVote.setQuestion(question);
        // newVote.setUser(user);
        // Configura la clave primaria compuesta con la pregunta y el usuario
        votePK.setQuestion(question);
        votePK.setUser(user);
        // Configura la entidad de voto con la clave primaria compuesta
        newVote.setId(votePK);
        // Guarda el voto en el repositorio
        voteRepository.save(newVote);
        // Establece el mensaje de respuesta
        response.setMessage("Voto creado");

        return null;
    }
}
