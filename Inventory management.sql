CREATE SCHEMA `Inventory`;



CREATE TABLE `Inventory`.`item_location` (
  `id` CHAR(36) NOT NULL,
  `location_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);



CREATE TABLE `Inventory`.`item_category` (
  `id` CHAR(36) NOT NULL,
  `category_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);


CREATE TABLE `Inventory`.`inventory` (
  `id` CHAR(36) NOT NULL,
  `item_name` VARCHAR(255) NOT NULL,
  `item_quantity` VARCHAR(255) NOT NULL,
  `item_category_id` CHAR(36) NOT NULL,
  `item_location_id` CHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `item_category_id_idx` (`item_category_id` ASC) VISIBLE,
  INDEX `item_location_id_idx` (`item_location_id` ASC) VISIBLE,
  CONSTRAINT `item_category_id`
    FOREIGN KEY (`item_category_id`)
    REFERENCES `Inventory`.`item_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `item_location_id`
    FOREIGN KEY (`item_location_id`)
    REFERENCES `Inventory`.`item_location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `Inventory`.`category_locations` (
  `category_id` CHAR(36) NOT NULL,
  `location_id` CHAR(36) NOT NULL,
  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,
  INDEX `location_id_idx` (`location_id` ASC) VISIBLE,
  CONSTRAINT `category_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `Inventory`.`item_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `location_id`
    FOREIGN KEY (`location_id`)
    REFERENCES `Inventory`.`item_location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `Inventory`.`inventory` 
DROP FOREIGN KEY `item_category_id`,
DROP FOREIGN KEY `item_location_id`;
ALTER TABLE `Inventory`.`inventory` 
ADD INDEX `item_location_id_idx` (`item_location_id` ASC) VISIBLE,
ADD INDEX `item_category_id_idx` (`item_category_id` ASC) VISIBLE,
DROP INDEX `item_location_id_idx` ,
DROP INDEX `item_category_id_idx` ;
;
ALTER TABLE `Inventory`.`inventory` 
ADD CONSTRAINT `item_category_id`
  FOREIGN KEY (`item_category_id`)
  REFERENCES `Inventory`.`category_locations` (`category_id`),
ADD CONSTRAINT `item_location_id`
  FOREIGN KEY (`item_location_id`)
  REFERENCES `Inventory`.`category_locations` (`location_id`);

CREATE INDEX inventory_index ON INVENTORY.INVENTORY(ID);
CREATE INDEX category_index ON INVENTORY.ITEM_CATEGORY(ID);
CREATE INDEX location_index ON INVENTORY.ITEM_LOCATION(ID);

-- Select all data (from all tables)
SELECT INVENTORY.ID, ITEM_NAME, ITEM_QUANTITY, ITEM_CATEGORY_ID, ITEM_CATEGORY.CATEGORY_NAME, ITEM_LOCATION_ID, ITEM_LOCATION.LOCATION_NAME
FROM INVENTORY.INVENTORY INNER JOIN INVENTORY.ITEM_CATEGORY
ON INVENTORY.INVENTORY.ITEM_CATEGORY_ID = INVENTORY.ITEM_CATEGORY.ID
INNER JOIN INVENTORY.ITEM_LOCATION
ON INVENTORY.INVENTORY.ITEM_LOCATION_ID = INVENTORY.ITEM_LOCATION.ID;

-- Where clause for location and category
WHERE INVENTORY.ITEM_CATEGORY_ID = ? AND INVENTORY.ITEM_LOCATION_ID = ?;


-- Select data table-wise.
SELECT * FROM INVENTORY.INVENTORY;
SELECT * FROM INVENTORY.ITEM_LOCATION;
SELECT * FROM INVENTORY.ITEM_CATEGORY;
SELECT * FROM INVENTORY.CATEGORY_LOCATIONS;


-- Select count of records table-wise.
SELECT COUNT(*) AS COUNT FROM INVENTORY.INVENTORY;
SELECT COUNT(*) AS COUNT FROM INVENTORY.ITEM_LOCATION;
SELECT COUNT(*) AS COUNT FROM INVENTORY.ITEM_CATEGORY;
SELECT COUNT(*) AS COUNT FROM INVENTORY.CATEGORY_LOCATIONS;