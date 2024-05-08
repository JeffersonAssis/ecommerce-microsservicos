CREATE TABLE `tb_clientes`(
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(255) NOT NULL,
    `cpf` VARCHAR(255) NOT NULL UNIQUE,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `telefone` VARCHAR(255) NOT NULL,
    `data_nascimento` DATE NOT NULL,
    `logradouro` VARCHAR(255),
    `numero` VARCHAR(255),
    `bairro` VARCHAR(255),
    `cidade` VARCHAR(255),
    `uf` VARCHAR(255),
    `cep` VARCHAR(255),
    `complemento` VARCHAR(255)
);