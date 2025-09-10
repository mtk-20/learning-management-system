package com.example.learning_management_system.repository;

import com.example.learning_management_system.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    List<Progress> findByStudentId(Long studentId);

    List<Progress> findByModuleId(Long moduleId);

    Progress findByStudentIdAndModuleId(Long studentId, Long moduleId);

    List<Progress> findByStatus(Progress.Status status);
}
