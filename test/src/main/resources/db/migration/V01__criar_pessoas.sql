CREATE TABLE cliente (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (50) NOT NULL,
    idade BIGINT(3) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    temp_min VARCHAR(15) NOT NULL,
    temp_max VARCHAR(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cliente (nome, idade, cidade, temp_min, temp_max) VALUES ('ZE DA SILVA', 15, 'Alagoinhas', '50°C', '120°C');