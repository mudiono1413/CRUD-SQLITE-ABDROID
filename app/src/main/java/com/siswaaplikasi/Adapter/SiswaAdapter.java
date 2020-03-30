package com.siswaaplikasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.siswaaplikasi.Model.SiswaModel;
import com.siswaaplikasi.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SiswaAdapter  extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {
    private List<SiswaModel> siswaList = new ArrayList<>();
    private Context mCtx;

    public SiswaAdapter(List<SiswaModel> siswaList , Context mCtx){
        this.siswaList = siswaList;
        this.mCtx = mCtx;
    }
    @NonNull
    @Override
    public SiswaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_siswa, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaAdapter.ViewHolder holder, int position) {
        holder.mLblNis.setText(Integer.toString(siswaList.get(position).getNis()));
        holder.mLblNama.setText(siswaList.get(position).getNama());
        holder.mLblJk.setText(siswaList.get(position).getJenis_kelamin());
        holder.mLblStatus.setText(siswaList.get(position).getStatus());
        holder.mLblTglLahir.setText(siswaList.get(position).getTgl_lahir());
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lblNis)
        TextView mLblNis;
        @BindView(R.id.lblNamaSiswa)
        TextView mLblNama;
        @BindView(R.id.lblJk)
        TextView mLblJk;
        @BindView(R.id.lblStatus)
        TextView mLblStatus;
        @BindView(R.id.lblTglLahir)
        TextView mLblTglLahir;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
