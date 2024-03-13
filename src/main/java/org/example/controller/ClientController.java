package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.auth.Auth;
import org.example.model.db.Client;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final Auth auth;

    @GetMapping
    public ResponseEntity<List<Client>> readAll() {
        return new ResponseEntity<>(clientService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> readByClientId(@PathVariable Long id) {
        Client client = clientService.readByPrivilegeId(id);
        auth.test(client);
        if (client != null && client.getPrivilege() != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else if (client != null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
