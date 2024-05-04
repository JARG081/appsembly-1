package appsembly.appsembly.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appsembly.appsembly.entities.AssemblyEntity;
import appsembly.appsembly.repositories.AssemblyRepository;
import appsembly.appsembly.services.IAssemblyService;
import appsembly.appsembly.services.models.dtos.AssemblyDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.validation.AssemblyValidation;

@Service
public class AssemblyServiceImpl implements IAssemblyService {

    @Autowired
    private AssemblyRepository assemblyRepository;

    @Autowired
    private AssemblyValidation assemblyValidation;

    @Override
    public List<AssemblyEntity> findAllAssemblies() {
        return assemblyRepository.findAll();
    }

    @Override
    public ResponseDTO register(AssemblyDTO assemblyDTO) throws Exception {
        try {
            ResponseDTO response = assemblyValidation.validate(assemblyDTO);

            AssemblyEntity assembly = new AssemblyEntity();
            assembly.setStartDate(assemblyDTO.getStartDate());
            assembly.setTitle(assemblyDTO.getTitle());
            assembly.setProposal(assemblyDTO.getProposal());
            assembly.setStatus(true);
            // Guardar la asamblea
            assemblyRepository.save(assembly);

            response.setMessage("assambly created successfully");
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
