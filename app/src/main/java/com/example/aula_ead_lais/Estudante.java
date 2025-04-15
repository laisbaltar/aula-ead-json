package com.example.aula_ead_lais;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Estudante {
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("idade")
    @Expose
    private Integer idade;
    @SerializedName("notas")
    @Expose
    private List<Double> notas;
    @SerializedName("presenca")
    @Expose
    private List<Boolean> presenca;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    public List<Boolean> getPresenca() {
        return presenca;
    }

    public void setPresenca(List<Boolean> presenca) {
        this.presenca = presenca;
    }

    @Override
    public String toString() {
        return "Estudante{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", notas=" + notas +
                ", presenca=" + presenca +
                '}';
    }

    // TODO CRIAR CONTROLLER

    public double calcularMedia() {
        double soma = 0;

        for (double nota : notas) {
            soma += nota;
        }

        return soma / notas.size();
    }

    public double calcularPercentualPresenca() {
        int presentes = 0;

        for (boolean p : presenca) {
            if (p) presentes++;
        }

        return (presentes * 100.0) / presenca.size();
    }

    public String getSituacao() {
        double media = calcularMedia();
        double presenca = calcularPercentualPresenca();

        return (media >= 7 && presenca >= 75) ? "Aprovado" : "Reprovado";
    }
}