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
    // Implementación del método para encontrar todos los usuarios
    @Override
    public List<UserDTO> findAllUsers() throws UsernameNotFoundException {
        try {
            List<UserEntity> usersEntities = userRepository.findAllUsers();
            List<UserDTO> usersDTO = new ArrayList<>();
            // Itera sobre los usuarios encontrados y los mapea a DTOs de usuario
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
            // Maneja cualquier excepción y la lanza como UsernameNotFoundException
            throw new UsernameNotFoundException("Users Not Found, error: " + e);
        }
    }

    // private static final String REGEX =
    // "^(?=.*[!@#$%^&*()-+=])(?=.*[0-9])(?=.*[A-Z]).*$";
    // Implementación del método para encontrar un usuario por su código personal
    @Override
    public UserDTO findUser(String personalCode) throws UsernameNotFoundException {
        try {
            // Busca el usuario por su código personal en el repositorio
            Optional<UserEntity> userOptional = userRepository.findUser(personalCode);

            if (userOptional.isEmpty()) {
                throw new UsernameNotFoundException("User not found");
            }
            // Mapea el usuario encontrado a un DTO de usuario
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
            // Maneja cualquier excepción y la lanza como UsernameNotFoundException
            throw new UsernameNotFoundException("Error al buscar usuario: " + ex.getMessage());
        }
    }
    // Implementación del método para cargar un usuario por su nombre de usuario (email)
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
    // Implementación del método para actualizar un usuario
    @Override
    public ResponseDTO updateUser(UserDTO updateUserDTO) {

        ResponseDTO response = new ResponseDTO();
        
        if (updateUserDTO.getId() == null) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("not id");
            return response;
        }
        // Busca el usuario a actualizar por su ID en el repositorio
        UserEntity userDB = userRepository.findById(Long.parseLong(updateUserDTO.getId())).get();
        // Actualiza los campos del usuario con los datos proporcionados en el DTO
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
    // Implementación del método para crear un nuevo usuario
    @Override
    public ResponseDTO createUser(UserDTO createUserDTO) throws Exception {
        try {
            ResponseDTO response = new ResponseDTO();

            UserEntity newUser = new UserEntity();
            // Verifica si ya existe un usuario con el código personal proporcionado
            if ((userRepository.findUser(createUserDTO.getPersonalCode()).isPresent())) {

                response.setNumOfErrors(response.getNumOfErrors() + 1);
                response.setMessage("This personal code already exists sadasdasd");
                return response;

            } else {
                newUser.setPersonalCode(createUserDTO.getPersonalCode());
            }
            // Verifica si ya existe un usuario con el correo electrónico proporcionado
            if ((userRepository.findByEmail(createUserDTO.getEmail()).isPresent())) {

                response.setNumOfErrors(response.getNumOfErrors() + 1);
                response.setMessage("This email already exists");
                return response;

            } else {
                newUser.setEmail(createUserDTO.getEmail().toLowerCase());
            }
            // Verifica que el nombre y apellido no estén vacíos
            if ((createUserDTO.getFirstName().isEmpty()) && (createUserDTO.getLastName().isEmpty())) {

                response.setNumOfErrors(response.getNumOfErrors() + 1);
                response.setMessage("The first and last name cannot be empty");
                return response;

            } else if (createUserDTO.getFirstName() != null) {

                newUser.setFirstName(createUserDTO.getFirstName());

            } else if (createUserDTO.getLastName() != null) {

                newUser.setLastName(createUserDTO.getLastName());

            }
            // Configura el rol del usuario
            if (createUserDTO.getRole() != null) {
                String role = createUserDTO.getRole().toUpperCase();
                switch (role) {
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
                // Si no se proporciona un rol, se asigna el rol por defecto de USER
                newUser.setRole(RolesEntity.USER);
            }
            // Configura el estado activo del usuario
            newUser.setActive(true);    
            // Codifica la contraseña del usuario antes de guardarla en la base de datos
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

            newUser.setPassword(encoder.encode(createUserDTO.getPersonalCode()));
            // Guarda el nuevo usuario en el repositorio
            userRepository.save(newUser);

            response.setMessage("User created successful");

            return response;
        } catch (Exception e) {
            // Maneja cualquier excepción y la lanza como una nueva excepción
            throw new Exception("The user could not be created: " + e.getMessage());
        }

    }
    // Implementación del método para eliminar un usuario
    @Override
    public ResponseDTO deleteUser(String personalCode) throws Exception {
        try {
            ResponseDTO response = new ResponseDTO();
            // Busca el usuario por su código personal en el repositorio
            UserEntity user = userRepository.findUser(personalCode).get();
            user.setActive(false);
            userRepository.save(user);
            //confirma usuario eliminado
            response.setMessage("El usuario ha sido eliminado correctamente");
            return response;
        } catch (Exception e) {
            // Maneja cualquier excepción y la lanza como una nueva excepción
            throw new Exception("User not found: " + e);
        }
    }
}
