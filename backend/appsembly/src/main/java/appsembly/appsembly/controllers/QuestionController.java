package appsembly.appsembly.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import appsembly.appsembly.entities.QuestionEntity;
import appsembly.appsembly.services.IQuestionService;
import appsembly.appsembly.services.models.dtos.QuestionDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // ANTES llamada /get/all
    @GetMapping("/search")
    public ResponseEntity<List<QuestionEntity>> getQuestions(@RequestParam("assemblyID") String questions) {
        // System.out.println("El assemblyID es:" + questions);
        return new ResponseEntity<>(questionService.findAllQuestions(questions), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createQuestion(@RequestBody QuestionDTO question) throws Exception {
        System.out.println("Antes del template");
        // messagingTemplate.convertAndSend("/topic/question", question);
        System.out.println("Se supone que el msstmpl ya se envío");
        return new ResponseEntity<>(questionService.create(question), HttpStatus.OK);
    }
}
