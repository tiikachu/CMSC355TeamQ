package plant;

//one masterlist
//other lists for each room or how have you
//list will be made of plant objects
//lists will need list identifier?
//list will need master length

import java.util.Arrays;

public class Plant_List {


    private int list_index;
    public static int num_lists = 0;
    private static int plant_max = 100;
    String list_name;
    // ArrayList<Plant_Object> plant_list;
    Plant_Object[] plantlist;

    Plant_List() {
        this.list_index = 0;
        num_lists++;
        this.list_name = "List " + num_lists;
        this.plantlist = new Plant_Object[plant_max];
    }

    Plant_List(String name_in) {
        this.list_index = 0;
        num_lists++;
        if (name_in == null || name_in.equals("")) {
            this.list_name = "List " + num_lists;
        }
        this.list_name = name_in;
        this.plantlist = new Plant_Object[plant_max];
    }

    Plant_List(Plant_Object plant_in, String name_in) {
        this.list_index = 0;
        num_lists++;
        if (name_in == null || name_in.equals("")) {
            this.list_name = "List " + num_lists;
        }
        this.list_name = name_in;
        this.plantlist = new Plant_Object[plant_max];
        add(plant_in);
    }

    void add(Plant_Object plant_in) {
        plantlist[this.list_index++] = plant_in;
        this.list_index++;
    }

    String name() {
        return this.list_name;
    }

    @Override
    public String toString() {
        return "Plant_List{" +
                "plantlist=" + Arrays.toString(plantlist) +
                '}';
    }
}
