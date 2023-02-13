-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2023 at 07:41 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `order_management`
--
CREATE DATABASE IF NOT EXISTS `order_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `order_management`;

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` varchar(36) NOT NULL DEFAULT 'uuid()',
  `username` varchar(36) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `username`, `password`) VALUES
('7a813962-c33c-461b-b8be-b8ff5d5416d2', 'Wasan', '$2a$10$1ioxKE9aRWHw5TXDq0NVaOU0Ear1XKEuPFBQF6MRehBtBq3q72taG'),
('4f72b669-ea70-4017-a50f-d0bca6fbbb16', 'Wasan123', '$2a$10$IvZo.J9OeU9YO651cJUUl.ywtdXSeMvc0Y1XccevOHcGRQ7HQQp7q');

-- --------------------------------------------------------

--
-- Table structure for table `order_db`
--

CREATE TABLE `order_db` (
  `id` varchar(36) NOT NULL DEFAULT uuid(),
  `create_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `total_amount` decimal(10,2) NOT NULL,
  `status` varchar(12) NOT NULL DEFAULT 'ACTIVE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_db`
--

INSERT INTO `order_db` (`id`, `create_at`, `total_amount`, `status`) VALUES
('042c1a4f-97b7-427b-b3db-291416e86393', '2023-02-13 01:22:56', '952.00', 'DELETE'),
('27de9a4e-8047-4475-94d1-714ed17f5b16', '2023-02-13 03:37:26', '20839.65', 'DELETE'),
('2eff5a3b-aa81-4a48-90ab-6b3e9e9d05c6', '2023-01-23 08:21:43', '25000.00', 'ACTIVE'),
('301c6e48-3d4a-462e-b2bc-ceeffa43bad7', '2023-01-23 09:02:52', '25000.00', 'DELETE'),
('32a17d67-e854-421e-b178-b0ded8017322', '2023-02-13 18:38:56', '196.00', 'DELETE'),
('4139b10e-9adc-11ed-a8fc-0242ac120002', '2023-01-17 05:33:10', '9.00', 'DELETE'),
('42fca515-8922-4a07-bb36-2dc1ec5529c9', '2023-02-13 01:37:04', '505.00', 'DELETE'),
('54b16203-44be-433b-934b-665d248626b1', '2023-02-13 05:39:03', '495.00', 'DELETE'),
('6d2291ba-abba-4fb8-bae3-84d9745a0e69', '2023-01-31 04:28:24', '49999.95', 'ACTIVE'),
('6da2b64c-8331-4b83-91ac-2871798e9177', '2023-02-13 05:39:44', '40942.30', 'DELETE'),
('93379b02-e8ca-452e-aabd-53251166edbf', '2023-01-23 08:22:01', '517031.30', 'ACTIVE'),
('aa75efd5-3a77-4bb0-b97a-cf44203d66ef', '2023-01-23 08:22:02', '200011.61', 'DELETE'),
('bd6e863d-4cc3-4715-a4e2-e57a04369238', '2023-01-23 08:34:06', '25000.00', 'ACTIVE'),
('c0b184d4-dbe2-4c5f-929b-236dca16d0bf', '2023-02-13 00:51:27', '18.00', 'ACTIVE'),
('c1cd1e96-3165-4a9a-adc6-46a8e609bd84', '2023-02-13 01:36:22', '745.00', 'DELETE'),
('d46d6d1d-fefb-40a1-bb24-e1de7bac530a', '2023-02-13 00:50:31', '18.00', 'ACTIVE'),
('e5407030-26e0-4bc7-a037-1338867996a3', '2023-02-13 03:34:49', '834.00', 'DELETE');

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_detail` (
  `id` varchar(36) NOT NULL DEFAULT uuid(),
  `product_name` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `product_id` varchar(36) NOT NULL,
  `order_id` varchar(36) NOT NULL,
  `status` varchar(12) NOT NULL DEFAULT 'ACTIVE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_detail`
--

INSERT INTO `order_detail` (`id`, `product_name`, `quantity`, `price`, `amount`, `create_at`, `product_id`, `order_id`, `status`) VALUES
('0708aa97-a055-4bc9-9181-7314cb54cf28', 'Handmade Soft Bacon', 199, '472.69', '94065.31', '2023-02-13 01:37:19', 'fbd0a32e-fb7f-4b11-843b-582a0008cb01', '42fca515-8922-4a07-bb36-2dc1ec5529c9', 'DELETE'),
('0e37d53e-6570-479f-83f7-dd705c651e43', 'Zio Mei', 5, '4999.99', '24999.95', '2023-01-31 09:23:13', '3b2df48d-a948-4cb5-b583-b081369fd18d', '6d2291ba-abba-4fb8-bae3-84d9745a0e69', 'DELETE'),
('2cd6cfaa-bda5-4166-8549-cc67fa6715c6', 'IJone', 5, '5000.00', '25000.00', '2023-01-23 09:04:28', '6327aa11-776f-4f45-a008-5a0f9cb52939', 'aa75efd5-3a77-4bb0-b97a-cf44203d66ef', 'DELETE'),
('304826d8-25d8-4f3f-867e-323d9d16a1d0', 'IJone', 5, '4000.33', '20001.65', '2023-01-23 09:15:28', 'e45420b2-0596-4df9-a578-c800e4787079', 'aa75efd5-3a77-4bb0-b97a-cf44203d66ef', 'DELETE'),
('35319300-9a8b-402b-99a9-078af9a8698e', 'Fantastic Rubber Pants', 526, '166.69', '87678.94', '2023-02-13 01:17:09', 'fbd0a32e-fb7f-4b11-843b-582a0008cb01', '93379b02-e8ca-452e-aabd-53251166edbf', 'ACTIVE'),
('3fbc2aa1-5055-4668-ad3a-bcd9bc0ed976', 'Moliope', 1, '100.69', '100.69', '2023-01-23 08:22:01', '3a838102-c5a9-48be-bf3f-8ce4c0224b82', '93379b02-e8ca-452e-aabd-53251166edbf', 'ACTIVE'),
('54c4c6e9-edd1-4292-9a06-36bc1a9bdc8c', 'IJone', 5, '4000.33', '20001.65', '2023-02-13 01:17:04', 'fbd0a32e-fb7f-4b11-843b-582a0008cb01', '93379b02-e8ca-452e-aabd-53251166edbf', 'ACTIVE'),
('6af02f05-20a4-43cd-8aa4-46c72f5acc2c', 'Tasty Rubber Chair', 579, '845.69', '489654.51', '2023-02-13 03:45:06', 'fbd0a32e-fb7f-4b11-843b-582a0008cb01', '27de9a4e-8047-4475-94d1-714ed17f5b16', 'DELETE'),
('6c6ccc9b-5600-475d-b7e5-0562413dabed', 'IJone', 5, '4000.33', '20001.65', '2023-02-13 03:44:57', 'fbd0a32e-fb7f-4b11-843b-582a0008cb01', '27de9a4e-8047-4475-94d1-714ed17f5b16', 'DELETE'),
('74f1e0e2-697d-49ba-b503-91a7b32ef1b8', 'IJone', 5, '4000.33', '20001.65', '2023-02-13 13:01:38', 'fbd0a32e-fb7f-4b11-843b-582a0008cb01', '6da2b64c-8331-4b83-91ac-2871798e9177', 'ACTIVE'),
('7739721e-4cf7-479f-b85e-6c84faa5302e', 'IJone', 5, '5000.33', '25001.67', '2023-01-23 09:06:38', '6327aa11-776f-4f45-a008-5a0f9cb52939', 'aa75efd5-3a77-4bb0-b97a-cf44203d66ef', 'DELETE'),
('8243bae2-8695-4f1a-9f33-c5b2762cee40', 'IJone', 5, '5000.00', '25000.00', '2023-01-23 09:02:52', '6327aa11-776f-4f45-a008-5a0f9cb52939', '301c6e48-3d4a-462e-b2bc-ceeffa43bad7', 'DELETE'),
('878d59a7-3d1e-44ba-8f78-eab8b7c402af', 'IJone', 5, '5000.00', '25000.00', '2023-01-23 09:03:32', '6327aa11-776f-4f45-a008-5a0f9cb52939', 'aa75efd5-3a77-4bb0-b97a-cf44203d66ef', 'DELETE'),
('96d77473-08bd-4afd-b180-036a79333b89', 'IJone', 5, '4000.33', '20001.65', '2023-02-13 00:15:50', 'fbd0a32e-fb7f-4b11-843b-582a0008cb01', '93379b02-e8ca-452e-aabd-53251166edbf', 'DELETE'),
('98c208c7-54b3-4c66-9192-bdb473893de3', 'Sleek Fresh Hat', 981, '283.69', '278299.89', '2023-02-13 18:39:02', 'fbd0a32e-fb7f-4b11-843b-582a0008cb01', '32a17d67-e854-421e-b178-b0ded8017322', 'DELETE'),
('99eff58f-e8ff-461e-9590-78090e7e6704', 'IJone', 5, '5000.00', '25000.00', '2023-01-23 08:21:43', '2cb0b726-36b6-476d-b6c4-0067f42f3fdc', '2eff5a3b-aa81-4a48-90ab-6b3e9e9d05c6', 'ACTIVE'),
('a7ac63a2-c077-435e-8459-17f923088680', 'IJone', 5, '4000.33', '20001.65', '2023-01-23 09:15:40', 'e45420b2-0596-4df9-a578-c800e4787079', 'aa75efd5-3a77-4bb0-b97a-cf44203d66ef', 'DELETE'),
('a80485a9-5f5c-459e-a565-6cd8ded8069e', 'IJone', 5, '4000.33', '20001.67', '2023-01-23 09:08:20', '6327aa11-776f-4f45-a008-5a0f9cb52939', 'aa75efd5-3a77-4bb0-b97a-cf44203d66ef', 'DELETE'),
('c70a879a-1a73-47dd-9d77-89fef25673bb', 'IJone', 5, '5000.00', '25000.00', '2023-01-31 04:28:24', '7c6696c9-0bd1-4e27-b12b-8a05c7c6e1a5', '6d2291ba-abba-4fb8-bae3-84d9745a0e69', 'DELETE'),
('e21d0fbb-0c7c-46a7-9ba9-2a1d8588a5e1', 'IJone', 5, '5000.33', '25001.67', '2023-01-23 09:07:13', '6327aa11-776f-4f45-a008-5a0f9cb52939', 'aa75efd5-3a77-4bb0-b97a-cf44203d66ef', 'DELETE'),
('e31f5e3c-330f-4b61-a37a-bbe37040e6f8', 'IJone', 5, '4000.33', '20001.65', '2023-02-13 18:33:23', 'fbd0a32e-fb7f-4b11-843b-582a0008cb01', '6da2b64c-8331-4b83-91ac-2871798e9177', 'ACTIVE'),
('e5cfa512-68fd-4172-a536-0c8bef02a2db', 'IJone', 5, '4000.33', '20001.65', '2023-01-23 09:16:43', 'e45420b2-0596-4df9-a578-c800e4787079', 'aa75efd5-3a77-4bb0-b97a-cf44203d66ef', 'DELETE'),
('f33ed8fa-2c51-4951-8262-203a00b502c1', 'IJone', 5, '4000.33', '20001.65', '2023-01-23 09:18:20', 'e45420b2-0596-4df9-a578-c800e4787079', 'aa75efd5-3a77-4bb0-b97a-cf44203d66ef', 'DELETE'),
('f6279178-4954-4361-b764-0c33a530a156', 'IJone', 5, '5000.00', '25000.00', '2023-01-23 08:34:06', '6327aa11-776f-4f45-a008-5a0f9cb52939', 'bd6e863d-4cc3-4715-a4e2-e57a04369238', 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` varchar(36) NOT NULL DEFAULT uuid(),
  `name` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `price`) VALUES
('09b9a39a-d23f-492b-8be1-0597d3728ce3', 'Licensed Wooden Computer', '737.99'),
('0c65ae7d-b187-48fe-8703-0199d22bbdfb', 'SamSan', '2999.99'),
('10b9e3bb-c8bf-44f8-ae75-f5db19135a79', 'Malfo', '399.99'),
('1c2f155c-9adc-11ed-a8fc-0242ac120002', 'Poppy', '2500.99'),
('1c56186a-d627-4e16-a022-ef70b47a6714', 'Zio Mei', '5000.00'),
('2cb0b726-36b6-476d-b6c4-0067f42f3fdc', 'IJone', '1000.00'),
('3a838102-c5a9-48be-bf3f-8ce4c0224b82', 'IJone', '1000.00'),
('3b2df48d-a948-4cb5-b583-b081369fd18d', 'IJone', '1000.00'),
('47a109cc-6351-41cc-b054-87990c9de217', 'Zio Mei', '4999.99'),
('6327aa11-776f-4f45-a008-5a0f9cb52939', 'IJone', '1000.00'),
('7c4aaeb7-523c-4edd-a909-e062dcf9b90c', 'Xob', '4555.19'),
('7c6696c9-0bd1-4e27-b12b-8a05c7c6e1a5', 'IJone', '1000.00'),
('bed0c05f-48f2-4e76-91d2-8fdc4ea33b9b', 'IJone', '1000.00'),
('cf27c72d-6e2b-4365-bcbf-5800297bd574', 'Zio Mei', '4999.99'),
('d216b1fd-8988-4973-9091-d7b60861557b', 'Melfo', '399.99'),
('e45420b2-0596-4df9-a578-c800e4787079', 'SamSan', '2999.99'),
('eeb96041-1695-4bd1-af7e-eeea05a6f223', 'Melfos', '399.99'),
('fa5b2e54-1299-4f67-ae0e-0366e5262b63', 'Malfo', '399.99'),
('fbd0a32e-fb7f-4b11-843b-582a0008cb01', 'Melfos', '399.99');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `order_db`
--
ALTER TABLE `order_db`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `order_db` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
