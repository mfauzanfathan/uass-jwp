CREATE TABLE doctor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) UNIQUE,
    full_name VARCHAR(50),
    specialist TEXT,
    practice_schedule DATE,
    );