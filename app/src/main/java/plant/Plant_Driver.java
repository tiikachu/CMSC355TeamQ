package plant;

import java.util.Scanner;
public class Plant_Driver{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String name = "my plant";
		int age = 2;
		int interval = 7;	
		String[] special_needs = {"mineral water", "extra sunlight"}; 	
		boolean indoor = false; 
		boolean potted = false; 

		Plant_Object my_plant = new Plant_Object();
		System.out.println(my_plant.age + " : " + my_plant.num_plants);

		
	}
}