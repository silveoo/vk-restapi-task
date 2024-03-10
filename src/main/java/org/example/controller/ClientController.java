package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.db.Client;
import org.example.model.db.Privilege;
import org.example.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> readAll() {
        return new ResponseEntity<>(clientService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Client>> readByClientId(@PathVariable Long id){
        return new ResponseEntity<>(clientService.readByPrivilegeId(id), HttpStatus.OK);
    }
}
