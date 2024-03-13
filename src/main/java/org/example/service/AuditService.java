package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.db.Audit;
import org.example.repository.AuditRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuditService {
    private final AuditRepository auditRepository;

    public Audit save(Audit audit){
        return auditRepository.save(audit);
    }
}
