package org.example.repository;

import org.example.model.db.Client;
import org.example.model.db.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
   public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
