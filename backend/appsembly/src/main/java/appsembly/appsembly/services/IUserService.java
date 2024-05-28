package appsembly.appsembly.services;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.UserDTO;
// Definición de la interfaz IUserService, que declara los métodos relacionados con la gestión de usuarios
public interface IUserService {
    // Método para obtener una lista de todos los usuarios. Devuelve una lista de UserDTO.
    // Puede lanzar UsernameNotFoundException en caso de no encontrar ningún usuario.
    public List<UserDTO> findAllUsers() throws UsernameNotFoundException;
    // Método para encontrar un usuario específico por su identificador. Devuelve un UserDTO.
    // Puede lanzar UsernameNotFoundException en caso de no encontrar el usuario.
    public UserDTO findUser(String identifier) throws UsernameNotFoundException;
    // Método para crear un nuevo usuario. Recibe un UserDTO con los datos del nuevo usuario y devuelve un ResponseDTO.
    // Puede lanzar una excepción en caso de error.
    public ResponseDTO createUser(UserDTO createUserDTO) throws Exception;
    // Método para actualizar un usuario existente. Recibe un UserDTO con los datos actualizados y devuelve un ResponseDTO.
    public ResponseDTO updateUser(UserDTO updateUserDTO);
    // Método para eliminar un usuario por su código personal. Recibe el código personal como parámetro y devuelve un ResponseDTO.
    // Puede lanzar una excepción en caso de error.
    public ResponseDTO deleteUser(String personalCode) throws Exception;

}
