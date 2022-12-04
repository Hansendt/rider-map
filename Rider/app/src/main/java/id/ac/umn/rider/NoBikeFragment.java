package id.ac.umn.rider;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NoBikeFragment extends Fragment {


    View view;
    TextView btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_no_bike, container, false);
//        btn = view.findViewById(R.id.createBike);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), CreateBikeActivity.class);
//                startActivityForResult(intent, 1);
//            }
//        });
        return view;


    }
}