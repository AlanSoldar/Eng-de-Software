package com.example.demo.mlp;

import java.util.List;
import java.util.Map;

public class Java9 {
    public static void main(String[] args) {

        //1 - JShell
        //2 - static and private methods in interfaces
        //3 - jigsaw modules
        List.of("a", "b", "c").forEach(System.out::println);
        Map.of("chave1", 1, "chave2", 2, "chave3", 3).forEach((chave, valor) -> System.out.printf("chave=%s -> valor=%d%n", chave, valor));

    }
}

