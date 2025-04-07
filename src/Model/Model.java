package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<String> dados;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Model() {
        this.dados = new ArrayList<>();
    }


    public List<String> getDados() {
        return new ArrayList<>(dados); //
    }

    public void setDados(List<String> dados) {
        this.dados = new ArrayList<>(dados);
    }


    public void adicionarDado(String nome, String data) throws IllegalArgumentException {
        if (nome == null || nome.trim().isEmpty() || data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("Preencha todos os campos (Nome e Data).");
        }

        try {
            LocalDate dataValidada = LocalDate.parse(data.trim(), DATE_FORMATTER);
            String registro = nome.trim() + " - " + dataValidada.format(DATE_FORMATTER);
            dados.add(registro);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida! Use dd/MM/yyyy (ex: 25/12/2023).");
        }
    }


    public void limparDados() {
        dados.clear();
    }

    public String[] getDadosAsArray() {
        return dados.toArray(new String[0]);
    }
}