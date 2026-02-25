-- Q3
CREATE SCHEMA ST10467645_INSY6112_Test;

USE ST10467645_INSY6112_Test;

-- 3.1 
CREATE TABLE Author
(
	AuthorID int NOT NULL PRIMARY KEY,
    AuthorName varchar(100),
    AuthorEmail varchar(100)
);

-- 3.2 
CREATE TABLE FictionBook
(
	BookID int NOT NULL PRIMARY KEY,
    BookTitle varchar(100),
    AuthorID int NOT NULL,
    FOREIGN KEY(AuthorID) REFERENCES Author(AuthorID),
    PublicationDate date
);

-- 3.3 
INSERT INTO Author (AuthorID, AuthorName, AuthorEmail)
values
(1, 'Thabo Bless', 'thabo@yahoo.com');

-- 3.4
INSERT INTO FictionBook(BookID, BookTitle, AuthorID, PublicationDate)
values
(1, 'Into The Darkness', 1, '2025-02-14');