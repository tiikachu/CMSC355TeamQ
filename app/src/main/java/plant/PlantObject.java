package plant;


import java.io.Serializable;

public class PlantObject implements Serializable {
	String name;
	int age;    //in months
	int interval;    //in days if in weeks convert to days
	String[] special_needs;    //on comma split
	//special needs include: soil type and health, leaf appearance, sun light per day, environment
	String imgURL;


	boolean indoor; // by default set to true, if outdoor set to false
	boolean potted; // by default set to potted by
	int num_plants = 0; //counter cannot be used in list view to get number of plants in list this would be global

	public PlantObject() {
		name = "plant " + num_plants;
		age = 0;
		interval = 1;
		special_needs = null;
		indoor = true;
		potted = true;
		imgURL = "";
		num_plants++;
	}



	public PlantObject(String name_in, int age_in, int interval_in, String[] special_needs_in, boolean indoor_in, boolean potted_in, String url_in){
		name = name_in;
		age = age_in;
		interval = interval_in;
		special_needs = special_needs_in;
		indoor = indoor_in;
		potted = potted_in;
		imgURL = url_in;
		num_plants++;
	}

	public String getName() {
		return this.name;
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

	public void setImgURL(String url_in){ this.imgURL = url_in; }

	public String getImgURL(){
		return this.imgURL;
	}

}