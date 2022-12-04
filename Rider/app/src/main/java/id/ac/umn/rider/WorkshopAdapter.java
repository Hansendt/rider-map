package id.ac.umn.rider;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkshopAdapter extends RecyclerView.Adapter<WorkshopAdapter.WorkshopViewHolder> {
    private ArrayList<Workshop> listWorkshop;

    public WorkshopAdapter(ArrayList<Workshop> listWorkshop) {
        this.listWorkshop = listWorkshop;
    }

    @NonNull
    @Override
    public WorkshopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workshop, parent, false);
        return new WorkshopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkshopViewHolder holder, int position) {
        Workshop workshop = listWorkshop.get(position);
        holder.nama.setText(workshop.getNama());
    }

    @Override
    public int getItemCount() {
        return listWorkshop.size();
    }

    public class WorkshopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nama, rating, jarak, desc, telp;
        public WorkshopViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            rating = itemView.findViewById(R.id.rating);
            jarak = itemView.findViewById(R.id.jarak);
            desc = itemView.findViewById(R.id.desc);
            telp = itemView.findViewById(R.id.telp);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Workshop bengkel = listWorkshop.get(getAdapterPosition());
            Intent intent = new Intent(itemView.getContext(), WorkshopDetail.class);
            intent.putExtra("nama", bengkel.getNama());
            intent.putExtra("rating", bengkel.getRating());
            intent.putExtra("jarak", bengkel.getDistance());
            intent.putExtra("desc", bengkel.getDesc());
            intent.putExtra("telp", bengkel.getTelp());
            itemView.getContext().startActivity(intent);
        }
    }
}



