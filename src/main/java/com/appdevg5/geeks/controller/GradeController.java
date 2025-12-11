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
import com.appdevg5.geeks.repository.GradeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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
    
    @Autowired
    GradeRepository gradeRepository;

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
        System.out.println("[Grades] Fetching detailed grades: studentId=" + studentId + 
            ", subjectId=" + subjectId + ", quarter=" + quarter);
        List<GradeEntity> grades = gserv.getGradesByStudentSubjectQuarter(studentId, subjectId, quarter); 
        System.out.println("[Grades] Returned count=" + grades.size());
        return grades; 
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

    @GetMapping("/student/{studentId}/quarter/{quarter}/average")
    public ResponseEntity<Double> getStudentQuarterAverage(
        @PathVariable int studentId,
        @PathVariable int quarter
    ) {
        try {
            List<GradeEntity> grades = gradeRepository.findByStudentIdAndGradingPeriod(studentId, quarter);
            if (grades.isEmpty()) {
                return ResponseEntity.ok(0.0);
            }

            java.util.Map<String, java.util.List<Double>> categoryScores = new java.util.HashMap<>();
            categoryScores.put("QUIZ", new java.util.ArrayList<>());
            categoryScores.put("EXAM", new java.util.ArrayList<>());
            categoryScores.put("PERF", new java.util.ArrayList<>());
            categoryScores.put("ASG", new java.util.ArrayList<>());

            for (GradeEntity grade : grades) {
                String name = grade.getAssessment_name();
                if (name == null) continue;
                if (name.startsWith("[QUIZ]")) {
                    categoryScores.get("QUIZ").add((double) grade.getGrade_value());
                } else if (name.startsWith("[EXAM]")) {
                    categoryScores.get("EXAM").add((double) grade.getGrade_value());
                } else if (name.startsWith("[PERF]")) {
                    categoryScores.get("PERF").add((double) grade.getGrade_value());
                } else if (name.startsWith("[ASG]")) {
                    categoryScores.get("ASG").add((double) grade.getGrade_value());
                }
            }

            double quizAvg = categoryScores.get("QUIZ").isEmpty() ? 0 :
                categoryScores.get("QUIZ").stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double examAvg = categoryScores.get("EXAM").isEmpty() ? 0 :
                categoryScores.get("EXAM").stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double perfAvg = categoryScores.get("PERF").isEmpty() ? 0 :
                categoryScores.get("PERF").stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double asgAvg = categoryScores.get("ASG").isEmpty() ? 0 :
                categoryScores.get("ASG").stream().mapToDouble(Double::doubleValue).average().orElse(0);

            double total = (quizAvg * 0.3) + (examAvg * 0.3) + (perfAvg * 0.3) + (asgAvg * 0.1);
            return ResponseEntity.ok(Math.round(total * 100.0) / 100.0);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0.0);
        }
    }

    @GetMapping("/student/{studentId}/quarter/{quarter}")
    public ResponseEntity<Double> getStudentQuarter(
        @PathVariable int studentId,
        @PathVariable int quarter
    ) {
        return getStudentQuarterAverage(studentId, quarter);
    }

    @GetMapping("/student/{studentId}/quarters")
    public ResponseEntity<java.util.Map<String, Double>> getStudentQuarterAverages(
        @PathVariable int studentId
    ) {
        java.util.Map<String, Double> result = new java.util.HashMap<>();
        result.put("Q1", getStudentQuarterAverage(studentId, 1).getBody());
        result.put("Q2", getStudentQuarterAverage(studentId, 2).getBody());
        result.put("Q3", getStudentQuarterAverage(studentId, 3).getBody());
        result.put("Q4", getStudentQuarterAverage(studentId, 4).getBody());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/student/{studentId}/finals")
    public ResponseEntity<Double> getStudentFinals(
        @PathVariable int studentId
    ) {
        double q1 = getStudentQuarterAverage(studentId, 1).getBody();
        double q2 = getStudentQuarterAverage(studentId, 2).getBody();
        double q3 = getStudentQuarterAverage(studentId, 3).getBody();
        double q4 = getStudentQuarterAverage(studentId, 4).getBody();

        java.util.List<Double> quarters = new java.util.ArrayList<>();
        if (q1 > 0) quarters.add(q1);
        if (q2 > 0) quarters.add(q2);
        if (q3 > 0) quarters.add(q3);
        if (q4 > 0) quarters.add(q4);

        if (quarters.isEmpty()) {
            return ResponseEntity.ok(0.0);
        }
        double finals = quarters.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        return ResponseEntity.ok(Math.round(finals * 100.0) / 100.0);
    }
}
