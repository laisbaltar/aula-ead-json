package com.example.aula_ead_lais;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DadosEstudanteActivity extends AppCompatActivity {
    private TextView textViewNomeEstudante, textViewIdade, textViewMedia, textViewFrequencia, textViewSituacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_estudante);

        textViewNomeEstudante = findViewById(R.id.textViewNomeEstudante);
        textViewIdade = findViewById(R.id.textViewIdade);
        textViewMedia = findViewById(R.id.textViewMedia);
        textViewFrequencia = findViewById(R.id.textViewFrequencia);
        textViewSituacao = findViewById(R.id.textViewSituacao);

        Intent intent = getIntent();
        String nomeEstudante = intent.getStringExtra("nomeEstudante");
        String idadeEstudante = intent.getStringExtra("idadeEstudante");
        String mediaEstudante = intent.getStringExtra("mediaEstudante");
        String frequenciaEstudante = intent.getStringExtra("frequenciaEstudante");
        String situacaoEstudante = intent.getStringExtra("situacaoEstudante");

        textViewNomeEstudante.setText("Nome: " + nomeEstudante);
        textViewIdade.setText("Idade: " + idadeEstudante);
        textViewMedia.setText("Média: " + mediaEstudante);
        textViewFrequencia.setText("Frequência: " + frequenciaEstudante);
        textViewSituacao.setText("Situação: " + situacaoEstudante);
    }
}