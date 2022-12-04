package id.ac.umn.rider;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder>{

    Context context;
    ArrayList<Reminder> reminderList;

    public ReminderAdapter(Context context, ArrayList<Reminder> reminderList) {
        this.context = context;
        this.reminderList = reminderList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_reminder, parent, false);
        return new MyViewHolder((ViewGroup) v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.MyViewHolder holder, int position) {
        Reminder reminder = reminderList.get(position);
        holder.reminderPart.setText(reminder.getReminderPart());
        holder.reminderDate.setText(reminder.getReminderDate());
        holder.reminderOptimalDate.setText(reminder.getReminderOptimalDate());
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView reminderImage;
        TextView reminderPart, reminderDate, reminderOptimalDate;
        public MyViewHolder(@NonNull ViewGroup itemView) {
            super(itemView);
            reminderImage = itemView.findViewById(R.id.reminderImage);
            reminderPart = itemView.findViewById(R.id.reminderPart);
            reminderDate = itemView.findViewById(R.id.reminderDate);
            reminderOptimalDate = itemView.findViewById(R.id.reminderOptimalDate);
        }
    }
}
