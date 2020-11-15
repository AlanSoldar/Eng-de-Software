package com.example.demo.mlp;

import com.example.demo.entities.Produto;
import com.example.demo.entities.Usuario;

public class Java10 {
    public static void main(String[] args) {
        //Local variable type inference -> var

        var produto = new Produto();

        //var usuario;

//        produto = new Usuario();

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
