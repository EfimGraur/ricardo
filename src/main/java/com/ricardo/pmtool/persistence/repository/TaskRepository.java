package com.ricardo.pmtool.persistence.repository;

import com.ricardo.pmtool.persistence.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
