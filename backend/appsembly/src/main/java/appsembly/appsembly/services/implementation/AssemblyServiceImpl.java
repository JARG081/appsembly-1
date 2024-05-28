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

// Indica que esta clase es un servicio de Spring
@Service
public class AssemblyServiceImpl implements IAssemblyService {
    // Inyección de dependencias de AssemblyRepository y AssemblyValidation mediante @Autowired
    @Autowired
    private AssemblyRepository assemblyRepository;

    @Autowired
    private AssemblyValidation assemblyValidation;
    // Implementación del método para obtener todas las asambleas
    @Override
    public List<AssemblyEntity> findAllAssemblies() {
        // Llama al método findAll del repositorio para obtener todas las asambleas
        return assemblyRepository.findAll();
    }
     // Implementación del método para registrar una nueva asamblea
    @Override
    public ResponseDTO register(AssemblyDTO assemblyDTO) throws Exception {
        try {
            // Valida los datos de la asamblea
            ResponseDTO response = assemblyValidation.validate(assemblyDTO);
            // Crea una nueva entidad de asamblea y la llena con los datos del DTO
            AssemblyEntity assembly = new AssemblyEntity();
            assembly.setStartDate(assemblyDTO.getStartDate());
            assembly.setTitle(assemblyDTO.getTitle());
            assembly.setProposal(assemblyDTO.getProposal());
            assembly.setStatus(true);
            // Guarda la asamblea en la base de datos
            assemblyRepository.save(assembly);
            // Establece un mensaje de éxito en la respuesta
            response.setMessage("assambly created successfully");
            return response;
        } catch (Exception e) {
            // Maneja cualquier excepción lanzada durante el proceso y la vuelve a lanzar
            throw new Exception(e.getMessage());
        }
    }
}
