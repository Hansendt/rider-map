package id.ac.umn.rider;

import java.util.ArrayList;

public class SpbuData {
    private static String[] company ={
            "Pertamina",
            "Shell",
            "Pertamina",
    };

    private static String[] name ={
            "SPBU Pertamina 1",
            "SPBU Shell 1",
            "SPBU Pertamina 2",
    };

    private static float[] distance ={
            0.5f,
            0.9f,
            1.5f,
    };

    public static ArrayList<Spbu> getListData(){
        ArrayList<Spbu> list = new ArrayList<>();
        for (int position = 0; position < company.length; position++){
            Spbu spbu = new Spbu();
            spbu.setCompany(company[position]);
            spbu.setName(name[position]);
            spbu.setDistance(distance[position]);
            list.add(spbu);
        }
        return list;
    }
}
