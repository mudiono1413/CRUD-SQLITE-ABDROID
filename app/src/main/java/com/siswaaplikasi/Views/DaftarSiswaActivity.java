package com.siswaaplikasi.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.siswaaplikasi.Adapter.SiswaAdapter;
import com.siswaaplikasi.Model.RayonModel;
import com.siswaaplikasi.Model.SiswaModel;
import com.siswaaplikasi.R;
import com.siswaaplikasi.Utils.database.DatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaftarSiswaActivity extends AppCompatActivity implements SiswaAdapter.Listener {
    @BindView(R.id.recyclerListSiswa)
    RecyclerView mRecyclerListSiswa;
    @BindView(R.id.btnTambah)
    Button mBtnTambah;
    @BindView(R.id.etSeacrh)
    EditText metSearch;

    SiswaAdapter siswaAdapter;

    private ArrayList<SiswaModel> siswaModelList;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_siswa);
        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);
        siswaModelList = databaseHelper.getAllSiswa();
        siswaAdapter = new SiswaAdapter(siswaModelList, DaftarSiswaActivity.this, this);
        mRecyclerListSiswa.setLayoutManager(new LinearLayoutManager(DaftarSiswaActivity.this));
        mRecyclerListSiswa.setAdapter(siswaAdapter);
        mBtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DaftarSiswaActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        metSearch.setImeOptions(EditorInfo.IME_ACTION_DONE);

        metSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("LOG","QUERY " + s);
                siswaAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onClick(SiswaModel siswaModel) {
        Log.d("LOG", "MODEL " + siswaModel);
        databaseHelper.deleteUSiswa(siswaModel.getNis());
        finish();
    }

    @Override
    public void onClickUbah(SiswaModel siswaModel) {
        Intent i = new Intent(DaftarSiswaActivity.this, MainActivity.class);
        i.putExtra("selectedItem",(Serializable)siswaModel);
        startActivity(i);
    }
}
