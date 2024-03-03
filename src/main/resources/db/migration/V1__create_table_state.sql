CREATE TABLE `tb_state` (
  `id` varchar(255) NOT NULL,
  `idh` float NOT NULL,
  `pib` float NOT NULL,
  `acronym` varchar(255) DEFAULT NULL,
  `area` double DEFAULT NULL,
  `capital` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `population` int NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
)