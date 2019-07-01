-- MySQL Script generated by MySQL Workbench
-- Sat Jun 29 12:06:46 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema planjamentoFinanceiro
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema planjamentoFinanceiro
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `planjamentoFinanceiro` DEFAULT CHARACTER SET utf8 ;
USE `planjamentoFinanceiro` ;

-- -----------------------------------------------------
-- Table `planjamentoFinanceiro`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `planjamentoFinanceiro`.`Usuario` (
  `idUsuario` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `emial` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `rg` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `sexo` ENUM('MASCULINO', 'FEMININO') NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `planjamentoFinanceiro`.`Movimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `planjamentoFinanceiro`.`Movimentacao` (
  `idMovimentacao` INT NOT NULL,
  `valor` DOUBLE NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `tipo` ENUM('Receita', 'Desoesa') NOT NULL,
  `tipoReceita` ENUM('SALARIO', 'OUTROS') NOT NULL,
  `tipoDespesa` ENUM('ACADEMIA', 'AGUA', 'ALUGUEL', 'CLUBE', 'INTERNET', 'TELEFONE', 'LUZ', 'SUPERMERCADO', 'OUTROS') NULL,
  `Movimentacaocol` VARCHAR(45) NULL,
  PRIMARY KEY (`Usuario_idUsuario`, `idMovimentacao`),
  INDEX `fk_Movimentacao_Usuario_idx` (`Usuario_idUsuario` ASC),
  CONSTRAINT `fk_Movimentacao_Usuario`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `planjamentoFinanceiro`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `planjamentoFinanceiro`.`Data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `planjamentoFinanceiro`.`Data` (
  `idData` INT NOT NULL,
  `dia` INT NULL,
  `mes` INT NULL,
  `ano` INT NULL,
  `Datacol` VARCHAR(45) NULL,
  `Movimentacao_idMovimentacao` INT NOT NULL,
  `Movimentacao_Usuario_idUsuario` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idData`),
  INDEX `fk_Data_Movimentacao1_idx` (`Movimentacao_idMovimentacao` ASC, `Movimentacao_Usuario_idUsuario` ASC),
  INDEX `fk_Data_Usuario1_idx` (`Usuario_idUsuario` ASC),
  CONSTRAINT `fk_Data_Movimentacao1`
    FOREIGN KEY (`Movimentacao_idMovimentacao` , `Movimentacao_Usuario_idUsuario`)
    REFERENCES `planjamentoFinanceiro`.`Movimentacao` (`idMovimentacao` , `Usuario_idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Data_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `planjamentoFinanceiro`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
