package appsembly.appsembly.services;

import java.util.HashMap;

import appsembly.appsembly.entities.UserEntity;
import appsembly.appsembly.services.models.dtos.LoginDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
// Definición de la interfaz IAuthService, que declara los métodos relacionados con la autenticación
public interface IAuthService {
    // Método para realizar el inicio de sesión. Recibe un LoginDTO como parámetro y devuelve un HashMap con información de autenticación.
    // Puede lanzar una excepción en caso de error.
    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception;
    // Método para registrar un nuevo usuario. Recibe un UserEntity como parámetro y devuelve un ResponseDTO.
    // Puede lanzar una excepción en caso de error.
    public ResponseDTO register(UserEntity user) throws Exception;
}
