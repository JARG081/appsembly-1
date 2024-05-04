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

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findUser(String identifier) {
        return userRepository.findUser(identifier);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userDB = userRepository.findByEmail(email);
        UserEntity userEntity = userDB.get();
        if (userEntity != null) {
            List<GrantedAuthority> permiss = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().toString());
            permiss.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usersession", userEntity);

            return new User(userEntity.getEmail(), userEntity.getPassword(), permiss);
        } else {
            return null;
        }
    }

    /*
     * public ResponseDTO deleteUser(DeleteDTO personalCode) throws Exception {
     * 
     * UserEntity user = userRepository.findUser(personalCode.personalCode);
     * if (user == null) {
     * throw new Exception("user not found");
     * 
     * }
     * 
     * userRepository.delete(user);
     * return null;
     * }
     */
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

        // Cambiar rol a usuario
        // GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" +
        // userEntity.getRole().toString());

        userRepository.save(userDB);

        response.setMessage("user updated successful");

        return response;
    }

    public ResponseDTO createUser(UserDTO createUserDTO) throws Exception {

        ResponseDTO response = new ResponseDTO();

        UserEntity newUser = new UserEntity();

        newUser.setEmail(createUserDTO.getEmail());
        newUser.setFirstName(createUserDTO.getFirstName());
        newUser.setLastName(createUserDTO.getLastName());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        newUser.setPassword(encoder.encode(createUserDTO.getPersonalCode()));

        if (createUserDTO.getRole() != null) {
            System.out.println("El user roles entity getRole");
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
                    throw new Exception("Role no found");
            }

        } else {
            newUser.setRole(RolesEntity.USER);
        }

        userRepository.save(newUser);

        response.setMessage("User created successful");

        return response;
    }

}