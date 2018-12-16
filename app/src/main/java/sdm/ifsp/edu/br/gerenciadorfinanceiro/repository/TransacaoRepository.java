package sdm.ifsp.edu.br.gerenciadorfinanceiro.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import sdm.ifsp.edu.br.gerenciadorfinanceiro.model.ContaEntity;
import sdm.ifsp.edu.br.gerenciadorfinanceiro.model.TransacaoEntity;

public class TransacaoRepository {

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public TransacaoRepository(Context context) {
        this.dbHelper = new SQLiteHelper(context);
    }

    public void salvar(TransacaoEntity transacao) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.DESCRICAO_CONTA, transacao.getConta().getDescricao());
        values.put(SQLiteHelper.VALOR, transacao.getValor());
        values.put(SQLiteHelper.OPERACAO, transacao.getOperacao());
        values.put(SQLiteHelper.TIPO_OPERACAO, transacao.getTipoOperacao());
        values.put(SQLiteHelper.DESCRICAO_OPERACAO, transacao.getDescricao());
        values.put(SQLiteHelper.DATA_OPERACAO, sdf.format(transacao.getDtCriacao()));
        database.insert(SQLiteHelper.DATABASE_TABLE_OPERACOES, null, values);
        database.close();
    }

    public List<TransacaoEntity> buscarPorConta(ContaEntity conta) throws ParseException {
        database = dbHelper.getReadableDatabase();
        List<TransacaoEntity> transacoes = new ArrayList<>();
        Cursor cursor;

        String[] cols = new String[]{SQLiteHelper.ID, SQLiteHelper.VALOR, SQLiteHelper.OPERACAO, SQLiteHelper.DATA_OPERACAO, SQLiteHelper.DESCRICAO_CONTA, SQLiteHelper.TIPO_OPERACAO, SQLiteHelper.DESCRICAO_OPERACAO};
        String where = SQLiteHelper.DESCRICAO_CONTA + " = ?";
        String[] argWhere = new String[]{conta.getDescricao()};

        cursor = database.query(SQLiteHelper.DATABASE_TABLE_OPERACOES, cols, where, argWhere,
                null, null, SQLiteHelper.DATA_OPERACAO);

        while (cursor.moveToNext()) {
            TransacaoEntity transacaoEntity = new TransacaoEntity();
            transacaoEntity.setId(cursor.getInt(0));
            transacaoEntity.setValor(cursor.getLong(1));
            transacaoEntity.setOperacao(cursor.getString(2));
            transacaoEntity.setDtCriacao(simpleDateFormat.parse(cursor.getString(3)));
            transacaoEntity.setConta(new ContaEntity(cursor.getString(4)));
            transacaoEntity.setTipoOperacao(cursor.getString(5));
            transacaoEntity.setDescricao(cursor.getString(6));
            transacoes.add(transacaoEntity);
        }
        cursor.close();
        database.close();
        return transacoes;
    }

    public List<TransacaoEntity> buscarPorTipoOperacao(String tipo) throws ParseException {
        database = dbHelper.getReadableDatabase();
        List<TransacaoEntity> transacoes = new ArrayList<>();
        Cursor cursor;

        String[] cols = new String[]{SQLiteHelper.ID, SQLiteHelper.VALOR, SQLiteHelper.OPERACAO, SQLiteHelper.DATA_OPERACAO, SQLiteHelper.DESCRICAO_CONTA, SQLiteHelper.TIPO_OPERACAO, SQLiteHelper.DESCRICAO_OPERACAO};
        String where = SQLiteHelper.TIPO_OPERACAO + " = ?";
        String[] argWhere = new String[]{tipo};

        cursor = database.query(SQLiteHelper.DATABASE_TABLE_OPERACOES, cols, where, argWhere,
                null, null, SQLiteHelper.DATA_OPERACAO);

        while (cursor.moveToNext()) {
            TransacaoEntity transacaoEntity = new TransacaoEntity();
            transacaoEntity.setId(cursor.getInt(0));
            transacaoEntity.setValor(cursor.getLong(1));
            transacaoEntity.setOperacao(cursor.getString(2));
            transacaoEntity.setDtCriacao(simpleDateFormat.parse(cursor.getString(3)));
            transacaoEntity.setConta(new ContaEntity(cursor.getString(4)));
            transacaoEntity.setTipoOperacao(cursor.getString(5));
            transacaoEntity.setDescricao(cursor.getString(6));
            transacoes.add(transacaoEntity);
        }
        cursor.close();
        database.close();
        return transacoes;
    }

    public List<TransacaoEntity> buscarPorNatureza(String natureza) throws ParseException {
        database = dbHelper.getReadableDatabase();
        List<TransacaoEntity> transacoes = new ArrayList<>();
        Cursor cursor;

        String[] cols = new String[]{SQLiteHelper.ID, SQLiteHelper.VALOR, SQLiteHelper.OPERACAO, SQLiteHelper.DATA_OPERACAO, SQLiteHelper.DESCRICAO_CONTA, SQLiteHelper.TIPO_OPERACAO, SQLiteHelper.DESCRICAO_OPERACAO};
        String where = SQLiteHelper.OPERACAO + " = ?";
        String[] argWhere = new String[]{natureza};


        cursor = database.query(SQLiteHelper.DATABASE_TABLE_OPERACOES, cols, where, argWhere,
                null, null, SQLiteHelper.DATA_OPERACAO);

        while (cursor.moveToNext()) {
            TransacaoEntity transacaoEntity = new TransacaoEntity();
            transacaoEntity.setId(cursor.getInt(0));
            transacaoEntity.setValor(cursor.getLong(1));
            transacaoEntity.setOperacao(cursor.getString(2));
            transacaoEntity.setDtCriacao(simpleDateFormat.parse(cursor.getString(3)));
            transacaoEntity.setConta(new ContaEntity(cursor.getString(4)));
            transacaoEntity.setTipoOperacao(cursor.getString(5));
            transacaoEntity.setDescricao(cursor.getString(6));
            transacoes.add(transacaoEntity);
        }
        cursor.close();
        database.close();
        return transacoes;
    }
}
