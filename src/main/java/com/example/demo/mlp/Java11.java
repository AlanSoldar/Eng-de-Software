package com.example.demo.mlp;

import org.springframework.lang.Nullable;

import java.util.function.BiFunction;

public class Java11 {
    public static void main(String[] args) {

        //Local Variable Syntax for Lambda Parameters

        BiFunction<String, String, String> printaParametros = (@Nullable var p1, var p2) -> String.format("Multiple parameters: parameter1=%s parameter2=%s", p1, p2);

        System.out.println(printaParametros.apply("p1", "p2"));
    }
}
