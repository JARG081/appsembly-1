package appsembly.appsembly.services;

import java.util.List;

import appsembly.appsembly.entities.AssemblyEntity;
import appsembly.appsembly.services.models.dtos.AssemblyDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

public interface IAssemblyService {
    public ResponseDTO register(AssemblyDTO assembly) throws Exception;

    public List<AssemblyEntity> findAllAssemblies();

}
