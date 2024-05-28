package appsembly.appsembly.services.models.validation;

import java.util.Date;

import org.springframework.stereotype.Component;

import appsembly.appsembly.services.models.dtos.AssemblyDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

@Component
public class AssemblyValidation {
     // Método para validar una asamblea
    public ResponseDTO validate(AssemblyDTO assembly) {
        // Crea un objeto de respuesta
        ResponseDTO response = new ResponseDTO();
         // Obtiene la fecha de inicio de la asamblea y la fecha actual
        Date assemblyDate = assembly.getStartDate();
        Date currentDate = new Date();
        // Establece el número de errores en cero inicialmente
        response.setNumOfErrors(0);
        // Verifica si la fecha de inicio de la asamblea es anterior a la fecha actual
        if (assemblyDate.before(currentDate)) {
            // Si es así, incrementa el número de errores y establece un mensaje de error
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("No puedes crear una asamblea en esta fecha");
        }
        // Devuelve la respuesta
        return response;
    }
}
