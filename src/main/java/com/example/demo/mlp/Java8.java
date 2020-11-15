package com.example.demo.mlp;

import com.example.demo.entities.Produto;
import com.example.demo.entities.Usuario;

import java.util.*;
import java.util.function.Consumer;

public class Java8 {
    public static void main(String[] args) {

        //1 - Default methods in interfaces
        //2 - new methods in popular interfaces
        //3 - lambda
        //4 - Optional

        System.out.println("Antes\n");

        List<Produto> shopList = new ArrayList<>();
        shopList.add(Ultilidades.createProduto("carne", 20L));
        shopList.add(Ultilidades.createProduto("presunto", 10L));
        shopList.add(Ultilidades.createProduto("leite", 5L));
        shopList.add(Ultilidades.createProduto("chocolate", 2L));

        for (Produto element : shopList) {
            System.out.println(element);
        }


        //----------------------------------------------------------------------------------------


        System.out.println("\nDepois sem lambda\n");

        Consumer<Produto> consumer = new PrintaElemento();
        shopList.forEach(consumer);


        //----------------------------------------------------------------------------------------


        System.out.println("\nDepois com lambda\n");

        shopList.forEach(System.out::println);


        //----------------------------------------------------------------------------------------

        System.out.println("\nSort antes\n");

        shopList = Ultilidades.resetList(shopList);

        Comparator<Produto> comparator = new OrdenaLista();
        Collections.sort(shopList, comparator);

        shopList.forEach(System.out::println);


        //----------------------------------------------------------------------------------------

        System.out.println("\nSort depois sem lambda\n");

        shopList = Ultilidades.resetList(shopList);

        shopList.sort(comparator);

        shopList.forEach(System.out::println);


        //----------------------------------------------------------------------------------------


        System.out.println("\nSort depois com lambda\n");

        shopList = Ultilidades.resetList(shopList);

        shopList.sort((elemento1, elemento2) -> elemento1.getPreco().compareTo(elemento2.getPreco()));

        shopList.forEach(System.out::println);


        //----------------------------------------------------------------------------------------


        System.out.println("\nLambda showcase\n");

        shopList.stream().filter(produto -> produto.getPreco() < 20L)
                .map(Produto::getNome)
                .forEach(System.out::println);


        //----------------------------------------------------------------------------------------


        System.out.println("\nOptional showcase\n");

        Usuario usuario = Ultilidades.findUsuarioById(2L);
        //System.out.println(usuario.getNome());

        if (usuario != null) {
            System.out.println(usuario.getNome());
        } else {
            System.out.println("usuario not found");
        }


        Ultilidades.findOptionalUsuarioById(2L)
                .ifPresentOrElse(
                        usuario1 -> System.out.println(usuario1.getNome())
                        , () -> System.out.println("usuario not found"));

    }

    static class PrintaElemento implements Consumer<Produto> {

        @Override
        public void accept(Produto produto) {
            System.out.println(produto);
        }
    }

    static class OrdenaLista implements Comparator<Produto> {
        @Override
        public int compare(Produto produto1, Produto produto2) {
            if (produto1.getNome().length() < produto2.getNome().length()) {
                return -1;
            }
            if (produto1.getNome().length() > produto2.getNome().length()) {
                return 1;
            }
            return 0;
        }
    }

    static class Ultilidades {
        public static Produto createProduto(String nome, Long preco) {
            return Produto.builder().nome(nome).preco(preco).build();
        }

        public static List<Produto> resetList(List<Produto> list) {
            List<Produto> shopList = new ArrayList<>();
            shopList.add(Ultilidades.createProduto("carne", 20L));
            shopList.add(Ultilidades.createProduto("presunto", 10L));
            shopList.add(Ultilidades.createProduto("leite", 5L));
            shopList.add(Ultilidades.createProduto("chocolate", 2L));
            return shopList;
        }

        public static Usuario findUsuarioById(Long id) {

            if (id.equals(1L))
                return Usuario.builder().id(1L).nome("joao").build();
            else
                return null;
        }

        public static Optional<Usuario> findOptionalUsuarioById(Long id) {
            if (id.equals(1L))
                return Optional.of(Usuario.builder().id(1L).nome("joao").build());
            else
                return Optional.empty();
        }
    }

}
