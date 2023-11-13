package com.generation.restaurant.main;

import java.util.ArrayList;
import com.generation.library.Console;
import com.generation.restaurant.entities.Meal;
import com.generation.restaurant.entities.Dish;

public class TestMeal {

	static Meal lastMeal = null;
	static ArrayList<Meal> meals = new ArrayList<Meal>();
	
	private static void printHelp() {
		Console.print("addMeal - inserisci un nuovo pasto\n" + "addDish  - aggiungi un nuovo piatto\n"
				+ "summary  - stampa\n" + "quit     - termina il programma\n" + "deleteMeal - elimina un meal a tua scelta\n" +
				"addDishNotLast - aggiungi piatti a un meal che non sia l'ultimo");
	}

	
	private static void addMeal() {
		lastMeal = new Meal();
		Console.print("Inserisci il numero del tavolo: ");
		lastMeal.tableNumber = Console.readInt();

		Console.print("Inserisci la data dell'ordine: ");
		lastMeal.orderDate = Console.readString();

		Console.print("Inserisci l'ora dell'ordine: ");
		lastMeal.orderTime = Console.readString();

		meals.add(lastMeal);
		Console.print("Meal aggiunto con successo");
	}

	
	private static void addDish() {
		if (lastMeal == null)
			Console.print("Non ho nessun Meal a cui aggiungere un Dish");

		else {
			Dish d = new Dish();
			Console.print("Inserisci il nome del piatto: ");
			d.name = Console.readString();

			Console.print("Inserisci la quantità di carbo del piatto: ");
			d.carbs = Console.readInt();

			Console.print("Inserisci la quantità di protein del piatto: ");
			d.proteins = Console.readInt();

			Console.print("Inserisci la quantità di grassi del piatto: ");
			d.fats = Console.readInt();

			lastMeal.dish.add(d);
			Console.print("Dish aggiunto con successo");
		}
	}
	
	
	private static void printSummary() {
		if (meals.size() == 0)
			Console.print("Non ho Meal");
		else
			for (int i = 0; i < meals.size(); i++)
				Console.print(meals.get(i).toString() + "\n");
	}
	
	
	//Aggiungere un metodo che permetta di eliminare un meal a scelta dell'utente
	private static void deleteMeal() {
		Console.print("Dammi l'indice del meal che vuoi eliminare");
		int ind= Console.readInt();
		for(int i=0; i<meals.size(); i++)
			if(i == ind)
				meals.remove(i);
	}
	
	
	//Aggiungere un metodo che permetta di aggiungere piatti (Dish) a un pasto (Meal) che non sia l'ultimo
	private static void addDishNotLast() {
		
	}
	
	
	public static void main(String[] args) {
		String cmd = "";
		Console.print("Benvenuto al programma di gestione piatti, scrivi \"help\" per avere la lista di comandi");

		do {
			Console.print("Inserire comando");
			cmd = Console.readString();

			switch (cmd) {
			case "help":
				printHelp();
				break;

			case "addMeal":
				addMeal();
				break;

			case "addDish":
				addDish();
				break;

			case "addDishNotLast" :
				addDishNotLast();
				break;
			
			case "deleteMeal" :
				deleteMeal();
				break;
			
			case "summary":
				printSummary();
				break;

			case "quit":
				Console.print("Alla prossima");
				break;

			default:
				Console.print("Comando non valido");
			}
		} while (!cmd.equals("quit"));
	}//main
	
}
