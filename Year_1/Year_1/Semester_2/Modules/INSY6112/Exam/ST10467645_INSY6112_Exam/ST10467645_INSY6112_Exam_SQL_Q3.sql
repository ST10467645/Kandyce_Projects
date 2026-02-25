CREATE SCHEMA ST10467645_INSY6112_Exam_Q3;

USE ST10467645_INSY6112_Exam_Q3;

-- 3.1
CREATE TABLE Patient
(
	PatientID int PRIMARY KEY,
    PatientName varchar(100),
    PatientSurname varchar(100),
    PatientDOB Date
);

-- 3.2
CREATE TABLE Doctor
(
	DoctorID int PRIMARY KEY,
    DoctorName varchar(100),
    DoctorSurname varchar(100)
);

-- 3.3
CREATE TABLE Appointment
(
	AppointmentID int PRIMARY KEY,
    PatientID int, 
    foreign key(PatientID) REFERENCES Patient(PatientID),
    DoctorID int,
    foreign key(DoctorID) REFERENCES Doctor(DoctorID),
    AppointmentDate date,
    AppointmentTime time,
    AppointmentDuration int
);

-- 3.4
INSERT INTO Patient (PatientID, PatientName, PatientSurname, PatientDOB)
values
(1, 'Debbie', 'Theart', '1980-03-17'),
(2, 'Thomas', 'Duncan', '1976-08-12');

INSERT INTO Doctor (DoctorID, DoctorName, DoctorSurname)
values
(1, 'Zintle', 'Nukani'),
(2, 'Ravi', 'Maharaj');

INSERT INTO Appointment (AppointmentID, PatientID, DoctorID, AppointmentDate, AppointmentTime, AppointmentDuration)
values
(1, 1, 2, '2025-01-15', '9:00', '15'),
(2, 2, 2, '2025-01-18', '15:00', '30'),
(3, 1, 1, '2025-01-20', '10:00', '15'),
(4, 1, 2, '2025-01-21', '11:00', '15');

-- 3.5
SELECT * FROM Appointment
WHERE AppointmentDate BETWEEN '2025-01-16'
AND '2025-01-20';

-- 3.6
SELECT p.PatientName, p.PatientSurname, COUNT(a.AppointmentID) AS `TOTAL APPOINTMENTS`
FROM Appointment a 
JOIN Patient p ON a.PatientID = p.PatientID
GROUP BY  p.PatientName, p.PatientSurname
ORDER BY `TOTAL APPOINTMENTS` DESC;

-- 3.7
CREATE VIEW DOCTOR2_PATIENTS AS 
SELECT DISTINCT p.PatientName, p.PatientSurname
FROM Appointment a 
JOIN Patient p ON a.PatientID = p.PatientID
WHERE a.DoctorID = 2
ORDER BY PatientSurname ASC;