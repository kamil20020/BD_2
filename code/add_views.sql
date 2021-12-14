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




