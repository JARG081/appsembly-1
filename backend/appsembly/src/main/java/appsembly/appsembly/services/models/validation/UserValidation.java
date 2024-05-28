package appsembly.appsembly.services.models.validation;

import appsembly.appsembly.entities.UserEntity;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

public class UserValidation {
    // Método para validar un usuario
    public ResponseDTO validate(UserEntity user) {
        // Creación de un objeto de respuesta
        ResponseDTO response = new ResponseDTO();
        // Establecimiento del número de errores en cero inicialmente
        response.setNumOfErrors(0);
        // Verificación del campo "firstName" del usuario, control de errores
        if (user.getFirstName() == null || user.getFirstName().length() > 15) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage(
                    "El campo firstName del usuario no puede ser nulo y tampoco puede ser mayor a 15 caracteres.");
        }
        // Verificación del campo "lastName" del usuario, control de errores
        if (user.getLastName() == null || user.getFirstName().length() > 30) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage(
                    "El campo lastName del usuario no puede ser nulo y tampoco puede ser mayor a 30 caracteres.");
        }
        // Verificación del campo "email" del usuario
        if (user.getEmail() == null) {
            response.setMessage("El email no puede ser nulo");
        }
        // Verificación del campo "password" del usuario
        if (user.getPassword() == null || !user.getPassword()
                .matches("^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*\\d)(?=.*[A-Z]).{8,}$")) {
            response.setMessage(
                    "La contraseña debe tener al menos 8 caracteres, una letra mayuscula, un numero y un caracter especial");
        }
        // Devolución de la respuesta
        return response;    
    }
}
