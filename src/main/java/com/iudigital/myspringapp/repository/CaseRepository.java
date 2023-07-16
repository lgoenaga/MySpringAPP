package com.iudigital.myspringapp.repository;

import com.iudigital.myspringapp.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long>{


}
