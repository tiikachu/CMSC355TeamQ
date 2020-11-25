package plant;

//one masterlist
//other lists for each room or how have you
//list will be made of plant objects
//lists will need list identifier?
//list will need master length

public class PlantList{

    private int listIndex;
    public static int numLists = 0;
    private final int plantMax = 100;
    String listName;
    PlantObject[] plantList;

    public PlantList(){
        this.listIndex = 0;
        numLists++;
        this.listName = "List " + numLists;
        this.plantList = new PlantObject[plantMax];
    }

    public PlantList(String name_in){
        this.listIndex = 0;
        numLists++;
        if(name_in == null || name_in.equals("")){
            this.listName = "List " + numLists;
        }
        this.listName = name_in;
        this.plantList = new PlantObject[plantMax];
    }

    public PlantList(PlantObject plant_in, String name_in){
        this.listIndex = 0;
        numLists++;
        if(name_in == null || name_in.equals("")){
            this.listName = "List " + numLists;
        }
        this.listName = name_in;
        this.plantList = new PlantObject[plantMax];
        add(plant_in);
    }

    public void add(PlantObject plant_in){
        this.plantList[this.listIndex] = plant_in;
        this.listIndex++;
    }

    public String getListName(){
        return this.listName;
    }

    //remove by object
    public void remove(PlantObject plant_in){
        for(int i =0; i < this.listIndex; i++){
            if(this.plantList[i].equals(plant_in)){
                for(int j = i; j < (this.listIndex-1); j++){
                    this.plantList[i] = this.plantList[i+1];
                }
            }

        }
        this.plantList[this.listIndex] = null;
        this.listIndex--;
    }

    public int getListIndex(){
        return this.listIndex;
    }

    //ascending
    public void sort_by_name(){
        PlantObject temp;
        for(int i = 0; i < this.listIndex; i++){
            for(int j = i; j > 0; j--){
                if(this.plantList[j].getName().compareTo(this.plantList[j-1].getName()) < 0){   //compare names
                    temp = this.plantList[j];
                    this.plantList[j] = this.plantList[j-1];
                    this.plantList[j-1] = temp;
                }

            }

        }
    }

    //descending
    public void sort_by_name_reverse(){
        PlantObject temp;
        for(int i = 0; i < this.listIndex; i++){
            for(int j = i; j > 0; j--){
                if(this.plantList[j].getName().compareTo(this.plantList[j-1].getName()) > 0){   //compare names
                    temp = this.plantList[j];
                    this.plantList[j] = this.plantList[j-1];
                    this.plantList[j-1] = temp;
                }

            }

        }
    }

    //as added
    public void sort_by_id(){
        PlantObject temp;
        for(int i = 0; i < this.listIndex; i++){
            for(int j = i; j > 0; j--){
                if(this.plantList[j].getNum_plants() < this.plantList[j-1].getNum_plants()){
                    temp = this.plantList[j];
                    this.plantList[j] = this.plantList[j-1];
                    this.plantList[j-1] = temp;
                }
            }
        }
    }

    public void sort_by_age(){
        PlantObject temp;
        for(int i = 0; i < this.listIndex; i++){
            for(int j = i; j > 0; j--){
                if(this.plantList[j].getAge() < this.plantList[j-1].getAge()){
                    temp = this.plantList[j];
                    this.plantList[j] = this.plantList[j-1];
                    this.plantList[j-1] = temp;
                }
            }
        }
    }

    @Override
    public String toString() {
        for(int i = 0; i < this.listIndex; i++){
            System.out.println("Plant " + i + " : " + this.plantList[i].getName());
        }
        return "end";  //note this is not good syntax
    }

    public PlantObject[] getPlantList() {
        return plantList;
    }

}
