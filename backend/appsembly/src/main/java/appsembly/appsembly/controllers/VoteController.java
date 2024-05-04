package appsembly.appsembly.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import appsembly.appsembly.services.models.dtos.ResponseDTO;

@RestController
public class VoteController {

    @PostMapping("vote")
    private ResponseEntity<ResponseDTO> vote(@RequestBody String answer) {
        return null;
    }
}
