package appsembly.appsembly.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import appsembly.appsembly.entities.AnswersEntity;
import appsembly.appsembly.entities.Message;
import appsembly.appsembly.services.IQuestionService;
import appsembly.appsembly.services.IVoteService;
import appsembly.appsembly.services.models.dtos.QuestionDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.VoteDTO;
import appsembly.appsembly.utils.StringUtils;

@RestController
public class VoteController {

    @Autowired
    private IVoteService voteService;

    @Autowired
    private IQuestionService questionService;

    @PostMapping("vote")
    public ResponseEntity<ResponseDTO> vote(@RequestBody VoteDTO vote) {
        return new ResponseEntity<>(voteService.vote(vote), HttpStatus.OK);
    }

    @SendTo("/topic/questions")
    @MessageMapping("/answer")
    public Message answer(Message message) throws Exception {
        UUID uuidQuestion = UUID.randomUUID();
        if ("question".equals(message.getType())) {
            QuestionDTO question = new QuestionDTO();

            // guardando la asamblea en la base de datos
            question.setAssemblyID(message.getAssemblyID());
            question.setQuestion(message.getContent());
            question.setQuestionID(uuidQuestion);
            questionService.create(question);

            Message response = new Message();
            response.setType("question");
            response.setContent(message.getContent());
            response.setOptions(message.getOptions());
            response.setAssemblyID(message.getAssemblyID());
            response.setQuestionID(uuidQuestion);
            response.setQuestionID(uuidQuestion);
            response.setTime((message.getTime()) * 1000);

            return response;
        } else if ("answer".equals(message.getType())) {
            // Procesar la respuesta
            System.out.println("La respuesta original es: " + message.getContent());
            System.out.println("La respuesta del questionID es: " + message.getQuestionID());

            String processText = StringUtils.quitarTildes(message.getContent()).toUpperCase();
            VoteDTO voteDTO = new VoteDTO();

            if (processText.equals(AnswersEntity.SI.toString())) {
                System.out.println("La respuesta es SI");
            } else if (processText.equals(AnswersEntity.NO.toString())) {
                System.out.println("La respuesta es NO");

            }
        }
        return message;
    }

}
