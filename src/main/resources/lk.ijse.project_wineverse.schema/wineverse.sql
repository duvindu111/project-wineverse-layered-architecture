DROP DATABASE IF EXISTS wineverse;
CREATE DATABASE wineverse;
USE wineverse;

CREATE TABLE User(
                     username VARCHAR(30),
                     password VARCHAR(20) NOT NULL,
                     user_job_title TEXT NOT NULL,
                     email VARCHAR(100) UNIQUE NOT NULL,
                     PRIMARY KEY(username)
);

CREATE TABLE Employee(
                         emp_id VARCHAR(20),
                         emp_name VARCHAR(100) NOT NULL,
                         emp_dob DATE NOT NULL,
                         emp_address TEXT NOT NULL,
                         emp_contact VARCHAR(20) UNIQUE NOT NULL,
                         emp_email VARCHAR(50),
                         emp_job_title TEXT NOT NULL,
                         emp_nic VARCHAR(15) UNIQUE NOT NULL,
                         PRIMARY KEY(emp_id)
);

CREATE TABLE Customer(
                         cust_id VARCHAR(20),
                         cust_name VARCHAR(100) NOT NULL,
                         cust_email VARCHAR(50),
                         cust_contact VARCHAR(15) NOT NULL,
                         PRIMARY KEY(cust_id)
);

CREATE TABLE Orders(
                       order_id VARCHAR(20),
                       cust_id VARCHAR(20),
                       delivery BOOLEAN NOT NULL ,
                       order_date DATE NOT NULL,
                       order_time TIME,
                       order_payment DECIMAL(10,2) NOT NULL ,
                       PRIMARY KEY(order_id),
                       CONSTRAINT FOREIGN KEY(cust_id) REFERENCES Customer(cust_id)
                           ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Delivery(
                         delivery_id VARCHAR(20),
                         delivery_status VARCHAR(20) NOT NULL ,
                         location TEXT NOT NULL ,
                         delivered_date DATE,
                         due_date DATE NOT NULL,
                         order_id VARCHAR(20),
                         emp_id VARCHAR(20),
                         PRIMARY KEY(delivery_id),
                         CONSTRAINT FOREIGN KEY(order_id) REFERENCES Orders(order_id)
                             ON UPDATE CASCADE ON DELETE CASCADE,
                         CONSTRAINT FOREIGN KEY(emp_id) REFERENCES Employee(emp_id)
                             ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Item(
                     item_code VARCHAR(20),
                     item_name VARCHAR(200) NOT NULL,
                     item_unit_price DECIMAL(10,2) NOT NULL,
                     item_category VARCHAR(100),
                     item_qty INT NOT NULL ,
                     PRIMARY KEY (item_code)
);

CREATE TABLE Supplier(
                         supp_id VARCHAR(20),
                         supp_name VARCHAR(200) NOT NULL ,
                         supp_email VARCHAR(100),
                         supp_contact VARCHAR(15) NOT NULL,
                         supp_address TEXT,
                         PRIMARY KEY(supp_id)
);

CREATE TABLE Event(
                      event_id VARCHAR(20),
                      event_name VARCHAR(100) NOT NULL ,
                      event_type VARCHAR(50),
                      event_date DATE NOT NULL ,
                      event_time TIME NOT NULL ,
                      emp_id VARCHAR(20),
                      PRIMARY KEY (event_id),
                      CONSTRAINT FOREIGN KEY(emp_id) REFERENCES Employee(emp_id)
                          ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Salary(
                       emp_id VARCHAR(20),
                       salary_id VARCHAR(20),
                       salary_amount DECIMAL(8,2) NOT NULL ,
                       OT DECIMAL(7,2),
                       payment_method VARCHAR(30) NOT NULL ,
                       PRIMARY KEY (emp_id),
                       CONSTRAINT FOREIGN KEY(emp_id) REFERENCES Employee(emp_id)
                           ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Order_detail(
                             order_id VARCHAR(20),
                             item_code VARCHAR(20),
                             order_qty INT NOT NULL ,
                             PRIMARY KEY (order_id,item_code),
                             CONSTRAINT FOREIGN KEY(order_id) REFERENCES Orders(order_id)
                                 ON UPDATE CASCADE ON DELETE CASCADE,
                             CONSTRAINT FOREIGN KEY(item_code) REFERENCES Item(item_code)
                                 ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Supply_load_details(
                                    load_id VARCHAR(20),
                                    supp_id VARCHAR(20),
                                    item_code VARCHAR(20),
                                    supp_qty INT,
                                    load_date DATE,
                                    load_time TIME,
                                    price DECIMAL(8,2),
                                    PRIMARY KEY (supp_id,item_code,load_id),
                                    CONSTRAINT FOREIGN KEY(supp_id) REFERENCES Supplier(supp_id)
                                        ON UPDATE CASCADE ON DELETE CASCADE,
                                    CONSTRAINT FOREIGN KEY(item_code) REFERENCES Item(item_code)
                                        ON UPDATE CASCADE ON DELETE CASCADE
);
















