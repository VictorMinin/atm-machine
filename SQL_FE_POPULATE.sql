-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema atm_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema atm_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `atm_db` DEFAULT CHARACTER SET utf8 ;
USE `atm_db` ;

-- -----------------------------------------------------
-- Table `atm_db`.`user_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`user_types` (
  `user_type_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_type_id`),
  UNIQUE INDEX `user_type_id_UNIQUE` (`user_type_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atm_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`users` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user_type_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  INDEX `fk_users_user_types1_idx` (`user_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_user_types1`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `atm_db`.`user_types` (`user_type_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atm_db`.`card_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`card_types` (
  `card_type_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`card_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atm_db`.`card_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`card_statuses` (
  `card_status_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`card_status_id`),
  UNIQUE INDEX `idcard_type_id_UNIQUE` (`card_status_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atm_db`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`accounts` (
  `account_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `credit_max` DOUBLE NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC) VISIBLE,
  INDEX `fk_accounts_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_accounts_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `atm_db`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atm_db`.`cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`cards` (
  `card_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `card_number` VARCHAR(45) NOT NULL,
  `pin_number` VARCHAR(45) NOT NULL,
  `expiration_date` VARCHAR(45) NOT NULL,
  `cvc` VARCHAR(45) NOT NULL,
  `card_type_id` INT UNSIGNED NOT NULL,
  `card_status_id` INT UNSIGNED NOT NULL,
  `account_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`card_id`),
  UNIQUE INDEX `card_id_UNIQUE` (`card_id` ASC) VISIBLE,
  UNIQUE INDEX `card_number_UNIQUE` (`card_number` ASC) VISIBLE,
  INDEX `fk_cards_card_types1_idx` (`card_type_id` ASC) VISIBLE,
  INDEX `fk_cards_card_statuses1_idx` (`card_status_id` ASC) VISIBLE,
  INDEX `fk_cards_accounts1_idx` (`account_id` ASC) VISIBLE,
  UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_cards_card_types1`
    FOREIGN KEY (`card_type_id`)
    REFERENCES `atm_db`.`card_types` (`card_type_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cards_card_statuses1`
    FOREIGN KEY (`card_status_id`)
    REFERENCES `atm_db`.`card_statuses` (`card_status_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cards_accounts1`
    FOREIGN KEY (`account_id`)
    REFERENCES `atm_db`.`accounts` (`account_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atm_db`.`transaction_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`transaction_types` (
  `transaction_type_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`transaction_type_id`),
  UNIQUE INDEX `transaction_type_id_UNIQUE` (`transaction_type_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atm_db`.`transaction_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`transaction_statuses` (
  `transaction_status_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`transaction_status_id`),
  UNIQUE INDEX `transaction_status_id_UNIQUE` (`transaction_status_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atm_db`.`event_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`event_types` (
  `event_type_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `event_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`event_type_id`),
  UNIQUE INDEX `event_type_id_UNIQUE` (`event_type_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atm_db`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`events` (
  `event_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date` TIMESTAMP NOT NULL,
  `event_type_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE INDEX `event_id_UNIQUE` (`event_id` ASC) VISIBLE,
  INDEX `fk_events_event_types1_idx` (`event_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_events_event_types1`
    FOREIGN KEY (`event_type_id`)
    REFERENCES `atm_db`.`event_types` (`event_type_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atm_db`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`transactions` (
  `transaction_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `transaction_status_id` INT UNSIGNED NOT NULL,
  `transaction_type_id` INT UNSIGNED NOT NULL,
  `sender_card_id` INT UNSIGNED NOT NULL,
  `recipient_card_id` INT UNSIGNED NULL,
  `amount` DOUBLE NOT NULL,
  `event_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `fk_transactions_transaction_statuses1_idx` (`transaction_status_id` ASC) VISIBLE,
  INDEX `fk_transactions_transaction_types1_idx` (`transaction_type_id` ASC) VISIBLE,
  INDEX `fk_transactions_cards1_idx` (`sender_card_id` ASC) VISIBLE,
  INDEX `fk_transactions_cards2_idx` (`recipient_card_id` ASC) VISIBLE,
  INDEX `fk_transactions_events1_idx` (`event_id` ASC) VISIBLE,
  UNIQUE INDEX `transaction_id_UNIQUE` (`transaction_id` ASC) VISIBLE,
  CONSTRAINT `fk_transactions_transaction_statuses1`
    FOREIGN KEY (`transaction_status_id`)
    REFERENCES `atm_db`.`transaction_statuses` (`transaction_status_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transactions_transaction_types1`
    FOREIGN KEY (`transaction_type_id`)
    REFERENCES `atm_db`.`transaction_types` (`transaction_type_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transactions_cards1`
    FOREIGN KEY (`sender_card_id`)
    REFERENCES `atm_db`.`cards` (`card_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transactions_cards2`
    FOREIGN KEY (`recipient_card_id`)
    REFERENCES `atm_db`.`cards` (`card_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transactions_events1`
    FOREIGN KEY (`event_id`)
    REFERENCES `atm_db`.`events` (`event_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- Insert in card_types
-- -----------------------------------------------------
INSERT INTO card_types(type_name) values ('credit'), ('debit');

-- -----------------------------------------------------
-- Insert in event_types
-- -----------------------------------------------------
INSERT INTO event_types (event_name) VALUES ('Withdraw operation done');
INSERT INTO event_types (event_name) VALUES ('Deposit operation done');
INSERT INTO event_types (event_name) VALUES ('Transaction is successful');

-- -----------------------------------------------------
-- Insert in transaction_types
-- -----------------------------------------------------

INSERT INTO transaction_types (type_name) VALUES ('deposit');
INSERT INTO transaction_types (type_name) VALUES ('withdraw');
INSERT INTO transaction_types (type_name) VALUES ('transfer');

-- -----------------------------------------------------
-- Insert in transaction_statuses
-- -----------------------------------------------------


INSERT INTO transaction_statuses (`status_name`) VALUES ('complete');
INSERT INTO transaction_statuses (`status_name`) VALUES ('blocked');

-- -----------------------------------------------------
-- Insert in user_types
-- -----------------------------------------------------

INSERT INTO user_types (user_type) VALUES ('user');
INSERT INTO user_types (user_type) VALUES ('admin');

-- -----------------------------------------------------
-- Insert in card_statuses
-- -----------------------------------------------------

INSERT INTO card_statuses (status) VALUES ('active');
INSERT INTO card_statuses (status) VALUES ('blocked');

-- -----------------------------------------------------
-- Insert in users
-- -----------------------------------------------------

INSERT INTO users (name, user_type_id) VALUES ('Maurene Perassi', 1);
INSERT INTO users (name, user_type_id) VALUES ('Odille Exell', 1);
INSERT INTO users (name, user_type_id) VALUES ('Rowen Millin', 1);
INSERT INTO users (name, user_type_id) VALUES ('Gib Brookwood', 2);
INSERT INTO users (name, user_type_id) VALUES ('Sheila Bonde', 1);
INSERT INTO users (name, user_type_id) VALUES ('Hector Guerry', 1);
INSERT INTO users (name, user_type_id) VALUES ('Hermann Daine', 1);
INSERT INTO users (name, user_type_id) VALUES ('Andee Cammacke', 1);
INSERT INTO users (name, user_type_id) VALUES ('Sidoney Antoni', 2);

-- -----------------------------------------------------
-- Insert in users
-- -----------------------------------------------------


INSERT INTO accounts (amount, user_id, credit_max) VALUES (262, 2, NULL);
INSERT INTO accounts (amount, user_id, credit_max) VALUES (5000, 3, 5000);
INSERT INTO accounts (amount, user_id, credit_max) VALUES (10000, 4, 10000);
INSERT INTO accounts (amount, user_id, credit_max) VALUES (913, 6, NULL);
INSERT INTO accounts (amount, user_id, credit_max) VALUES (15000, 7, 15000);
INSERT INTO accounts (amount, user_id, credit_max) VALUES (9012, 8, NULL);


-- -----------------------------------------------------
-- Insert in cards
-- -----------------------------------------------------

INSERT INTO cards (card_number, pin_number, expiration_date, cvc, card_type_id, account_id, card_status_id) VALUES ('4562-9908-0123-2456', '1477d87b43c2fda86eb3ca5cfc3ab6f2099bed88', '03/26', '112', 2, 1, 1); -- pin: 3141
INSERT INTO cards (card_number, pin_number, expiration_date, cvc, card_type_id, account_id, card_status_id) VALUES ('6689-0123-4567-8901', '26af178fea9fa7ebdf73dfbb388e71df2291b3fb', '03/30', '671', 2, 2, 1); -- pin: 4568
INSERT INTO cards (card_number, pin_number, expiration_date, cvc, card_type_id, account_id, card_status_id) VALUES ('4547-8901-8465-2467', '639520ebf6f1d416ab86828dc373b4ec290626ed', '05/28', '312', 1, 3, 1); -- pin: 5127
INSERT INTO cards (card_number, pin_number, expiration_date, cvc, card_type_id, account_id, card_status_id) VALUES ('8591-3457-0986-6780', '52d058996cafd64a21bab4e75154b5a738c22dd8', '02/28', '141', 1, 4, 1); -- pin: 7124
-- INSERT INTO cards (card_number, pin_number, expiration_date, cvc, card_type_id, account_id, card_status_id) VALUES ('7701-2678-9012-4437', '53a5e159ca63c196633afa43976d1cba5644f38c', '08/28', '780', 2, 4, 1); -- pin: 5697
INSERT INTO cards (card_number, pin_number, expiration_date, cvc, card_type_id, account_id, card_status_id) VALUES ('9812-3346-8701-9987', '1bfc6ac1cbe3440b88d0a544ad67d51b1a5a304c', '09/29', '881', 2, 5, 1); -- pin: 7809
INSERT INTO cards (card_number, pin_number, expiration_date, cvc, card_type_id, account_id, card_status_id) VALUES ('8101-1123-7680-3465', '63ab910cb3a7bc89faae5a46aa337aa22f5f4d30', '10/29', '121', 1, 6, 2); -- pin: 0987
-- -----------------------------------------------------
-- Insert in events
-- -----------------------------------------------------

INSERT INTO events (date, event_type_id) VALUES ('2024-01-10 08:01:05', 1);
INSERT INTO events (date, event_type_id) VALUES ('2024-01-11 10:12:34', 2);
INSERT INTO events (date, event_type_id) VALUES ('2023-12-10 12:10:45', 1);
INSERT INTO events (date, event_type_id) VALUES ('2023-12-11 11:10:34', 3);
INSERT INTO events (date, event_type_id) VALUES ('2023-12-13 12:12:34', 2);

-- -----------------------------------------------------
-- Insert in transactions
-- -----------------------------------------------------

INSERT INTO transactions (transaction_status_id, transaction_type_id, sender_card_id, amount, event_id) VALUES (2, 1, 4, 200, 1);
INSERT INTO transactions (transaction_status_id, transaction_type_id, sender_card_id, amount, event_id) VALUES (2, 2, 2, 700, 2);
INSERT INTO transactions (transaction_status_id, transaction_type_id, sender_card_id, amount, event_id) VALUES (1, 1, 1, 350, 3);
INSERT INTO transactions (transaction_status_id, transaction_type_id, sender_card_id, amount, event_id) VALUES (2, 1, 4, 91, 4);
INSERT INTO transactions (transaction_status_id, transaction_type_id, sender_card_id, recipient_card_id, amount, event_id) VALUES (1, 1, 1, 1, 350, 5);
INSERT INTO transactions (transaction_status_id, transaction_type_id, sender_card_id, recipient_card_id, amount, event_id) VALUES (1, 2, 1, 4, 91, 4);
