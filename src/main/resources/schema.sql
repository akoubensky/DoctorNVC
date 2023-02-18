CREATE TABLE position (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL
)

CREATE TABLE doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    position_id BIGINT FOREIGN KEY position(id)
)