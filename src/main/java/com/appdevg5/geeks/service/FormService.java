package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import com.appdevg5.geeks.entity.FormEntity;
import com.appdevg5.geeks.entity.TeacherEntity;
import com.appdevg5.geeks.repository.FormRepository;
import com.appdevg5.geeks.repository.TeacherRepository;

@Service
public class FormService {

    @Autowired
    FormRepository frepo;

    @Autowired
    TeacherRepository trepo;

    public FormEntity insertFormRecord(FormEntity form) {
        if (form.getTeacher() != null && form.getTeacher().getTeacher_id() > 0) {
            TeacherEntity teacher = trepo.findById(form.getTeacher().getTeacher_id())
                .orElseThrow(() -> new NoSuchElementException("Teacher not found"));
            form.setTeacher(teacher);
        }
        
        return frepo.save(form);
    }

    public List<FormEntity> getAllForms() {
        return frepo.findAll();
    }

    public FormEntity updateForm(int formId, FormEntity newFormDetails) {
        FormEntity form = frepo.findById(formId)
            .orElseThrow(() -> new NoSuchElementException("Form " + formId + " does not exist"));

        form.setTitle(newFormDetails.getTitle());
        form.setDue_date(newFormDetails.getDue_date());

        return frepo.save(form);
    }

    public String deleteForm(int formId) {
        if (frepo.existsById(formId)) {
            frepo.deleteById(formId);
            return "Form " + formId + " is successfully deleted!";
        } else {
            return "Form " + formId + " does not exist.";
        }
    }
}