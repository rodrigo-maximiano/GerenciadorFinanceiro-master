package sdm.ifsp.edu.br.gerenciadorfinanceiro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.text.ParseException;
import java.util.List;

import sdm.ifsp.edu.br.gerenciadorfinanceiro.R;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.model.ContaEntity;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.model.TransacaoEntity;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.repository.ContaRepository;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.repository.TransacaoRepository;

public class RelatorioContaActivity extends Activity {

    private Button voltarButton;

    private Spinner spinnerContas;
    private Button gerarRelatorioContaButton;

    ContaRepository contaRepository = new ContaRepository(RelatorioContaActivity.this);
    TransacaoRepository transacaoRepository = new TransacaoRepository(RelatorioContaActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_conta);

        spinnerContas = findViewById(R.id.contasRelatorioSpinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, listaContas()
        );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerContas.setAdapter(dataAdapter);

        gerarRelatorioContaButton = findViewById(R.id.gerarRelatorioContaButton2);

        gerarRelatorioContaButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Gerar relatorio nos logs por enquanto
                        //Realiza o cadastro no 'banco'
                        ContaEntity conta = contaRepository.buscarContaPelaDescricao(spinnerContas.getSelectedItem().toString());

                        gerarRelatorio(conta);

                        Intent i = new Intent(getApplicationContext(), RelatorioContaActivity.class);
                        startActivityForResult(i, 1);
                    }
                }
        );

        voltarButton = findViewById(R.id.voltarRelatorioContaButton);

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

    private void gerarRelatorio(ContaEntity conta) {
        List<TransacaoEntity> transacaoEntities = null;
        try {
            transacaoEntities = transacaoRepository.buscarPorConta(conta);
        } catch (ParseException e) {
            Log.e("ERROR: ", "Deu Ruim para converter a data");
        }

        for (TransacaoEntity transaction : transacaoEntities) {
            Log.i("Relatorio: ", transaction.toString());
        }
    }

    private List<String> listaContas() {
        return contaRepository.listarContas();
    }

}
