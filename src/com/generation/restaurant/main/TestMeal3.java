package com.generation.restaurant.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.generation.library.Console;
import com.generation.restaurant.entities.Meal;
import com.generation.restaurant.entities.Dish;

public class TestMeal3 {

	public static ArrayList<Meal> meals = new ArrayList<Meal>(); //pasti
	public static HashMap<String,String> dictionary = new HashMap<String,String>();

	public static void init() throws Exception {
		Console.print(
			"Scegli una lingua/Choose a language\n"+
			"ITA - italiano\n"+
			"ENG - english\n"+
			"other - english"
		);
		
		String language= Console.readString();
		String filename;
		
		if(language.equalsIgnoreCase("ITA"))
			filename= "italiano.txt";
		else
			filename= "inglese.txt";
			
		File f= new File(filename);
		Scanner reader= new Scanner(f);
		while(reader.hasNextLine()) {
			String row= reader.nextLine();
			String[] rowParts= row.split("=");
			dictionary.put(rowParts[0], rowParts[1]);
		}
		reader.close();
	}
	
	
	
	private static void printHelp() {
		Console.print(dictionary.get("PRINT_1"));
		Console.print(dictionary.get("PRINT_2"));
		Console.print(dictionary.get("PRINT_3"));
		Console.print(dictionary.get("PRINT_4"));
		Console.print(dictionary.get("PRINT_5"));
		Console.print(dictionary.get("PRINT_6"));
		Console.print(dictionary.get("PRINT_7"));
		Console.print(dictionary.get("PRINT_8"));
	}

	
	private static void addMeal() {
		Console.print(dictionary.get("INS_NUM_TAVOLO"));
		
		int tableNumber = Console.readInt();
		Console.print(dictionary.get("INS_DATA_ORDINE"));
		String orderDate = Console.readString();
		
		Console.print(dictionary.get("INS_ORA_ORDINE"));
		String orderTime = Console.readString();

		Meal meal = new Meal(tableNumber, orderDate, orderTime);
		meals.add(meal);
		Console.print(dictionary.get("MEAL_OK"));
	}

	
	//Aggiungere un metodo che permetta di aggiungere piatti (Dish) a un pasto (Meal) che non sia l'ultimo
	private static void addDish(int pos) {
		if(pos < 0 || pos >= meals.size()) {
			Console.print(dictionary.get("POS_NOOK"));
			return;
		}	

		Console.print(dictionary.get("INS_NOME_PIATTO"));
		String name = Console.readString();
		Console.print(dictionary.get("INS_CARBO"));
		int carbs= Console.readInt();
		Console.print(dictionary.get("INS_PROT"));
		int	proteins= Console.readInt();
		Console.print(dictionary.get("INS_FAT"));
		int fats = Console.readInt();
		Console.print(dictionary.get("INS_PREZZO"));
		double price= Console.readDouble();
			
		Dish d = new Dish(name, carbs, proteins, fats, price);
		if(d.isValid()) {
			meals.get(pos).dish.add(d);
		    Console.print(dictionary.get("PIATTO_OK"));
		}
		else
			Console.print(dictionary.get("PIATTO_NOOK"));
	}

		
	private static void addDishToSpecificMeal() {
		Console.print(dictionary.get("INDEX"));
		int pos= Console.readInt();
		
		if(pos<0 || pos >= meals.size())
			Console.print(dictionary.get("NO_TAVOLI"));
		
		else {
			for(int i=0; i<meals.size(); i++) //for(Meal m : meals) ...
				if(meals.get(i).tableNumber == pos) //if(m.tableNumber == pos)
					addDish(i);
		}
	}

	
	//Aggiungere un metodo che permetta di eliminare un meal a scelta dell'utente
	private static void deleteMeal() {
		Console.print(dictionary.get("POS_MEAL"));
		int pos= Console.readInt();
		boolean f= false;
		for (int i = 0; i < meals.size(); i++) { //for(Meal m : meals) ...
			if (meals.get(i).tableNumber == pos) { //if(m.tableNumber == pos)
				meals.remove(i);
				f= true;
			}	
		}
		
		if(!f)
			Console.print(dictionary.get("INDEX_ERR"));
	}
	
	
	//Rimuovere un piatto (dish) a scelta dentro un meal a scelta
	private static void deleteDishMealx() {
		Console.print(dictionary.get("SCEGLI_PIATTO"));
		int m= Console.readInt();
		
		Console.print(dictionary.get("DEL_PIATTO"));
		String p= Console.readString();
		
		Meal meal= null;
		for(int i=0; i<meals.size(); i++) //for(Meal m : meals) ...
			if(meals.get(i).tableNumber == m)
				meal= meals.get(i);

		for(int i=0; i<meal.dish.size(); i++) {
			if(meal.dish.get(i).name.equalsIgnoreCase(p)) {
				meal.dish.remove(i); //
				Console.print("Piatto " + p + " eliminato con successo dal pasto " + m);
			}
			else
				Console.print(dictionary.get("NO_PIATTO"));
		}
		
	}
	
	
	//Stampare 3/4 piatti tipici e chiedere all'utente quale piatto tra questi vuole aggiungere a quale pasto
	private static void printTipici() {
		String[] tipici= {"Lasagna", "Pizza", "Carbonare", "Pasta"};
		String res= "Questi sono i piatti tipici...\n";
		for(int i=0; i<tipici.length; i++) //for(String s : tipici)
			res += tipici[i] + " "; //res += s + " "
		Console.print(res);
		
		Console.print(dictionary.get("TIPIC"));
		String pt= Console.readString();
		Dish dish= new Dish(pt, 20, 10, 12, 10);
		
		Console.print(dictionary.get("SCEGLI2"));
		int nt= Console.readInt();
		
		for(Meal meal : meals)
			if(meal.tableNumber == nt) {
				meal.dish.add(dish);
				Console.print(dictionary.get("TIPIC_OK"));
			}
	}
	
	
	private static void printSummary() {
		if (meals.size() == 0)
			Console.print(dictionary.get("NO_MEAL"));
		else
			for (int i = 0; i < meals.size(); i++)
				Console.print(meals.get(i).toString() + "\n");
	}

	
	public static void main(String[] args) throws Exception {
		init();
		String cmd = "";
		Console.print(dictionary.get("WELCOME"));

		do {
			Console.print(dictionary.get("INSERT_COMMAND"));
			cmd = Console.readString();

			switch (cmd) {
			case "help":
				printHelp();
				break;

			case "addMeal":
				addMeal();
				break;

			case "addDish":
				addDish(meals.size()-1);
				break;

			case "addDishNotLast":
				addDishToSpecificMeal();
				break;

			case "deleteMeal":
				deleteMeal();
				break;

			case "deleteDishMealx":
				deleteDishMealx();
				break;
			
			case "printTipici":
				printTipici();
				break;
			
			case "summary":
				printSummary();
				break;

			case "quit":
				Console.print(dictionary.get("QUIT"));
				break;

			default:
				Console.print(dictionary.get("CMD_NOOK"));
			}
		} while (!cmd.equals("quit"));
	}

}
