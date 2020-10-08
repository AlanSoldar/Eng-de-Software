DROP TABLE IF EXISTS user;

CREATE TABLE user (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NOME VARCHAR(250) NOT NULL,
  MATRICULA NUMERIC NOT NULL
);

INSERT INTO user (nome, matricula) VALUES
  ('alan', 123),
  ('leo', 234),
  ('roger', 345),
  ('gabs', 456),
  ('gui', 567);

DROP TABLE IF EXISTS product;

CREATE TABLE product (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NOME VARCHAR(250) NOT NULL,
  PRECO NUMERIC NOT NULL
);

INSERT INTO product (nome, preco) VALUES
  ('Genshin Impact', 3900),
  ('Rocket League', 2500),
  ('Among Us', 1000),
  ('Persona 5', 15000),
  ('Final Fantasy XIV', 12000);