package id.ac.umn.rider;

import java.util.ArrayList;

public class WorkshopData {
    private static String[] nama = {
            "Bengkel Bocuan",
            "Aliong Motors",
            "Sensen Auto",
    };

    private static String[] rating = {
            "4.5",
            "4.2",
            "4.0",
    };

    private static String[] distance = {
            "1.2 km",
            "2.5 km",
            "3.0 km",
    };

    private static String[] telp = {
            "08123456789",
            "08123456789",
            "08123456789",
    };

    private static String[] desc = {
            "Bengkel yang menyediakan jasa perbaikan motor dan mobil",
            "Bengkel yang menyediakan jasa perbaikan motor dan mobil",
            "Bengkel yang menyediakan jasa perbaikan motor dan mobil",
    };


    public static ArrayList<Workshop> getListData(){
        ArrayList<Workshop> list = new ArrayList<>();
        for (int position = 0; position < nama.length; position++){
            Workshop workshop = new Workshop();
            workshop.setNama(nama[position]);
            workshop.setRating(rating[position]);
            workshop.setDistance(distance[position]);
            workshop.setTelp(telp[position]);
            workshop.setDesc(desc[position]);
            list.add(workshop);
        }
        return list;
    }
}
