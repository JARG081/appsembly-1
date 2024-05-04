package appsembly.appsembly.services.models.validation;

import appsembly.appsembly.entities.UserEntity;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

public class UserValidation {
    public ResponseDTO validate(UserEntity user) {
        ResponseDTO response = new ResponseDTO();
        response.setNumOfErrors(0);
        if (user.getFirstName() == null || user.getFirstName().length() > 15) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage(
                    "El campo firstName del usuario no puede ser nulo y tampoco puede ser mayor a 15 caracteres.");
        }
        if (user.getLastName() == null || user.getFirstName().length() > 30) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage(
                    "El campo lastName del usuario no puede ser nulo y tampoco puede ser mayor a 30 caracteres.");
        }
        if (user.getEmail() == null) {
            response.setMessage("El email no puede ser nulo");
        }
        if (user.getPassword() == null || !user.getPassword()
                .matches("^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*\\d)(?=.*[A-Z]).{8,}$")) {
            response.setMessage(
                    "La contraseña debe tener al menos 8 caracteres, una letra mayuscula, un numero y un caracter especial");
        }

        return response;
    }
}
