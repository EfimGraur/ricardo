package net.proselyte.springsecuritydemo.persistence.repository;

import net.proselyte.springsecuritydemo.persistence.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
