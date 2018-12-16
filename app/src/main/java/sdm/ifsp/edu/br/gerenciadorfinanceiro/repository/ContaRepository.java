package sdm.ifsp.edu.br.gerenciadorfinanceiro.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sdm.ifsp.edu.br.gerenciadorfinanceiro.model.ContaEntity;

public class ContaRepository {

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public ContaRepository(Context context) {
        this.dbHelper = new SQLiteHelper(context);
    }

    public void salvar(ContaEntity c, boolean update) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.KEY_ID, c.getDescricao());
        values.put(SQLiteHelper.KEY_SALDO, c.getSaldo().toString());


        if (update) {
            database.update(SQLiteHelper.DATABASE_TABLE, values, SQLiteHelper.KEY_ID + "='" + c.getDescricao() + "'", null);
        } else {
            database.insert(SQLiteHelper.DATABASE_TABLE, null, values);
        }
        database.close();
    }

    public List<String> listarContas() {
        database = dbHelper.getReadableDatabase();
        List<String> contas = new ArrayList<>();
        Cursor cursor;
        String[] cols = new String[]{SQLiteHelper.KEY_ID};
        cursor = database.query(SQLiteHelper.DATABASE_TABLE, cols, null, null,
                null, null, SQLiteHelper.KEY_ID);
        while (cursor.moveToNext()) {
            contas.add(cursor.getString(0));
        }
        cursor.close();
        database.close();
        return contas;
    }

    public ContaEntity buscarContaPelaDescricao(String descricao) {
        database = dbHelper.getReadableDatabase();
        List<ContaEntity> contas = new ArrayList<>();
        Cursor cursor;
        String[] cols = new String[]{SQLiteHelper.KEY_ID, SQLiteHelper.KEY_SALDO};
        String where = SQLiteHelper.KEY_ID + " = ?";
        String[] argWhere = new String[]{descricao};

        cursor = database.query(SQLiteHelper.DATABASE_TABLE, cols, where, argWhere,
                null, null, SQLiteHelper.KEY_ID);
        while (cursor.moveToNext()) {
            ContaEntity conta = new ContaEntity();
            conta.setDescricao(cursor.getString(0));
            conta.setSaldo(cursor.getLong(1));
            contas.add(conta);
        }
        cursor.close();
        database.close();
        return contas != null && contas.size() > 0 ? contas.get(0) : null;
    }

    public Long getSaldoTotal() {
        Long saldoTotal = 0L;
        database = dbHelper.getReadableDatabase();
        List<ContaEntity> contas = new ArrayList<>();
        Cursor cursor;
        String[] cols = new String[]{SQLiteHelper.KEY_ID, SQLiteHelper.KEY_SALDO};
        cursor = database.query(SQLiteHelper.DATABASE_TABLE, cols, null, null,
                null, null, SQLiteHelper.KEY_ID);

        while (cursor.moveToNext()) {
            contas.add(new ContaEntity(cursor.getString(0), Long.valueOf(cursor.getString(1))));
        }
        cursor.close();
        database.close();

        for (ContaEntity conta : contas) {
            saldoTotal += conta.getSaldo();
        }
        return saldoTotal;
    }
}
