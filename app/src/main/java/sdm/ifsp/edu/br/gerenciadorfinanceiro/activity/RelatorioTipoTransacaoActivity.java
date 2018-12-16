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
import sdm.ifsp.edu.br.gerenciadorfinanceiro.model.TransacaoEntity;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.repository.ContaRepository;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.repository.TransacaoRepository;

public class RelatorioTipoTransacaoActivity extends Activity {

    private Button voltarButton;

    private Spinner spinnerTipoOperacoes;
    private Button gerarRelatorioTipoOperButton;

    ContaRepository contaRepository = new ContaRepository(RelatorioTipoTransacaoActivity.this);
    TransacaoRepository transacaoRepository = new TransacaoRepository(RelatorioTipoTransacaoActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_tipo_transacao);

        spinnerTipoOperacoes = (Spinner) findViewById(R.id.tipoOperRelatorioSpinner);
        final List<String> categorias = new ArrayList<String>();
        categorias.add("Alimentacao");
        categorias.add("Saude");
        categorias.add("Transporte");
        categorias.add("Moradia");
        categorias.add("Educacao");
        categorias.add("Lazer");
        categorias.add("Tarifas Banco");
        categorias.add("Energia");
        categorias.add("Agua");
        categorias.add("Telefone");
        categorias.add("Outros");
        ArrayAdapter<String> tiposAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorias);
        tiposAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoOperacoes.setAdapter(tiposAdapter);

        gerarRelatorioTipoOperButton = findViewById(R.id.gerarRelatorioTipoOperButton);

        gerarRelatorioTipoOperButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gerarRelatorio(spinnerTipoOperacoes.getSelectedItem().toString());

                        Intent i = new Intent(getApplicationContext(), RelatorioTipoTransacaoActivity.class);
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
            transacaoEntities = transacaoRepository.buscarPorTipoOperacao(natureza);
        } catch (ParseException e) {
            Log.e("ERROR: ", "Erro ao converter a data");
        }

        for (TransacaoEntity transaction : transacaoEntities) {
            Log.i("Relatorio: ", transaction.toString());
        }
    }
}
