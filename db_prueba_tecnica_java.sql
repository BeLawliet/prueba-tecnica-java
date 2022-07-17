Create database `db_prueba_tecnica_java`;

Use `db_prueba_tecnica_java`;

Create table `tbl_categories` (
	`category_id` int auto_increment not null,
    `category_name` varchar(50) not null,
    Primary key (`category_id`)
);

Insert into `tbl_categories` (`category_name`) values ('Cafés regionales'), ("Cafés exóticos"), ("Cafés de finca"), ("Cafés selectos"), ("Cafés Caracol"), ("Cafés supremos");

Create table `tbl_products` (
	`product_id` int auto_increment not null,
    `product_name` varchar(50) not null,
    `reference` varchar(50) not null,
    `price` int not null,
    `weight` int not null,
    `stock` int not null,
    `creation_date` date,
    `categories_id` int not null,
    Constraint `products_categories_idx` Foreign key (`categories_id`) References `tbl_categories` (`category_id`) ON UPDATE CASCADE ON DELETE CASCADE,
    Primary key (`product_id`)
);

Create table `tbl_sales` (
	`sale_id` int auto_increment not null,
    `product_id` int not null,
    `sale_amount` int not null,
    `sale_date` date not null,
    Key `product_id_idx` (`product_id`),
    Constraint `sales_product_fk` Foreign key (`product_id`) References `tbl_products` (`product_id`) ON UPDATE CASCADE ON DELETE CASCADE,
    Primary key (`sale_id`)
);


-- ========== 2° PUNTO ===========

-- PRODUCTO CON MAYOR STOCK
-- SELECT product_id AS 'ID', product_name AS 'NOMBRE', reference AS 'REFERENCIA', price AS 'PRECIO', weight AS 'PESO', stock AS 'STOCK', creation_date AS 'FECHA DE CREACION', categories_id AS 'ID CATEGORIA' FROM `tbl_products` WHERE stock = (SELECT MAX(stock) FROM `tbl_products`);

-- PRODUCTO MAS VENDIDO
-- SELECT product_id AS 'ID', product_name AS 'NOMBRE', reference AS 'REFERENCIA', price AS 'PRECIO', weight AS 'PESO', stock AS 'STOCK', creation_date AS 'FECHA DE CREACION', categories_id AS 'ID CATEGORIA', count(s.product_id) as 'CANTIDAD' FROM `tbl_sales` AS s INNER JOIN `tbl_products` USING(product_id) GROUP BY s.product_id HAVING `CANTIDAD` = (SELECT COUNT(*) from tbl_sales GROUP BY product_id ORDER BY COUNT(*) DESC LIMIT 1);