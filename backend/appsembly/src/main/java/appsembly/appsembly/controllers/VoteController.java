package appsembly.appsembly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import appsembly.appsembly.entities.Message;
import appsembly.appsembly.services.IVoteService;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.VoteDTO;

@RestController
public class VoteController {

    @Autowired
    private IVoteService voteService;

    @PostMapping("vote")
    public ResponseEntity<ResponseDTO> vote(@RequestBody VoteDTO vote) {
        return new ResponseEntity<>(voteService.vote(vote), HttpStatus.OK);
    }

    @SendTo("/topic/questions")
    @MessageMapping("/answer")
    public Message answer(Message message) {
        if ("question".equals(message.getType())) {
            // La pregunta es
            System.out.println("La pregunta es: " + message.getContent());
            // Procesar la respuesta
            if (message.getOptions() != null) {
                System.out.print("Las RESPUESTAS son: ");
                for (String option : message.getOptions()) {
                    System.out.print(option + ", ");
                }
                System.out.println();
            } else {
                System.out.println("No hay preguntas.");
            }

            System.out.println("El id de la asamblea son: " + message.getAssemblyID());
            // Aquí deberías procesar la pregunta y preparar la respuesta
            Message response = new Message();
            response.setType("question");
            response.setContent(message.getContent());
            response.setOptions(message.getOptions());
            response.setAssemblyID(message.getAssemblyID());
            return response;
        } else if ("answer".equals(message.getType())) {
            // Procesar la respuesta
            System.out.println("La respuesta es: " + message.getContent());
        }
        return message;
    }

}
