package com.siswaaplikasi.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.siswaaplikasi.Adapter.SiswaAdapter;
import com.siswaaplikasi.Model.RayonModel;
import com.siswaaplikasi.Model.SiswaModel;
import com.siswaaplikasi.R;
import com.siswaaplikasi.Utils.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaftarSiswaActivity extends AppCompatActivity {
    @BindView(R.id.recyclerListSiswa)
    RecyclerView mRecyclerListSiswa;
    @BindView(R.id.btnTambah)
    Button mBtnTambah;

    SiswaAdapter siswaAdapter;

    private List<SiswaModel> siswaModelList = new ArrayList<>();

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_siswa);
        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);
        siswaModelList = databaseHelper.getAllSiswa();
        siswaAdapter = new SiswaAdapter(siswaModelList, DaftarSiswaActivity.this);
        mRecyclerListSiswa.setLayoutManager(new LinearLayoutManager(DaftarSiswaActivity.this));
        mRecyclerListSiswa.setAdapter(siswaAdapter);
        mBtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DaftarSiswaActivity.this, MainActivity.class);
                startActivity(i);
            }
        });



    }

}
