package com.siswaaplikasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.siswaaplikasi.Model.SiswaModel;
import com.siswaaplikasi.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {
    private List<SiswaModel> siswaList = new ArrayList<>();
    private Context mCtx;
    Listener mListener;

    public SiswaAdapter(List<SiswaModel> siswaList, Context mCtx,Listener mListener) {
        this.siswaList = siswaList;
        this.mCtx = mCtx;
        this.mListener = mListener;
    }

    public interface Listener {
        void onClick(SiswaModel siswaModel);
        void onClickUbah(SiswaModel siswaModel);
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
    public void onBindViewHolder(@NonNull SiswaAdapter.ViewHolder holder, final int position) {
        holder.mLblNis.setText(Integer.toString(siswaList.get(position).getNis()));
        holder.mLblNama.setText(siswaList.get(position).getNama());
        holder.mLblJk.setText(siswaList.get(position).getJenis_kelamin());
        holder.mLblStatus.setText(siswaList.get(position).getStatus());
        holder.mLblTglLahir.setText(siswaList.get(position).getTgl_lahir());
        holder.mBtnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(siswaList.get(position));
            }
        });

        holder.mBtnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickUbah(siswaList.get(position));
            }
        });
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
        @BindView(R.id.btnHapus)
        ImageView mBtnHapus;
        @BindView(R.id.btnUbah)
        ImageView mBtnUbah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
