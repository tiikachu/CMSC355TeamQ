package plant;
//This class maintains the list of Plant lists

//must have 1 master list of all Plant lists
import java.util.Arrays;


public class PlantLibrary {

    private int list_length = 0;
    private PlantList[] plant_library;

    PlantLibrary(){
        int list_max = 100;
        this.plant_library = new PlantList[list_max];
    }

    public void add(PlantList list_in){
        this.plant_library[list_length] = list_in;
        list_length++;
    }

    public void remove(PlantList list_in){
        int list_id = list_in.getListIndex();
        for(int i =0; i < list_length; i++){
            if(plant_library[i].getListIndex() == list_id){
                for(int j = i; j < (this.list_length-1); j++){
                    plant_library[i] = plant_library[i+1];
                }
            }

        }
        plant_library[list_length] = null;
        list_length--;
    }

    public void sort_by_name(){
        PlantList temp;
        for(int i = 0; i < this.list_length; i++){
            for(int j = i; j > 0; j--){
                if(plant_library[j].getListName().compareTo(plant_library[j-1].getListName()) < 0){   //compare list id
                    temp = plant_library[j];
                    plant_library[j] = plant_library[j-1];
                    plant_library[j-1] = temp;
                }

            }

        }
    }

    public void sort_by_name_reverse(){
        PlantList temp;
        for(int i = 0; i < this.list_length; i++){
            for(int j = i; j > 0; j--){
                if(plant_library[j].getListName().compareTo(plant_library[j-1].getListName()) > 0){   //compare list id
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
