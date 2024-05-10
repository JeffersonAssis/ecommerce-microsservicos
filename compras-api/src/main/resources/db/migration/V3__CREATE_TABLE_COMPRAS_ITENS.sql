CREATE TABLE `tb_compras_itens`(
  `idCompra` bigint,
  `idItem` bigint,
  Primary Key (`idCompra`,`idItem`),
  FOREIGN KEY (`idCompra`) REFERENCES `tb_compras`(`id`),
  FOREIGN KEY (`idItem`) REFERENCES `tb_itens`(`id`)
);