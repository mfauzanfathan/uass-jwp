CREATE TABLE patient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nik VARCHAR(10) UNIQUE,
    full_name VARCHAR(50),
    disease TEXT,
    date_of_birth DATE,
    );