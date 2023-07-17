package com.iudigital.myspringapp.repository;

import com.iudigital.myspringapp.model.Crime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeRepository extends JpaRepository<Crime, Long>{

}
