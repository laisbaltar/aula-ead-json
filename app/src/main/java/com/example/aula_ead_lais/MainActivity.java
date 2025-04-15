package com.example.aula_ead_lais;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    private final String
            URL = "https://my-json-server.typicode.com/laisbaltar/pdmi2_aula2/db";

    private StringBuilder builder = null;
    private Turma dadosBaixados = null;
    private MyAdapterEstudante adapterEstudante;
    private ArrayList<Estudante> estudantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        listView = findViewById(R.id.list_view);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(URL);
                String textoJSON = conexao.converter(inputStream);
                Log.i("JSON", "doInBackground: " + textoJSON);
                Gson gson = new Gson();
                builder = new StringBuilder();
                if (textoJSON != null) {
                    Type type = new TypeToken<Turma>() {
                    }.getType();
                    dadosBaixados = gson.fromJson(textoJSON, type);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Não foi possível obter JSON", Toast.LENGTH_SHORT).show();
                        }
                    });
                }//if else

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        estudantes = new ArrayList<>();
                        for (Estudante estudante : dadosBaixados.getEstudantes()) {
                            estudantes.add(estudante);
                        }
                        adapterEstudante = new MyAdapterEstudante(MainActivity.this, estudantes);

                        listView.setAdapter(adapterEstudante);
                        listView.setOnItemClickListener(MainActivity.this);
                    }
                });
            }
        });
    }//onCreate

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Estudante estudante = (Estudante) parent.getItemAtPosition(position);
        Intent intent = new Intent(MainActivity.this, DadosEstudanteActivity.class);

        intent.putExtra("nomeEstudante", estudante.getNome());
        intent.putExtra("idadeEstudante", String.valueOf(estudante.getIdade()));
        intent.putExtra("mediaEstudante", String.format("%.2f", estudante.calcularMedia()));
        intent.putExtra("frequenciaEstudante", String.format("%.1f%%", estudante.calcularPercentualPresenca()));
        intent.putExtra("situacaoEstudante", estudante.getSituacao());

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it = null;

        if(item.getItemId() == R.id.dados) {
            it = new Intent(MainActivity.this, InformacoesGeraisActivity.class);

            it.putExtra("mediaGeral", dadosBaixados.calcularMediaGeral());
            it.putExtra("alunoMaiorNota", dadosBaixados.getAlunoMaiorNota());
            it.putExtra("alunoMenorNota", dadosBaixados.getAlunoMenorNota());
            it.putExtra("idadeMedia", dadosBaixados.calcularIdadeMedia());

            it.putExtra("aprovados", new Gson().toJson(dadosBaixados.getAprovados()));
            it.putExtra("reprovados", new Gson().toJson(dadosBaixados.getReprovados()));

            startActivity(it);
            return true;
        }

        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}