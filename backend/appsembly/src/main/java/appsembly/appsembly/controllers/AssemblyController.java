package appsembly.appsembly.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import appsembly.appsembly.entities.AssemblyEntity;
import appsembly.appsembly.services.IAssemblyService;
import appsembly.appsembly.services.models.dtos.AssemblyDTO;
import appsembly.appsembly.services.models.dtos.ResponseDTO;

@RestController
@RequestMapping("/assembly")
public class AssemblyController {

    @Autowired
    private IAssemblyService assemblyService;

    // ✅✅✅
    @GetMapping("/all")
    public ResponseEntity<List<AssemblyEntity>> getAssemblies() {
        return new ResponseEntity<>(assemblyService.findAllAssemblies(), HttpStatus.OK);
    }

    // ✅✅✅
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAssembly(@RequestBody AssemblyDTO assembly) throws Exception {
        System.out.println("CREANDO ASAMBLEA");
        return new ResponseEntity<>(assemblyService.register(assembly), HttpStatus.OK);
    }

}
