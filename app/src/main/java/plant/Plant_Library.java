package plant;
//This class maintains the list of Plant lists

//must have 1 master list of all plant lists
import java.util.Arrays;


public class Plant_Library{

    private int list_max = 100;
    private int list_length = 0;
    private Plant_List[] plant_library;

    Plant_Library(){
        this.plant_library = new Plant_List[list_max];
    }

    void add(Plant_List list_in){
        this.plant_library[list_length] = list_in;
        list_length++;
    }

    void remove(Plant_List list_in){
        int list_id = list_in.getList_id();
        for(int i =0; i < list_length; i++){
            if(plant_library[i].getList_id() == list_id){
                for(int j = i; j < (this.list_length-1); j++){
                    plant_library[i] = plant_library[i+1];
                }
            }

        }
        plant_library[list_length] = null;
        list_length--;
    }

    void sort_by_name(){
        Plant_List temp;
        for(int i = 0; i < this.list_length; i++){
            for(int j = i; j > 0; j--){
                if(plant_library[j].getList_name().compareTo(plant_library[j-1].getList_name()) < 0){   //compare list id
                    temp = plant_library[j];
                    plant_library[j] = plant_library[j-1];
                    plant_library[j-1] = temp;
                }

            }

        }
    }

    void sort_by_name_reverse(){
        Plant_List temp;
        for(int i = 0; i < this.list_length; i++){
            for(int j = i; j > 0; j--){
                if(plant_library[j].getList_name().compareTo(plant_library[j-1].getList_name()) > 0){   //compare list id
                    temp = plant_library[j];
                    plant_library[j] = plant_library[j-1];
                    plant_library[j-1] = temp;
                }

            }

        }
    }

    public int getList_length(){
        return list_length;
    }






}
