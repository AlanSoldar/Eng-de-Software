package com.example.demo.mlp;


import java.util.function.BiFunction;

public class Java11 {
    public static void main(String[] args) {

        //Local Variable Syntax for Lambda Parameters

        BiFunction<String, String, String> printaParametros = (p1, p2) -> String.format("Multiple parameters: parameter1=%s parameter2=%s", p1, p2);

        System.out.println(printaParametros.apply("p1", "p2"));
    }
}
