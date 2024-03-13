package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.db.Audit;
import org.example.service.AuditService;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@AllArgsConstructor
@Controller
public class AuditController {
    private final AuditService auditService;

    public void addAuditRecord(Long usedId, Boolean allowed, String request){
        Audit audit = new Audit();
        audit.setDate(LocalDate.now());
        audit.setUserId(usedId);
        audit.setAllowed(allowed);
        audit.setRequest(request);

        auditService.save(audit);
    }
}
