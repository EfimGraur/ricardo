package com.ricardo.pmtool.persistence.repository;

import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByCode(String code);
}
