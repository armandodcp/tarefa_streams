package br.com.backend.curso.ebac;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite os nomes de pessoas junto ao sexo, Ex.: Rodrigo-M, separados por vírgula:");
        String input = scanner.nextLine();
        scanner.close();

        // Teste: Luis-M,Talita-F,Alexandre-M,Maíra-F,Vinicius-M,Leandro-M,Tatiane-F,Beatriz-F,Larissa
        List<Pessoas> pessoas = List.of(input.split(",")).stream()
                .map(pessoa -> {
                    String[] dados = pessoa.trim().split("-");

                    if (dados.length == 2) {
                        return new Pessoas(dados[0], dados[1]);
                    } else {
                        System.out.println();
                        System.out.println("Entrada inválida ignorada: " + pessoa);
                        return null;
                    }
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<Pessoas> mulheres = pessoas.stream()
                .filter(mulher -> "F".equalsIgnoreCase((mulher.getSexo())))
                .sorted(Comparator.comparing(Pessoas::getNome))
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("**** Tarefa - Listar as mulheres do grupo ****");
        System.out.println();
        mulheres.forEach(mulher -> System.out.println(mulher.getNome()));
    }
}
