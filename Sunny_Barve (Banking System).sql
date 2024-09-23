# Banking System

CREATE DATABASE HMBank;
USE HMBank;

#Tasks 1: Database Design:

CREATE TABLE Customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    DOB DATE,
    email VARCHAR(100),
    phone_number VARCHAR(15),
    address VARCHAR(255)
);

CREATE TABLE Accounts (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    account_type VARCHAR(50),
    balance DECIMAL(10, 2),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    transaction_type VARCHAR(50),
    amount DECIMAL(10, 2),
    transaction_date DATE,
    FOREIGN KEY (account_id) REFERENCES Accounts(account_id)
);


# Task 2: SQL Queries -Select, Where, Between, AND, LIKE

INSERT INTO Customers (first_name, last_name, DOB, email, phone_number, address)
VALUES 
('Sunny', 'Barve', '2024-01-01', 'sbarve@example.com', '1234567890', '123 abc Street'),
('Rahul', 'Gupta', '1990-02-02', 'rahul.g@example.com', '9876543210', '456 def venue'),
('Alice', 'Johnson', '1985-03-03', 'alice.johnson@example.com', '5554443333', '789 ghi Lane'),
('Bob', 'Williams', '1975-04-04', 'bob.williams@example.com', '4445556666', '101 jkl Street'),
('Charlie', 'Brown', '1992-05-05', 'charlie.brown@example.com', '3332221111', '202 mno venue'),
('John', 'Doe', '1992-05-05', 'john.doe@example.com', '3332232111', '202 pqr Lane'),
('Jane', 'Smith', '1992-05-05', 'jane.smith@example.com', '3339861111', '202 stu Street'),
('Alexander', 'Dane', '1992-05-05', 'alex.dane@example.com', '33322852111', '202 vwx venue'),
('Max', 'kale', '1992-05-05', 'max.kale@example.com', '3337853111', '202 ace Lane'),
('Stevin', 'Jhone', '1992-05-05', 'stevin.jhone@example.com', '3332209711', '202 jsve Street');

INSERT INTO Accounts (customer_id, account_type, balance)
VALUES 
(1, 'savings', 1500.00),
(2, 'current', 2500.00),
(3, 'savings', 1000.00),
(4, 'current', 3500.00),
(5, 'savings', 500.00),
(6, 'zero_balance', 0.00),
(7, 'savings', 5600.00),
(8, 'zero_balance', 0.00),
(9, 'savings', 7500.00),
(10, 'current', 8500.00);

INSERT INTO Transactions (account_id, transaction_type, amount, transaction_date)
VALUES 
(1, 'deposit', 500.00, '2024-01-10'),
(2, 'withdrawal', 200.00, '2024-01-15'),
(3, 'deposit', 1000.00, '2024-02-05'),
(4, 'withdrawal', 300.00, '2024-03-20'),
(5, 'deposit', 400.00, '2024-04-10'),
(6, 'deposit', 1000.00, '2024-02-01'),
(7, 'withdrawal', 900.00, '2024-03-20'),
(8, 'deposit', 500.00, '2024-07-18'),
(9, 'transfer', 400.00, '2024-08-15'),
(10, 'transfer', 300.00, '2024-09-10');

# 1. Write a SQL query to retrieve the name, account type and email of all customers.

SELECT first_name, last_name, email, account_type 
FROM Customers, Accounts 
WHERE Customers.customer_id = Accounts.customer_id;

# 2. Write a SQL query to list all transaction corresponding customer.

SELECT first_name, last_name, transaction_type, amount, transaction_date 
FROM Customers, Accounts, Transactions 
WHERE Customers.customer_id = Accounts.customer_id 
AND Accounts.account_id = Transactions.account_id;

# 3. Write a SQL query to increase the balance of a specific account by a certain amount.

UPDATE Accounts 
SET balance = balance + 500 
WHERE account_id = 1;

# 4. Write a SQL query to Combine first and last names of customers as a full_name.

SELECT CONCAT(first_name, ' ', last_name) AS full_name 
FROM Customers;

# 5. Write a SQL query to remove accounts with a balance of zero where the account type is savings.

DELETE FROM Accounts 
WHERE balance = 0 AND account_type = 'savings';

SET SQL_SAFE_UPDATES = 0;

DELETE FROM Accounts 
WHERE balance = 0 AND account_type = 'savings';

SET SQL_SAFE_UPDATES = 1;

# # 6. Write a SQL query to Find customers living in a specific city.

SELECT first_name, last_name, address 
FROM Customers 
WHERE address LIKE '%Street%';

# # 7. Write a SQL query to Get the account balance for a specific account.

SELECT balance 
FROM Accounts 
WHERE account_id = 1; 

# # 8. Write a SQL query to List all current accounts with a balance greater than $1,000.

SELECT * 
FROM Accounts 
WHERE account_type = 'current' AND balance > 1000;

# 9. Write a SQL query to Retrieve all transactions for a specific account.

SELECT * 
FROM Transactions 
WHERE account_id = 1;

# 10. Write a SQL query to Calculate the interest accrued on savings accounts based on a given interest rate.

SELECT account_id, balance, (balance * 0.05) AS interest_accrued 
FROM Accounts 
WHERE account_type = 'savings';

# 11. Write a SQL query to Identify accounts where the balance is less than a specified overdraft limit.

SELECT * 
FROM Accounts 
WHERE balance < 1000;

# 12. Write a SQL query to Find customers not living in a specific city.

SELECT first_name, last_name, address 
FROM Customers 
WHERE address NOT LIKE '%venue%';


# Tasks 3: Aggregate functions, Having, Order By, GroupBy and Joins:

# 1. Write a SQL query to Find the average account balance for all customers.

SELECT AVG(balance) AS average_balance 
FROM Accounts;

# 2. Write a SQL query to Retrieve the top 10 highest account balances. 


SELECT account_id, MAX(balance) AS highest_balance
FROM Accounts
GROUP BY account_id
ORDER BY highest_balance DESC
LIMIT 10;

# 3. Write a SQL query to Calculate Total Deposits for All Customers in specific date.

SELECT SUM(amount) AS total_deposits 
FROM Transactions 
WHERE transaction_type = 'deposit' AND transaction_date = '2024-01-10';

# 4. Write a SQL query to Find the Oldest and Newest Customers. **

SELECT  MIN(DOB) AS oldest_customer, MAX(DOB) AS newest_customer 
FROM Customers;

# 5. Write a SQL query to Retrieve transaction details along with the account type.

SELECT Transactions.*, Accounts.account_type 
FROM Transactions 
JOIN Accounts ON Transactions.account_id = Accounts.account_id;

# 6. Write a SQL query to Get a list of customers along with their account details.

SELECT first_name, last_name, account_type, balance 
FROM Customers 
JOIN Accounts ON Customers.customer_id = Accounts.customer_id;

# 7. Write a SQL query to Retrieve transaction details along with customer information for a specific account. **

SELECT first_name, last_name, transaction_type, amount, transaction_date 
FROM Transactions 
JOIN Accounts ON Transactions.account_id = Accounts.account_id
JOIN Customers ON Accounts.customer_id = Customers.customer_id
WHERE Accounts.account_id = 1;

# 8. Write a SQL query to Identify customers who have more than one account.
SELECT first_name, last_name, COUNT(account_id) AS account_count 
FROM Customers 
JOIN Accounts ON Customers.customer_id = Accounts.customer_id 
GROUP BY Customers.customer_id 
HAVING COUNT(account_id) > 1;

-- 9. Write a SQL query to Calculate the difference in transaction amounts between deposits and withdrawals.
SELECT 
    (SELECT SUM(amount) FROM Transactions WHERE transaction_type = 'deposit') - 
    (SELECT SUM(amount) FROM Transactions WHERE transaction_type = 'withdrawal') AS transaction_difference;


-- 10. Write a SQL query to Calculate the average daily balance for each account over a specified period.

SELECT account_id, AVG(amount) AS average_daily_balance 
FROM Transactions 
WHERE account_id = 1 
AND transaction_date BETWEEN '2024-01-01' AND '2024-02-01';


-- 11. Calculate the total balance for each account type.

SELECT account_type, SUM(balance) AS total_balance 
FROM Accounts 
GROUP BY account_type;

-- 12. Identify accounts with the highest number of transactions order by descending order.

SELECT account_id, COUNT(transaction_id) AS transaction_count 
FROM Transactions 
GROUP BY account_id 
ORDER BY transaction_count DESC;

-- 13. List customers with high aggregate account balances, along with their account types.


SELECT account_type, SUM(balance) AS total_balance
FROM Accounts
GROUP BY account_type
HAVING total_balance > 10000;


-- 14. Identify and list duplicate transactions based on transaction amount, date, and account.

SELECT account_id, amount, transaction_date, COUNT(*) AS duplicate_count 
FROM Transactions 
GROUP BY account_id, amount, transaction_date 
HAVING COUNT(*) > 1;


-- Tasks 4: Subquery and its type:
-- 1. Retrieve the customer(s) with the highest account balance.
SELECT customer_id, first_name, last_name 
FROM Customers 
WHERE customer_id = (
    SELECT customer_id 
    FROM Accounts 
    ORDER BY balance DESC 
    LIMIT 1
);

-- 2. Calculate the average account balance for customers who have more than one account.

SELECT AVG(balance) AS avg_balance 
FROM Accounts 
WHERE customer_id IN (
    SELECT customer_id 
    FROM Accounts 
    GROUP BY customer_id 
    HAVING COUNT(account_id) > 1
);

-- 3. Retrieve accounts with transactions whose amounts exceed the average transaction amount.
SELECT account_id, transaction_id, amount 
FROM Transactions 
WHERE amount > (
    SELECT AVG(amount) 
    FROM Transactions
);

-- 4. Identify customers who have no recorded transactions.
SELECT customer_id, first_name, last_name 
FROM Customers 
WHERE customer_id NOT IN (
    SELECT DISTINCT customer_id 
    FROM Accounts 
    JOIN Transactions ON Accounts.account_id = Transactions.account_id
);

-- 5. Calculate the total balance of accounts with no recorded transactions.
SELECT SUM(balance) AS total_balance 
FROM Accounts 
WHERE account_id NOT IN (
    SELECT account_id 
    FROM Transactions
);

-- 6. Retrieve transactions for accounts with the lowest balance.
SELECT transaction_id, account_id, amount, transaction_date 
FROM Transactions 
WHERE account_id = (
    SELECT account_id 
    FROM Accounts 
    ORDER BY balance ASC 
    LIMIT 1
);

-- 7. Identify customers who have accounts of multiple types.
SELECT customer_id, first_name, last_name 
FROM Customers 
WHERE customer_id IN (
    SELECT customer_id 
    FROM Accounts 
    GROUP BY customer_id 
    HAVING COUNT(DISTINCT account_type) > 1
);

-- 8. Calculate the percentage of each account type out of the total number of accounts. **
SELECT account_type, 
    (COUNT(*) * 100.0 / (SELECT COUNT(*) FROM Accounts)) AS percentage 
FROM Accounts 
GROUP BY account_type;

-- 9. Retrieve all transactions for a customer with a given customer_id.
SELECT transaction_id, account_id, amount, transaction_date 
FROM Transactions 
WHERE account_id IN (
    SELECT account_id 
    FROM Accounts 
    WHERE customer_id = 1  
);

-- 10. Calculate the total balance for each account type, including a subquery within the SELECT clause.**
SELECT account_type, 
    (SELECT SUM(balance) FROM Accounts AS A WHERE A.account_type = Accounts.account_type) AS total_balance 
FROM Accounts 
GROUP BY account_type;

