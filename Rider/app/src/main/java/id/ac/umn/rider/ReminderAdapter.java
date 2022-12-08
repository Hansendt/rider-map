package id.ac.umn.rider;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView reminderImage;
        TextView reminderPart, reminderDate, reminderOptimalDate;
        Button reminderDelete;

        public MyViewHolder(@NonNull ViewGroup itemView) {
            super(itemView);
            reminderImage = itemView.findViewById(R.id.reminderImage);
            reminderPart = itemView.findViewById(R.id.reminderPart);
            reminderDate = itemView.findViewById(R.id.reminderDate);
            reminderOptimalDate = itemView.findViewById(R.id.reminderOptimalDate);
            reminderDelete = itemView.findViewById(R.id.reminderDelete);
//            ImageView reminderDelete= (ImageView) findViewById(R.id.image);
//            reminderDelete.setImageResource(R.drawable.my_image);


            reminderDelete.setOnClickListener(new View.OnClickListener() {
//                reminderDelete.setImageResource(R.drawable.ic_baseline_check_box_24);
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        alert = new AlertDialog.Builder(itemView.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        alert = new AlertDialog.Builder(itemView.getContext());
                    }
                    alert.setTitle("Confirmation")
                            .setMessage("Have you really changed your part or paid the tax?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    reminderList.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .show();
                }
            });

        }




    }
}
