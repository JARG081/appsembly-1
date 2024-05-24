package appsembly.appsembly.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import appsembly.appsembly.entities.AssemblyEntity;
import appsembly.appsembly.entities.QuestionEntity;
import appsembly.appsembly.services.IAssemblyService;
import appsembly.appsembly.services.IQuestionService;
import appsembly.appsembly.services.IUserService;
import appsembly.appsembly.services.models.dtos.AssemblyDTO;
import appsembly.appsembly.services.models.dtos.AssemblyQuestionDTO;
import appsembly.appsembly.services.models.dtos.QuestionDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;
import appsembly.appsembly.services.models.dtos.UserDTO;

@RestController
public class AdminController {

    @Autowired
    private IAssemblyService assemblyService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IUserService userService;

    /********* ASAMBLEAS *********/
    // movido al controlador de asembleas
    // @PostMapping("/create/assembly")
    // public ResponseEntity<ResponseDTO> createAssembly(@RequestBody AssemblyDTO
    // assembly) throws Exception {
    // System.out.println("CREANDO ASAMBLEA");
    // return new ResponseEntity<>(assemblyService.register(assembly),
    // HttpStatus.OK);
    // }

    // // movido al controlador de asembleas
    // @GetMapping("/get/assembly")
    // public ResponseEntity<List<AssemblyEntity>> getAssemblies() {
    // System.out.println("Obteniendo ASAMBLEAS");
    // return new ResponseEntity<>(assemblyService.findAllAssemblies(),
    // HttpStatus.OK);
    // }

    /****************************************** ******************************************/
    /****************************************** ******************************************/

    /********* PREGUNTAS *********/
    // se pasa a ser manejas por las preguntas
    // @GetMapping("get/questions")
    // public ResponseEntity<List<QuestionEntity>>
    // getQuestions(@RequestParam("assemblyID") String question) {
    // AssemblyQuestionDTO questions = new AssemblyQuestionDTO();
    // questions.setAssemblyID(question);
    // return new ResponseEntity<>(questionService.findAllQuestions(question),
    // HttpStatus.OK);
    // }

    // Pasa a ser manejada por question controller
    // @PostMapping("/create/question")
    // public ResponseEntity<ResponseDTO> createQuestion(@RequestBody QuestionDTO
    // question) throws Exception {
    // return new ResponseEntity<>(questionService.create(question), HttpStatus.OK);
    // }

    /****************************************** ******************************************/
    /****************************************** ******************************************/

    /********* USUARIO *********/
    /* Pasan a ser manejados por el userController */
    // @PostMapping("/update/user")
    // public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO
    // updateUser) throws Exception {
    // // return new ResponseEntity<>(u)
    // return new ResponseEntity<>(userService.updateUser(updateUser),
    // HttpStatus.OK);
    // }

    // @PostMapping("/create/user")
    // public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO
    // createUserDTO) throws Exception {
    // System.out.println("El personal code es: " +
    // createUserDTO.getPersonalCode());
    // return new ResponseEntity<>(userService.createUser(createUserDTO),
    // HttpStatus.OK);
    // }

    // @PostMapping("/delete/user")
    // public ResponseEntity<ResponseDTO> deleteUser(@RequestBody UserDTO
    // deleteUserDTO) throws Exception {
    // System.out.println("El personalcode obtenido desde el frontend: " +
    // deleteUserDTO.getPersonalCode());
    // return new
    // ResponseEntity<>(userService.deleteUser(deleteUserDTO.getPersonalCode()),
    // HttpStatus.OK);
    // }

}
