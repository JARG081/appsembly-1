package appsembly.appsembly.services;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.UserDTO;

public interface IUserService {

    public List<UserDTO> findAllUsers() throws UsernameNotFoundException;

    public UserDTO findUser(String identifier) throws UsernameNotFoundException;

    public ResponseDTO createUser(UserDTO createUserDTO) throws Exception;

    public ResponseDTO updateUser(UserDTO updateUserDTO);

    public ResponseDTO deleteUser(String personalCode) throws Exception;

}
