package plant;

//one masterlist
//other lists for each room or how have you
//list will be made of plant objects
//lists will need list identifier?
//list will need master length

//num)lists used as list id

import java.util.Arrays;

public class Plant_List{


    private int list_length;
    public static int num_lists = 0;
    private static int plant_max = 100;
    private int list_id = 0;
    String list_name;
    Plant_Object[] plantlist;


    Plant_List(){
        this.list_length = 0;
        num_lists++;
        this.list_id = num_lists;
        this.list_name = "List " + num_lists;
        this.plantlist = new Plant_Object[plant_max];
    }

    Plant_List(String name_in){
        this.list_length = 0;
        num_lists++;
        if(name_in == null || name_in.equals("")){
            this.list_name = "List " + num_lists;
        }
        this.list_id = num_lists;
        this.list_name = name_in;
        this.plantlist = new Plant_Object[plant_max];
    }


    Plant_List(Plant_Object plant_in, String name_in){
        this.list_length = 0;
        num_lists++;
        if(name_in == null || name_in.equals("")){
            this.list_name = "List " + num_lists;
        }

        this.list_name = name_in;
        this.plantlist = new Plant_Object[plant_max];
        add(plant_in);
    }

    void add(Plant_Object plant_in){
        this.plantlist[this.list_length] = plant_in;
        this.list_length++;
    }

    public String getList_name(){
        return this.list_name;
    }

    //remove by object
    void remove(Plant_Object plant_in){
        for(int i =0; i < this.list_length; i++){
            if(this.plantlist[i].equals(plant_in)){
                for(int j = i; j < (this.list_length-1); j++){
                    this.plantlist[i] = this.plantlist[i+1];
                }
            }

        }
        this.plantlist[this.list_length] = null;
        this.list_length--;
    }

    //ascending
    void sort_by_name(){
        Plant_Object temp;
        for(int i = 0; i < this.list_length; i++){
            for(int j = i; j > 0; j--){
                if(this.plantlist[j].getName().compareTo(this.plantlist[j-1].getName()) < 0){   //compare names
                    temp = this.plantlist[j];
                    this.plantlist[j] = this.plantlist[j-1];
                    this.plantlist[j-1] = temp;
                }

            }

        }
    }

    //descending
    void sort_by_name_reverse(){
        Plant_Object temp;
        for(int i = 0; i < this.list_length; i++){
            for(int j = i; j > 0; j--){
                if(this.plantlist[j].getName().compareTo(this.plantlist[j-1].getName()) > 0){   //compare names
                    temp = this.plantlist[j];
                    this.plantlist[j] = this.plantlist[j-1];
                    this.plantlist[j-1] = temp;
                }

            }

        }
    }

    //as added
    void sort_by_id(){
        Plant_Object temp;
        for(int i = 0; i < this.list_length; i++){
            for(int j = i; j > 0; j--){
                if(this.plantlist[j].getNum_plants() < this.plantlist[j-1].getNum_plants()){
                    temp = this.plantlist[j];
                    this.plantlist[j] = this.plantlist[j-1];
                    this.plantlist[j-1] = temp;
                }
            }
        }
    }

    void sort_by_age(){
        Plant_Object temp;
        for(int i = 0; i < this.list_length; i++){
            for(int j = i; j > 0; j--){
                if(this.plantlist[j].getAge() < this.plantlist[j-1].getAge()){
                    temp = this.plantlist[j];
                    this.plantlist[j] = this.plantlist[j-1];
                    this.plantlist[j-1] = temp;
                }
            }
        }
    }

    public int getList_id(){
        return this.list_id;
    }


    

    @Override
    public String toString() {
        for(int i = 0; i < this.list_length; i++){
            System.out.println("Plant " + i + " : " + this.plantlist[i].getName());
        }
        return "end";  //note this is not good syntax
    }
}