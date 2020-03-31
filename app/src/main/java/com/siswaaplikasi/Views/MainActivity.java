package com.siswaaplikasi.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.siswaaplikasi.Model.RayonModel;
import com.siswaaplikasi.Model.SiswaModel;
import com.siswaaplikasi.R;
import com.siswaaplikasi.Utils.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btnSimpan)
    Button mBtnSimpan;
    @BindView(R.id.spRayon)
    Spinner mSpRayon;
    @BindView(R.id.spRombel)
    Spinner mSpRombel;
    @BindView(R.id.rgJk)
    RadioGroup mRgJk;
    @BindView(R.id.etTglLahir)
    EditText mTglLahir;
    @BindView(R.id.btnDate)
    ImageView mBtnDate;
    @BindView(R.id.etNis)
    EditText mEtNis;
    @BindView(R.id.etNama)
    EditText mEtNama;
    String status, jk;
    @BindView(R.id.cbAktif)
    CheckBox mCbAktif;
    @BindView(R.id.cbTdkAktif)
    CheckBox mcbTdkAktif;
    private DatabaseHelper databaseHelper;

    SiswaModel mSiswaModel;


    private List<RayonModel> rayonList = new ArrayList<>();
    private List<RayonModel> rombelList = new ArrayList<>();
    List<String> listRayon = new ArrayList<String>();
    List<String> listRembol = new ArrayList<String>();

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        mSiswaModel = ((SiswaModel)getIntent().getSerializableExtra("selectedItem"));
        Log.d("LOG", "NAMA ITEM : " + mSiswaModel);
        if (mSiswaModel != null){
            mEtNis.setText(Integer.toString(mSiswaModel.getNis()));
            mEtNama.setText(mSiswaModel.getNama());
            mTglLahir.setText(mSiswaModel.getTgl_lahir());
        }
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.getAllSiswa();
        rayonList = databaseHelper.getAllRayon();
        rombelList = databaseHelper.getAllRombel();
        getSpRombel();
        getSpRayon();

        mBtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        mBtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = mRgJk.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rbP:
//                        Toast.makeText(MainActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        jk = ((RadioButton) findViewById(id)).getText().toString();
                        break;
                    case R.id.rbL:
//                        Toast.makeText(MainActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        jk = ((RadioButton) findViewById(id)).getText().toString();
                        break;
                }
                if (mCbAktif.isChecked()) {
                    status = "Aktif";
                } else {
                    status = "Tidak Aktif";
                }

                if (mEtNis.getText().toString().isEmpty() || mEtNama.getText().toString().isEmpty() || status.isEmpty() || jk.isEmpty() || mSpRayon.getSelectedItem().equals("") || mSpRombel.getSelectedItem().equals("") || mTglLahir.getText().toString().isEmpty()) {
                    Log.d("LOG", " DATA " + " NIS " + mEtNis.getText().toString() + " NAMA " + mEtNama.getText().toString() + " STATUS" + status + " JK" + jk + " RAYON ID" + mSpRayon.getSelectedItem() + "ROMBEL ID " + mSpRombel.getSelectedItem() + "TGL " + mTglLahir.getText().toString());
                    Toast.makeText(MainActivity.this, "Data Belum Lengkap" + " NIS " + mEtNis.getText().toString() + " NAMA " + mEtNama.getText().toString() + " STATUS" + status + " JK" + jk + " RAYON ID" + mSpRayon.getSelectedItem() + "ROMBEL ID " + mSpRombel.getSelectedItem() + "TGL " + mTglLahir.getText().toString(), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                if (mSiswaModel !=null){
                    databaseHelper.updateSiswa(mSiswaModel.getId(),Integer.parseInt(mEtNis.getText().toString()), mEtNama.getText().toString(), mSpRayon.getSelectedItemPosition() + 1, mSpRombel.getSelectedItemPosition() + 1, jk, status, mTglLahir.getText().toString());
                    Toast.makeText(MainActivity.this, "Data Berhasil DiUbah", Toast.LENGTH_LONG).show();
                }else {
                    databaseHelper.addSiswa(Integer.parseInt(mEtNis.getText().toString()), mEtNama.getText().toString(), mSpRayon.getSelectedItemPosition() + 1, mSpRombel.getSelectedItemPosition() + 1, jk, status, mTglLahir.getText().toString());
                    Toast.makeText(MainActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();
                    finish();
                }


                }

            }
        });
    }

    private void getSpRombel() {
        for (int i = 0; i < rombelList.size(); i++) {
            listRembol.add(rombelList.get(i).getNama());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, listRembol);
        mSpRombel.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    private void showDateDialog() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                mTglLahir.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }


    private void getSpRayon() {
        for (int i = 0; i < rayonList.size(); i++) {
            listRayon.add(rayonList.get(i).getNama());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, listRayon);
        mSpRayon.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

//    @OnClick(R.id.btnSimpan)
//    void simpan(){
//        Intent i = new Intent(MainActivity.this, RayonActivity.class);
//        startActivity(i);
//    }
}
