DROP TABLE enchantments, professions, customers, purchases, items, item_types; 

CREATE TABLE "enchantments" (
  "id" serial,
  "name" TEXT NOT NULL,
  "description" TEXT NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "professions" (
  "id" serial,
  "profession" TEXT UNIQUE NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "customers" (
  "id" serial,
  "first_name" TEXT NOT NULL,
  "last_name" TEXT NOT NULL,
  "profession_fk" int NOT NULL,
  PRIMARY KEY ("id"),
  CONSTRAINT "FK_customers.profession_fk"
    FOREIGN KEY ("profession_fk")
      REFERENCES "professions"("id")
);

CREATE TABLE "item_types" (
  "id" serial,
  "type" TEXT UNIQUE NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "items" (
  "id" serial,
  "name" TEXT NOT NULL,
  "description" TEXT NOT NULL,
  "price" decimal(10, 2) NOT NULL,
  "item_type_fk" int NOT NULL,
  "enchantment_fk" int,
  PRIMARY KEY ("id"),
  CONSTRAINT "FK_items.enchantment_fk"
    FOREIGN KEY ("enchantment_fk")
      REFERENCES "enchantments"("id"),
  CONSTRAINT "FK_items.item_type_fk"
    FOREIGN KEY ("item_type_fk")
      REFERENCES "item_types"("id")
);

CREATE TABLE "purchases" (
  "id" serial,
  "customer_fk" int NOT NULL,
  "item_fk" int NOT NULL,
  PRIMARY KEY ("id"),
  CONSTRAINT "FK_purchases.customer_fk"
    FOREIGN KEY ("customer_fk")
      REFERENCES "customers"("id"),
  CONSTRAINT "FK_purchases.item_fk"
    FOREIGN KEY ("item_fk")
      REFERENCES "items"("id")
);

--INSERT STATEMENTS------------------------------------------------------------
INSERT INTO professions (profession)
VALUES ('Wizard'),
       ('Cleric'),
       ('Adventurer'),
       ('Blacksmith'),
       ('Baker');
     
INSERT INTO item_types("type")
VALUES ('Weapon'),
       ('Armor'),
       ('Potion');
     
INSERT INTO enchantments ("name", description)
VALUES ('Flame','Empowers the item with the power of flame'),
       ('Ice', 'Imbues the item with frosty powers'),
       ('Luck', 'Maybe it''ll bring you luck?');
     
INSERT INTO items ("name", description, price, item_type_fk, enchantment_fk)
VALUES ('Long Sword', 'A slightly used long sword. It''s warm to the touch.', 250.0, 1, 1),
       ('Short Sword', 'Just a short sword', 100.0, 1, NULL),
       ('Boomerang', 'Seems dangerous...', 125.0, 1, 3),
       ('Health Potion', 'Cures what ails you.', 25.0, 3, NULL),
       ('Leather Bracers', 'Suitable forearm protection', 75.0, 2, NULL),
       ('Frost Shield', 'A shield with a cold aura.', 2000.0, 2, 2);
       
     
INSERT INTO customers (first_name, last_name, profession_fk)
VALUES ('Wiz', 'McWizington', 1),
       ('Biff', 'Sporg', 4);
     
UPDATE customers SET id = 1 WHERE first_name='Wiz';     
     
insert into customers (first_name, last_name, profession_fk) values ('Ernesta', 'Mosby', 1);
insert into customers (first_name, last_name, profession_fk) values ('Delbert', 'Yerby', 1);
insert into customers (first_name, last_name, profession_fk) values ('Diandra', 'Lerohan', 4);
insert into customers (first_name, last_name, profession_fk) values ('Retha', 'Florio', 3);
insert into customers (first_name, last_name, profession_fk) values ('Aurelie', 'Oldred', 5);
insert into customers (first_name, last_name, profession_fk) values ('Marabel', 'Franciskiewicz', 4);
insert into customers (first_name, last_name, profession_fk) values ('Taddeo', 'Ochiltree', 3);
insert into customers (first_name, last_name, profession_fk) values ('Reine', 'Outram', 1);
insert into customers (first_name, last_name, profession_fk) values ('Shanna', 'Sommerton', 3);
insert into customers (first_name, last_name, profession_fk) values ('Danika', 'Yurkiewicz', 3);
insert into customers (first_name, last_name, profession_fk) values ('Gino', 'Bushen', 1);
insert into customers (first_name, last_name, profession_fk) values ('Adam', 'Jursch', 2);
insert into customers (first_name, last_name, profession_fk) values ('Yalonda', 'Forman', 3);
insert into customers (first_name, last_name, profession_fk) values ('Rozele', 'Walcar', 3);
insert into customers (first_name, last_name, profession_fk) values ('Dorry', 'Blockwell', 4);
insert into customers (first_name, last_name, profession_fk) values ('Avril', 'McPhelim', 2);
insert into customers (first_name, last_name, profession_fk) values ('Cassie', 'Baunton', 1);
insert into customers (first_name, last_name, profession_fk) values ('Tod', 'Dungate', 1);
insert into customers (first_name, last_name, profession_fk) values ('Chicky', 'Faye', 4);
insert into customers (first_name, last_name, profession_fk) values ('Luke', 'Winson', 4);
insert into customers (first_name, last_name, profession_fk) values ('Norene', 'Alsop', 4);
insert into customers (first_name, last_name, profession_fk) values ('Philippe', 'Cotterel', 2);
insert into customers (first_name, last_name, profession_fk) values ('Aubine', 'Djurisic', 2);
insert into customers (first_name, last_name, profession_fk) values ('Valaria', 'Yard', 3);
insert into customers (first_name, last_name, profession_fk) values ('Nonah', 'Dye', 3);  
       
insert into purchases (customer_fk, item_fk) values (22, 4);
insert into purchases (customer_fk, item_fk) values (10, 4);
insert into purchases (customer_fk, item_fk) values (3, 6);
insert into purchases (customer_fk, item_fk) values (14, 2);
insert into purchases (customer_fk, item_fk) values (13, 2);
insert into purchases (customer_fk, item_fk) values (17, 3);
insert into purchases (customer_fk, item_fk) values (10, 5);
insert into purchases (customer_fk, item_fk) values (13, 4);
insert into purchases (customer_fk, item_fk) values (21, 2);
insert into purchases (customer_fk, item_fk) values (7, 3);
insert into purchases (customer_fk, item_fk) values (21, 3);
insert into purchases (customer_fk, item_fk) values (10, 6);
insert into purchases (customer_fk, item_fk) values (20, 3);
insert into purchases (customer_fk, item_fk) values (19, 5);
insert into purchases (customer_fk, item_fk) values (7, 2);
insert into purchases (customer_fk, item_fk) values (16, 6);
insert into purchases (customer_fk, item_fk) values (9, 3);
insert into purchases (customer_fk, item_fk) values (20, 5);
insert into purchases (customer_fk, item_fk) values (6, 4);
insert into purchases (customer_fk, item_fk) values (20, 2);
insert into purchases (customer_fk, item_fk) values (14, 3);
insert into purchases (customer_fk, item_fk) values (18, 4);
insert into purchases (customer_fk, item_fk) values (17, 6);
insert into purchases (customer_fk, item_fk) values (18, 2);
insert into purchases (customer_fk, item_fk) values (17, 5);
insert into purchases (customer_fk, item_fk) values (13, 3);
insert into purchases (customer_fk, item_fk) values (7, 3);
insert into purchases (customer_fk, item_fk) values (7, 3);
insert into purchases (customer_fk, item_fk) values (26, 1);
insert into purchases (customer_fk, item_fk) values (13, 1);
insert into purchases (customer_fk, item_fk) values (13, 3);
insert into purchases (customer_fk, item_fk) values (6, 6);
insert into purchases (customer_fk, item_fk) values (21, 1);
insert into purchases (customer_fk, item_fk) values (17, 3);
insert into purchases (customer_fk, item_fk) values (5, 6);
insert into purchases (customer_fk, item_fk) values (19, 5);
insert into purchases (customer_fk, item_fk) values (12, 2);
insert into purchases (customer_fk, item_fk) values (15, 5);
insert into purchases (customer_fk, item_fk) values (1, 4);
insert into purchases (customer_fk, item_fk) values (22, 5);
insert into purchases (customer_fk, item_fk) values (25, 1);
insert into purchases (customer_fk, item_fk) values (15, 1);
insert into purchases (customer_fk, item_fk) values (12, 3);
insert into purchases (customer_fk, item_fk) values (20, 1);
insert into purchases (customer_fk, item_fk) values (4, 2);
insert into purchases (customer_fk, item_fk) values (13, 4);
insert into purchases (customer_fk, item_fk) values (26, 2);
insert into purchases (customer_fk, item_fk) values (27, 4);
insert into purchases (customer_fk, item_fk) values (19, 6);
insert into purchases (customer_fk, item_fk) values (4, 3);
insert into purchases (customer_fk, item_fk) values (10, 2);
insert into purchases (customer_fk, item_fk) values (24, 4);
insert into purchases (customer_fk, item_fk) values (14, 4);
insert into purchases (customer_fk, item_fk) values (6, 6);
insert into purchases (customer_fk, item_fk) values (26, 2);
insert into purchases (customer_fk, item_fk) values (27, 6);
insert into purchases (customer_fk, item_fk) values (19, 5);
insert into purchases (customer_fk, item_fk) values (11, 3);
insert into purchases (customer_fk, item_fk) values (18, 3);
insert into purchases (customer_fk, item_fk) values (10, 3);
insert into purchases (customer_fk, item_fk) values (19, 3);
insert into purchases (customer_fk, item_fk) values (23, 4);
insert into purchases (customer_fk, item_fk) values (7, 3);
insert into purchases (customer_fk, item_fk) values (19, 2);
insert into purchases (customer_fk, item_fk) values (9, 5);
insert into purchases (customer_fk, item_fk) values (22, 3);
insert into purchases (customer_fk, item_fk) values (8, 3);
insert into purchases (customer_fk, item_fk) values (24, 2);
insert into purchases (customer_fk, item_fk) values (3, 4);
insert into purchases (customer_fk, item_fk) values (12, 1);
insert into purchases (customer_fk, item_fk) values (26, 5);
insert into purchases (customer_fk, item_fk) values (25, 4);
insert into purchases (customer_fk, item_fk) values (22, 6);
insert into purchases (customer_fk, item_fk) values (5, 6);
insert into purchases (customer_fk, item_fk) values (8, 6);
insert into purchases (customer_fk, item_fk) values (15, 1);
insert into purchases (customer_fk, item_fk) values (5, 3);
insert into purchases (customer_fk, item_fk) values (2, 1);
insert into purchases (customer_fk, item_fk) values (9, 3);
insert into purchases (customer_fk, item_fk) values (25, 2);
insert into purchases (customer_fk, item_fk) values (12, 3);
insert into purchases (customer_fk, item_fk) values (5, 6);
insert into purchases (customer_fk, item_fk) values (18, 2);
insert into purchases (customer_fk, item_fk) values (15, 1);
insert into purchases (customer_fk, item_fk) values (11, 6);
insert into purchases (customer_fk, item_fk) values (12, 2);
insert into purchases (customer_fk, item_fk) values (25, 2);
insert into purchases (customer_fk, item_fk) values (8, 1);
insert into purchases (customer_fk, item_fk) values (22, 2);
insert into purchases (customer_fk, item_fk) values (23, 6);
insert into purchases (customer_fk, item_fk) values (22, 3);
insert into purchases (customer_fk, item_fk) values (3, 5);
insert into purchases (customer_fk, item_fk) values (15, 5);
insert into purchases (customer_fk, item_fk) values (3, 6);
insert into purchases (customer_fk, item_fk) values (26, 3);
insert into purchases (customer_fk, item_fk) values (1, 3);
insert into purchases (customer_fk, item_fk) values (21, 2);
insert into purchases (customer_fk, item_fk) values (24, 2);
insert into purchases (customer_fk, item_fk) values (6, 2);
insert into purchases (customer_fk, item_fk) values (17, 1);

SELECT * FROM customers;