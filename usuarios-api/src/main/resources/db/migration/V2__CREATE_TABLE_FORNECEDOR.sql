CREATE TABLE `tb_fornecedores`(
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `nome_fantasia` VARCHAR(255) NOT NULL,
    `razao_social` VARCHAR(255) NOT NULL,
    `cnpj` VARCHAR(255) NOT NULL UNIQUE,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `telefone` VARCHAR(255) NOT NULL,
    `logradouro` VARCHAR(255),
    `numero` VARCHAR(255),
    `bairro` VARCHAR(255),
    `cidade` VARCHAR(255),
    `uf` VARCHAR(255),
    `cep` VARCHAR(255),
    `complemento` VARCHAR(255)
);