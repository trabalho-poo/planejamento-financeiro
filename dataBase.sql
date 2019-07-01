use planejamentoFinanceiropma__central_columnspma__central_columnspma__central_columns;
CREATE TABLE Usuario(
  idUsuario INT NOT NULL,
  nome VARCHAR(45) NOT NULL,
  emial VARCHAR(45) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  rg VARCHAR(45) NOT NULL,
  cpf VARCHAR(45) NOT NULL,
  sexo ENUM('MASCULINO', 'FEMININO') NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;

