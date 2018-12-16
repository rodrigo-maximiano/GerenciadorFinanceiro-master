package sdm.ifsp.edu.br.gerenciadorfinanceiro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import sdm.ifsp.edu.br.gerenciadorfinanceiro.R;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.repository.ContaRepository;

public class MainActivity extends AppCompatActivity {

    private Button cadastrarButton;
    private Button operacoesButton;
    private Button relatoriosButton;

    private TextView saldoTotal;

    private ContaRepository contaRepository = new ContaRepository(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saldoTotal = findViewById(R.id.saldoTotalTextView);
        saldoTotal.setText(getSaldoTotal());
        cadastrarButton = findViewById(R.id.cadastrarNovaContaButton);
        cadastrarButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), CadastrarActivity.class);
                        startActivityForResult(i, 1);
                    }
                }
        );
        operacoesButton = findViewById(R.id.operacoesButton);
        operacoesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), OperacoesActivity.class);
                        startActivityForResult(i, 1);
                    }
                }
        );
        relatoriosButton = findViewById(R.id.relatorioButton);
        relatoriosButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), RelatoriosActivity.class);
                        startActivityForResult(i, 1);
                    }
                }
        );
    }

    private String getSaldoTotal() {
        return String.valueOf(contaRepository.getSaldoTotal());
    }
}
