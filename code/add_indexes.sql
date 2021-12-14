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
