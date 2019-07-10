-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2019 at 06:02 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `madhang`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id_chart` int(11) NOT NULL,
  `img_url` varchar(250) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id_customer` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `kursi` int(11) DEFAULT NULL,
  `items` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `stat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id_customer`, `name`, `kursi`, `items`, `total`, `stat`) VALUES
(10, 'df', 23, NULL, NULL, 0),
(11, 'sad', 15, NULL, NULL, 0),
(12, 'das', 22, NULL, NULL, 0),
(13, 'Bang', 23, NULL, NULL, 0),
(14, 'gg', 9, NULL, NULL, 0),
(15, ' a sss', 6, NULL, NULL, 0),
(16, 'Maem', 23, NULL, NULL, 0),
(17, 'asd', 22, NULL, NULL, 0),
(18, 'AA', 12, NULL, NULL, 0),
(19, 'COBA', 21, NULL, NULL, 0),
(20, 'kjkj', 23, NULL, NULL, 0),
(21, 'AAAA', 22, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `customerlog`
--

CREATE TABLE `customerlog` (
  `id_customer` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `kursi` int(11) DEFAULT NULL,
  `items` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `stat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customerlog`
--

INSERT INTO `customerlog` (`id_customer`, `name`, `kursi`, `items`, `total`, `stat`) VALUES
(1, 'Bambank', 3, 4, 10, 0);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id_items` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `desc` varchar(150) NOT NULL,
  `price` int(11) NOT NULL,
  `rate` float NOT NULL,
  `type` int(11) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id_items`, `name`, `desc`, `price`, `rate`, `type`, `img_url`, `status`) VALUES
(1, 'Jus Mangga', 'Mencegah kanker, menurunkan kolesterol', 10, 4.5, 2, '/sample/res/juice/mangga.jpg', 0),
(2, 'Jus Alpukat', 'Menurunkan stres, Tekanan darah, Meningkatkan penyerapan nutrisi', 10, 5, 2, '/sample/res/juice/jusalpukat.jpg', 0),
(3, 'Jus melon', 'Mengandung vitamin C, Baik untuk ibu hamil ', 8, 3, 2, '/sample/res/juice/jusmelon.jpg', 0),
(4, 'Jus Jambu', 'Mencegah kanker, Menjaga tekanan darah', 8, 4, 2, '/sample/res/juice/jusjambu.jpg', 0),
(5, 'Jus Sirsak', 'Membunuh parasit, Meredakan saluran pernapasan', 8, 4, 2, '/sample/res/juice/jussirsak.jpg', 0),
(6, 'Jus Jeruk', 'Meningkatkan sistem kekebalan tubuh, Mencegah batu ginjal, Menurukan berat badan', 8, 3, 2, '/sample/res/juice/jusjeruk.png', 0),
(7, 'Jus Buah Naga', 'mampu menurunkan kadar kolestrol', 8, 4.5, 2, '/sample/res/juice/jusnaga.jpg', 0),
(8, 'Jus Tomat', 'Kesehatan mata akan prima, Menjaga kesehatan rambut', 8, 2.5, 2, '/sample/res/juice/justomat.jpg', 0),
(9, 'Jus Wortel', 'Menurunkan risiko kanker, Memperkuat fungsi otak', 8, 3, 2, '/sample/res/juice/juswortel.jpg', 0),
(10, 'Lemon Tea', 'membantu menghilangkan gejala flu, Antiseptik alami', 6, 4, 2, '/sample/res/juice/lemontea.jpg', 0),
(11, 'Hot Tea', 'Mencegah Kerusakan akibat Radiasi UV, Menjaga Jantung Sehat', 4, 4, 2, '/sample/res/juice/hottea.jpg', 0),
(12, 'Ice Tea', 'mengandung beberapa antioksidan yang baik untuk tubuh kita.', 4, 4.5, 2, '/sample/res/juice/esteh.jpg', 0),
(13, 'Air Mineral', 'Menurunkan berat badan, Meningkatkan kesehatan tulang, Menurunkan tekanan darah', 5, 5, 2, '/sample/res/juice/mineral.jpg', 0),
(27, 'Mendoan', 'sejenis masakan tempe yang terbuat dari tempe yang tipis, dan digoreng dengan tepung sehingga rasanya gurih dan renyah.', 5, 5, 1, '/sample/res/snacks/mendoan.jpg', 0),
(28, 'Pisang goreng', 'dipotong-potong sesuai keinginan kemudian di lumuri bersama adonan kental terbuat dari campuran tepung', 8, 5, 1, '/sample/res/snacks/pisgor.jpg', 0),
(29, 'Stick kentang', 'irisan kentang yang digoreng dengan minyak', 8, 3, 1, '/sample/res/snacks/stikkentang.jpg', 0),
(30, 'Bakwan Jagung', 'makanan gorengan yang terbuat dari sayuran dan tepung terigu yang lazim ditemukan di Indonesia', 8, 4.7, 1, '/sample/res/snacks/bakwanjagung.jpg', 0),
(31, 'Jamur Crispy', 'irisan jamur yang digoreng dengan lumuran tepung ', 8, 2, 1, '/sample/res/snacks/jamurcrispy.jpg', 0),
(32, 'Roti Bakar', 'kepingan roti yang dibakar dan dikasih selai.', 9, 4, 1, '/sample/res/snacks/rotibakar.jpg', 0),
(33, 'Batagor', ' Batagor mirip dengan siomay yang digoreng.', 10, 4.5, 1, '/sample/res/snacks/batagor.jpg', 0),
(34, 'Somay', 'merupakan salah satu jenis dim sum', 10, 4, 1, '/sample/res/snacks/siomay.jpg', 0),
(35, 'Lumpia Udang', 'makanan semacam rollade yang berisi rebung, telur, dan daging ayam atau udang', 12, 5, 1, '/sample/res/snacks/lumpiaudang.jpg', 0),
(36, 'Salad Buah', 'berarti makanan yang bahan utamanya adalah campuran buahsegar yang diberi tambahan saus (27,dressing)', 10, 5, 1, '/sample/res/snacks/saladbuah.jpg', 0),
(37, 'Rokue Snack', 'adonan yang dibuat dari telur dan kentang dan isinya ada cingcang daging ', 10, 2, 1, '/sample/res/snacks/rokue.jpg', 0),
(38, 'Pisang penyet', 'dibuat dari pisang yang dipenyet dan dikasih toping', 8, 2.5, 1, '/sample/res/snacks/pisangpenyet.jpg', 0),
(39, 'Kebab Pisang ', 'dari kulit kebab yang beri isi pisang ', 10, 3.5, 1, '/sample/res/snacks/kebabpisang.jpg', 0),
(40, 'Kebab Sayur', 'makanan khas turki yang isinya daging ', 13, 5, 1, '/sample/res/snacks/kebab.jpg', 0),
(41, 'Paprika Goreng ', 'dibuat dari irisan paprika yang dilumuri tepung dan digoreng', 7, 4, 1, '/sample/res/snacks/paprikagoreng.jpg', 0),
(42, 'Kaki Naga', ' camilan seperti nugget tapi disajikan dengan tusukan sate.', 6, 4, 1, '/sample/res/snacks/kakinaga.jpg', 0),
(43, 'Pompom Kentang', 'kentang yang dipenyet seperti adonan perkedel', 6, 3, 1, '/sample/res/snacks/pompom.jpg', 0),
(44, 'Sate kambing', 'irisan daring kambing yang empuk dan ditusuki', 45, 4.5, 0, '/sample/res/food/satekambing.jpg', 0),
(45, 'Tongseng Kambing', 'sejenis gulaidengan bumbu yang lebih \"tajam\"', 25, 3.1, 0, '/sample/res/food/tongsengkambing.jpg', 0),
(46, 'Tempura', 'udang yang dilumuri dengan tepung dan digoreng dengan minya sayur', 15, 5, 0, '/sample/res/food/tempura.jpg', 0),
(47, 'Cheese Omlet', 'omlet yang dilumuri dengan keju', 10, 2.9, 0, '/sample/res/food/omurice.jpg', 0),
(48, 'Ayam Nanas ', 'ayam yang beri irisan nanas dalam penyajiannya dan ada paprika sebagai pelengkap', 25, 4, 0, '/sample/res/food/ayamnanaspaprika.jpg', 0),
(49, 'Basko Teras Bali', 'bakso khas bali dengan daging sapi pilihan ', 28, 3, 0, '/sample/res/food/bakso.jpg', 0),
(50, 'Soto Ayam', 'soto ayam dengan kuah segar', 14, 3.3, 0, '/sample/res/food/sotoayam.jpg', 0),
(51, 'Mie Kuah', 'mie kuah dengan toping daging', 14, 4.5, 0, '/sample/res/food/mierebus.jpg', 0),
(52, 'Mie Goreng', 'mie goreng dengan toping ayam', 16, 3, 0, '/sample/res/food/miegoreng.jpg', 0),
(53, 'Capcay ', 'masakan sederhana yang terdiri dari aneka sayuran dipotong kecil-kecil ', 18, 3.5, 0, '/sample/res/food/capcay.jpg', 0),
(54, 'Kwetiau', 'mi Tionghoa berwarna putih yang terbuat dari beras. ', 17, 2.6, 0, '/sample/res/food/kwetiau.jpg', 0),
(55, 'Oseng Udang ', 'Olahan udang goreng yang ditumis dengan cabai hijau ini enak banget dengan Sambal Petai Udang', 6, 2, 0, '/sample/res/food/osengudang.jpg', 0),
(56, 'Cah Kangkung', 'Kangkung memiliki tekstur yang renyah dan tidak pahit sehingga dapat diolah menjadi beragam masakan', 5, 2.2, 0, '/sample/res/food/cahkangkung.jpg', 0),
(57, 'Sayur Asem ', 'campuran dari beberapa sayur yang memiliki rasa yang khas seperti namanya.', 13, 4.7, 0, '/sample/res/food/sayurasem.jpg', 0),
(58, 'Gado-gado', 'olahan dari sayuran dan telur yang diiris dengan sambal kacang dan krupuk', 13, 5, 0, '/sample/res/food/gadogado.jpg', 0),
(59, 'Coffe Affogato', 'sajian es krim dalam cangkir atau mangkuk kecil yang disiram dengan espresso', 20, 4.5, 3, '/sample/res/coffee/affogato.jpg', 0),
(60, 'Coffe Americano', 'merupakan espresso yang dicampur air namum memiliki rasa yang unik', 15, 2.7, 3, '/sample/res/coffee/americano.jpg', 0),
(61, 'Coffe Blackeye', 'merupakan coffe bubuk khas Bali yang harus dan lembut 100% made in Bali.', 15, 5, 3, '/sample/res/coffee/blackeye.jpg', 0),
(62, 'Coffe Capucino', 'minuman khas Italia yang dibuat dari espresso dan susu', 18, 4, 3, '/sample/res/coffee/capucino.jpg', 0),
(63, 'Chocolate', 'minuman cokelat manis dengan susu bubuk dengan coklat, gula, dan susu bubuk.', 20, 2, 3, '/sample/res/coffee/chocolate.jpg', 0),
(64, 'Coffe Conpanna', 'Dalam bahasa italia artinya Espresso dalam krim, di AS biasa disebut Café Vienne dan di Perancis.', 20, 2.5, 3, '/sample/res/coffee/conpanna.jpg', 0),
(65, 'Coffe Doppio', 'Doppio berasal dari Bahasa Italia yang berarti double', 20, 5, 3, '/sample/res/coffee/doppio.jpg', 0),
(67, 'Coffe Espresso', 'kopi dari ekstraksi biji kopi yang sudah digiling dengan menyemburkan air panas di bawah tekanan tinggi', 20, 4, 3, '/sample/res/coffee/kopiespresso.jpeg', 0),
(68, 'Coffe Latte', 'adalah espresso atau kopi yang dicampur dengan susu dan memiliki lapisan busa yang tipis di bagian atasnya', 20, 4.5, 3, '/sample/res/coffee/latte.jpg', 0),
(69, 'Coffe Longblack', 'Jenis minuman yang termasuk ke dalam “keluarga kopi hitam” ini terdiri dari air panas dan espresso', 18, 3.6, 3, '/sample/res/coffee/longblack.jpg', 0),
(70, 'Coffe Lungo', 'adalah Bahasa Italia yang berarti “long” dalam bahasa Inggris. ', 15, 3.4, 3, '/sample/res/coffee/lungo.jpg', 0),
(71, 'Coffe Macchiato', 'minuman kopi yang dibuat dengan mencampurkan espresso dengan susu.', 20, 4.5, 3, '/sample/res/coffee/macchiato.jpg', 0),
(72, 'Miloshake', 'minuman dingin dari campuran susu, es krim, dan sirop berperasa yang dikocok hingga berbusa. ', 20, 2.3, 3, '/sample/res/coffee/miloshake.jpg', 0),
(73, 'Coffe Mochaccino', 'minuman olahan kopi dengan cokelat yang begitu eksotis rasanya. ', 20, 3, 3, '/sample/res/coffee/mochaccino.jpg', 0),
(74, 'Coffe Ristretto', 'minuman kopi yang serupa dengan espresso tetapi menggunakan air yang lebih sedikit. ', 18, 4.5, 3, '/sample/res/coffee/ristretto.jpg', 0),
(75, 'Vanila', 'Bubuk rasa Vanilla Latte yang mudah untuk disajikan sebagai minuman maupun pelengkap makanan.', 20, 4, 3, '/sample/res/coffee/vanila.jpeg', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id_chart`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id_customer`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id_items`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id_chart` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id_customer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id_items` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
