package com.appdevg5.geeks.controller;

import com.appdevg5.geeks.entity.FormSignatureEntity;
import com.appdevg5.geeks.service.FormSignatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/form-signatures")
@CrossOrigin(origins = "http://localhost:3000")
public class FormSignatureController {

    private final FormSignatureService formSignatureService;

    public FormSignatureController(FormSignatureService formSignatureService) {
        this.formSignatureService = formSignatureService;
    }

    // Create new form signature
    @PostMapping
    public ResponseEntity<FormSignatureEntity> createFormSignature(
            @RequestBody FormSignatureEntity formSignature
    ) {
        FormSignatureEntity saved = formSignatureService.saveFormSignature(formSignature);
        return ResponseEntity.ok(saved);
    }

    // Get all form signatures
    @GetMapping
    public ResponseEntity<List<FormSignatureEntity>> getAllFormSignatures() {
        return ResponseEntity.ok(formSignatureService.getAllFormSignatures());
    }

    // Get a form signature by ID
    @GetMapping("/{id}")
    public ResponseEntity<FormSignatureEntity> getFormSignatureById(@PathVariable int id) {
        return formSignatureService.getFormSignatureById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a form signature
    @PutMapping("/{id}")
    public ResponseEntity<FormSignatureEntity> updateFormSignature(
            @PathVariable int id,
            @RequestBody FormSignatureEntity updatedFormSignature
    ) {
        FormSignatureEntity updated = formSignatureService.updateFormSignature(id, updatedFormSignature);
        return ResponseEntity.ok(updated);
    }

    // Delete a form signature
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormSignature(@PathVariable int id) {
        formSignatureService.deleteFormSignature(id);
        return ResponseEntity.noContent().build();
    }

    // Verify signature (based on verifySignature(): boolean in the class diagram)
    @PostMapping("/{id}/verify")
    public ResponseEntity<Boolean> verifySignature(@PathVariable int id) {
        boolean isValid = formSignatureService.verifySignature(id);
        return ResponseEntity.ok(isValid);
    }
}
