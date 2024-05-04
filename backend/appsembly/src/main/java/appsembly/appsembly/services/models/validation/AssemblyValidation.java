package appsembly.appsembly.services.models.validation;

import java.util.Date;

import org.springframework.stereotype.Component;

import appsembly.appsembly.services.models.dtos.AssemblyDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

@Component
public class AssemblyValidation {
    public ResponseDTO validate(AssemblyDTO assembly) {
        ResponseDTO response = new ResponseDTO();

        Date assemblyDate = assembly.getStartDate();
        Date currentDate = new Date();

        response.setNumOfErrors(0);

        if (assemblyDate.before(currentDate)) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("No puedes crear una asamblea en esta fecha");
        }
        return response;
    }
}
