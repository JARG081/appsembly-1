package appsembly.appsembly.services;

import java.util.List;

import appsembly.appsembly.entities.UserEntity;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.UserDTO;

public interface IUserService {

    public List<UserEntity> findAllUsers();

    public ResponseDTO createUser(UserDTO createUserDTO) throws Exception;

    public ResponseDTO updateUser(UserDTO updateUserDTO);

}
