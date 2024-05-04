package appsembly.appsembly.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/create/assembly")
    private ResponseEntity<ResponseDTO> createAssembly(@RequestBody AssemblyDTO assembly) throws Exception {
        System.out.println("CREANDO ASAMBLEA");
        return new ResponseEntity<>(assemblyService.register(assembly), HttpStatus.OK);
    }

    @GetMapping("/get/assembly")
    private ResponseEntity<List<AssemblyEntity>> getAssemblies() {
        System.out.println("Obteniendo ASAMBLEAS");
        return new ResponseEntity<>(assemblyService.findAllAssemblies(), HttpStatus.OK);
    }

    @GetMapping("get/questions")
    private ResponseEntity<List<QuestionEntity>> getQuestions(@RequestBody AssemblyQuestionDTO questions) {
        return new ResponseEntity<>(questionService.findAllQuestions(questions), HttpStatus.OK);
    }

    @PostMapping("/create/question")
    private ResponseEntity<ResponseDTO> createQuestion(@RequestBody QuestionDTO question) throws Exception {
        return new ResponseEntity<>(questionService.create(question), HttpStatus.OK);
    }

    @PostMapping("/update/user")
    private ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO updateUser) throws Exception {
        // return new ResponseEntity<>(u)
        return new ResponseEntity<>(userService.updateUser(updateUser), HttpStatus.OK);
    }

    @PostMapping("/create/user")
    private ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO createUserDTO) throws Exception {
        return new ResponseEntity<>(userService.createUser(createUserDTO), HttpStatus.OK);
    }

}
