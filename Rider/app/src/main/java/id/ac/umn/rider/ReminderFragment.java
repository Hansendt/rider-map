package id.ac.umn.rider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ReminderFragment extends Fragment {

    private ArrayList<Reminder> reminderArrayList;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialized();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        ReminderAdapter reminderAdapter = new ReminderAdapter(getContext(), reminderArrayList);
        recyclerView.setAdapter(reminderAdapter);
        reminderAdapter.notifyDataSetChanged();
    }

    private void dataInitialized() {

        reminderArrayList = new ArrayList<>();
//        reminderArrayList.add(new Reminder("Oli", "2021-05-01", "2021-05-05"));
        reminderArrayList.add(new Reminder("Ban", "2021-05-01", "2021-05-05"));

    }


}