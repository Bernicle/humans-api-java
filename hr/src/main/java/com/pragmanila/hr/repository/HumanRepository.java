package com.pragmanila.hr.repository;

import com.pragmanila.hr.model.Human;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface HumanRepository extends JpaRepository<Human, Long> {

    @Query("SELECT h FROM Human h WHERE h.last_name = :last_name")
    List<Human> findByLastName(String last_name);
    
    @Query("SELECT h FROM Human h WHERE h.first_name = :first_name")
    List<Human> findByFirstName(String first_name);
    
}

