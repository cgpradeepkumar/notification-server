DROP TABLE IF EXISTS Email_Out;

CREATE TABLE Email_Out(
	ID int IDENTITY(1,1) NOT NULL,
	EmailBody varchar(5000) NULL,
	EmailSubject varchar(500) NULL,
	FromID varchar(50) NULL,
	ToID varchar(500) NULL,
	Status char(10) NULL,
	Eventtime datetime NULL
);