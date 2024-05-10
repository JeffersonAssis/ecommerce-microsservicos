CREATE TABLE `tb_itens` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `codigo_produto` VARCHAR(255) NOT NULL,
  `quantidade` INT NOT NULL,
  `preco` DECIMAL(10,2) NOT NULL
);