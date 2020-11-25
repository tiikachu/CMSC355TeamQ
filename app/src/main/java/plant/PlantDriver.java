package plant;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.example.pocketgarden.R;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Scanner;



public class PlantDriver {


	public static File file = new File("Plant.txt");
	public static void main(String[] args) throws JSONException, FileNotFoundException {
		Scanner in = new Scanner(System.in);
		String name = "my plant";
		int age = 2;
		int interval = 7;
		String[] special_needs = {"mineral water", "extra sunlight"};
		boolean indoor = false;
		boolean potted = false;

		PlantObject my_plant = new PlantObject();
		System.out.println(my_plant.age + " : " + my_plant.num_plants);
		System.out.println(my_plant.getName() + " : " + my_plant.name);
		System.out.println(my_plant.getInterval() + " : " + my_plant.interval);

		PlantObject my_plant_2 = new PlantObject("abc", 1, 1, null, true, true, "https://bs.floristic.org/image/o/473e2ed33e13f12e5424fff21996c7476520dc4d");
		PlantObject my_plant_3 = new PlantObject("xyz", 10, 1, null, true, true,"https://bs.floristic.org/image/o/473e2ed33e13f12e5424fff21996c7476520dc4d");


		PlantList list1 = new PlantList("Named list");
		PlantList list2 = new PlantList("");
		PlantList list3 = new PlantList();


		list1.add(my_plant_3);
		list1.add(my_plant);
		list1.add(my_plant);
		list1.add(my_plant);
		list1.add(my_plant_2);

		System.out.println(list1.getListName());
		System.out.println(list1.toString());

		list1.remove(my_plant);
		System.out.println(list1.getListName());
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

		PlantLibrary plant_DataBase = new PlantLibrary();
		plant_DataBase.add(list1);
		plant_DataBase.add(list2);
		plant_DataBase.add(list3);
		plant_DataBase.remove(list2);

		System.out.println("\nlength of plant database : " + plant_DataBase.getList_length());






	}





}