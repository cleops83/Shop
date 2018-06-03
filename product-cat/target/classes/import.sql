
DROP TABLE product;
DROP TABLE category;

CREATE TABLE category
( id integer NOT NULL PRIMARY KEY,
  name varchar(37),
  parent_id integer,
  FOREIGN KEY(parent_id) REFERENCES category(id)
);

create table product
( id integer NOT NULL PRIMARY KEY,
  name varchar(37),
  description varchar(250),
  currency varchar(37),
  price NUMERIC (10, 2)
  category_id integer,
  FOREIGN KEY(category_id) REFERENCES category(id)
);

insert into category(id, name, parent_id) values(1, 'HOME', NULL);
insert into category(id, name, parent_id) values(2, 'Category 1', 1);
insert into category(id, name, parent_id) values(3, 'Category 2',  1);
insert into category(id, name, parent_id) values(4, 'Sub-Category 1', 2);
insert into category(id, name, parent_id) values(5, 'Sub-Category 2', 3);

insert into PRODUCT(id,NAME,DESCRIPTION,CURRENCY,price, CATEGORY_ID)values(100,'Product 1', 'Product 1  Description', 2.5, 'USD', 4);
insert into PRODUCT(id,NAME,DESCRIPTION,CURRENCY,price, CATEGORY_ID)values(101,'Product 2', 'Product 2  Description', 10.5, 'EUR', 5);
