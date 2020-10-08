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
  PRECO NUMERIC NOT NULL,
  DESCRICAO VARCHAR(50000)
);

INSERT INTO product (nome, preco, descricao) VALUES
  ('Genshin Impact', 3900, 'RPG mundo aberto estilo cell shaded de animação com coop opcional.'),
  ('Rocket League', 2500, 'Esport com a premissa de juntar futebol com jogo de corrida em um futebol de carros.'),
  ('Among Us', 1000, 'Party game de interpretação com o objetivo de ou desmascarar o impostor ou eliminar todos os tripulantes.'),
  ('Persona 5', 15000, 'RPG focado na história do dia dia de um grupo de jovens que descobre um mundo fantástico.'),
  ('Final Fantasy XIV', 12000, 'MMORPG da franquia final fantasy inspirado em world of warcraft.'),
  ('God of War', 80000, 'God of War é uma série de jogos eletrônicos de ação-aventura vagamente baseado nas mitologias grega e nórdica.'),
  ('Life is Strange', 30000, 'Life Is Strange é um jogo eletrônico episódico de aventura gráfica.'),
  ('Nier: Automata', 100000, 'é um jogo eletrônico de RPG de ação desenvolvido pela PlatinumGames e publicado pela Square Enix.'),
  ('Monster Hunter', 90000, 'A franquia Monster Hunter é uma série de jogos de fantasia desenvolvida e publicada pela Capcom.')
;