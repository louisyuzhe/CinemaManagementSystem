-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2017 at 06:45 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinemadatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `movieID` int(30) NOT NULL,
  `title` varchar(50) NOT NULL,
  `runtime` int(10) NOT NULL,
  `year` int(4) NOT NULL,
  `rating` varchar(4) NOT NULL,
  `plot` varchar(200) NOT NULL,
  `poster` varchar(50) NOT NULL,
  `video` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`movieID`, `title`, `runtime`, `year`, `rating`, `plot`, `poster`, `video`) VALUES
(100, 'Avengers', 200, 2013, 'PG', 'Superhero TeamUp', 'image/avengers.jpg', 'video/avengers.mp4'),
(101, 'Titanic', 120, 1997, 'G', 'rose dies', 'image/ff8.jpg', 'video/ff8.mp4'),
(102, 'Fast and Furious 8', 200, 2017, 'G', 'The Fate of the Furious	', 'image/ff8.jpg', 'video/ff8.mp4'),
(103, 'Suicide Squad', 210, 2016, 'R', 'Super-villain Team Up.		', 'image/suicideSquad.jpg', 'video/suicideSquad.mp4'),
(104, 'Split', 150, 2017, 'R', '13 Personalities', 'image/split.jpg', 'video/split.mp4'),
(105, 'Star Wars', 180, 2017, 'R', 'JUSTIN', 'image/starWars.jpeg', 'video/starWars.mp4');

-- --------------------------------------------------------

--
-- Table structure for table `seat`
--

CREATE TABLE `seat` (
  `hallType` varchar(10) NOT NULL,
  `Row Initial` varchar(10) NOT NULL,
  `Seat Number` int(6) NOT NULL,
  `Seat Quality` varchar(30) NOT NULL DEFAULT 'Standard',
  `Tray Table` varchar(10) NOT NULL DEFAULT 'false',
  `Price` double NOT NULL DEFAULT '12',
  `Availability` varchar(5) NOT NULL DEFAULT 'true'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seat`
--

INSERT INTO `seat` (`hallType`, `Row Initial`, `Seat Number`, `Seat Quality`, `Tray Table`, `Price`, `Availability`) VALUES
('2D', 'A', 1, 'Standard', 'false', 12, 'true'),
('2D', 'A', 2, 'Standard', 'false', 12, 'true'),
('2D', 'A', 3, 'Standard', 'false', 12, 'true'),
('2D', 'A', 4, 'Standard', 'false', 12, 'true'),
('2D', 'A', 5, 'Standard', 'false', 12, 'true'),
('2D', 'A', 6, 'Standard', 'false', 12, 'false'),
('2D', 'B', 1, 'Standard', 'false', 12, 'true'),
('2D', 'B', 2, 'Standard', 'false', 12, 'false'),
('2D', 'B', 3, 'Standard', 'false', 12, 'true'),
('2D', 'B', 4, 'Standard', 'false', 12, 'true'),
('2D', 'B', 5, 'Standard', 'false', 12, 'true'),
('2D', 'B', 6, 'Standard', 'false', 12, 'true'),
('2D', 'C', 1, 'Standard', 'false', 12, 'true'),
('2D', 'C', 2, 'Standard', 'false', 12, 'false'),
('2D', 'C', 3, 'Standard', 'false', 12, 'false'),
('2D', 'C', 4, 'Standard', 'false', 12, 'false'),
('2D', 'C', 5, 'Standard', 'false', 12, 'false'),
('2D', 'C', 6, 'Standard', 'false', 12, 'true'),
('2D', 'D', 1, 'Standard', 'false', 12, 'true'),
('2D', 'D', 2, 'Standard', 'false', 12, 'false'),
('2D', 'D', 3, 'Standard', 'false', 12, 'false'),
('2D', 'D', 4, 'Standard', 'false', 12, 'false'),
('2D', 'D', 5, 'Standard', 'false', 12, 'false'),
('2D', 'D', 6, 'Standard', 'false', 12, 'true'),
('2D', 'E', 1, 'Standard', 'false', 12, 'true'),
('2D', 'E', 2, 'Standard', 'false', 12, 'true'),
('2D', 'E', 3, 'Standard', 'false', 12, 'true'),
('2D', 'E', 4, 'Standard', 'false', 12, 'true'),
('2D', 'E', 5, 'Standard', 'false', 12, 'true'),
('2D', 'E', 6, 'Standard', 'false', 12, 'true'),
('IMAX', 'A', 1, 'Standard', 'false', 12, 'false'),
('IMAX', 'A', 2, 'Standard', 'false', 12, 'false'),
('IMAX', 'A', 3, 'Standard', 'false', 12, 'false'),
('IMAX', 'A', 4, 'Standard', 'false', 12, 'false'),
('IMAX', 'A', 5, 'Standard', 'false', 12, 'true'),
('IMAX', 'A', 6, 'Standard', 'false', 12, 'true'),
('IMAX', 'B', 1, 'Standard', 'false', 12, 'true'),
('IMAX', 'B', 2, 'Standard', 'false', 12, 'true'),
('IMAX', 'B', 3, 'Standard', 'false', 12, 'true'),
('IMAX', 'B', 4, 'Standard', 'false', 12, 'true'),
('IMAX', 'B', 5, 'Standard', 'false', 12, 'true'),
('IMAX', 'B', 6, 'Standard', 'false', 12, 'true'),
('IMAX', 'C', 1, 'Standard', 'false', 12, 'true'),
('IMAX', 'C', 2, 'Standard', 'false', 12, 'true'),
('IMAX', 'C', 3, 'Standard', 'false', 12, 'true'),
('IMAX', 'C', 4, 'Standard', 'false', 12, 'true'),
('IMAX', 'C', 5, 'Standard', 'false', 12, 'true'),
('IMAX', 'C', 6, 'Standard', 'false', 12, 'true'),
('IMAX', 'D', 1, 'Standard', 'false', 12, 'true'),
('IMAX', 'D', 2, 'Standard', 'false', 12, 'true'),
('IMAX', 'D', 3, 'Standard', 'false', 12, 'true'),
('IMAX', 'D', 4, 'Standard', 'false', 12, 'true'),
('IMAX', 'D', 5, 'Standard', 'false', 12, 'true'),
('IMAX', 'D', 6, 'Standard', 'false', 12, 'true'),
('IMAX', 'E', 1, 'Standard', 'false', 12, 'true'),
('IMAX', 'E', 2, 'Standard', 'false', 12, 'true'),
('IMAX', 'E', 3, 'Standard', 'false', 12, 'true'),
('IMAX', 'E', 4, 'Standard', 'false', 12, 'true'),
('IMAX', 'E', 5, 'Standard', 'false', 12, 'true'),
('IMAX', 'E', 6, 'Standard', 'false', 12, 'true'),
('INDULGE', 'A', 1, 'First Class', 'true', 18, 'true'),
('INDULGE', 'A', 2, 'First Class', 'true', 22, 'false'),
('INDULGE', 'A', 3, 'Standard', 'false', 12, 'true'),
('INDULGE', 'A', 4, 'Standard', 'false', 12, 'false'),
('INDULGE', 'A', 5, 'Standard', 'false', 12, 'true'),
('INDULGE', 'A', 6, 'Standard', 'false', 12, 'true'),
('INDULGE', 'B', 1, 'Standard', 'false', 12, 'true'),
('INDULGE', 'B', 2, 'Standard', 'false', 12, 'true'),
('INDULGE', 'B', 3, 'Standard', 'false', 12, 'true'),
('INDULGE', 'B', 4, 'Standard', 'false', 12, 'false'),
('INDULGE', 'B', 5, 'Standard', 'false', 12, 'true'),
('INDULGE', 'B', 6, 'Standard', 'false', 12, 'true'),
('INDULGE', 'C', 1, 'Standard', 'false', 12, 'true'),
('INDULGE', 'C', 2, 'Standard', 'false', 12, 'true'),
('INDULGE', 'C', 3, 'Standard', 'false', 12, 'false'),
('INDULGE', 'C', 4, 'Standard', 'false', 12, 'false'),
('INDULGE', 'C', 5, 'Standard', 'false', 12, 'true'),
('INDULGE', 'C', 6, 'Standard', 'false', 12, 'true'),
('INDULGE', 'D', 1, 'Premium', 'false', 22, 'true'),
('INDULGE', 'D', 2, 'Standard', 'false', 12, 'true'),
('INDULGE', 'D', 3, 'Standard', 'false', 12, 'true'),
('INDULGE', 'D', 4, 'Standard', 'false', 12, 'true'),
('INDULGE', 'D', 5, 'Standard', 'false', 12, 'true'),
('INDULGE', 'D', 6, 'Standard', 'false', 12, 'true'),
('INDULGE', 'E', 1, 'Standard', 'false', 12, 'true'),
('INDULGE', 'E', 2, 'Standard', 'false', 12, 'false'),
('INDULGE', 'E', 3, 'Standard', 'false', 12, 'true'),
('INDULGE', 'E', 4, 'Standard', 'false', 12, 'true'),
('INDULGE', 'E', 5, 'Standard', 'false', 12, 'true'),
('INDULGE', 'E', 6, 'Standard', 'false', 12, 'true'),
('LUXE', 'A', 1, 'Standard', 'false', 12, 'true'),
('LUXE', 'A', 2, 'Standard', 'false', 12, 'true'),
('LUXE', 'A', 3, 'Standard', 'false', 12, 'true'),
('LUXE', 'A', 4, 'Standard', 'false', 12, 'true'),
('LUXE', 'A', 5, 'Standard', 'false', 12, 'true'),
('LUXE', 'A', 6, 'Standard', 'false', 12, 'true'),
('LUXE', 'B', 1, 'Standard', 'false', 12, 'true'),
('LUXE', 'B', 2, 'Standard', 'false', 12, 'true'),
('LUXE', 'B', 3, 'Standard', 'false', 12, 'true'),
('LUXE', 'B', 4, 'Standard', 'false', 12, 'true'),
('LUXE', 'B', 5, 'Standard', 'false', 12, 'true'),
('LUXE', 'B', 6, 'Standard', 'false', 12, 'true'),
('LUXE', 'C', 1, 'Standard', 'false', 12, 'true'),
('LUXE', 'C', 2, 'Standard', 'false', 12, 'true'),
('LUXE', 'C', 3, 'Standard', 'false', 12, 'true'),
('LUXE', 'C', 4, 'Standard', 'false', 12, 'true'),
('LUXE', 'C', 5, 'Standard', 'false', 12, 'true'),
('LUXE', 'C', 6, 'Standard', 'false', 12, 'true'),
('LUXE', 'D', 1, 'Standard', 'false', 12, 'true'),
('LUXE', 'D', 2, 'Standard', 'false', 12, 'true'),
('LUXE', 'D', 3, 'Standard', 'false', 12, 'true'),
('LUXE', 'D', 5, 'Standard', 'false', 12, 'true'),
('LUXE', 'D', 6, 'Standard', 'false', 12, 'true'),
('LUXE', 'E', 1, 'Standard', 'false', 12, 'true'),
('LUXE', 'E', 2, 'Standard', 'false', 12, 'true'),
('LUXE', 'E', 3, 'Standard', 'false', 12, 'true'),
('LUXE', 'E', 4, 'Standard', 'false', 12, 'true'),
('LUXE', 'E', 5, 'Standard', 'false', 12, 'true'),
('LUXE', 'E', 6, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'A', 1, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'A', 2, 'Standard', 'false', 12, 'false'),
('PREMIUM', 'A', 3, 'Standard', 'false', 12, 'false'),
('PREMIUM', 'A', 4, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'A', 5, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'A', 6, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'B', 1, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'B', 2, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'B', 3, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'B', 4, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'B', 5, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'B', 6, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'C', 1, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'C', 2, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'C', 3, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'C', 4, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'C', 5, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'C', 6, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'D', 1, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'D', 2, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'D', 3, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'D', 4, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'D', 5, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'D', 6, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'E', 1, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'E', 2, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'E', 3, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'E', 4, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'E', 5, 'Standard', 'false', 12, 'true'),
('PREMIUM', 'E', 6, 'Standard', 'false', 12, 'true'),
('SUPREME', 'A', 1, 'Standard', 'false', 12, 'true'),
('SUPREME', 'A', 2, 'Standard', 'false', 12, 'true'),
('SUPREME', 'A', 3, 'Standard', 'false', 12, 'true'),
('SUPREME', 'A', 4, 'Standard', 'false', 12, 'true'),
('SUPREME', 'A', 5, 'Standard', 'false', 12, 'true'),
('SUPREME', 'A', 6, 'Standard', 'false', 12, 'true'),
('SUPREME', 'B', 1, 'Standard', 'false', 12, 'true'),
('SUPREME', 'B', 2, 'Standard', 'false', 12, 'true'),
('SUPREME', 'B', 3, 'Standard', 'false', 12, 'true'),
('SUPREME', 'B', 4, 'Standard', 'false', 12, 'true'),
('SUPREME', 'B', 5, 'Standard', 'false', 12, 'true'),
('SUPREME', 'B', 6, 'Standard', 'false', 12, 'true'),
('SUPREME', 'C', 1, 'Standard', 'false', 12, 'false'),
('SUPREME', 'C', 3, 'Standard', 'false', 12, 'false'),
('SUPREME', 'C', 4, 'Standard', 'false', 12, 'false'),
('SUPREME', 'C', 5, 'Standard', 'false', 12, 'true'),
('SUPREME', 'C', 6, 'Standard', 'false', 12, 'true'),
('SUPREME', 'D', 1, 'Standard', 'false', 12, 'true'),
('SUPREME', 'D', 2, 'Standard', 'false', 12, 'true'),
('SUPREME', 'D', 3, 'Standard', 'false', 12, 'false'),
('SUPREME', 'D', 5, 'Standard', 'false', 12, 'true'),
('SUPREME', 'D', 6, 'Standard', 'false', 12, 'true'),
('SUPREME', 'E', 1, 'Standard', 'false', 12, 'true'),
('SUPREME', 'E', 2, 'Standard', 'false', 12, 'true'),
('SUPREME', 'E', 3, 'Standard', 'false', 12, 'true'),
('SUPREME', 'E', 4, 'Standard', 'false', 12, 'true'),
('SUPREME', 'E', 5, 'Standard', 'false', 12, 'true'),
('SUPREME', 'E', 6, 'Standard', 'false', 12, 'true');

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `Title` varchar(50) NOT NULL,
  `Date` varchar(20) NOT NULL,
  `timeStart` int(10) NOT NULL,
  `Status` varchar(15) NOT NULL,
  `Type` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`Title`, `Date`, `timeStart`, `Status`, `Type`) VALUES
('Split', '2017-05-09', 1230, 'INDULGE', '2D'),
('Avengers', '2017-04-05', 1700, 'Now Seating', 'IMAX'),
('Deadpool', '2017-04-12', 1230, 'Now Seating', 'LUXE'),
('Suicide Squad', '2017-04-06', 1700, 'On sale', 'PREMIUM');

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `ticket_ID` varchar(20) NOT NULL,
  `movieTitle` varchar(100) NOT NULL,
  `showDate` varchar(10) NOT NULL,
  `showTime` int(50) NOT NULL,
  `hallType` varchar(11) NOT NULL,
  `seatLocation` varchar(20) NOT NULL,
  `timePurchase` varchar(50) NOT NULL,
  `billAmount` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`ticket_ID`, `movieTitle`, `showDate`, `showTime`, `hallType`, `seatLocation`, `timePurchase`, `billAmount`) VALUES
('20170426160604', 'Suicide Squad', '2017-04-06', 1700, 'PREMIUM', '[A3,A4]', '2017/04/26 16:06:04', 24),
('20170426161421', 'Titanic', '2017-04-21', 1330, 'INDULGE', '[B4,A4]', '2017/04/26 16:14:21', 24),
('20170426161501', 'Titanic', '2017-04-21', 1330, 'INDULGE', '[B4,A2]', '2017/04/26 16:15:01', 24),
('20170426165430', 'Titanic', '2017-04-14', 1030, 'SUPREME', '[C4]', '2017/04/26 16:54:30', 12),
('20170426165515', 'Titanic', '2017-04-21', 1330, 'INDULGE', '[E2]', '2017/04/26 16:55:15', 12),
('20170426170157', 'Avengers', '2017-04-05', 1700, 'IMAX', '[A1]', '2017/04/26 17:01:57', 12),
('20170426170441', 'Avengers', '2017-04-05', 1700, 'IMAX', '[A4]', '2017/04/26 17:04:41', 12),
('20170426170636', 'Split', '2017-05-09', 1230, '2D', '[B2]', '2017/04/26 17:06:36', 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`movieID`);

--
-- Indexes for table `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`hallType`,`Row Initial`,`Seat Number`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`Type`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ticket_ID`),
  ADD UNIQUE KEY `movieTitle` (`movieTitle`,`showDate`,`showTime`,`hallType`,`seatLocation`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
