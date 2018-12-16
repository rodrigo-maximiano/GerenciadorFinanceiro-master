package sdm.ifsp.edu.br.gerenciadorfinanceiro.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "gf.db";


    static final String DATABASE_TABLE = "contas";
    static final String KEY_ID = "descricao";
    static final String KEY_SALDO = "saldo";

    static final String DATABASE_TABLE_OPERACOES = "operacoes";
    static final String ID = "id";
    static final String VALOR = "valor";
    static final String OPERACAO = "operacao";
    static final String TIPO_OPERACAO = "tipo_operacao";
    static final String DESCRICAO_CONTA = "descricao_conta";
    static final String DATA_OPERACAO = "data_operacao";
    static final String DESCRICAO_OPERACAO = "descricao_operacao";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE " + DATABASE_TABLE + " (" +
            KEY_ID + " TEXT PRIMARY KEY, " +
            KEY_SALDO + " TEXT NOT NULL);";

    private static final String DATABASE_CREATE_OPERACAO = "CREATE TABLE " + DATABASE_TABLE_OPERACOES + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            OPERACAO + " TEXT NOT NULL, " +
            TIPO_OPERACAO + " TEXT NOT NULL, " +
            DESCRICAO_CONTA + " TEXT NOT NULL, " +
            DESCRICAO_OPERACAO + " TEXT NOT NULL, " +
            DATA_OPERACAO + " TEXT NOT NULL, " +
            VALOR + " TEXT NOT NULL);";


    SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE_OPERACAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }
}

