package id.ac.umn.rider;

import static java.sql.DriverManager.println;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BikeCreatedFragment extends Fragment {

    View view;

    private String Name;
    private String Brand;
    private String Model;
    private String plate;
    private String cc;
    private String year;
    private Integer image;

    private TextView bikeName;
    private TextView bikeBrand;
    private TextView bikeModel;
    private TextView plateNumber;
    private TextView cyclinderCapacity;
    private TextView yearOfManufacture;
    private ImageView bikeImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_bike_created, container, false);

        bikeName = view.findViewById(R.id.bikeName);
        bikeBrand = view.findViewById(R.id.bikeBrand);
        bikeModel = view.findViewById(R.id.bikeModel);
        plateNumber = view.findViewById(R.id.bikePlate);
        cyclinderCapacity = view.findViewById(R.id.bikeCC);
        yearOfManufacture = view.findViewById(R.id.bikeYear);
        bikeImage = view.findViewById(R.id.bikeImage);

        Bundle bundle = getArguments();

        if (bundle != null) {
            Name = bundle.getString("vehicleName");
            Brand = bundle.getString("vehicleBrand");
            Model = bundle.getString("vehicleModel");
            plate = bundle.getString("vehiclePlate");
            cc = bundle.getString("cylinderCapacity");
            year = bundle.getString("vehicleYear");
            image = bundle.getInt("vehiclePhoto");

        }

        bikeName.setText(Name);
        bikeBrand.setText(Brand);
        bikeModel.setText(Model);
        plateNumber.setText(plate);
        cyclinderCapacity.setText(cc + " cc");
        yearOfManufacture.setText(year);
        bikeImage.setImageResource(image);

        return view;
    }
}