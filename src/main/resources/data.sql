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
  ('roger', 18, 'masculino', 'roger', 'pacoca', 6000, null),
  ('gabs', 20, 'feminino', 'gabigol9', 'miabrothers', 10000, 'longe bagarai'),
  ('guilherme', 50, 'masculino', 'gui', 'password', 2000, null);

DROP TABLE IF EXISTS PRODUTO;

CREATE TABLE PRODUTO (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NOME VARCHAR(250) NOT NULL,
  PRECO NUMERIC NOT NULL,
  DESCRICAO VARCHAR(50000)
);

INSERT INTO PRODUTO (NOME, PRECO, DESCRICAO) VALUES
  ('Genshin Impact', 3900, 'RPG mundo aberto estilo cell shaded de animacao com coop opcional.'),
  ('Rocket League', 2500, 'Esport com a premissa de juntar futebol com jogo de corrida em um futebol de carros.'),
  ('Among Us', 1000, 'Party game de interpretacao com o objetivo de ou desmascarar o impostor ou eliminar todos os tripulantes.'),
  ('Persona 5', 120000, 'RPG focado na historia do dia dia de um grupo de jovens que descobre um mundo fantastico.'),
  ('Final Fantasy XIV', 12000, 'MMORPG da franquia final fantasy inspirado em world of warcraft.'),
  ('God of War', 80000, 'God of War e uma serie de jogos eletrônicos de acao-aventura vagamente baseado nas mitologias grega e nordica.'),
  ('Life is Strange', 30000, 'Life Is Strange e um jogo eletronico episodico de aventura grafica.'),
  ('Nier: Automata', 100000, 'e um jogo eletronico de RPG de acao desenvolvido pela PlatinumGames e publicado pela Square Enix.'),
  ('Monster Hunter', 90000, 'A franquia Monster Hunter e uma serie de jogos de fantasia desenvolvida e publicada pela Capcom.'),
  ('Assassins Creed: Valhalla', 150000, 'Assassins Creed Valhalla, o mais novo jogo da franquia Assassins Creed.'),
  ('Kingdom Hearts 3', 100000, 'KINGDOM HEARTS III conta a historia do poder da amizade enquanto Sora e seus amigos embarcam em uma perigosa aventura.'),
  ('Phasmophobia', 27000, 'Phasmophobia e um terror psicologico cooperativo online para 4 jogadores'),
  ('Fall Guys: Ultimate Knockout', 38000, 'Fall Guys: Ultimate Knockout reune hordas de participantes online em disparada por rounds e rounds cada vez mais caóticos ate sobrar um unico vencedor!'),
  ('Nioh', 42000, 'Prepare sua espada: combates intensos estao à sua espera em todos os cantos desse RPG de acao que se passa em uma grande regiao devastada pela guerra civil.'),
  ('Terraria', 20000, 'Cave, lute, explore, construa! Nada e impossível nesse jogo de aventura cheio de acao. O mundo e a sua tela de pintar e o chao em si e a sua tinta.');
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
(1,11),
(2,3),
(3,4),
(3,7),
(3,10),
(3,9),
(3,3),
(3,8),
(4,2),
(4,5),
(4,6),
(2,7),
(5,2),
(5,8);

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
(3,4),
(4,6),
(2,7),
(5,8),
(3,9),
(3,3),
(3,8);

DROP TABLE IF EXISTS CHAT;

CREATE TABLE CHAT (
  USUARIO_1_ID INT NOT NULL,
  USUARIO_2_ID INT NOT NULL,
  RESOLVIDO BIT NOT NULL,
  PRIMARY KEY (USUARIO_1_ID, USUARIO_2_ID),
  FOREIGN KEY (USUARIO_1_ID) REFERENCES USUARIO(ID),
  FOREIGN KEY (USUARIO_2_ID) REFERENCES USUARIO(ID)
);

INSERT INTO CHAT (USUARIO_1_ID, USUARIO_2_ID, RESOLVIDO) VALUES
(1,2,0),
(1,3,0),
(2,3,1),
(2,4,0),
(1,5,0);

DROP TABLE IF EXISTS CHAT_CONTEUDO;

CREATE TABLE CHAT_CONTEUDO (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  USUARIO_1_ID INT NOT NULL,
  USUARIO_2_ID INT NOT NULL,
  REMETENTE_ID INT NOT NULL,
  MENSAGEM VARCHAR(300) NOT NULL,
  DATE_TIME INT NOT NULL,
  FOREIGN KEY (USUARIO_1_ID, USUARIO_2_ID) REFERENCES CHAT(USUARIO_1_ID, USUARIO_2_ID)
);

INSERT INTO CHAT_CONTEUDO (USUARIO_1_ID, USUARIO_2_ID, REMETENTE_ID, MENSAGEM, DATE_TIME) VALUES
(1,3,1,'oi', 1),
(1,3,1,'q ta fazendo?',2),
(1,3,3,'testando o chat XD',3),
(1,2,2,'ola', 4),
(1,2,2,'gostou do game?',5),
(1,2,1,'nao :(',6),
(1,2,2,'pq?',7),
(1,2,1,'muito grind...',8),
(1,2,1,'queria outro',9),
(2,4,2,'ola',10),
(2,4,2,'gostou do game?',11),
(2,4,4,'sim!!',12),
(2,4,4,'valeu muito a pena.',13),
(1,5,1,'oi',14);