/**
 * Init DB script
 */

-- Create some brands

insert into brand (id, name) values (brand_seq.nextval, 'Audi');
insert into brand (id, name) values (brand_seq.nextval, 'BMW');
insert into brand (id, name) values (brand_seq.nextval, 'Mercedes');
insert into brand (id, name) values (brand_seq.nextval, 'Opel');
insert into brand (id, name) values (brand_seq.nextval, 'Porsche');
insert into brand (id, name) values (brand_seq.nextval, 'Volkswagen');

/**
 Attach some products to the brands
  - Audi        2 Products / 1 On Sale
  - BMW         2 Products / 1 On Sale
  - Mercedes    4 Products / 2 On Sale
  - Porsche     3 Products / 1 On Sale
  - Opel        0 Products
  - Volkswagen  4 Products / 3 On Sale

  TOTAL: 15 Products
 */

/**
  Audi
 */
insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Audi', 'TT', 10000.00, true);

insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Audi', '60', 500.00, false);

/**
  BMW
 */
insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'BMW', 'M8', 65000.25, false);

insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'BMW', 'F35', 30000.00, true);

/**
  Mercedes
 */
insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Mercedes', 'W201', 12000.25, false);

insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Mercedes', 'A-Class', 62000.25, true);

insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Mercedes', 'AMG GT', 68000.25, true);

insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Mercedes', 'L0 1114', 88000.55, false);

/**
  Porsche
 */
insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Porsche', '356', 150000.00, false);

insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Porsche', '912', 50000.00, false);

insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Porsche', 'Panamera', 88100.00, true);

/**
  Volkswagen
 */
insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Volkswagen', 'Beetle', 1000.00, false);

insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Volkswagen', 'Polo', 1500.00, true);

insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Volkswagen', 'Passat', 3502.15, true);

insert into product (id, brand_id, name, price, on_sale)
values (product_seq.nextval, select id from brand where name = 'Volkswagen', 'UP', 850.00, true);



