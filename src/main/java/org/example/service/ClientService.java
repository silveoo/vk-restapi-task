package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.db.Client;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> readAll(){
        return clientRepository.findAll();
    }

    public Client readById(Long id){
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found - " + id));
    }

    public Client readByPrivilegeId(Long id){
        return clientRepository.findByPrivilegeId(id);
    }

}
