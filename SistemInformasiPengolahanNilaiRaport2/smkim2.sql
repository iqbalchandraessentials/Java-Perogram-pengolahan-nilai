-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 22 Agu 2019 pada 07.29
-- Versi Server: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smkim`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `absen`
--

CREATE TABLE `absen` (
  `id_absen` int(11) NOT NULL,
  `id_siswa` varchar(11) NOT NULL,
  `kd_mapel` varchar(11) NOT NULL,
  `kd_guru` varchar(11) NOT NULL,
  `kehadiran` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `absen`
--

INSERT INTO `absen` (`id_absen`, `id_siswa`, `kd_mapel`, `kd_guru`, `kehadiran`) VALUES
(123, '123', 'BHS10GA', 'GBIND001', 12),
(112202, '123', 'BHS10GA', 'GBIND001', 100);

-- --------------------------------------------------------

--
-- Struktur dari tabel `guru`
--

CREATE TABLE `guru` (
  `kd_guru` varchar(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `jenkel` char(1) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `guru`
--

INSERT INTO `guru` (`kd_guru`, `nama`, `jenkel`, `alamat`) VALUES
('GBIND001', 'Muhammad Fatih, S.Pd', 'L', 'Bekasi'),
('GBING001', 'Fatimah, S.Pd', 'P', 'Jakarta'),
('GMTK001', 'Saraswati, S.Pd', 'P', 'Depok'),
('GTKJ001', 'Usman, S.Kom', 'L', 'Bogor'),
('GTKR001', 'Bambang Harianto, S.T', 'L', 'Bandung');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mapel`
--

CREATE TABLE `mapel` (
  `kd_mapel` varchar(11) NOT NULL,
  `nm_mapel` text NOT NULL,
  `kelas` int(2) NOT NULL,
  `semester` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `mapel`
--

INSERT INTO `mapel` (`kd_mapel`, `nm_mapel`, `kelas`, `semester`) VALUES
('BHS10GA', 'BAHASA INDONESIA', 10, 'Ganjil'),
('BHS10GE', 'BAHASA INDONESIA', 10, 'Genap'),
('BIG10GA', 'BAHASA INGGRIS', 10, 'Ganjil'),
('BIG10GE', 'BAHASA INGGRIS', 10, 'Genap'),
('KJR0110GA', 'KEJURUAN TKR', 10, 'Ganjil'),
('KJR0110GE', 'KEJURUAN TKR', 10, 'Genap'),
('KJR0210GA', 'KEJURUAN TKJ', 10, 'Ganjil'),
('KJR0210GE', 'KEJURUAN TKJ', 10, 'Genap'),
('MTK10GA', 'MATEMATIKA', 10, 'Ganjil'),
('MTK10GE', 'MATEMATIKA', 10, 'Genap'),
('PKN11GE', 'PKN', 11, 'Genap');

-- --------------------------------------------------------

--
-- Struktur dari tabel `n_harian`
--

CREATE TABLE `n_harian` (
  `id_harian` int(11) NOT NULL,
  `id_siswa` int(11) NOT NULL,
  `kd_mapel` varchar(11) NOT NULL,
  `kd_guru` varchar(11) NOT NULL,
  `nilaiharian` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `n_harian`
--

INSERT INTO `n_harian` (`id_harian`, `id_siswa`, `kd_mapel`, `kd_guru`, `nilaiharian`) VALUES
(123, 123, 'BHS10GA', 'GBIND001', 90);

-- --------------------------------------------------------

--
-- Struktur dari tabel `n_uas`
--

CREATE TABLE `n_uas` (
  `id_uas` int(11) NOT NULL,
  `id_siswa` int(11) NOT NULL,
  `kd_mapel` varchar(11) NOT NULL,
  `kd_guru` varchar(11) NOT NULL,
  `nilaiuas` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `n_uts`
--

CREATE TABLE `n_uts` (
  `id_uts` int(11) NOT NULL,
  `id_siswa` int(11) NOT NULL,
  `kd_mapel` varchar(11) NOT NULL,
  `kd_guru` varchar(11) NOT NULL,
  `nilaiuts` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `report`
--

CREATE TABLE `report` (
  `id_report` varchar(11) NOT NULL,
  `id_siswa` varchar(11) NOT NULL,
  `na_uas` int(5) NOT NULL,
  `na_uts` int(5) NOT NULL,
  `na_harian` int(5) NOT NULL,
  `na_absen` int(5) NOT NULL,
  `na_akhir` int(5) NOT NULL,
  `grade` char(1) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `siswa`
--

CREATE TABLE `siswa` (
  `id_siswa` int(11) NOT NULL,
  `nama_siswa` text NOT NULL,
  `tempat_lahir` text NOT NULL,
  `tanggal_lahir` text NOT NULL,
  `jk` varchar(1) NOT NULL,
  `kelas` int(2) NOT NULL,
  `kejuruan` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `siswa`
--

INSERT INTO `siswa` (`id_siswa`, `nama_siswa`, `tempat_lahir`, `tanggal_lahir`, `jk`, `kelas`, `kejuruan`) VALUES
(123, 'jojo saputra', 'bekasi', '12121999', 'L', 10, 'TKJ'),
(124, 'agus yoyoi', 'malang', '01021995', 'L', 10, 'TKJ'),
(1001, 'melinda', 'kuningan', '12122001', 'P', 12, 'TKJ'),
(1004, 'yoga b', 'Bandung', '03041999', 'L', 11, 'TKR'),
(1005, 'yoga b', 'Bandung', '03041999', 'L', 11, 'TKJ'),
(66666, '12345', 'hhhhhhhh', '12121122', 'P', 10, ''),
(1005777, 'yoga b', 'Bandung', '03041999', 'L', 11, '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`username`, `password`) VALUES
('admin', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absen`
--
ALTER TABLE `absen`
  ADD PRIMARY KEY (`id_absen`);

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`kd_guru`);

--
-- Indexes for table `mapel`
--
ALTER TABLE `mapel`
  ADD PRIMARY KEY (`kd_mapel`);

--
-- Indexes for table `n_harian`
--
ALTER TABLE `n_harian`
  ADD PRIMARY KEY (`id_harian`);

--
-- Indexes for table `n_uas`
--
ALTER TABLE `n_uas`
  ADD PRIMARY KEY (`id_uas`);

--
-- Indexes for table `n_uts`
--
ALTER TABLE `n_uts`
  ADD PRIMARY KEY (`id_uts`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id_report`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id_siswa`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
  MODIFY `id_siswa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1005778;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
