package com.generation.restaurant.entities;

import java.security.PublicKey;
import java.util.ArrayList;

public class Meal {

	public int tableNumber;
	public String orderDate, orderTime;
	public ArrayList<Dish>  dish= new ArrayList<Dish>();
	
	
	//costruttore
	public Meal() {}
	
	public Meal(int tn, String od, String ot) {
		tableNumber= tn;
		orderDate= od;
		orderTime= ot;
	}
	
	
	public boolean isValid() {
		boolean res= true;
		if(tableNumber < 1 || tableNumber > 50)
			res= false;
		
		for(int i=0; i<dish.size(); i++)
			if(!dish.get(i).isValid())
				res= false;
		return res;
	}
	
	
	public int getWeight() {
		int ris= 0;
		for(int i=0; i<dish.size(); i++)
			ris += dish.get(i).getWeight();
		return ris;
	}
	
	
	public int getCalories() {
		int ris= 0;
		for(int i=0; i<dish.size(); i++)
			ris += dish.get(i).getCalories();
		return ris;	}
	
	
	public double getPrice() {
		int ris= 0;
		for(int i=0; i<dish.size(); i++)
			ris += dish.get(i).price;
		return ris;	}
	
	
	public String toString() {
		String res= "Tavolo n° " + tableNumber + "\nData ordinazione " + orderDate + "\nOra ordinazione " + orderTime + "\n";
		for(int i=0; i<dish.size(); i++)
			res += dish.get(i).toString();
		return res;		
	}
	
}
