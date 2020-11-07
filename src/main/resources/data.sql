DROP TABLE IF EXISTS USUARIO;

CREATE TABLE USUARIO (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NOME VARCHAR(250) NOT NULL,
  IDADE NUMERIC NOT NULL,
  SEXO VARCHAR(250),
  USUARIO VARCHAR(250) NOT NULL,
  PASSWORD VARCHAR(250) NOT NULL,
  SALDO NUMERIC,
  ENDERECO VARCHAR(1000),
  UNIQUE(USUARIO, PASSWORD)
);

INSERT INTO USUARIO (nome, idade, sexo, usuario, password, saldo, endereco) VALUES
  ('alan', 12, 'indefinido', 'alin', '1234testando', 5000, 'minha casa'),
  ('leo', 15, 'helicoptero apache', 'Giba7', 'alice', 0, 'perto do nacional'),
  ('roger', 18, 'masculino', 'roger', 'pacoca', 1000, null),
  ('gabs', 20, 'feminino', 'gabigol9', 'miabrothers', 10000, 'longe bagarai'),
  ('guilherme', 50, 'masculino', 'gui', 'password', 2000, null);

DROP TABLE IF EXISTS PRODUTO;

CREATE TABLE PRODUTO (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NOME VARCHAR(250) NOT NULL,
  PRECO NUMERIC NOT NULL,
  DESCRICAO VARCHAR(50000),
  USUARIO_ID INT NOT NULL,
    FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID)
);

INSERT INTO PRODUTO (NOME, PRECO, DESCRICAO, USUARIO_ID) VALUES
  ('Genshin Impact', 3900, 'RPG mundo aberto estilo cell shaded de animação com coop opcional.',1),
  ('Rocket League', 2500, 'Esport com a premissa de juntar futebol com jogo de corrida em um futebol de carros.',1),
  ('Among Us', 1000, 'Party game de interpretação com o objetivo de ou desmascarar o impostor ou eliminar todos os tripulantes.',2),
  ('Persona 5', 15000, 'RPG focado na história do dia dia de um grupo de jovens que descobre um mundo fantástico.',4),
  ('Final Fantasy XIV', 12000, 'MMORPG da franquia final fantasy inspirado em world of warcraft.',2),
  ('God of War', 80000, 'God of War é uma série de jogos eletrônicos de ação-aventura vagamente baseado nas mitologias grega e nórdica.',3),
  ('Life is Strange', 30000, 'Life Is Strange é um jogo eletrônico episódico de aventura gráfica.',4),
  ('Nier: Automata', 100000, 'é um jogo eletrônico de RPG de ação desenvolvido pela PlatinumGames e publicado pela Square Enix.',1),
  ('Monster Hunter', 90000, 'A franquia Monster Hunter é uma série de jogos de fantasia desenvolvida e publicada pela Capcom.',2)
;

DROP TABLE IF EXISTS BIBLIOTECA;

CREATE TABLE BIBLIOTECA (
  USUARIO_ID INT NOT NULL,
  PRODUTO_ID INT NOT NULL,
  PRIMARY KEY (USUARIO_ID, PRODUTO_ID),
  FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID),
  FOREIGN KEY (PRODUTO_ID) REFERENCES PRODUTO(ID)
);

INSERT INTO BIBLIOTECA (USUARIO_ID, PRODUTO_ID) VALUES
(1,1),
(1,2),
(1,8),
(1,9),
(2,3),
(3,4),
(3,7);

DROP TABLE IF EXISTS COMUNIDADE;

CREATE TABLE COMUNIDADE (
  USUARIO_ID INT NOT NULL,
  PRODUTO_ID INT NOT NULL,
  VALOR NUMERIC,
  PRIMARY KEY (USUARIO_ID, PRODUTO_ID),
  FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID),
  FOREIGN KEY (PRODUTO_ID) REFERENCES PRODUTO(ID)
);

INSERT INTO COMUNIDADE (USUARIO_ID, PRODUTO_ID) VALUES
(1,2),
(1,8),
(3,4);