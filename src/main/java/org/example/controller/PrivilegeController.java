package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.db.Privilege;
import org.example.service.PrivilegeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/privilege")
public class PrivilegeController {
    private final PrivilegeService privilegeService;

    @GetMapping
    public ResponseEntity<List<Privilege>> readAll(){
        return new ResponseEntity<>(privilegeService.readAll(), HttpStatus.OK);
    }
}
