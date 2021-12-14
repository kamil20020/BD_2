CREATE OR REPLACE PROCEDURE BD_2.INSERT_TRANSACTION (creation_Date DATE, 
	Customer_Id NUMBER, Employee_Id NUMBER, Address_Id NUMBER, transaction_Amount NUMBER)
AS
BEGIN
    INSERT INTO BD_2.TRANSACTION_t (Address_Id, Customer_Id, Employee_Id, creation_Date, finalized, transaction_Amount)
    VALUES(Address_Id, Customer_Id, Employee_Id, creation_Date, 0, transaction_Amount);
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
