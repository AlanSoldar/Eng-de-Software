DROP TABLE IF EXISTS aluno;

CREATE TABLE aluno (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NOME VARCHAR(250) NOT NULL,
  MATRICULA NUMERIC NOT NULL
);

INSERT INTO aluno (nome, matricula) VALUES
  ('alan', 123),
  ('leo', 234),
  ('roger', 345),
  ('gabs', 456),
  ('gui', 567);