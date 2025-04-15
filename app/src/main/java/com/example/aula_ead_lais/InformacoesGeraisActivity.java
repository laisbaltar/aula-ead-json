package com.example.aula_ead_lais;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class InformacoesGeraisActivity extends AppCompatActivity {
    private TextView textViewMediaGeral, textViewMaiorNota, textViewMenorNota, textViewIdadeMedia;
    private ListView listAprovados, listReprovados;
    private MyAdapterEstudante adapterAprovados, adapterReprovados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_gerais);

        textViewMediaGeral = findViewById(R.id.textViewMediaGeral);
        textViewMaiorNota = findViewById(R.id.textViewMaiorNota);
        textViewMenorNota = findViewById(R.id.textViewMenorNota);
        textViewIdadeMedia = findViewById(R.id.textViewIdadeMedia);

        listAprovados = findViewById(R.id.list_alunos_aprovados);
        listReprovados = findViewById(R.id.list_alunos_reprovados);

        adapterAprovados = new MyAdapterEstudante(this, new ArrayList<>());
        adapterReprovados = new MyAdapterEstudante(this, new ArrayList<>());

        listAprovados.setAdapter(adapterAprovados);
        listReprovados.setAdapter(adapterReprovados);

        Intent intent = getIntent();
        if (intent != null) {
            double mediaGeral = intent.getDoubleExtra("mediaGeral", 0.0);
            String alunoMaiorNota = intent.getStringExtra("alunoMaiorNota");
            String alunoMenorNota = intent.getStringExtra("alunoMenorNota");
            double idadeMedia = intent.getDoubleExtra("idadeMedia", 0.0);

            textViewMediaGeral.setText(String.format("Média geral da turma: %.2f", mediaGeral));
            textViewMaiorNota.setText("Aluno com a maior nota: " + alunoMaiorNota);
            textViewMenorNota.setText("Aluno com a menor nota: " + alunoMenorNota);
            textViewIdadeMedia.setText(String.format("Idade média da turma: %.1f anos", idadeMedia));

            String jsonAprovados = intent.getStringExtra("aprovados");
            String jsonReprovados = intent.getStringExtra("reprovados");

            Type listType = new TypeToken<ArrayList<Estudante>>(){}.getType();
            ArrayList<Estudante> aprovados = new Gson().fromJson(jsonAprovados, listType);
            ArrayList<Estudante> reprovados = new Gson().fromJson(jsonReprovados, listType);

            adapterAprovados.atualizarDados(aprovados);
            adapterReprovados.atualizarDados(reprovados);
        }
    }
}