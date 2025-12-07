package com.appdevg5.geeks.controller;

import java.util.List;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.appdevg5.geeks.entity.GradeEntity;
import com.appdevg5.geeks.entity.StudentEntity;
import com.appdevg5.geeks.entity.SubjectEntity;
import com.appdevg5.geeks.service.GradeService;
import com.appdevg5.geeks.dto.GradeDTO;
import com.appdevg5.geeks.service.StudentService;
import com.appdevg5.geeks.repository.StudentRepository;
import com.appdevg5.geeks.repository.SubjectRepository;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/grades")
@CrossOrigin(origins = "http://localhost:3000")
public class GradeController {

    @Autowired
    GradeService gserv;

    @Autowired
    StudentService sserv;
    
    @Autowired
    StudentRepository studentRepo;
    
    @Autowired
    SubjectRepository subjectRepo;

    @PostMapping("/insertGradeRecord")
    public GradeEntity insertGradeRecord(@RequestBody GradeEntity grade){
        return gserv.insertGradeRecord(grade);
    }

    // NEW: Better endpoint that accepts simple data
    @PostMapping
    public GradeEntity createGrade(@RequestBody Map<String, Object> payload){
        try {
            int studentId = ((Number) payload.get("student_id")).intValue();
            int subjectId = ((Number) payload.get("subject_id")).intValue();
            int gradingPeriod = ((Number) payload.get("grading_period")).intValue();
            float gradeValue = ((Number) payload.get("grade_value")).floatValue();
            
            // NEW: assessment_name field 
            String assessmentName = payload.containsKey("assessment_name") ? 
                (String) payload.get("assessment_name") : null; 
            
            // Optional teacher_id
            Integer teacherId = payload.containsKey("teacher_id") ? 
                ((Number) payload.get("teacher_id")).intValue() : 1;
            
            // Fetch the actual entities
            StudentEntity student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));
            SubjectEntity subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found: " + subjectId));
            
            // Create the grade entity
            GradeEntity grade = new GradeEntity();
            grade.setStudent(student);
            grade.setSubject(subject);
            grade.setTeacher_id(teacherId);
            grade.setGrade_value(gradeValue);
            grade.setGrading_period(gradingPeriod);
            grade.setAssessment_name(assessmentName);  // NEW LINE 
            grade.setRecorded_at(new Timestamp(System.currentTimeMillis()));
            
            return gserv.insertGradeRecord(grade);
        } catch (Exception e) {
            System.err.println("Error creating grade: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to create grade: " + e.getMessage());
        }
    }

    @GetMapping("/getAllGrades")
    public List<GradeEntity> getAllGrades(){
        return gserv.getAllGrades();
    }

    @GetMapping
    public List<GradeEntity> getGrades(){
        return gserv.getAllGrades();
    }

    @PutMapping("/updateGrade")
    public GradeEntity updateGrade(@RequestParam int gid, @RequestBody GradeEntity newGradeDetails){
        return gserv.updateGrade(gid, newGradeDetails);
    }

    @DeleteMapping("/deleteGrade/{gid}")
    public String deleteGrade(@PathVariable int gid){
        return gserv.deleteGrade(gid);
    }

    @GetMapping("/by-section/{sectionId}")
    public List<GradeDTO> getGradesBySection(@PathVariable int sectionId){
        List<StudentEntity> students = sserv.getStudentsBySection(sectionId);
        List<GradeEntity> sectionGrades = gserv.getGradesBySection(sectionId);
        
        List<GradeDTO> result = new ArrayList<>();
        
        for (StudentEntity s : students){
            for (int q = 1; q <= 4; q++){
                final int quarter = q;
                final int studentId = s.getStudent_id();
                
                // Filter grades for this student and quarter
                List<GradeEntity> quarterGrades = sectionGrades.stream()
                    .filter(g -> g.getStudent_id() == studentId && g.getGrading_period() == quarter)
                    .collect(java.util.stream.Collectors.toList());
                
                // Add each subject grade
                for (GradeEntity grade : quarterGrades){
                    String subjectName = grade.getSubject() != null ? 
                        grade.getSubject().getSubject_name() : "";
                    
                    result.add(new GradeDTO(
                        grade.getGrade_id(),
                        studentId,
                        s.getFirst_name() + " " + s.getLast_name(),
                        subjectName,
                        grade.getGrade_value(),
                        quarter
                    ));
                }
            }
        }
        
        return result;
    }

    // Get all assessments for specific student/subject/quarter 
    @GetMapping("/student/{studentId}/subject/{subjectId}/quarter/{quarter}") 
    public List<GradeEntity> getDetailedGrades( 
        @PathVariable int studentId, 
        @PathVariable int subjectId, 
        @PathVariable int quarter 
    ) { 
        return gserv.getGradesByStudentSubjectQuarter(studentId, subjectId, quarter); 
    } 
 
    // Calculate quarterly average from all assessments 
    @GetMapping("/calculate/{studentId}/{subjectId}/{quarter}") 
    public Map<String, Object> calculateQuarterGrade( 
        @PathVariable int studentId, 
        @PathVariable int subjectId, 
        @PathVariable int quarter 
    ) { 
        List<GradeEntity> grades = gserv.getGradesByStudentSubjectQuarter( 
            studentId, subjectId, quarter 
        ); 
        
        if (grades.isEmpty()) { 
            Map<String, Object> empty = new java.util.HashMap<>(); 
            empty.put("quarter_grade", 0.0); 
            return empty; 
        } 
        
        double avg = grades.stream() 
            .mapToDouble(GradeEntity::getGrade_value) 
            .average() 
            .orElse(0.0); 
        
        Map<String, Object> result = new java.util.HashMap<>(); 
        result.put("quarter_grade", avg); 
        return result; 
    }
}