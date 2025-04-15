package com.example.aula_ead_lais;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    @SerializedName("estudantes")
    @Expose
    private List<Estudante> estudantes;

    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<Estudante> estudantes) {
        this.estudantes = estudantes;
    }

    public double calcularMediaGeral() {
        double soma = 0;
        for (Estudante e : estudantes) {
            soma += e.calcularMedia();
        }
        return soma / estudantes.size();
    }

    public String getAlunoMaiorNota() {
        Estudante maior = estudantes.get(0);
        for (Estudante e : estudantes) {
            if (e.calcularMedia() > maior.calcularMedia()) {
                maior = e;
            }
        }
        return maior.getNome() + " (" + String.format("%.2f", maior.calcularMedia()) + ")";
    }

    public String getAlunoMenorNota() {
        Estudante menor = estudantes.get(0);
        for (Estudante e : estudantes) {
            if (e.calcularMedia() < menor.calcularMedia()) {
                menor = e;
            }
        }
        return menor.getNome() + " (" + String.format("%.2f", menor.calcularMedia()) + ")";
    }

    public double calcularIdadeMedia() {
        if (estudantes == null || estudantes.isEmpty()) return 0.0;

        double soma = 0;
        for (Estudante e : estudantes) {
            soma += e.getIdade();
        }
        return soma / estudantes.size();
    }

    public ArrayList<Estudante> getAprovados() {
        ArrayList<Estudante> aprovados = new ArrayList<>();
        for (Estudante e : estudantes) {
            if (e.getSituacao().equalsIgnoreCase("aprovado")) {
                aprovados.add(e);
            }
        }
        return aprovados;
    }

    public ArrayList<Estudante> getReprovados() {
        ArrayList<Estudante> reprovados = new ArrayList<>();
        for (Estudante e : estudantes) {
            if (e.getSituacao().equalsIgnoreCase("reprovado")) {
                reprovados.add(e);
            }
        }
        return reprovados;
    }
}
