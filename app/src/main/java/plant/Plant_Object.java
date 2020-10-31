package plant;

import org.json.JSONArray;

public class Plant_Object{
	String name;
	int age;	//in months
	int interval;	//in days if in weeks convert to days
	String[] special_needs; 	//on comma split
	//special needs include: soil type and health, leaf appearance, sun light per day, environment


	boolean indoor; // by default set to true, if outdoor set to false
	boolean potted; // by default set to potted by
	int num_plants = 0; //counter cannot be used in list view to get number of plants in list this would be global

	public Plant_Object(){
		name = "plant " + num_plants;
		age = 0;
		interval = 1;
		special_needs = null;
		indoor = true;
		potted = true;
		num_plants++;
	}

	//JSON constructor
	Plant_Object(String name_in){
		Get_Plant_Info JSON_in = new Get_Plant_Info();
		name = JSON_in.getSciName(name_in);  //getsciname?
		age = 0;							// user input
		interval = 1;						//use if else?
		special_needs = null;				//maybe fill with ph level, and temp needs
		indoor = true;						//user input
		potted = true;						//user input
		num_plants++;
	}

	Plant_Object(String name_in, int age_in, int interval_in,String[] special_needs_in, boolean indoor_in, boolean potted_in){
		name = name_in;
		age = age_in;
		interval = interval_in;
		special_needs = special_needs_in;
		indoor = indoor_in;
		potted = potted_in;
		num_plants++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String[] getSpecial_needs() {
		return special_needs;
	}

	public void setSpecial_needs(String[] special_needs) {
		this.special_needs = special_needs;
	}

	public boolean isIndoor() {
		return indoor;
	}

	public void setIndoor(boolean indoor) {
		this.indoor = indoor;
	}

	public boolean isPotted() {
		return potted;
	}

	public void setPotted(boolean potted) {
		this.potted = potted;
	}

	public int getNum_plants() {
		return num_plants;
	}

	public void setNum_plants(int num_plants) {
		this.num_plants = num_plants;
	}
}