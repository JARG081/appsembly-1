package appsembly.appsembly.services;

import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.VoteDTO;

public interface IVoteService {
    public ResponseDTO vote(VoteDTO voteDTO);
}
