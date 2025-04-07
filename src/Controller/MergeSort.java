package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class MergeSort {



    /**Está sendo feito o Merge Sort juntamente com uma comparação.
    s -> s.split(" - ")[0] = Extrai o nome da string.
    String.CASE_INSENSITIVE_ORDER = Faz a ordenação sem considerar letras maiúsculas ou minúsculas (Carlos = carlos).
    Comparator.<String, String>comparing(...) = Cria um Comparator<String> que compara as strings pelo nome extraído.
    mergeSort(data, comparador) = Chama o algoritmo de ordenação (mergeSort) passando o array e o comparador.
    */

    public static String[] SortbyName(String[] data) {
        return mergeSort(data, 0, data.length-  1, Comparator.<String, String>comparing(s -> s.split(" - ")
                [0].trim(), String.CASE_INSENSITIVE_ORDER));
    }




    /**
    DateTimeFormatter formatter = ...
    Define o formato de data esperado: "dd/MM/yyyy" (ex: 10/05/2000).

    Dentro do Comparator.comparing():
    s.split(" - ")
    Divide a string em nome e data. Exemplo:
    "Carlos - 23/11/1998" → ["Carlos", "23/11/1998"]

    Verifica se a data existe:
    Se partes.length < 2, a string está mal formatada,
    então retorna LocalDate.MIN (menor data possível, usada como fallback).

    Tenta converter a data:
    Se for válida, retorna como LocalDate, que o Java consegue comparar de forma cronológica.

    Se der erro (data inválida):
    Retorna LocalDate.MIN.

    O Comparator compara as datas de nascimento como objetos LocalDate.

    O mergeSort() ordena o array com base nas datas.
    */
    public static String[] SortbyDate(String[] data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Comparator<String> comparator = Comparator.comparing(s -> {
            String[] part = s.split(" - ");
            if (part.length < 2) {
                return LocalDate.MIN;
            }
            try {
                return LocalDate.parse(part[1].trim(), formatter);
            } catch (Exception e) {
                return LocalDate.MIN;
            }
        });

        return mergeSort(data, 0, data.length - 1, comparator);
    }



    private static String[] mergeSort(String[] array, int start, int end, Comparator<String> comparator) {
        if (start >= end) {
            return new String[]{array[start]};
        }

        int mid = start + (end - start) / 2;
        String[] left = mergeSort(array, start, mid, comparator);
        String[] right = mergeSort(array, mid + 1, end, comparator);

        return merge(left, right, comparator);
    }

    // Merge de dois arrays ordenados
    private static String[] merge(String[] left, String[] right, Comparator<String> comparator) {
        String[] result = new String[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }
}
