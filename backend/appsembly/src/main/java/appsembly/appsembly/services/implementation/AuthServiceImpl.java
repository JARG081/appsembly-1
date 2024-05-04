package appsembly.appsembly.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import appsembly.appsembly.entities.UserEntity;
import appsembly.appsembly.repositories.UserRepository;
import appsembly.appsembly.services.IAuthService;
import appsembly.appsembly.services.IJWTUtilityService;
import appsembly.appsembly.services.models.dtos.LoginDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.validation.UserValidation;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IJWTUtilityService jwtUtilityService;

    @Autowired
    private UserValidation userValidation;

    @Override
    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception {
        try {
            HashMap<String, String> response = new HashMap<>();
            Optional<UserEntity> user = userRepository.findByEmail(loginRequest.getEmail());

            if (user.isEmpty()) {
                response.put("error", "User not registered!");
                return response;
            }

            if (verifyPassword(loginRequest.getPassword(), user.get().getPassword())) {
                String firstName = user.get().getFirstName();
                String lastName = user.get().getLastName();
                String role = user.get().getRole().toString();
                String id = user.get().getUserID().toString();
                String jwt = jwtUtilityService.generateJWT(user.get().getUserID(), user.get().getRole().toString());

                response.put("jwt", jwt);
                response.put("id", id);
                response.put("firstName", firstName);
                response.put("lastName", lastName);
                response.put("role", role);

            } else {
                response.put("error", "Authentication failed");
            }
            return response;
        } catch (IllegalArgumentException e) {
            System.err.println("Error generating JWT: " + e.getMessage());
            throw new Exception("Error generating JWT", e);
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.toString());
            throw new Exception("Unknown error", e);
        }
    }

    @Override
    public ResponseDTO register(UserEntity user) throws Exception {
        try {

            ResponseDTO response = userValidation.validate(user);
            List<UserEntity> getAllUsers = userRepository.findAll();

            if (response.getNumOfErrors() > 0) {
                return response;
            }

            for (UserEntity existingUser : getAllUsers) {
                if (existingUser.getEmail().equals(user.getEmail())) {
                    response.setMessage("Email already exists!");
                    return response;
                }
                // Agrega más comparaciones de campos relevantes según sea necesario.
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            response.setMessage("User created successfull!");

            return response;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
