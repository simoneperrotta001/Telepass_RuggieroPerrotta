CREATE DATABASE telepass;
CREATE TABLE `casello` (
  `NomeCasello` varchar(20) NOT NULL
);


INSERT INTO `casello` (`NomeCasello`) VALUES
('Bologna'),
('Firenze'),
('Genova'),
('Milano'),
('Napoli'),
('Roma'),
('Taranto'),
('Torino'),
('Trieste');

CREATE TABLE `cliente` (
  `CodiceTransponder` int(11) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `NomeCliente` varchar(20) NOT NULL,
  `CognomeCliente` varchar(20) NOT NULL,
  `NascitaCliente` date NOT NULL,
  `CodiceContoCorrente` varchar(12) NOT NULL,
  `TransponderAttivo` int(11) NOT NULL,
  `Plus` int(11) NOT NULL,
  `Amministratore` int(11) NOT NULL,
  `Password` varchar(40) NOT NULL
);

INSERT INTO `cliente` (`CodiceTransponder`, `Username`, `NomeCliente`, `CognomeCliente`, `NascitaCliente`, `CodiceContoCorrente`, `TransponderAttivo`, `Plus`, `Amministratore`, `Password`) VALUES
(1, 'Massimino', 'Massimo', 'Carriolo', '2001-03-23', '000000000001', 1, 1, 0, 'massimo1'),
(2, 'SimoncinoMici', 'Simone', 'Micillo', '2002-01-17', '000000000002', 1, 0, 0, 'simone2'),
(3, 'admin', 'admin', 'admin', '2002-01-17', '900000000001', 0, 0, 1, 'admin');



CREATE TABLE `dista` (
  `NomeCasello1` varchar(20) NOT NULL,
  `NomeCasello2` varchar(20) NOT NULL,
  `Distanza` float NOT NULL
);


INSERT INTO `dista` (`NomeCasello1`, `NomeCasello2`, `Distanza`) VALUES
('Bologna', 'Firenze', 119),
('Bologna', 'Genova', 293),
('Bologna', 'Milano', 213.6),
('Bologna', 'Napoli', 572),
('Bologna', 'Roma', 375),
('Bologna', 'Taranto', 733.4),
('Bologna', 'Torino', 332),
('Bologna', 'Trieste', 302),
('Firenze', 'Genova', 244),
('Firenze', 'Milano', 314),
('Firenze', 'Napoli', 733.4),
('Firenze', 'Roma', 273),
('Firenze', 'Taranto', 758),
('Firenze', 'Torino', 433),
('Firenze', 'Trieste', 417.89),
('Genova', 'Napoli', 701.54),
('Genova', 'Roma', 505),
('Genova', 'Taranto', 992.23),
('Genova', 'Torino', 180),
('Genova', 'Trieste', 544),
('Milano', 'Genova', 133.6),
('Milano', 'Napoli', 763.3),
('Milano', 'Roma', 573),
('Milano', 'Taranto', 965),
('Milano', 'Torino', 145),
('Milano', 'Trieste', 417.3),
('Napoli', 'Roma', 224),
('Napoli', 'Taranto', 341),
('Napoli', 'Torino', 889.4),
('Napoli', 'Trieste', 901),
('Roma', 'Taranto', 510),
('Roma', 'Torino', 690),
('Roma', 'Trieste', 673),
('Taranto', 'Torino', 1081),
('Taranto', 'Trieste', 1050),
('Torino', 'Trieste', 524);


CREATE TABLE `entra` (
  `TargaVeicolo` varchar(10) NOT NULL,
  `NomeCasello` varchar(20) NOT NULL,
  `OrarioEntrata` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
);



CREATE TABLE `esce` (
  `TargaVeicolo` varchar(10) NOT NULL,
  `NomeCasello` varchar(20) NOT NULL,
  `OrarioUscita` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `Tariffa` float NOT NULL
);


CREATE TABLE `veicolo` (
  `TargaVeicolo` varchar(10) NOT NULL,
  `CodiceTransponder` int(11) NOT NULL,
  `ModelloVeicolo` varchar(1) NOT NULL
);

INSERT INTO `veicolo` (`TargaVeicolo`, `CodiceTransponder`, `ModelloVeicolo`) VALUES
('AA000AA', 1, 'A'),
('AA000BB', 1, 'B'),
('BB000AA', 2, 'A'),
('BB000BB', 2, 'B');


ALTER TABLE `casello`
  ADD PRIMARY KEY (`NomeCasello`);

ALTER TABLE `cliente`
  ADD PRIMARY KEY (`CodiceTransponder`);

ALTER TABLE `dista`
  ADD PRIMARY KEY (`NomeCasello1`,`NomeCasello2`),
  ADD KEY `NomeCasello2` (`NomeCasello2`);


ALTER TABLE `entra`
  ADD PRIMARY KEY (`OrarioEntrata`,`TargaVeicolo`,`NomeCasello`),
  ADD KEY `TargaVeicolo` (`TargaVeicolo`),
  ADD KEY `NomeCasello` (`NomeCasello`);


ALTER TABLE `esce`
  ADD PRIMARY KEY (`OrarioUscita`,`TargaVeicolo`,`NomeCasello`),
  ADD KEY `TargaVeicolo` (`TargaVeicolo`),
  ADD KEY `NomeCasello` (`NomeCasello`);


ALTER TABLE `veicolo`
  ADD PRIMARY KEY (`TargaVeicolo`),
  ADD KEY `CodiceTransponder` (`CodiceTransponder`);


ALTER TABLE `cliente`
  MODIFY `CodiceTransponder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

ALTER TABLE `dista`
  ADD CONSTRAINT `dista_ibfk_1` FOREIGN KEY (`NomeCasello1`) REFERENCES `casello` (`NomeCasello`),
  ADD CONSTRAINT `dista_ibfk_2` FOREIGN KEY (`NomeCasello2`) REFERENCES `casello` (`NomeCasello`);


ALTER TABLE `entra`
  ADD CONSTRAINT `entra_ibfk_1` FOREIGN KEY (`TargaVeicolo`) REFERENCES `veicolo` (`TargaVeicolo`),
  ADD CONSTRAINT `entra_ibfk_2` FOREIGN KEY (`NomeCasello`) REFERENCES `casello` (`NomeCasello`);


ALTER TABLE `esce`
  ADD CONSTRAINT `esce_ibfk_1` FOREIGN KEY (`TargaVeicolo`) REFERENCES `veicolo` (`TargaVeicolo`),
  ADD CONSTRAINT `esce_ibfk_2` FOREIGN KEY (`NomeCasello`) REFERENCES `casello` (`NomeCasello`);


ALTER TABLE `veicolo`
  ADD CONSTRAINT `veicolo_ibfk_1` FOREIGN KEY (`CodiceTransponder`) REFERENCES `cliente` (`CodiceTransponder`);

CREATE VIEW ContaQuantiViaggi AS (
SELECT V.CodiceTransponder, E.TargaVeicolo, E.NomeCasello, E.OrarioUscita, E.Tariffa
FROM ESCE E JOIN VEICOLO V ON E.TargaVeicolo=V.TargaVeicolo)
