package com.siswaaplikasi.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.siswaaplikasi.Model.RayonModel;
import com.siswaaplikasi.R;
import com.siswaaplikasi.Utils.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

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
    private DatabaseHelper databaseHelper;


    private List<RayonModel> rayonList = new ArrayList<>();
    private List<RayonModel> rombelList = new ArrayList<>();
    List<String> listRayon = new ArrayList<String>();
    List<String> listRembol = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);
        rayonList = databaseHelper.getAllRayon();
        rombelList = databaseHelper.getAllRombel();
        getSpRombel();
        getSpRayon();

        mBtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RombelActivity.class);
                startActivity(i);

            }
        });
    }

    private void getSpRombel(){
        for (int i = 0; i <rombelList.size() ; i++) {
            listRembol.add(rombelList.get(i).getNama());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, listRembol);
        mSpRombel.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }



    private void getSpRayon(){
        for (int i = 0; i <rayonList.size() ; i++) {
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
