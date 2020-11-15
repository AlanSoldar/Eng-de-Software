package com.example.demo.mlp;

public class Java15 {
    public static void main(String[] args) {

        //Text Blocks

        String sqlAntes = "CREATE TABLE PRODUTO (" +
                "ID INT AUTO_INCREMENT  PRIMARY KEY," +
                "NOME VARCHAR(250) NOT NULL," +
                "PRECO NUMERIC NOT NULL," +
                "DESCRICAO VARCHAR(50000)," +
                "USUARIO_ID INT NOT NULL," +
                "FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID)" +
                ");";

        System.out.println(sqlAntes);


        String sqlDepois = """
                  CREATE TABLE PRODUTO (
                  ID INT AUTO_INCREMENT  PRIMARY KEY,
                  NOME VARCHAR(250) NOT NULL,
                  PRECO NUMERIC NOT NULL,
                  DESCRICAO VARCHAR(50000),
                  USUARIO_ID INT NOT NULL,
                    FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID)
                );
                """;

        System.out.println(sqlDepois);
    }



}
