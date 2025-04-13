package com.example.userform;

import com.example.userform.UserSubmissionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private UserSubmissionRepository repository;

    // CREATE
    @PostMapping
    public ResponseEntity<UserSubmission> createSubmission(@Valid @RequestBody UserSubmission submission) {
        UserSubmission saved = repository.save(submission);
        return ResponseEntity.ok(saved);
    }

    // READ
    @GetMapping
    public ResponseEntity<List<UserSubmission>> getAllSubmissions() {
        return ResponseEntity.ok(repository.findAll());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubmission(@PathVariable Long id, @Valid @RequestBody UserSubmission updated) {
        Optional<UserSubmission> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserSubmission existing = optional.get();
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        return ResponseEntity.ok(repository.save(existing));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubmission(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
