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

    @Override
    public ResponseDTO vote(VoteDTO voteDTO) {

        ResponseDTO response = new ResponseDTO();
        VoteEntityPK votePK = new VoteEntityPK();

        VoteEntity newVote = new VoteEntity();

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

        QuestionEntity question = questionRepository.findById(voteDTO.getQuestionID()).get();
        UserEntity user = userRepository.findById(Long.parseLong(voteDTO.getUserID())).get();

        // newVote.setQuestion(question);
        // newVote.setUser(user);

        votePK.setQuestion(question);
        votePK.setUser(user);

        newVote.setId(votePK);

        voteRepository.save(newVote);

        response.setMessage("Voto creado");

        return null;
    }
}
