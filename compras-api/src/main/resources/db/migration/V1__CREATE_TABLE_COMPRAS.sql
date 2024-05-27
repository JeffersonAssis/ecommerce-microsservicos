CREATE TABLE `tb_compras` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `email_cliente` VARCHAR(255) NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `data` DATE NOT NULL
);