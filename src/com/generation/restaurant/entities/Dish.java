package com.generation.restaurant.entities;

public class Dish {
	
	public String name;
	public int carbs, proteins, fats;
	public double price;
	
	
	//costruttore
	public Dish() {};
	
	public Dish(String n, int c, int p, int f, double pr) {
		name= n;
		carbs= c;
		proteins= p;
		fats= f;
		price= pr;
	}
	
	
	public boolean isValid() {
		if(price <= 0 || name.isBlank() || carbs < 0 || proteins < 0 || fats < 0)
			return false;
		return true;		
	}
	
	
	public int getWeight() {
		return carbs + proteins + fats;
	}
	
	
	public int getCalories() {
		return (carbs + proteins)*4 + fats*9;
	}
	
	
	public String toString() {
		String res= "\n\nIl piatto " + name + " ha..." + "\n" + carbs + " gr di carboidrati" +
	                "\n" + proteins + " gr di proteine \n" + fats + " gr di grassi" +
				    "\ne costa " + price + " euro";
		return res;
	}

}
