-- Drop tables if they exist
DROP TABLE IF EXISTS Sales_Facts;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Outlets_Location;
DROP TABLE IF EXISTS Time;
DROP TABLE IF EXISTS Staff;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Payment;

-- Products Dimension Table
CREATE TABLE Products (
    Product_ID INT PRIMARY KEY,
    Brand VARCHAR(100),
    Supplier VARCHAR(100),
    Loyalty_Points boolean
);

-- Outlets/Location Dimension Table
CREATE TABLE Outlets_Location (
    Outlet_ID INT PRIMARY KEY,
    Vendor VARCHAR(100),
    Address VARCHAR(255),
    City VARCHAR(100),
    Country VARCHAR(100)
);

-- Time Dimension Table
CREATE TABLE Time (
    Time_ID INT PRIMARY KEY,
    Date DATE,
    Hours TIME,
    Day INT,
    Week INT,
    Month INT,
    Year INT
);

-- Staff Dimension Table
CREATE TABLE Staff (
    Staff_ID INT PRIMARY KEY,
    Staff_Name VARCHAR(100)
);

-- Customers Dimension Table
CREATE TABLE Customers (
    Customer_ID INT PRIMARY KEY,
    Gender VARCHAR(10),
    Age INT
);

-- Payment Dimension Table
CREATE TABLE Payment (
    Payment_ID INT PRIMARY KEY,
    Payment_Type VARCHAR(100)
);

-- Sales Facts Fact Table
CREATE TABLE Sales_Facts (
    Sales_ID INT PRIMARY KEY,
    Product_ID INT,
    Outlet_ID INT,
    Time_ID INT,
    Staff_ID INT,
    Customer_ID INT,
    Payment_ID INT,
    Units INT,
    Total_Revenue DECIMAL(10, 2),
    Total_Profit DECIMAL(10, 2),
    Discount_Amount DECIMAL(10, 2),
    FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID),
    FOREIGN KEY (Outlet_ID) REFERENCES Outlets_Location(Outlet_ID),
    FOREIGN KEY (Time_ID) REFERENCES Time(Time_ID),
    FOREIGN KEY (Staff_ID) REFERENCES Staff(Staff_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customers(Customer_ID),
    FOREIGN KEY (Payment_ID) REFERENCES Payment(Payment_ID)
);
