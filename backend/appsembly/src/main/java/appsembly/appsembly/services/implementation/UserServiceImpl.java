package appsembly.appsembly.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import appsembly.appsembly.entities.RolesEntity;
import appsembly.appsembly.entities.UserEntity;
import appsembly.appsembly.repositories.UserRepository;
import appsembly.appsembly.services.IUserService;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.UserDTO;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    // no se si es composición o agreagación
    @Autowired
    UserRepository userRepository;
    // public class UserDTO {

    // private String id;

    // private String firstName;

    // private String lastName;

    // private String email;

    // private String role;

    // private String personalCode;

    // private Bool active;

    // }

    @Override
    public List<UserDTO> findAllUsers() throws UsernameNotFoundException {
        try {
            List<UserEntity> usersEntities = userRepository.findAllUsers();
            List<UserDTO> usersDTO = new ArrayList<>();
            for (UserEntity userEntity : usersEntities) {
                UserDTO aux = new UserDTO();
                aux.setId(Long.toString(userEntity.getUserID()));
                aux.setFirstName(userEntity.getFirstName());
                aux.setLastName(userEntity.getLastName());
                aux.setEmail(userEntity.getEmail());
                aux.setRole(userEntity.getRole().toString());
                aux.setPersonalCode(userEntity.getPersonalCode());
                aux.setActive(userEntity.getActive());
                usersDTO.add(aux);
            }
            return usersDTO;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Users Not Found, error: " + e);
        }
    }

    // private static final String REGEX =
    // "^(?=.*[!@#$%^&*()-+=])(?=.*[0-9])(?=.*[A-Z]).*$";

    @Override
    public UserDTO findUser(String personalCode) throws UsernameNotFoundException {
        try {
            Optional<UserEntity> userOptional = userRepository.findUser(personalCode);

            if (userOptional.isEmpty()) {
                throw new UsernameNotFoundException("User not found");
            }

            UserDTO userResponse = new UserDTO();

            UserEntity user = userOptional.get();
            userResponse.setId(Long.toString(user.getUserID()));
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setEmail(user.getEmail());
            userResponse.setRole(user.getRole().toString());
            userResponse.setPersonalCode(user.getPersonalCode());

            return userResponse;
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Error al buscar usuario: " + ex.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> userDB = userRepository.findByEmail(email);
        UserEntity userEntity = userDB.get();
        if (userEntity != null) {
            List<GrantedAuthority> permiss = new ArrayList<>();
            GrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().toString());
            permiss.add(role);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                    .currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usersession", userEntity);

            return new User(userEntity.getEmail(), userEntity.getPassword(), permiss);
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }

    }

    @Override
    public ResponseDTO updateUser(UserDTO updateUserDTO) {

        ResponseDTO response = new ResponseDTO();

        if (updateUserDTO.getId() == null) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("not id");
            return response;
        }

        UserEntity userDB = userRepository.findById(Long.parseLong(updateUserDTO.getId())).get();

        if (updateUserDTO.getFirstName() != null) {
            userDB.setFirstName(updateUserDTO.getFirstName());
        }

        if (updateUserDTO.getLastName() != null) {
            userDB.setLastName(updateUserDTO.getLastName());
        }

        if (updateUserDTO.getEmail() != null) {
            userDB.setEmail(updateUserDTO.getEmail());
        }

        if (updateUserDTO.getActive() != null) {
            if (updateUserDTO.getActive()) {
                userDB.setActive(true);
            } else {
                userDB.setActive(false);
            }
        }

        userRepository.save(userDB);

        response.setMessage("user updated successful");

        return response;
    }

    @Override
    public ResponseDTO createUser(UserDTO createUserDTO) throws Exception {
        try {
            ResponseDTO response = new ResponseDTO();

            UserEntity newUser = new UserEntity();
            if ((userRepository.findUser(createUserDTO.getPersonalCode()).isPresent())) {

                response.setNumOfErrors(response.getNumOfErrors() + 1);
                response.setMessage("This personal code already exists sadasdasd");
                return response;

            } else {
                newUser.setPersonalCode(createUserDTO.getPersonalCode());
            }

            if ((userRepository.findByEmail(createUserDTO.getEmail()).isPresent())) {

                response.setNumOfErrors(response.getNumOfErrors() + 1);
                response.setMessage("This email already exists");
                return response;

            } else {
                newUser.setEmail(createUserDTO.getEmail().toLowerCase());
            }

            if ((createUserDTO.getFirstName().isEmpty()) && (createUserDTO.getLastName().isEmpty())) {

                response.setNumOfErrors(response.getNumOfErrors() + 1);
                response.setMessage("The first and last name cannot be empty");
                return response;

            } else if (createUserDTO.getFirstName() != null) {

                newUser.setFirstName(createUserDTO.getFirstName());

            } else if (createUserDTO.getLastName() != null) {

                newUser.setLastName(createUserDTO.getLastName());

            }

            if (createUserDTO.getRole().toUpperCase() != null) {
                switch (createUserDTO.getRole()) {
                    case "USER":
                        RolesEntity rol = RolesEntity.USER;
                        newUser.setRole(rol);
                        break;
                    case "ADMIN":
                        RolesEntity rol2 = RolesEntity.ADMIN;
                        newUser.setRole(rol2);
                        break;
                    default:
                        return response;
                }

            } else {
                newUser.setRole(RolesEntity.USER);
            }

            newUser.setActive(true);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

            newUser.setPassword(encoder.encode(createUserDTO.getPersonalCode()));

            userRepository.save(newUser);

            response.setMessage("User created successful");

            return response;
        } catch (Exception e) {
            throw new Exception("The user could not be created: " + e.getMessage());
        }

    }

    @Override
    public ResponseDTO deleteUser(String personalCode) throws Exception {
        try {
            ResponseDTO response = new ResponseDTO();
            UserEntity user = userRepository.findUser(personalCode).get();
            user.setActive(false);
            userRepository.save(user);
            response.setMessage("El usuario ha sido eliminado correctamente");
            return response;
        } catch (Exception e) {
            throw new Exception("User not found: " + e);
        }
    }
}