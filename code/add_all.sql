
--Create tables-------------------------------------------------------------------------------------------------------

DROP TABLE BD_2.COUNTRY CASCADE CONSTRAINTS;
DROP TABLE BD_2.CITY CASCADE CONSTRAINTS;
DROP TABLE BD_2.POSTAL_CODE CASCADE CONSTRAINTS;
DROP TABLE BD_2.ADDRESS CASCADE CONSTRAINTS;
DROP TABLE BD_2.PERSON CASCADE CONSTRAINTS;
DROP TABLE BD_2.PHONE;
DROP TABLE BD_2.ROLE_T CASCADE CONSTRAINTS;
DROP TABLE BD_2.ROLES_T;
DROP TABLE BD_2.COLOR CASCADE CONSTRAINTS;
DROP TABLE BD_2.PRODUCER CASCADE CONSTRAINTS;
DROP TABLE BD_2.PRODUCT_CATEGORY CASCADE CONSTRAINTS;
DROP TABLE BD_2.ABSTRACT_PRODUCT CASCADE CONSTRAINTS;
DROP TABLE BD_2.PRODUCT_SPECIMEN CASCADE CONSTRAINTS;
DROP TABLE BD_2.TRANSACTION_T CASCADE CONSTRAINTS;
DROP TABLE BD_2.ORDERED_PRODUCTS CASCADE CONSTRAINTS;
DROP TABLE BD_2.PROMOTION CASCADE CONSTRAINTS;

CREATE TABLE BD_2.COUNTRY(
	ID NUMBER(10) CONSTRAINT COUNTRY_ID_PK PRIMARY KEY,
	NAME VARCHAR2(56) NOT NULL
);

CREATE TABLE BD_2.CITY(
	ID NUMBER(10) CONSTRAINT CITY_ID_PK PRIMARY KEY,
	COUNTRY_ID NUMBER(10) NOT NULL,
	NAME VARCHAR2(20) NOT NULL,
	STATE_V VARCHAR2(20) NULL
);

CREATE TABLE BD_2.POSTAL_CODE(
	ID NUMBER(10) CONSTRAINT POSTAL_CODE_ID_PK PRIMARY KEY,
	CODE CHAR(6) NOT NULL
);

CREATE TABLE BD_2.ADDRESS(
	ID NUMBER(10) CONSTRAINT ADDRESS_ID_PK PRIMARY KEY,
	CITY_ID NUMBER(10) NOT NULL,
	POSTAL_CODE_ID NUMBER(10) NOT NULL,
	STREET_NUMBER VARCHAR2(10) NOT NULL,
	FLAT_NUMBER VARCHAR2(10),
	STREET_NAME VARCHAR2(100)
);

CREATE TABLE BD_2.PERSON(
	ID NUMBER(10) CONSTRAINT PERSON_ID_PK PRIMARY KEY,
	ADDRESS_ID NUMBER(10) NULL,
	FIRSTNAME VARCHAR2(100) NOT NULL,
	SURNAME VARCHAR2(100) NOT NULL,
	E_MAIL VARCHAR2(255) NOT NULL,
	USERNAME VARCHAR2(100) NOT NULL UNIQUE,
	PASSWORD CHAR(255) NOT NULL,
	CREATION_DATE DATE NOT NULL,
	HIRING_DATE DATE,
	EMPLOYEE_NUMBER NUMBER(10) UNIQUE,
	REGULAR_CUSTOMER NUMBER(1),
	CONSTRAINT CHECK_E_MAIL CHECK(E_MAIL LIKE '%_@_%._%'),
	CONSTRAINT CHECK_REGULAR_CUSTOMER CHECK(REGULAR_CUSTOMER IN (0,1))
);

CREATE TABLE BD_2.PHONE(
	ID NUMBER(10) CONSTRAINT PHONE_ID_PK PRIMARY KEY,
	PERSON_ID NUMBER(10) NOT NULL,
	PHONE_NUMBER VARCHAR2(20) NOT NULL,
	IS_EMPLOYEE_PHONE NUMBER(1) NOT NULL,
	CONSTRAINT CHECK_IS_EMPLOYEE_PHONE CHECK(IS_EMPLOYEE_PHONE IN (0,1))
);

CREATE TABLE BD_2.ROLE_T(
	ID NUMBER(10) CONSTRAINT ROLE_ID_PK PRIMARY KEY,
	NAME VARCHAR2(20) NOT NULL
);

CREATE TABLE BD_2.ROLES_T(
	PERSON_ID NUMBER(10) NOT NULL,
	ROLE_ID NUMBER(10) NOT NULL
);

CREATE TABLE BD_2.COLOR(
	ID NUMBER(10) CONSTRAINT COLOR_ID_PK PRIMARY KEY,
	NAME VARCHAR2(20) NOT NULL
);

CREATE TABLE BD_2.PRODUCER(
	ID NUMBER(10) CONSTRAINT PRODUCER_ID_PK PRIMARY KEY,
	NAME VARCHAR2(20) NOT NULL
);

CREATE TABLE BD_2.PRODUCT_CATEGORY(
	ID NUMBER(10) CONSTRAINT PRODUCT_CATEGORY_ID_PK PRIMARY KEY,
	NAME VARCHAR2(20) NOT NULL
);

CREATE TABLE BD_2.ABSTRACT_PRODUCT(
	ID NUMBER(10) CONSTRAINT ABSTRACT_PRODUCT_ID_PK PRIMARY KEY,
	PRODUCT_CATEGORY_ID NUMBER(10) NOT NULL,
	PRODUCER_ID NUMBER(10) NOT NULL,
    IMAGE BLOB NULL,
	PRICE NUMBER(8,2) NOT NULL,
	NAME VARCHAR2(20) NOT NULL,
	DESCRIPTION VARCHAR2(255) NOT NULL,
	WEIGHT NUMBER(5,3),
	HEIGHT NUMBER(5),
	WIDTH NUMBER(5),
	TAX_VALUE NUMBER(3) NOT NULL,
	CONSTRAINT CHECK_PRICE CHECK(PRICE > 0),
	CONSTRAINT CHECK_WEIGHT CHECK(WEIGHT > 0 OR WEIGHT IS NULL),
	CONSTRAINT CHECK_HEIGHT CHECK(HEIGHT > 0 OR HEIGHT IS NULL),
	CONSTRAINT CHECK_WIDTH CHECK(WIDTH > 0 OR WIDTH IS NULL),
	CONSTRAINT CHECK_TAX_VALUE CHECK(TAX_VALUE > 0 AND TAX_VALUE <= 100)
);

CREATE TABLE BD_2.PRODUCT_SPECIMEN(
	ID NUMBER(10) CONSTRAINT PRODUCT_SPECIMEN_ID_PK PRIMARY KEY,
	ABSTRACT_PRODUCT_ID NUMBER(10) NOT NULL,
	COLOR_ID NUMBER(10) NOT NULL,
	IMAGE BLOB NULL,
	PRODUCTION_DATE DATE,
	DESCRIPTION VARCHAR2(255),
	SERIAL_NUMBER NUMBER(10) NOT NULL UNIQUE,
	QUANTITY NUMBER(5) NOT NULL,
	CONSTRAINT CHECK_PRODUCT_QUANTITY CHECK(QUANTITY > 0)
);

CREATE TABLE BD_2.TRANSACTION_T(
	ID NUMBER(10) CONSTRAINT TRANSACTION_T_ID_PK PRIMARY KEY,
	ADDRESS_ID NUMBER(10) NOT NULL,
	CUSTOMER_ID NUMBER(10) NOT NULL,
	EMPLOYEE_ID NUMBER(10) NOT NULL,
	CREATION_DATE DATE NOT NULL,
	FINALIZED NUMBER(1),
	TRANSACTION_AMOUNT NUMBER(10, 2),
	CONSTRAINT CHECK_FINALIZED CHECK(FINALIZED IN (0,1)),
	CONSTRAINT CHECK_TRANSACTION_AMOUNT CHECK(TRANSACTION_AMOUNT > 0)
);

CREATE TABLE BD_2.ORDERED_PRODUCTS(
	TRANSACTION_ID NUMBER(10) NOT NULL,
	PRODUCT_SPECIMEN_ID NUMBER(10) NOT NULL,
	QUANTITY NUMBER(5) NOT NULL,
	PRICE_OF_ONE_PRODUCT NUMBER(8, 2) NOT NULL,
	CONSTRAINT CHECK_QUANTITY CHECK(QUANTITY > 0),
	CONSTRAINT CHECK_PRICE_OF_ONE_PRODUCT CHECK(PRICE_OF_ONE_PRODUCT > 0)
);


CREATE TABLE BD_2.PROMOTION(
	ID NUMBER(10) CONSTRAINT PROMOTION_ID_PK PRIMARY KEY,
	PRODUCT_CATEGORY_ID NUMBER(10),
	ABSTRACT_PRODUCT_ID NUMBER(10),
	PRODUCT_SPECIMEN_ID NUMBER(10),
	PERCENTAGE NUMBER(3) NOT NULL,
	START_DATE DATE,
	END_DATE DATE,
	CONSTRAINT CHECK_PERCENTAGE CHECK(PERCENTAGE > 0 AND PERCENTAGE <= 100)
);

--Add indexes----------------------------------------------------------------------------------------------------------

CREATE INDEX BD_2.transaction_creation_Date_done
ON BD_2.Transaction_t (creation_Date, finalized);

CREATE INDEX BD_2.product_quantity
ON BD_2.Product_Specimen (ABSTRACT_Product_Id, quantity);

CREATE INDEX BD_2.product_price_desc
ON BD_2.Abstract_Product (price DESC, Id);

CREATE INDEX BD_2.promotion_start_date
ON BD_2.PROMOTION (start_date DESC);

CREATE INDEX BD_2.promotion_end_date
ON BD_2.PROMOTION (end_date ASC);

CREATE INDEX BD_2.promotion_perc_end_start_date
ON BD_2.PROMOTION (percentage, end_Date, start_Date);

CREATE INDEX BD_2.product_name
ON BD_2.Abstract_Product (name);

CREATE INDEX BD_2.person_regular_Customer
ON BD_2.Person(regular_Customer);

CREATE INDEX BD_2.person_employee_Number
ON BD_2.Person (employee_Number);

CREATE INDEX BD_2.product_Producent_Id
ON BD_2.Abstract_Product (Producer_Id);

CREATE INDEX BD_2.product_production_Date
ON BD_2.Product_Specimen (production_Date);

CREATE INDEX BD_2.ordered_prod_Prod_Specimen_Id
ON BD_2.Ordered_Products (Product_Specimen_Id);

CREATE INDEX BD_2.loc_street_name_number_flat
ON BD_2.Address(street_Name, street_Number, flat_Number);

CREATE INDEX BD_2.phone__phone_number
ON BD_2.PHONE(PHONE_NUMBER);

--Add sequences--------------------------------------------------------------------------------------------------------

DROP SEQUENCE BD_2.COUNTRY_ID_SEQ;
DROP SEQUENCE BD_2.CITY_ID_SEQ;
DROP SEQUENCE BD_2.POSTAL_CODE_ID_SEQ;
DROP SEQUENCE BD_2.ADDRESS_ID_SEQ;
DROP SEQUENCE BD_2.PERSON_ID_SEQ;
DROP SEQUENCE BD_2.PHONE_ID_SEQ;
DROP SEQUENCE BD_2.ROLE_T_ID_SEQ;
DROP SEQUENCE BD_2.COLOR_ID_SEQ;
DROP SEQUENCE BD_2.PRODUCER_ID_SEQ;
DROP SEQUENCE BD_2.PRODUCT_CATEGORY_ID_SEQ;
DROP SEQUENCE BD_2.ABSTRACT_PRODUCT_ID_SEQ;
DROP SEQUENCE BD_2.PRODUCT_SPECIMEN_ID_SEQ;
DROP SEQUENCE BD_2.TRANSACTION_T_ID_SEQ;
DROP SEQUENCE BD_2.PROMOTION_ID_SEQ;

CREATE SEQUENCE BD_2.COUNTRY_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.CITY_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.POSTAL_CODE_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.ADDRESS_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.PERSON_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.PHONE_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.ROLE_T_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.COLOR_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.PRODUCER_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.PRODUCT_CATEGORY_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.ABSTRACT_PRODUCT_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.PRODUCT_SPECIMEN_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.TRANSACTION_T_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

CREATE SEQUENCE BD_2.PROMOTION_ID_SEQ
MINVALUE 1
INCREMENT BY 1;

--Add triggers--------------------------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER BD_2.COUNTRY_ID_T
BEFORE INSERT ON BD_2.COUNTRY
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := COUNTRY_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.CITY_ID_T
BEFORE INSERT ON BD_2.CITY
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := CITY_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.POSTAL_CODE_ID_T
BEFORE INSERT ON BD_2.POSTAL_CODE
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := POSTAL_CODE_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.ADDRESS_ID_T
BEFORE INSERT ON BD_2.ADDRESS
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := ADDRESS_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.PERSON_ID_T
BEFORE INSERT ON BD_2.Person
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := PERSON_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.PHONE_ID_T
BEFORE INSERT ON BD_2.PHONE
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := PHONE_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.ROLE_T_ID_T
BEFORE INSERT ON BD_2.ROLE_T
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := ROLE_T_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.COLOR_ID_T
BEFORE INSERT ON BD_2.COLOR
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := COLOR_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.PRODUCER_ID_T
BEFORE INSERT ON BD_2.PRODUCER
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := PRODUCER_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.PRODUCT_CATEGORY_ID_T
BEFORE INSERT ON BD_2.PRODUCT_CATEGORY
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := PRODUCT_CATEGORY_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.ABSTRACT_PRODUCT_ID_T
BEFORE INSERT ON BD_2.ABSTRACT_PRODUCT
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := ABSTRACT_PRODUCT_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.PRODUCT_SPECIMEN_T
BEFORE INSERT ON BD_2.PRODUCT_SPECIMEN
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := PRODUCT_SPECIMEN_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.TRANSACTION_T_ID_SEQ
BEFORE INSERT ON BD_2.TRANSACTION_T
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := TRANSACTION_T_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER BD_2.PROMOTION_ID_T
BEFORE INSERT ON BD_2.PROMOTION
FOR EACH ROW
    WHEN (NEW.Id is NULL)
BEGIN
    :NEW.Id := PROMOTION_ID_SEQ.NEXTVAL;
END;
/
CREATE OR REPLACE TRIGGER INSERT_PROMOTION
    BEFORE INSERT ON BD_2.PROMOTION
    FOR EACH ROW
BEGIN
    IF(:new.PRODUCT_CATEGORY_ID IS NULL AND 
	   :new.ABSTRACT_PRODUCT_ID IS NULL AND 
	   :new.PRODUCT_SPECIMEN_ID IS NULL)
	THEN
		 RAISE_APPLICATION_ERROR( -20000, 'Promocja musi mieć kategorię' );
	END IF;
END;
/

--Add forgein keys----------------------------------------------------------------------------------------------

ALTER TABLE
    BD_2.PERSON
ADD
    CONSTRAINT PERSON_ADDRESS_FK FOREIGN KEY(ADDRESS_ID) 
		REFERENCES BD_2.ADDRESS(ID);

ALTER TABLE
	BD_2.PHONE
ADD
	CONSTRAINT PHONE_PERSON_FK FOREIGN KEY(PERSON_ID) 
		REFERENCES BD_2.PERSON(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.ROLES_T
ADD
	CONSTRAINT ROLE_T_PERSON_FK FOREIGN KEY(PERSON_ID) 
		REFERENCES BD_2.PERSON(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.ROLES_T
ADD
	CONSTRAINT ROLES_T_ROLE_FK FOREIGN KEY(ROLE_ID) 
		REFERENCES BD_2.ROLE_T(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.CITY
ADD
	CONSTRAINT CITY_COUNTRY_FK FOREIGN KEY(COUNTRY_ID) 
		REFERENCES BD_2.COUNTRY(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.ADDRESS
ADD
	CONSTRAINT ADDRESS_CITY_FK FOREIGN KEY(CITY_ID) 
		REFERENCES BD_2.CITY(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.ADDRESS
ADD
	CONSTRAINT ADDRESS_POSTAL_CODE_FK FOREIGN KEY(POSTAL_CODE_ID) 
		REFERENCES BD_2.POSTAL_CODE(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.ORDERED_PRODUCTS
ADD
	CONSTRAINT ORDERED_PROD_TRANSACTION_T_FK FOREIGN KEY(TRANSACTION_ID) 
		REFERENCES BD_2.TRANSACTION_T(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.ORDERED_PRODUCTS
ADD
	CONSTRAINT ORDERED_PROD_PROD_SPECIMEN_FK FOREIGN KEY(PRODUCT_SPECIMEN_ID) 
		REFERENCES BD_2.PRODUCT_SPECIMEN(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.TRANSACTION_T
ADD
	CONSTRAINT TRANSACTION_T_ADDRESS_FK FOREIGN KEY(ADDRESS_ID) 
		REFERENCES BD_2.ADDRESS(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.TRANSACTION_T
ADD
	CONSTRAINT TRANSACTION_T_CUSTOMER_FK FOREIGN KEY(CUSTOMER_ID) 
		REFERENCES BD_2.PERSON(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.TRANSACTION_T
ADD
	CONSTRAINT TRANSACTION_T_EMPLOYEE_FK FOREIGN KEY(EMPLOYEE_ID) 
		REFERENCES BD_2.PERSON(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.ABSTRACT_PRODUCT
ADD
	CONSTRAINT ABSTRACT_PROD_PRODUCT_CAT_FK FOREIGN KEY(PRODUCT_CATEGORY_ID) 
		REFERENCES BD_2.PRODUCT_CATEGORY(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.ABSTRACT_PRODUCT
ADD
	CONSTRAINT ABSTRACT_PROD_PRODUCER_FK FOREIGN KEY(PRODUCER_ID) 
		REFERENCES BD_2.PRODUCER(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.PRODUCT_SPECIMEN
ADD
	CONSTRAINT PRODUCT_SPEC_ASBTRACT_PROD_FK FOREIGN KEY(ABSTRACT_PRODUCT_ID) 
		REFERENCES BD_2.ABSTRACT_PRODUCT(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.PRODUCT_SPECIMEN
ADD
	CONSTRAINT PRODUCT_SPECIMEN_COLOR_FK FOREIGN KEY(COLOR_ID) 
		REFERENCES BD_2.COLOR(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.PROMOTION
ADD
	CONSTRAINT PROMOTION_PRODUCT_CATEGORY_FK FOREIGN KEY(PRODUCT_CATEGORY_ID) 
		REFERENCES BD_2.PRODUCT_CATEGORY(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.PROMOTION
ADD
	CONSTRAINT PROMOTION_ABSTRACT_PRODUCT_FK FOREIGN KEY(ABSTRACT_PRODUCT_ID) 
		REFERENCES BD_2.ABSTRACT_PRODUCT(ID) ON DELETE CASCADE;

ALTER TABLE
	BD_2.PROMOTION
ADD
	CONSTRAINT PROMOTION_PRODUCT_SPECIMEN_FK FOREIGN KEY(PRODUCT_SPECIMEN_ID) 
		REFERENCES BD_2.PRODUCT_SPECIMEN(ID) ON DELETE CASCADE;


--Add roles and privilages------------------------------------------------------------------------------------------------

DROP ROLE ADMINISTRATOR;
DROP ROLE EMPLOYEE;
DROP ROLE CUSTOMER;
DROP ROLE NOT_LOGGED_IN;

CREATE ROLE ADMINISTRATOR;
CREATE ROLE EMPLOYEE;
CREATE ROLE CUSTOMER;
CREATE ROLE NOT_LOGGED_IN;

GRANT ALL ON BD_2.PERSON
	TO ADMINISTRATOR;

GRANT SELECT, INSERT, UPDATE ON BD_2.PERSON
	TO EMPLOYEE, CUSTOMER;

GRANT ALL ON BD_2.ROLE_T
	TO ADMINISTRATOR;

GRANT SELECT ON BD_2.ROLE_T
	TO EMPLOYEE, CUSTOMER, NOT_LOGGED_IN;

GRANT ALL ON BD_2.ROLES_T
	TO ADMINISTRATOR;

GRANT SELECT ON BD_2.ROLES_T
	TO EMPLOYEE, CUSTOMER, NOT_LOGGED_IN;

GRANT ALL ON BD_2.COUNTRY
	TO ADMINISTRATOR;

GRANT SELECT ON BD_2.COUNTRY
	TO EMPLOYEE, CUSTOMER;

GRANT ALL ON BD_2.CITY
	TO ADMINISTRATOR;

GRANT SELECT, INSERT ON BD_2.CITY
	TO EMPLOYEE, CUSTOMER;

GRANT ALL ON BD_2.POSTAL_CODE
	TO ADMINISTRATOR;

GRANT SELECT, INSERT ON BD_2.POSTAL_CODE
	TO EMPLOYEE, CUSTOMER;

GRANT ALL ON BD_2.ADDRESS
	TO ADMINISTRATOR, EMPLOYEE, CUSTOMER;

GRANT SELECT ON BD_2.ORDERED_PRODUCTS
	TO ADMINISTRATOR, EMPLOYEE, CUSTOMER;

GRANT SELECT ON BD_2.TRANSACTION_T
	TO ADMINISTRATOR, CUSTOMER;

GRANT SELECT, INSERT, UPDATE ON BD_2.TRANSACTION_T
	TO EMPLOYEE;

GRANT SELECT ON BD_2.ABSTRACT_PRODUCT
	TO ADMINISTRATOR, CUSTOMER, NOT_LOGGED_IN;

GRANT ALL ON BD_2.ABSTRACT_PRODUCT
	TO EMPLOYEE;

GRANT SELECT ON BD_2.PRODUCT_SPECIMEN
	TO ADMINISTRATOR, CUSTOMER, NOT_LOGGED_IN;

GRANT ALL ON BD_2.PRODUCT_SPECIMEN
	TO EMPLOYEE;

GRANT SELECT ON BD_2.PRODUCER
	TO ADMINISTRATOR, CUSTOMER, NOT_LOGGED_IN;

GRANT ALL ON BD_2.PRODUCER
	TO EMPLOYEE;

GRANT SELECT ON BD_2.PRODUCT_CATEGORY
	TO ADMINISTRATOR, CUSTOMER, NOT_LOGGED_IN;

GRANT ALL ON BD_2.PRODUCT_CATEGORY
	TO EMPLOYEE;

GRANT SELECT ON BD_2.PROMOTION
	TO ADMINISTRATOR, CUSTOMER, NOT_LOGGED_IN;

GRANT ALL ON BD_2.PROMOTION
	TO EMPLOYEE;

GRANT ALL ON BD_2.COLOR
	TO ADMINISTRATOR, EMPLOYEE;

GRANT SELECT ON BD_2.COLOR
	TO CUSTOMER, NOT_LOGGED_IN;

GRANT ALL ON BD_2.PHONE
	TO ADMINISTRATOR, EMPLOYEE, CUSTOMER;

GRANT CREATE SESSION TO ADMINISTRATOR;
GRANT CREATE SESSION TO EMPLOYEE;
GRANT CREATE SESSION TO CUSTOMER;
GRANT CREATE SESSION TO NOT_LOGGED_IN;

GRANT EXECUTE ON BD_2.INSERT_EMPLOYEE TO EMPLOYEE;
GRANT EXECUTE ON BD_2.INSERT_PHONE_NUMBER TO EMPLOYEE;
GRANT EXECUTE ON BD_2.INSERT_ABSTRACT_PRODUCT TO EMPLOYEE;

DROP USER ADMINISTRATOR_U;
DROP USER EMPLOYEE_U;
DROP USER CUSTOMER_U;
DROP USER NOT_LOGGED_IN_U;


CREATE USER ADMINISTRATOR_U IDENTIFIED BY a_pass;
GRANT ADMINISTRATOR TO ADMINISTRATOR_U;

CREATE USER EMPLOYEE_U IDENTIFIED BY e_pass;
GRANT EMPLOYEE TO EMPLOYEE_U;

CREATE USER CUSTOMER_U IDENTIFIED BY c_pass;
GRANT CUSTOMER TO CUSTOMER_U;

CREATE USER NOT_LOGGED_IN_U IDENTIFIED BY n_l_pass;
GRANT NOT_LOGGED_IN TO NOT_LOGGED_IN_U;

--Add views-------------------------------------------------------------------------------------------------------------------

DROP VIEW BD_2.PRODUCTS_WITH_PRICE_LESS_100;
DROP VIEW BD_2.ALL_ORDERED_PRODUCTS;
DROP VIEW BD_2.PRODUCTS_ON_PROMOTIONS;
DROP VIEW BD_2.Best_Selling_Products;
DROP VIEW BD_2.finalized_Orders;
DROP VIEW BD_2.Person_Numbers;

CREATE VIEW BD_2.PRODUCTS_WITH_PRICE_LESS_100 AS
SELECT
	PRODUCER.name Producer,
	P_C.name "Product category",
	A_P.name "Abstract Product",
	A_P.price,
	COLOR.name "Color",
	P_S.quantity,
	P_S.PRODUCTION_DATE
FROM BD_2.ABSTRACT_PRODUCT A_P
INNER JOIN BD_2.PRODUCT_SPECIMEN P_S ON A_P.Id = P_S.ABSTRACT_PRODUCT_ID 
INNER JOIN BD_2.PRODUCT_CATEGORY P_C ON A_P.PRODUCT_CATEGORY_ID = P_C.Id 
INNER JOIN BD_2.PRODUCER ON A_P.PRODUCER_ID = PRODUCER.Id 
INNER JOIN BD_2.COLOR ON P_S.COLOR_ID = COLOR.Id
WHERE A_P.price < 100
ORDER BY A_P.price DESC;

CREATE VIEW BD_2.ALL_ORDERED_PRODUCTS AS
SELECT
	O_P.quantity,
	O_P.price_Of_One_Product,
	Color.name "Color name",
	t.finalized,
	t.transaction_Amount,
	t.creation_Date,
	p_s.production_Date,
	a_p.name "Abstract product name",
	Producer.name "Producer name",
	p_c.name "Product category name"
FROM BD_2.ORDERED_PRODUCTS O_P
INNER JOIN BD_2.TRANSACTION_T t ON O_P.Transaction_Id = t.Id 
INNER JOIN BD_2.PRODUCT_SPECIMEN p_s ON O_P.Product_Specimen_Id = p_s.Id 
INNER JOIN BD_2.Color ON p_s.Color_Id = Color.Id 
INNER JOIN BD_2.ABSTRACT_PRODUCT a_p ON p_s.Abstract_Product_Id = a_p.Id 
INNER JOIN BD_2.Producer ON a_p.Producer_Id = Producer.Id 
INNER JOIN BD_2.PRODUCT_CATEGORY p_c ON a_p.Product_Category_Id = p_c.Id;

CREATE VIEW BD_2.PRODUCTS_ON_PROMOTIONS AS
SELECT
	Color.name,
	Producer.name "Producer",
	a_p.name "Abstract product name",
	a_p.price,
	p.Product_Category_Id,
	p.Abstract_Product_Id,
	p.Product_Specimen_Id,
	p.start_Date,
	p.end_Date,
	p.percentage,
	p_s.quantity,
	p_s.production_Date,
	a_p.weight,
	a_p.height,
	a_p.width,
	p_c.name AS name4
FROM BD_2.Promotion p 
INNER JOIN BD_2.Product_Category p_c ON p.Product_Category_Id = p_c.Id 
INNER JOIN BD_2.Abstract_Product a_p ON p.Abstract_Product_Id = a_p.Id AND p_c.Id = a_p.Product_Category_Id 
INNER JOIN BD_2.Product_Specimen p_s ON p.Product_Specimen_Id = p_s.Id AND a_p.Id = p_s.Abstract_Product_Id 
INNER JOIN BD_2.Producer ON a_p.Producer_Id = Producer.Id 
INNER JOIN BD_2.Color ON p_s.Color_Id = Color.Id
ORDER BY p.percentage DESC;

CREATE VIEW BD_2.Best_Selling_Products AS
SELECT
	p_s.production_Date,
	Color.name "Color",
	Producer.name "Producer",
	a_p.name AS "Abstract product name",
	o_p.price_Of_One_Product,
	SUM(o_p.quantity) sold
FROM BD_2.Ordered_Products o_p
INNER JOIN BD_2.Product_Specimen p_s ON p_s.Id = o_p.Product_Specimen_Id 
INNER JOIN BD_2.Abstract_Product a_p ON p_s.Abstract_Product_Id = a_p.Id 
INNER JOIN BD_2.Color ON p_s.Color_Id = Color.Id 
INNER JOIN BD_2.Producer ON a_p.Producer_Id = Producer.Id 
GROUP BY o_p.quantity, p_s.production_Date, Color.name, Producer.name, a_p.name, o_p.price_Of_One_Product
ORDER BY o_p.quantity DESC;

CREATE VIEW BD_2.finalized_Orders AS
SELECT
	t.transaction_Amount,
	t.creation_Date,
	p.firstname,
	p.surname,
	p.Id,
	p.regular_Customer,
	p.e_mail
FROM BD_2.Transaction_t t
INNER JOIN BD_2.Person p ON t.Customer_Id = p.Id AND t.Employee_Id = p.Id		
WHERE p.employee_Number IS NULL;

CREATE VIEW BD_2.Person_Numbers AS
SELECT
	Phone.phone_Number,
	Person.firstname,
	Person.surname,
	Person.Id,
	Person.regular_Customer,
	Person.employee_Number,
	Person.creation_Date
FROM BD_2.Person 
INNER JOIN BD_2.Phone ON Person.Id = Phone.Person_Id;

--Add stored procedures------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE BD_2.INSERT_TRANSACTION (creation_Date DATE, 
	Customer_Id NUMBER, Employee_Id NUMBER, Address_Id NUMBER, transaction_Amount NUMBER)
AS
BEGIN
    INSERT INTO BD_2.TRANSACTION_t (Address_Id, Customer_Id, Employee_Id, creation_Date, finalized, transaction_Amount)
    VALUES(Address_Id, Customer_Id, Employee_Id, creation_Date, 0, transaction_Amount);
END;
/
CREATE OR REPLACE PROCEDURE BD_2.INSERT_PHONE_NUMBER (PERSON_ID NUMBER, 
	PHONE_NUMBER VARCHAR, IS_EMPLOYEE_PHONE NUMBER)
AS
BEGIN
    INSERT INTO BD_2.PHONE(PERSON_ID, PHONE_NUMBER, IS_EMPLOYEE_PHONE)
    VALUES(PERSON_ID, PHONE_NUMBER, IS_EMPLOYEE_PHONE);
END;
/
CREATE OR REPLACE PROCEDURE BD_2.INSERT_EMPLOYEE 
(
	FIRSTNAME VARCHAR2, SURNAME VARCHAR2, E_MAIL VARCHAR2, USERNAME VARCHAR2, PASSWORD CHAR, 
	HIRING_DATE DATE, EMPLOYEE_NUMBER NUMBER
)
AS
BEGIN
    INSERT INTO BD_2.PERSON(FIRSTNAME, SURNAME, E_MAIL, USERNAME, PASSWORD, CREATION_DATE,
		HIRING_DATE, EMPLOYEE_NUMBER)
    VALUES(FIRSTNAME, SURNAME, E_MAIL, USERNAME, PASSWORD, SYSDATE, HIRING_DATE, EMPLOYEE_NUMBER);
END;
/
CREATE OR REPLACE PROCEDURE BD_2.INSERT_ABSTRACT_PRODUCT
(
	PRODUCT_CATEGORY_ID NUMBER, PRODUCER_ID NUMBER, IMAGE BLOB,
    PRICE NUMBER, NAME VARCHAR, DESCRIPTION VARCHAR, WEIGHT NUMBER, HEIGHT
    NUMBER, WIDTH NUMBER, TAX_VALUE NUMBER
)
AS
BEGIN
    INSERT INTO BD_2.ABSTRACT_PRODUCT(PRODUCT_CATEGORY_ID, PRODUCER_ID,
        IMAGE, PRICE, NAME, DESCRIPTION, WEIGHT, HEIGHT, WIDTH, TAX_VALUE)
    VALUES(PRODUCT_CATEGORY_ID, PRODUCER_ID,
        IMAGE, PRICE, NAME, DESCRIPTION, WEIGHT, HEIGHT, WIDTH, TAX_VALUE);
END;
/
CREATE OR REPLACE PROCEDURE BD_2.INSERT_ORDERED_PRODUCTS (Transaction_Id NUMBER, 
	Product_Specimen_Id NUMBER, quantity NUMBER)
AS
BEGIN
    INSERT INTO BD_2.ORDERED_PRODUCTS(Transaction_Id, Product_Specimen_Id, quantity)
    VALUES(Transaction_Id, Product_Specimen_Id, quantity);
END;
/
CREATE OR REPLACE PROCEDURE BD_2.NUMBER_OF_ORDERED_PRODUCTS (Transaction_Id NUMBER, sum_Of_products OUT NUMBER)
AS
BEGIN
    SELECT  COUNT(*) "Sum of diffrent products"
    INTO sum_Of_products
    FROM BD_2.Ordered_Products o
    WHERE o.Transaction_Id = Transaction_Id;
END;
/
CREATE OR REPLACE PROCEDURE BD_2.PROMOTION_DAYS_LEFT (PROMOTION_ID NUMBER, DAYS_LEFT OUT NUMBER)
AS
BEGIN
    SELECT trunc(start_date,'day') - trunc(end_date,'day') "Days left"
    INTO DAYS_LEFT
    FROM BD_2.PROMOTION p
    WHERE p.ID = PROMOTION_ID;
END;
/

--Generate tables data-------------------------------------------------------------------------------------

INSERT INTO BD_2.ROLE_T(NAME)
VALUES('ADMINISTRATOR');

INSERT INTO BD_2.ROLE_T(NAME)
VALUES('EMPLOYEE');

INSERT INTO BD_2.ROLE_T(NAME)
VALUES('CUSTOMER');

INSERT INTO BD_2.ROLE_T(NAME)
VALUES('NOT_LOGGED_IN');


INSERT INTO BD_2.PRODUCT_CATEGORY(NAME)
VALUES('peryferia');

INSERT INTO BD_2.PRODUCT_CATEGORY(NAME)
VALUES('tablety');

INSERT INTO BD_2.PRODUCT_CATEGORY(NAME)
VALUES('komputery');

INSERT INTO BD_2.PRODUCT_CATEGORY(NAME)
VALUES('laptopy');

INSERT INTO BD_2.PRODUCT_CATEGORY(NAME)
VALUES('karty graficzne');


INSERT INTO BD_2.COLOR(NAME)
VALUES('GREEN');

INSERT INTO BD_2.COLOR(NAME)
VALUES('SILVER');

INSERT INTO BD_2.COLOR(NAME)
VALUES('GOLD');

INSERT INTO BD_2.COLOR(NAME)
VALUES('BLUE');


INSERT INTO BD_2.PRODUCER(NAME)
VALUES('SAMSUNG');

INSERT INTO BD_2.PRODUCER(NAME)
VALUES('STEELSERIES');

INSERT INTO BD_2.PRODUCER(NAME)
VALUES('GENESIS');

INSERT INTO BD_2.PRODUCER(NAME)
VALUES('RAZER');

INSERT INTO BD_2.PRODUCER(NAME)
VALUES('LOGITECH');