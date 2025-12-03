package com.appdevg5.geeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import com.appdevg5.geeks.entity.FormEntity;
import com.appdevg5.geeks.service.FormService;

@RestController
@RequestMapping("/api/forms")
@CrossOrigin(origins = "http://localhost:3000")
public class FormController {

    @Autowired
    FormService fserv;

    @PostMapping
    public FormEntity createForm(@RequestBody Map<String, Object> payload) {
        int teacherId = (Integer) payload.get("teacher_id");
        String title = (String) payload.get("title");
        String dueDate = (String) payload.get("due_date");
        
        if (!dueDate.contains(" ")) {
            dueDate = dueDate + " 00:00:00";
        }
        
        FormEntity form = new FormEntity();
        form.setTeacher_id(teacherId);
        form.setTitle(title);
        form.setDue_date(Timestamp.valueOf(dueDate));
        form.setCreated_at(new Timestamp(System.currentTimeMillis()));
        
        return fserv.insertFormRecord(form);
    }

    @GetMapping
    public List<FormEntity> getAllForms() {
        return fserv.getAllForms();
    }

    @PutMapping("/{formId}")
    public FormEntity updateForm(@PathVariable int formId, @RequestBody Map<String, Object> payload) {
        String title = (String) payload.get("title");
        String dueDate = (String) payload.get("due_date");
        
        if (!dueDate.contains(" ")) {
            dueDate = dueDate + " 00:00:00";
        }
        
        FormEntity formDetails = new FormEntity();
        formDetails.setTitle(title);
        formDetails.setDue_date(Timestamp.valueOf(dueDate));
        
        return fserv.updateForm(formId, formDetails);
    }

    @DeleteMapping("/{formId}")
    public String deleteForm(@PathVariable int formId) {
        return fserv.deleteForm(formId);
    }
}