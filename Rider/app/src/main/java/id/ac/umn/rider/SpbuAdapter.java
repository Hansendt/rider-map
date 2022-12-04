package id.ac.umn.rider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SpbuAdapter extends RecyclerView.Adapter<SpbuAdapter.SpbuViewHolder> {
    private ArrayList<Spbu> listSpbu;

    public SpbuAdapter(ArrayList<Spbu> listSpbu) {
        this.listSpbu = listSpbu;
    }

    @NonNull
    @Override
    public SpbuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spbu, parent, false);
        return new SpbuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpbuViewHolder holder, int position) {
        Spbu spbu = listSpbu.get(position);
        holder.perusahaan.setText(spbu.getCompany());
        holder.nama.setText(spbu.getName());
        holder.jarak.setText("(" + spbu.getDistance() + " km" + ")");
    }

    @Override
    public int getItemCount() {
        return listSpbu.size();
    }

    public class SpbuViewHolder extends RecyclerView.ViewHolder {
        TextView perusahaan, nama, jarak;
        public SpbuViewHolder(@NonNull View itemView) {
            super(itemView);
            perusahaan = itemView.findViewById(R.id.perusahaan);
            nama = itemView.findViewById(R.id.nama);
            jarak = itemView.findViewById(R.id.jarak);
        }
    }
}
