SET FOREIGN_KEY_CHECKS=0;
DELETE FROM grade_entity;
DELETE FROM student_entity;
DELETE FROM section_entity;
DELETE FROM subject_entity;
ALTER TABLE section_entity AUTO_INCREMENT = 1;
ALTER TABLE subject_entity AUTO_INCREMENT = 1;
ALTER TABLE student_entity AUTO_INCREMENT = 1;

INSERT INTO section_entity (section_id) VALUES (DEFAULT);
INSERT INTO section_entity (section_id) VALUES (DEFAULT);

INSERT INTO subject_entity (subject_id, subject_code, subject_name) VALUES
(DEFAULT, 'SCI', 'Science'),
(DEFAULT, 'MATH', 'Mathematics'),
(DEFAULT, 'READ', 'Reading'),
(DEFAULT, 'LANG', 'Language');

-- Insert students with EXPLICIT static IDs (1, 2, 3, etc.)
INSERT INTO student_entity (student_id, section_id, student_number, first_name, last_name, grade_level) VALUES
(1, 1, '1234-56789', 'John', 'Doe', 10),
(2, 1, '12-3456-789', 'Jane', 'Smith', 9),
(3, 1, '9876-54321', 'Alex', 'Lee', 11),
(4, 1, '11-2222-333', 'Sophia', 'Anderson', 7),
(5, 1, '44-7777-888', 'Charlotte', 'Jackson', 9),
(6, 1, '6789-01234', 'Lucas', 'White', 11),
(7, 1, '8642-97531', 'Ethan', 'Garcia', 10),
(8, 1, '2109-87654', 'Michael', 'Robinson', 11),
(9, 1, '34-1298-456', 'Maria', 'Garcia', 8),
(10, 1, '4567-89012', 'Liam', 'Johnson', 12),
(11, 1, '73-4521-908', 'Emma', 'Brown', 7),
(12, 1, '4321-09876', 'James', 'Taylor', 12),
(13, 2, '1357-24680', 'Noah', 'Davis', 10),
(14, 2, '28-1134-762', 'Olivia', 'Miller', 9),
(15, 2, '2468-13579', 'William', 'Wilson', 11),
(16, 2, '90-5678-321', 'Ava', 'Moore', 8),
(17, 2, '5555-66666', 'Benjamin', 'Thomas', 10),
(18, 2, '66-7788-990', 'Mia', 'Thompson', 7),
(19, 2, '13-5791-357', 'Isabella', 'Martinez', 9),
(20, 2, '3141-59265', 'Daniel', 'Rodriguez', 12),
(21, 2, '22-3344-556', 'Amelia', 'Harris', 8),
(22, 2, '1111-22222', 'Henry', 'Martin', 12),
(23, 2, '75-4210-864', 'Evelyn', 'Clark', 8),
(24, 2, '27-1828-459', 'Harper', 'Lewis', 7);

SET FOREIGN_KEY_CHECKS=1;