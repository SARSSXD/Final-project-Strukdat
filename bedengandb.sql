-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.28-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for bedengan_db
CREATE DATABASE IF NOT EXISTS `bedengan_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `bedengan_db`;

-- Dumping structure for table bedengan_db.data_user
CREATE TABLE IF NOT EXISTS `data_user` (
  `id_user` int(11) DEFAULT NULL,
  `nik` varchar(50) DEFAULT NULL,
  `no_tlp` varchar(50) DEFAULT NULL,
  `norek` varchar(50) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `jk` varchar(20) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT 'alamat',
  KEY `id_user` (`id_user`),
  CONSTRAINT `FK_data_user_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table bedengan_db.data_user: ~5 rows (approximately)
INSERT INTO `data_user` (`id_user`, `nik`, `no_tlp`, `norek`, `tanggal_lahir`, `jk`, `alamat`) VALUES
	(1, '123123213', '12312321321', '12321321321', '0000-00-00', 'Laki Laki', 'iya disitu'),
	(2, '3514091502040001', '08515969989', '12356278482', '2004-11-25', 'Perempuan', 'ya gatau alamatnya'),
	(3, '3514091502040001', '08515969989', '12356278482', '2004-02-16', 'Laki-laki', 'Jl. Raya Glagahsari no.130 Sukorejo Pasuruan'),
	(4, '0', '0', '0', NULL, NULL, 'alamat'),
	(5, '4345354', '089776768', '123', '0002-11-02', 'Laki-laki', 'jl');

-- Dumping structure for table bedengan_db.order
CREATE TABLE IF NOT EXISTS `order` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL DEFAULT 0,
  `jml_pengunjung` int(20) NOT NULL DEFAULT 0,
  `tanggal` date DEFAULT NULL,
  `paket` varchar(50) NOT NULL DEFAULT '0',
  `metodbyr` varchar(50) NOT NULL DEFAULT '0',
  `buktibyr` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `total` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_order`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `FK_order_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table bedengan_db.order: ~12 rows (approximately)
INSERT INTO `order` (`id_order`, `id_user`, `jml_pengunjung`, `tanggal`, `paket`, `metodbyr`, `buktibyr`, `status`, `total`) VALUES
	(1, 2, 3, '2023-12-25', 'Paket 2', 'Transfer', 'src\\upload\\1.png', 'Lunas', 'Rp. 60000'),
	(2, 2, 5, '0000-00-00', 'Paket 1', 'Transfer', 'src\\upload\\2.jpg', 'Belum Lunas', 'Rp. 50000'),
	(3, 1, 2, '0000-00-00', 'Paket 1', 'Transfer', 'src\\upload\\3.png', 'Lunas', 'Rp. 20000'),
	(4, 2, 1, '2023-12-20', 'Paket 1', 'Tunai', NULL, 'Lunas', 'Rp. 10000'),
	(5, 2, 2, '2023-12-21', 'Paket 2', 'Transfer', 'src\\upload\\5.PNG', 'Lunas', 'Rp. 40000'),
	(6, 1, 3, '2023-12-18', 'Paket 3', 'Tunai', NULL, 'Lunas', 'Rp. 90000'),
	(7, 2, 6, '2023-12-28', 'Paket 3', 'Tunai', NULL, 'Belum Lunas', 'Rp. 180000'),
	(8, 3, 7, '2023-12-25', 'Paket 1', 'Transfer', 'src\\upload\\8.png', 'Lunas', 'Rp. 70000'),
	(9, 3, 5, '2023-12-22', 'Paket 2', 'Transfer', 'src\\upload\\9.jpg', 'Lunas', 'Rp. 100000'),
	(10, 2, 4, '2023-12-28', 'Paket 2', 'Transfer', NULL, 'Belum Lunas', 'Rp. 80000'),
	(11, 3, 4, '2023-12-21', 'Paket 2', 'Transfer', 'src\\upload\\11.jpg', 'Lunas', 'Rp. 80000'),
	(12, 5, 5675, '2023-12-07', 'Paket 3', 'Tunai', 'src\\upload\\12.sql', 'Lunas', 'Rp. 170250000'),
	(13, 5, 98, '2023-12-12', 'Paket 2', 'Transfer', 'src\\upload\\13.jpg', 'Lunas', 'Rp. 1960000');

-- Dumping structure for table bedengan_db.user
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_user`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table bedengan_db.user: ~5 rows (approximately)
INSERT INTO `user` (`id_user`, `nama`, `email`, `username`, `password`) VALUES
	(1, 'Kautsar', 'admin@gmail.com', 'admin', 'admin'),
	(2, 'Jeneng e user', 'user@gmail.com', 'user', 'user'),
	(3, 'Kautsar Quraisy Al Hamidy', '220605110162@student-uin-malang.ac.id', 'SARSS', 'asik'),
	(4, 'Jenny Runa', 'jennyangels@gmail.com', 'jenarun', '123'),
	(5, 'annisa', 'annisacantik@gmail.com', 'jenny', 'runa');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
