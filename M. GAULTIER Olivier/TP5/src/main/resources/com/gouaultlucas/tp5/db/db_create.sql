DROP DATABASE IF EXISTS db_tp5_gouaultlucas;
CREATE DATABASE IF NOT EXISTS db_tp5_gouaultlucas;
USE db_tp5_gouaultlucas;


CREATE TABLE Compte (
    numero INT PRIMARY KEY,
    type INT,
    solde FLOAT,
    taux_placement FLOAT DEFAULT NULL
);

CREATE TABLE LigneComptable (
    id INT PRIMARY KEY AUTO_INCREMENT,
    compte_numero INT,
    somme FLOAT,
    date DATE,
    motif VARCHAR(255),
    mode_paiement INT,
    CONSTRAINT fk_compte FOREIGN KEY (compte_numero) REFERENCES Compte(numero)
);
