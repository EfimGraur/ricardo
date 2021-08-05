package com.ricardo.pmtool.persistence.repository;

import com.ricardo.pmtool.persistence.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
