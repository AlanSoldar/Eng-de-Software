package com.example.demo.mlp;

import com.example.demo.entities.Produto;

public class Java10 {
    public static void main(String[] args) {
        //Local variable type inference -> var

        //var usuario;
        var produto = new Produto();

        System.out.println(produto);

        System.out.println(produto.getClass());

    }

//    static class VarTest {
//        var variavelIndefinida = 0L;
//
//        private void metodoTeste(var indefinido) {
//            System.out.println(indefinido);
//        }
//    }
}
