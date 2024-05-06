package appsembly.appsembly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import appsembly.appsembly.services.IVoteService;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.VoteDTO;

@RestController
public class VoteController {

    @Autowired
    IVoteService voteService;

    @PostMapping("vote")
    private ResponseEntity<ResponseDTO> vote(@RequestBody VoteDTO vote) {
        return new ResponseEntity<>(voteService.vote(vote), HttpStatus.OK);
    }
}
