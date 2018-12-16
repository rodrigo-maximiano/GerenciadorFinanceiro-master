package sdm.ifsp.edu.br.gerenciadorfinanceiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import sdm.ifsp.edu.br.gerenciadorfinanceiro.R;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.model.ContaEntity;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.model.TransacaoEntity;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.repository.ContaRepository;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.repository.TransacaoRepository;

public class RelatorioNaturezaActivity extends Activity {

    private Spinner spinnerNaturezas;

    private Button gerarRelatorioNaturezaButton;
    private Button voltarButton;
    private TransacaoRepository transacaoRepository = new TransacaoRepository(RelatorioNaturezaActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_natureza);

        //Carrega o spinner com as opcoes de transacoes

        spinnerNaturezas = (Spinner) findViewById(R.id.naturezasSpinner);
        final List<String> categorias = new ArrayList<String>();
        categorias.add("CREDITO");
        categorias.add("DEBITO");
        ArrayAdapter<String> tiposAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorias);
        tiposAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNaturezas.setAdapter(tiposAdapter);

        //Botao para voltar para a tela inicial da porra toda
        gerarRelatorioNaturezaButton = findViewById(R.id.gerarRelatorioNaturezaOperButton);

        gerarRelatorioNaturezaButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gerarRelatorio(spinnerNaturezas.getSelectedItem().toString());

                        Intent i = new Intent(getApplicationContext(), RelatorioNaturezaActivity.class);
                        startActivityForResult(i, 1);
                    }
                }
        );

        voltarButton = findViewById(R.id.voltarRelatorioButton);

        voltarButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivityForResult(i, 1);
                    }
                }
        );
    }

    private void gerarRelatorio(String natureza) {
        List<TransacaoEntity> transacaoEntities = null;
        try {
            transacaoEntities = transacaoRepository.buscarPorNatureza(natureza);
        } catch (ParseException e) {
            Log.e("ERROR: ", "Erro ao converter a data");
        }

        for (TransacaoEntity transaction : transacaoEntities) {
            Log.i("Relatorio: ", transaction.toString());
        }
    }
}
