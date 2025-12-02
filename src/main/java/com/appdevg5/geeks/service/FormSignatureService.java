package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.appdevg5.geeks.entity.FormSignatureEntity;
import com.appdevg5.geeks.repository.FormSignatureRepository;

@Service
public class FormSignatureService {

    @Autowired
    FormSignatureRepository fsrepo;

    // Used by your controller's POST /api/form-signatures
    public FormSignatureEntity saveFormSignature(FormSignatureEntity formSignature) {
        return fsrepo.save(formSignature);
    }

    public FormSignatureEntity insertFormSignatureRecord(FormSignatureEntity formSignature) {
        return fsrepo.save(formSignature);
    }

    public List<FormSignatureEntity> getAllFormSignatures() {
        return fsrepo.findAll();
    }

    // Used by your controller's GET /api/form-signatures/{id}
    public Optional<FormSignatureEntity> getFormSignatureById(int signatureId) {
        return fsrepo.findById(signatureId);
    }

    public FormSignatureEntity updateFormSignature(int signatureId, FormSignatureEntity newDetails) {
        FormSignatureEntity formSignature = fsrepo.findById(signatureId)
            .orElseThrow(() -> new NoSuchElementException("FormSignature " + signatureId + " does not exist"));

        formSignature.setForm_id(newDetails.getForm_id());
        formSignature.setParent_id(newDetails.getParent_id());
        formSignature.setStudent_id(newDetails.getStudent_id());
        formSignature.setSigned_at(newDetails.getSigned_at());

        return fsrepo.save(formSignature);
    }

    public String deleteFormSignature(int signatureId) {
        if (fsrepo.existsById(signatureId)) {
            fsrepo.deleteById(signatureId);
            return "FormSignature " + signatureId + " is successfully deleted!";
        } else {
            return "FormSignature " + signatureId + " does not exist.";
        }
    }

    public boolean verifySignature(int signatureId) {
        FormSignatureEntity formSignature = fsrepo.findById(signatureId)
            .orElseThrow(() -> new NoSuchElementException("FormSignature " + signatureId + " does not exist"));

        return formSignature.getSigned_at() != null;
    }
}
