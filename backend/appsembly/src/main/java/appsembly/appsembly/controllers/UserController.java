package appsembly.appsembly.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import appsembly.appsembly.services.IUserService;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    // ✅✅✅
    @PostMapping("/update")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO updateUser) throws Exception {
        // return new ResponseEntity<>(u)
        return new ResponseEntity<>(userService.updateUser(updateUser), HttpStatus.OK);
    }

    // ✅✅✅
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO createUserDTO) throws Exception {
        ResponseDTO response = userService.createUser(createUserDTO);
        if (response.getNumOfErrors() != 0) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    // ✅✅✅
    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteUser(@RequestBody UserDTO deleteUserDTO) throws Exception {
        try {
            return new ResponseEntity<>(userService.deleteUser(deleteUserDTO.getPersonalCode()), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO();
            errorResponse.setNumOfErrors(errorResponse.getNumOfErrors() + 1);
            errorResponse.setMessage("User Not exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    // ✅✅✅
    @GetMapping("/search")
    public ResponseEntity<?> getUser(@RequestParam String personalCode) throws Exception {
        try {
            return new ResponseEntity<>(userService.findUser(personalCode), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO();
            errorResponse.setNumOfErrors(errorResponse.getNumOfErrors() + 1);
            errorResponse.setMessage("User Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    // ✅✅✅
    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllUsers() {
        try {

            return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);

        } catch (Exception e) {
            ResponseDTO errorResponse = new ResponseDTO();
            errorResponse.setNumOfErrors(errorResponse.getNumOfErrors() + 1);
            errorResponse.setMessage("Users Not Found");
            List<ResponseDTO> errorList = new ArrayList<>();
            errorList.add(errorResponse);
            return new ResponseEntity<>(errorList, HttpStatus.NOT_FOUND);
        }
    }

}
