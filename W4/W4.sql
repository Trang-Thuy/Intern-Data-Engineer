USE myDB;

CREATE TABLE Students (
  StudentID INT PRIMARY KEY,
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  Age INT,
  Class INT
);

INSERT INTO Students (StudentID, FirstName, LastName, Age, Class)
VALUES
  (1, 'John', 'Doe', 16, 10),
  (2, 'Jane', 'Smith', 15, 9),
  (3, 'Michael', 'Johnson', 17, 11),
  (4, 'Emily', 'Williams', 16, 10);

CREATE TABLE Grades (
  GradeID INT PRIMARY KEY,
  StudentID INT,
  Subject VARCHAR(50),
  Score INT,
  FOREIGN KEY (StudentID) REFERENCES Students(StudentID)
);

INSERT INTO Grades (GradeID, StudentID, Subject, Score)
VALUES
  (1, 1, 'Math', 90),
  (2, 1, 'Science', 85),
  (3, 1, 'History', 78),
  (4, 2, 'Math', 92),
  (5, 2, 'Science', 88),
  (6, 2, 'History', 95),
  (7, 3, 'Math', 78),
  (8, 3, 'Science', 80),
  (9, 3, 'History', 85),
  (10, 4, 'Math', 85),
  (11, 4, 'Science', 90),
  (12, 4, 'History', 88);






 -- 1
-- SELECT * FROM Students;
-- -- optimize
-- SELECT StudentID, FirstName, LastName FROM Students;

-- 2
-- SELECT COUNT(*) FROM Students WHERE Class=10;
-- optimize
-- SELECT EXISTS (SELECT 1 FROM Students WHERE Class= 10);

-- 3
-- SELECT DISTINCT Subject
-- FROM Grades;
-- optimize
-- SELECT Subject
-- FROM Grades
-- GROUP BY Subject;

-- 4 
-- SELECT * FROM Grades WHERE Subject = 'Math';
-- optimize 
-- CREATE INDEX idx_Subject ON Grades(Subject);

-- 5 
-- SELECT * FROM Students WHERE FirstName LIKE '%John%';
-- optimize
-- SELECT * FROM Students WHERE FirstName LIKE '%John%';

-- 6  

-- SELECT FirstName, LastName FROM Students 
-- INNER JOIN Grades ON Students.StudentID = Grades.StudentID 
-- WHERE Grades.Subject = 'Math';







