package com.example.demo.mlp;

public class Java14 {
    public static void main(String[] args) {

        //Switch expressions

        System.out.println(DiaUtils.analisaDia("SEGUNDA"));

        System.out.println(DiaUtils.analisaDiaEnhanced("SABADO"));

    }

    static class DiaUtils {

        private static String analisaDia(String dia) {

            switch (dia) {
                case "SEGUNDA":
                case "TERÇA":
                case "QUARTA":
                case "QUINTA":
                case "SEXTA":
                    return "dia de semana";
                case "SABADO":
                case "DOMINGO":
                    return "fim de semana";
                default:
                    return "dia invalido";
            }
        }

        private static String analisaDiaEnhanced(String dia) {

            return switch (dia) {
                case "SEGUNDA", "TERÇA", "QUARTA", "QUINTA", "SEXTA" -> "dia de semana";
                case "SABADO", "DOMINGO" -> "fim de semana";
                default -> "dia invalido";
            };
        }
    }
}
