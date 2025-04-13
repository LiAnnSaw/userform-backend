package com.example.userform;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubmissionRepository extends JpaRepository<UserSubmission, Long> {
    // nth here cuz for basic CRUD
    // store UserSubmission objects n ID is Long
}
