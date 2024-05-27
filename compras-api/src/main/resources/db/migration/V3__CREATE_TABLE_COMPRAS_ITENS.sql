CREATE TABLE `tb_compras_itens`(
  `id_compra` bigint,
  `id_item` bigint,
  Primary Key (`id_compra`,`id_item`),
  FOREIGN KEY (`id_compra`) REFERENCES `tb_compras`(`id`),
  FOREIGN KEY (`id_item`) REFERENCES `tb_itens`(`id`)
);