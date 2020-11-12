package plant;

import org.json.JSONException;

import java.util.Scanner;
public class Plant_Driver{
	public static void main(String[] args) throws JSONException {
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

		Plant_Object my_plant_2 = new Plant_Object("abc",1,1,null,true,true);
		Plant_Object my_plant_3 = new Plant_Object("xyz",10,1,null,true,true);



		Plant_List list1 = new Plant_List("Named list");
		Plant_List list2 = new Plant_List("");
		Plant_List list3 = new Plant_List();


		list1.add(my_plant_3);
		list1.add(my_plant);
		list1.add(my_plant);
		list1.add(my_plant);
		list1.add(my_plant_2);

		System.out.println(list1.getList_name());
		System.out.println(list1.toString());

		list1.remove(my_plant);
		System.out.println(list1.getList_name());
		System.out.println(list1.toString());

		System.out.println("Sort by name: ");
		list1.sort_by_name();
		list1.toString();

		System.out.println("\n Reverse sort by name");
		list1.sort_by_name_reverse();
		list1.toString();

		System.out.println("\nSort by age");
		list1.sort_by_age();
		list1.toString();

		Plant_Library plant_DataBase = new Plant_Library();
		plant_DataBase.add(list1);
		plant_DataBase.add(list2);
		plant_DataBase.add(list3);
		plant_DataBase.remove(list2);

		System.out.println("\nlength of plant database : " + plant_DataBase.getList_length());





		
	}
}