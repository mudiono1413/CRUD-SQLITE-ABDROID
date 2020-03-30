package com.siswaaplikasi.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.siswaaplikasi.Model.RombelModel;
import com.siswaaplikasi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.btnSiswa)
    Button mBtnSiswa;
    @BindView(R.id.btnRayon)
    Button mBtnRayon;
    @BindView(R.id.btnRembol)
    Button mBtnRombel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mBtnRayon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, RayonActivity.class);
                startActivity(i);
            }
        });
        mBtnRombel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, RombelActivity.class);
                startActivity(i);
            }
        });
        mBtnSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, DaftarSiswaActivity.class);
                startActivity(i);
            }
        });
    }
}
