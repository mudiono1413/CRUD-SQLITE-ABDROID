package com.siswaaplikasi.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> implements Filterable {
    private ArrayList<SiswaModel> siswaList ;
    private ArrayList<SiswaModel> siswaListFiltered ;
    private Context mCtx;
    Listener mListener;

    public SiswaAdapter(ArrayList<SiswaModel> siswaList, Context mCtx,Listener mListener) {
        this.siswaList = siswaList;
        this.mCtx = mCtx;
        this.mListener = mListener;
        this.siswaListFiltered = siswaList;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    siswaListFiltered = siswaList;
                }else {
                    ArrayList<SiswaModel> filteredList = new ArrayList<>();
                    for (SiswaModel row : siswaList){
                        if (row.getNama().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    siswaListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = siswaListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                siswaListFiltered = (ArrayList<SiswaModel>) filterResults.values;
                Log.d("LOG","siswa " + siswaListFiltered.size());
                notifyDataSetChanged();
            }
        };
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
        holder.mLblNis.setText(Integer.toString(siswaListFiltered.get(position).getNis()));
        holder.mLblNama.setText(siswaListFiltered.get(position).getNama());
        holder.mLblJk.setText(siswaListFiltered.get(position).getJenis_kelamin());
        holder.mLblStatus.setText(siswaListFiltered.get(position).getStatus());
        holder.mLblTglLahir.setText(siswaListFiltered.get(position).getTgl_lahir());
        holder.mBtnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(siswaListFiltered.get(position));
            }
        });

        holder.mBtnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickUbah(siswaListFiltered.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return siswaListFiltered.size();
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
