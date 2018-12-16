package sdm.ifsp.edu.br.gerenciadorfinanceiro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sdm.ifsp.edu.br.gerenciadorfinanceiro.R;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.model.ContaEntity;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.model.TransacaoEntity;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.repository.ContaRepository;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.repository.TransacaoRepository;

public class OperacoesActivity extends Activity {

    private static final String SOMA = "CREDITO";
    private static final String SUBTRACAO = "DEBITO";

    private Spinner spinnerContas;
    private Spinner spinnerTipos;

    private Button somarButton;
    private Button subtrairButton;

    private EditText valor;
    private EditText descricao;

    ContaRepository contaRepository = new ContaRepository(OperacoesActivity.this);
    TransacaoRepository transacaoRepository = new TransacaoRepository(OperacoesActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacoes);

        //Carrega o spinner com as opcoes de transacoes

        spinnerTipos = (Spinner) findViewById(R.id.tipoTransacaoSpinner);
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
        spinnerTipos.setAdapter(tiposAdapter);

        spinnerContas = findViewById(R.id.contasSpinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, listaContas()
        );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerContas.setAdapter(dataAdapter);

        somarButton = findViewById(R.id.somarButton);

        somarButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        contaRepository = new ContaRepository(OperacoesActivity.this);
                        transacaoRepository = new TransacaoRepository(OperacoesActivity.this);
                        valor = findViewById(R.id.valorOperacaoEditText);
                        descricao = findViewById(R.id.descricaoEditText);
                        ContaEntity conta = contaRepository.buscarContaPelaDescricao(spinnerContas.getSelectedItem().toString());
                        conta.setSaldo(conta.getSaldo() + Long.parseLong(String.valueOf(valor.getText())));
                        contaRepository.salvar(conta, true);

                        TransacaoEntity transacao = new TransacaoEntity(conta, SOMA, spinnerTipos.getSelectedItem().toString() , Long.parseLong(String.valueOf(valor.getText())), String.valueOf(descricao.getText()), Calendar.getInstance().getTime());
                        transacaoRepository.salvar(transacao);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivityForResult(i, 1);
                    }
                }
        );

        subtrairButton = findViewById(R.id.subtrairButton);

        subtrairButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        contaRepository = new ContaRepository(OperacoesActivity.this);
                        transacaoRepository = new TransacaoRepository(OperacoesActivity.this);
                        valor = findViewById(R.id.valorOperacaoEditText);
                        descricao = findViewById(R.id.descricaoEditText);
                        ContaEntity conta = contaRepository.buscarContaPelaDescricao(spinnerContas.getSelectedItem().toString());
                        conta.setSaldo(conta.getSaldo() - Long.parseLong(String.valueOf(valor.getText())));

                        contaRepository.salvar(conta, true);

                        TransacaoEntity transacao = new TransacaoEntity(conta, SUBTRACAO, spinnerTipos.getSelectedItem().toString() , Long.parseLong(String.valueOf(valor.getText())), String.valueOf(descricao.getText()), Calendar.getInstance().getTime());
                        transacaoRepository.salvar(transacao);

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivityForResult(i, 1);
                    }
                }
        );

    }

    private List<String> listaContas() {
        return contaRepository.listarContas();
    }

}
