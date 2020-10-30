package plant;

import java.util.Scanner;

public class Plant_Driver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name = "my plant";
        int age = 2;
        int interval = 7;
        String[] special_needs = {"mineral water", "extra sunlight"};
        boolean indoor = false;
        boolean potted = false;

        Plant_Object my_plant = new Plant_Object();
        System.out.println(my_plant.age + " : " + my_plant.num_plants);
        System.out.println(my_plant.getName() + " : " + my_plant.name);
        System.out.println(my_plant.getInterval() + " : " + my_plant.interval);

        Plant_List list1 = new Plant_List("Named list");
        Plant_List list2 = new Plant_List("");
        Plant_List list3 = new Plant_List();

        System.out.println(list1.name());
        list1.toString();
        System.out.println("\nList2 : " + list2.name());
        list2.toString();
        System.out.println("\nList3 : " + list3.name());
        list3.toString();

    }
}