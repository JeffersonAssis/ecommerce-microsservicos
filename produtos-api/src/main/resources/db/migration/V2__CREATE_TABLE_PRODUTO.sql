CREATE TABLE `tb_produtos` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `codigo` VARCHAR(255) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `descricao` TEXT NOT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  `categoria_id` BIGINT NOT NULL,
  FOREIGN KEY (`categoria_id`) REFERENCES `tb_categorias`(`id`)
);