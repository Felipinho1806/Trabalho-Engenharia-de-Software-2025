-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_vvv
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_vvv
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_vvv` DEFAULT CHARACTER SET utf8 ;
USE `db_vvv` ;

-- -----------------------------------------------------
-- Table `db_vvv`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_vvv`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `senha` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vvv`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_vvv`.`cliente` (
  `id_cliente` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `telefone` VARCHAR(15) NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  INDEX `fk_usuario_cliente_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_cliente`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `db_vvv`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vvv`.`local`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_vvv`.`local` (
  `id_local` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `endereco` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id_local`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vvv`.`proprietarioModal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_vvv`.`proprietarioModal` (
  `id_proprietarioModal` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(60) NOT NULL,
  `telefone` VARCHAR(15) NULL,
  PRIMARY KEY (`id_proprietarioModal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vvv`.`modal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_vvv`.`modal` (
  `id_modal` INT NOT NULL AUTO_INCREMENT,
  `id_proprietario` INT NULL,
  `capacidade` INT NOT NULL,
  `tipo` VARCHAR(10) NOT NULL,
  `categoria` VARCHAR(10) NULL,
  `marca` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_modal`),
  INDEX `fk_proprietario_modal_idx` (`id_proprietario` ASC) VISIBLE,
  CONSTRAINT `fk_proprietario_modal`
    FOREIGN KEY (`id_proprietario`)
    REFERENCES `db_vvv`.`proprietarioModal` (`id_proprietarioModal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vvv`.`viagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_vvv`.`viagem` (
  `id_viagem` INT NOT NULL AUTO_INCREMENT,
  `id_localPartida` INT NOT NULL,
  `id_localDestino` INT NOT NULL,
  `id_modal` INT NOT NULL,
  `data` DATE NOT NULL,
  `horaPartida` VARCHAR(45) NOT NULL,
  `horaChegada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_viagem`),
  INDEX `fk_localPartida_local_idx` (`id_localPartida` ASC) VISIBLE,
  INDEX `fk_localDestino_local_idx` (`id_localDestino` ASC) VISIBLE,
  INDEX `fk_modal_viagem_idx` (`id_modal` ASC) VISIBLE,
  CONSTRAINT `fk_localPartida_viagem`
    FOREIGN KEY (`id_localPartida`)
    REFERENCES `db_vvv`.`local` (`id_local`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_localDestino_viagem`
    FOREIGN KEY (`id_localDestino`)
    REFERENCES `db_vvv`.`local` (`id_local`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_modal_viagem`
    FOREIGN KEY (`id_modal`)
    REFERENCES `db_vvv`.`modal` (`id_modal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vvv`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_vvv`.`reserva` (
  `id_reserva` INT NOT NULL AUTO_INCREMENT,
  `id_cliente` INT NOT NULL,
  `id_viagem` INT NOT NULL,
  `valor` DOUBLE NOT NULL,
  `data` DATE NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_reserva`),
  INDEX `fk_cliente_reserva_idx` (`id_cliente` ASC) VISIBLE,
  INDEX `fk_viagem_reserva_idx` (`id_viagem` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_reserva`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `db_vvv`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viagem_reserva`
    FOREIGN KEY (`id_viagem`)
    REFERENCES `db_vvv`.`viagem` (`id_viagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vvv`.`bilhete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_vvv`.`bilhete` (
  `id_bilhete` INT NOT NULL AUTO_INCREMENT,
  `id_reserva` INT NOT NULL,
  `data_emissao` DATE NOT NULL,
  PRIMARY KEY (`id_bilhete`),
  INDEX `fk_reserva_bilhete_idx` (`id_reserva` ASC) VISIBLE,
  CONSTRAINT `fk_reserva_bilhete`
    FOREIGN KEY (`id_reserva`)
    REFERENCES `db_vvv`.`reserva` (`id_reserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vvv`.`pontoDeVendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_vvv`.`pontoDeVendas` (
  `id_pontoDeVendas` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `endereco` VARCHAR(150) NOT NULL,
  `telefone` VARCHAR(15) NULL,
  `id_bilhete` INT NULL,
  PRIMARY KEY (`id_pontoDeVendas`),
  INDEX `fk_pontoDeVenda_bilhete_idx` (`id_bilhete` ASC) VISIBLE,
  CONSTRAINT `fk_pontoDeVenda_bilhete`
    FOREIGN KEY (`id_bilhete`)
    REFERENCES `db_vvv`.`bilhete` (`id_bilhete`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_vvv`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_vvv`.`funcionario` (
  `id_funcionario` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `id_pontoDeVendas` INT NULL,
  `totalVendas` DOUBLE NULL,
  PRIMARY KEY (`id_funcionario`),
  INDEX `fk_usuario_funcionario_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_funcionario_pontoDeVenda_idx` (`id_pontoDeVendas` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_funcionario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `db_vvv`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_pontoDeVenda`
    FOREIGN KEY (`id_pontoDeVendas`)
    REFERENCES `db_vvv`.`pontoDeVendas` (`id_pontoDeVendas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
