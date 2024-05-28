package appsembly.appsembly.services;

import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.VoteDTO;
// Definición de la interfaz IVoteService, que declara los métodos relacionados con la votación
public interface IVoteService {
    // Método para registrar un voto. Recibe un VoteDTO como parámetro y devuelve un ResponseDTO.
    public ResponseDTO vote(VoteDTO voteDTO);
}
