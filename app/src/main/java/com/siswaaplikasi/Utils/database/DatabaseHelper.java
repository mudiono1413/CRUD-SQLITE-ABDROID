package com.siswaaplikasi.Utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.siswaaplikasi.Model.RayonModel;
import com.siswaaplikasi.Model.SiswaModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "siswa_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SISWA = "siswa";
    private static final String TABLE_ROMBEL = "rombel";
    private static final String TABLE_RAYON = "rayon";
    private static final String KEY_ID = "id";
    private static final String KEY_NIS = "nis";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_TANGGAL_LAHIR = "tanggal_lahir";
    private static final String KEY_RAYON_ID = "rayon_id";
    private static final String KEY_ROMBEL_ID = "rombel_id";
    private static final String KEY_JK = "jenis_kelamin";
    private static final String KEY_STATUS = "status";

    private static final String CREATE_TABLE_SISWA = "CREATE TABLE " + TABLE_SISWA + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NIS + " INTEGER ,"
            + KEY_NAMA + " TEXT,"
            + KEY_RAYON_ID + " INTEGER,"
            + KEY_ROMBEL_ID + " INETEGER,"
            + KEY_JK + " TEXT,"
            + KEY_STATUS + " TEXT,"
            + KEY_TANGGAL_LAHIR + " TEXT);";

    private static final String CREATE_TABLE_RAYON = "CREATE TABLE "
            + TABLE_RAYON + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAMA + " TEXT );";

    private static final String CREATE_TABLE_ROMBEL = "CREATE TABLE "
            + TABLE_ROMBEL + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAMA + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_SISWA);
        Log.d("table", CREATE_TABLE_RAYON);
        Log.d("table", CREATE_TABLE_ROMBEL);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SISWA);
        db.execSQL(CREATE_TABLE_RAYON);
        db.execSQL(CREATE_TABLE_ROMBEL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_SISWA + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_ROMBEL + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_RAYON + "'");
        onCreate(db);
    }

    public void addRayon(String nama) {
        Log.d("LOG", "nama rayon " + nama);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesRayon = new ContentValues();
        valuesRayon.put(KEY_NAMA, nama);
        db.insert(TABLE_RAYON, null, valuesRayon);
    }

    public ArrayList<RayonModel> getAllRayon() {
        ArrayList<RayonModel> rayonList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_RAYON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                RayonModel rayonModel = new RayonModel();
                rayonModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                rayonModel.setNama(c.getString(c.getColumnIndex(KEY_NAMA)));

                rayonList.add(rayonModel);

            } while (c.moveToNext());
        }

        return rayonList;

    }

    public ArrayList<RayonModel> getAllRombel() {
        ArrayList<RayonModel> rombelList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_ROMBEL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                RayonModel rayonModel = new RayonModel();
                rayonModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                rayonModel.setNama(c.getString(c.getColumnIndex(KEY_NAMA)));

                rombelList.add(rayonModel);

            } while (c.moveToNext());
        }

        return rombelList;

    }

    public void addRombel(String nama) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesRombel = new ContentValues();
        valuesRombel.put(KEY_NAMA, nama);
        db.insert(TABLE_ROMBEL, null, valuesRombel);
    }

    public void addSiswa(int nis, String nama, int rayon_id, int rombel_id, String jk, String status, String tgl_lahir) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesSiswa = new ContentValues();
        valuesSiswa.put(KEY_NIS, nis);
        valuesSiswa.put(KEY_NAMA, nama);
        valuesSiswa.put(KEY_RAYON_ID, rayon_id);
        valuesSiswa.put(KEY_ROMBEL_ID, rombel_id);
        valuesSiswa.put(KEY_JK, jk);
        valuesSiswa.put(KEY_STATUS, status);
        valuesSiswa.put(KEY_TANGGAL_LAHIR, tgl_lahir);
        db.insert(TABLE_SISWA, null, valuesSiswa);

        Log.d("LOG"," DATA DI SAVE");


    } public void updateSiswa(int id,int nis, String nama, int rayon_id, int rombel_id, String jk, String status, String tgl_lahir) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesSiswa = new ContentValues();
        valuesSiswa.put(KEY_NIS, nis);
        valuesSiswa.put(KEY_NAMA, nama);
        valuesSiswa.put(KEY_RAYON_ID, rayon_id);
        valuesSiswa.put(KEY_ROMBEL_ID, rombel_id);
        valuesSiswa.put(KEY_JK, jk);
        valuesSiswa.put(KEY_STATUS, status);
        valuesSiswa.put(KEY_TANGGAL_LAHIR, tgl_lahir);
        db.update(TABLE_SISWA, valuesSiswa, KEY_ID + " = ?", new String[]{String.valueOf(id)});

        Log.d("LOG"," DATA DI SAVE");


    }

    public List<SiswaModel> getAllSiswa(){
        List<SiswaModel> listSiswa = new ArrayList<>();
        String MY_QUERY = "SELECT * FROM '" + TABLE_SISWA + "' a " +
                "INNER JOIN '" + TABLE_RAYON + "' b ON a.'" + KEY_RAYON_ID + "'= b.'"+ KEY_ID +"'" +
                "INNER JOIN '"+TABLE_ROMBEL +"' c ON a.'"+KEY_ROMBEL_ID+"' = c.'"+KEY_ID+"'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(MY_QUERY, null);
        Log.d("LOG" ,"DATA SISWA " + c);

        if (c.moveToFirst()){
            do {
               SiswaModel siswaModel = new SiswaModel();
               siswaModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
               siswaModel.setNis(c.getInt(c.getColumnIndex(KEY_NIS)));
               siswaModel.setNama(c.getString(c.getColumnIndex(KEY_NAMA)));
               siswaModel.setJenis_kelamin(c.getString(c.getColumnIndex(KEY_JK)));
               siswaModel.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));
               siswaModel.setTgl_lahir(c.getString(c.getColumnIndex(KEY_TANGGAL_LAHIR)));

               listSiswa.add(siswaModel);

            }while (c.moveToNext());

        }

        return listSiswa;

    }

    public void deleteUSiswa(int nis) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_SISWA, KEY_NIS + " = ?",new String[]{String.valueOf(nis)});


    }


}
