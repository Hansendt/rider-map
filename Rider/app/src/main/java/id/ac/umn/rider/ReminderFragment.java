package id.ac.umn.rider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ReminderFragment extends Fragment {

    private ArrayList<Reminder> reminderArrayList;
    private RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private FirebaseUser user;
    private String userUID;
    ReminderAdapter reminderAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userUID = user.getUid();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        reminderArrayList = new ArrayList<>();
        reminderAdapter = new ReminderAdapter(getContext(), reminderArrayList);
        recyclerView.setAdapter(reminderAdapter);

        databaseReference.child("Users").child(userUID).child("Reminder").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String name = dataSnapshot.getKey();
                    String date = dataSnapshot.child("date").getValue().toString();
                    String dateOptimal = dataSnapshot.child("dateOptimal").getValue().toString();
                    int image = dataSnapshot.child("image").getValue(Integer.class);

                    reminderArrayList.add(new Reminder(name, date, dateOptimal, image));
                }
                reminderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }




}