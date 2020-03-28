package com.siswaaplikasi.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.siswaaplikasi.R;
import com.siswaaplikasi.Utils.database.DatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RayonActivity extends AppCompatActivity {
    @BindView(R.id.TxtNama)
    EditText mTxtNama;
    @BindView(R.id.btnSimpan)
    Button mBtnSimpan;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rayon);
        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);

        mBtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addRayon(mTxtNama.getText().toString());

                mTxtNama.setText("");
                Toast.makeText(RayonActivity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
