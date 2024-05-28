package appsembly.appsembly.services;

import java.util.List;

import appsembly.appsembly.entities.AssemblyEntity;
import appsembly.appsembly.services.models.dtos.AssemblyDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

public interface IAssemblyService {
    // Método para registrar una nueva asamblea. Recibe un AssemblyDTO como parámetro y devuelve un ResponseDTO.
    // Puede lanzar una excepción en caso de error.
    public ResponseDTO register(AssemblyDTO assembly) throws Exception;
    // Método para obtener una lista de todas las asambleas. Devuelve una lista de AssemblyEntity.
    public List<AssemblyEntity> findAllAssemblies();

}
