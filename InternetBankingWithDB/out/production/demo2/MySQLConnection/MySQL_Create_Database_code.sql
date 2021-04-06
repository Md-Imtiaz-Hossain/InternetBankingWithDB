DROP DATABASE IF EXISTS abcd_bank;
CREATE DATABASE abcd_bank;
USE abcd_bank; 
CREATE TABLE abcd_bank.users (
  id INT NOT NULL AUTO_INCREMENT,
  Account_TypeChoice VARCHAR(45) NOT NULL,
  First_Name VARCHAR(45) NOT NULL,
  Last_Name VARCHAR(45) NULL,
  Email VARCHAR(45) NULL,
  Phone VARCHAR(45) NOT NULL,
  Account_Number BIGINT(16) NOT NULL,
  Country_Choice VARCHAR(45) NOT NULL,
  Branch_Choice VARCHAR(45) NOT NULL,
  Birth_Date VARCHAR(45) NOT NULL,
  Initial_Amount DECIMAL NOT NULL,
  Gender_Choice VARCHAR(45) NOT NULL,
  Nid_Passport_No VARCHAR(45) NOT NULL,
  Address VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
  UNIQUE INDEX Email_UNIQUE (Email ASC) VISIBLE,
  UNIQUE INDEX Phone_UNIQUE (Phone ASC) VISIBLE,
  UNIQUE INDEX Account_Number_UNIQUE (Account_Number ASC) VISIBLE,
  UNIQUE INDEX Nid_Passport_No_UNIQUE (Nid_Passport_No ASC) VISIBLE,


	userName VARCHAR(255) ,
    password VARCHAR(255) ,
    image VARCHAR(255),
	sign VARCHAR(255),
	UNIQUE INDEX userName_UNIQUE (userName ASC) VISIBLE,
	UNIQUE INDEX image_UNIQUE (image ASC) VISIBLE,
	UNIQUE INDEX sign_UNIQUE (sign ASC) VISIBLE

  );



INSERT INTO users (Account_TypeChoice,First_Name,Last_Name,Email,Phone,Account_Number,Country_Choice,Branch_Choice,Birth_Date,Initial_Amount,Gender_Choice,Nid_Passport_No,Address)
VALUES
('General','Imtiaz1','Hossain','imtiaz1@gmail.com','019601536601',4404103876727001,'Bangladesh','Ghorashal','7/5/1998',500,'Male','Dhaka1','Narsingdi'),
('General','Imtiaz2','Hossain','imtiaz2@gmail.com','019601536602',4404103876727002,'Bangladesh','Ghorashal','7/5/1998',500,'Male','Dhaka2','Narsingdi'),
('General','Imtiaz3','Hossain','imtiaz3@gmail.com','019601536603',4404103876727003,'Bangladesh','Ghorashal','7/5/1998',500,'Male','Dhaka3','Narsingdi'),
('General','Imtiaz4','Hossain','imtiaz4@gmail.com','019601536604',4404103876727004,'Bangladesh','Ghorashal','7/5/1998',500,'Male','Dhaka4','Narsingdi'),
('General','Imtiaz5','Hossain','imtiaz5@gmail.com','019601536605',4404103876727005,'Bangladesh','Ghorashal','7/5/1998',500,'Male','Dhaka5','Narsingdi');


