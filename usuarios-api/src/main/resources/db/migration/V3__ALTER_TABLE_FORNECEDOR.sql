ALTER TABLE `tb_fornecedores` 
ADD COLUMN `telefone2` VARCHAR(255) NULL AFTER `complemento`,
CHANGE COLUMN `telefone` `telefone1` VARCHAR(255) NOT NULL ;