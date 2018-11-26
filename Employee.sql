CREATE DATABASE employeeDb; 
--
-- Table structure for table `tblemployee`
--


DROP TABLE IF EXISTS 'tblemployee';
CREATE TABLE `tblemployee` (
  `Emp_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Emp_Name` varchar(50) NOT NULL,
  `Email_Id` varchar(50) NOT NULL,
  `Mobile_No` varchar(20) NOT NULL,
  `Country_Code` varchar(5) DEFAULT NULL,
  `Alt_Mobile_No` varchar(20) DEFAULT NULL,
  `Address` varchar(50) NOT NULL,
  `City` varchar(50) DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `Company_Name` varchar(100) DEFAULT NULL,
  `Created_By` int(11) DEFAULT NULL,
  `Created_Date` datetime DEFAULT NULL,
  `Last_Modified_by` int(11) DEFAULT NULL,
  `Last_Modified_Date` datetime DEFAULT NULL,
  `Is_Active` bit(1) NOT NULL,
  PRIMARY KEY (`Emp_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
