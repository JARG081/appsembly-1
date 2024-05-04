package appsembly.appsembly.services;

import java.util.HashMap;

import appsembly.appsembly.entities.UserEntity;
import appsembly.appsembly.services.models.dtos.LoginDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

public interface IAuthService {
    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception;

    public ResponseDTO register(UserEntity user) throws Exception;
}
