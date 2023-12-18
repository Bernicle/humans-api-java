package com.pragmanila.hr.repository;

import com.pragmanila.hr.model.Human;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HumanRepository extends JpaRepository<Human, Long> {

    List<Human> findByLastName(String lastName);
    List<Human> findByFirstName(String lastName);
    
}

