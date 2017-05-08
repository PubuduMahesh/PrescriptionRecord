-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 08, 2017 at 05:55 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prescription_record`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `name` varchar(35) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `regNumber` varchar(15) NOT NULL,
  `speciality` text NOT NULL,
  `gender` varchar(8) NOT NULL,
  `birthday` date DEFAULT NULL,
  `telephone` varchar(10) DEFAULT NULL,
  `jobHistory` text,
  `profilePicture` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`id`, `name`, `nic`, `regNumber`, `speciality`, `gender`, `birthday`, `telephone`, `jobHistory`, `profilePicture`) VALUES
(1, 'Sanath Jayasuriya', '692584575V', 'slmcreg123', 'Batting', 'Male', '1969-07-30', '0774548545', 'Opener', NULL),
(2, 'Kusal Janith Perera', '902154588V', 'slmcreg2006', 'WicketKeeperBatsman', 'Male', '1990-02-06', '0772585652', 'SchoolCricketer', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `drug`
--

CREATE TABLE `drug` (
  `drugID` int(11) NOT NULL,
  `drugName` text NOT NULL,
  `description` text NOT NULL,
  `type` text NOT NULL,
  `dosage` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `drug`
--

INSERT INTO `drug` (`drugID`, `drugName`, `description`, `type`, `dosage`) VALUES
(1, 'Penadol', 'Tablet', 'Capsules', '4');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `name` varchar(35) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `address` text,
  `gender` varchar(6) NOT NULL,
  `status` varchar(10) NOT NULL,
  `birthday` date NOT NULL,
  `telephone` varchar(10) DEFAULT NULL,
  `profilePicture` blob,
  `healthDescription` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `name`, `nic`, `address`, `gender`, `status`, `birthday`, `telephone`, `profilePicture`, `healthDescription`) VALUES
(2, 'Kumara Sangakkara', '725632147V', 'Kandy', 'Male', 'Married', '1972-05-02', '0710258965', NULL, 'Good');

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `id` int(11) NOT NULL,
  `patientID` int(11) NOT NULL,
  `doctorID` int(11) NOT NULL,
  `diagDescription` text NOT NULL,
  `drugList` text NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`id`, `patientID`, `doctorID`, `diagDescription`, `drugList`, `date`) VALUES
(1, 2, 1, 'Fever', 'null1-Penadol-Tablet-Capsules-6,\n1-Penadol-Tablet-Capsules-4,\n', '2017-05-01'),
(2, 2, 1, 'Fever', 'null1-Penadol-Tablet-Capsules-4,\n', '2017-05-01');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `drug`
--
ALTER TABLE `drug`
  ADD PRIMARY KEY (`drugID`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nic` (`nic`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `patientID` (`patientID`),
  ADD KEY `doctorID` (`doctorID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `drug`
--
ALTER TABLE `drug`
  MODIFY `drugID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
