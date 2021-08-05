package net.proselyte.springsecuritydemo.persistence.repository;

import net.proselyte.springsecuritydemo.persistence.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
