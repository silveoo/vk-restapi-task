package org.example.service;


import lombok.AllArgsConstructor;
import org.example.model.db.Privilege;
import org.example.repository.PrivilegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    public Privilege readById(long id){

        return privilegeRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found - " + id));
    }

    public List<Privilege> readAll(){
        return privilegeRepository.findAll();
    }
}
