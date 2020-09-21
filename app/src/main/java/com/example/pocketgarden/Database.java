package com.example.pocketgarden;

public class Database {
    private plant[] PLANTS_LIST = makePlantsList();
    class plant {

        private String type;
        private String commonName;
        private String scientificName;
        private int wateringFrequency;

        public plant(){
            type = "n/a";
            commonName = "n/a";
            scientificName = "n/a";
            wateringFrequency = 0;
        }

        public plant(String type, String commonName, String scientificName, int wateringFrequency){
            this.type = type;
            this.commonName = commonName;
            this.scientificName = scientificName;
            this.wateringFrequency = wateringFrequency;
        }

    }


    private plant[] makePlantsList(){

        plant cactus = new plant("succulent", "cactus", "Pachycereus pringlei", 2);

        plant[] plantList = {cactus};

        return plantList;

    }



}
