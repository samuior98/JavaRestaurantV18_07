package com.generation.restaurant.main;

import java.util.ArrayList;
import com.generation.library.Console;
import com.generation.restaurant.entities.Meal;
import com.generation.restaurant.entities.Dish;

public class TestMeal2 {

	public static ArrayList<Meal> meals = new ArrayList<Meal>(); //pasti

	
	private static void printHelp() {
		Console.print("addMeal          - inserisci un nuovo pasto\n" +
	                  "addDish          - aggiungi un nuovo piatto\n" +
	                  "addDishNotLast   - aggiungi piatti a un meal che non sia l'ultimo" +
	                  "deleteMeal       - elimina un meal a tua scelta\n" +
	                  "deleteDishMealx  - elimina piatto x del pasto y" +
	                  "printTipici      - stampa i piatti tipici" +
	                  "summary          - stampa\n" +
	                  "quit             - termina il programma\n");
	}

	
	private static void addMeal() {
		Console.print("Inserisci il numero del tavolo: ");
		int tableNumber = Console.readInt();
		Console.print("Inserisci la data dell'ordine: ");
		String orderDate = Console.readString();
		Console.print("Inserisci l'ora dell'ordine: ");
		String orderTime = Console.readString();

		Meal meal = new Meal(tableNumber, orderDate, orderTime);
		meals.add(meal);
		Console.print("Meal aggiunto con successo");
	}

	
	//Aggiungere un metodo che permetta di aggiungere piatti (Dish) a un pasto (Meal) che non sia l'ultimo
	private static void addDish(int pos) {
		if(pos < 0 || pos >= meals.size()) {
			Console.print("Attenzione! Posizione non esistente!");
			return;
		}	

		Console.print("Inserisci il nome del piatto: ");
		String name = Console.readString();
		Console.print("Inserisci la quantità di carbo del piatto: ");
		int carbs= Console.readInt();
		Console.print("Inserisci la quantità di protein del piatto: ");
		int	proteins= Console.readInt();
		Console.print("Inserisci la quantità di grassi del piatto: ");
		int fats = Console.readInt();
		Console.print("Inserisci il suo prezzo");
		double price= Console.readDouble();
			
		Dish d = new Dish(name, carbs, proteins, fats, price);
		if(d.isValid()) {
			meals.get(pos).dish.add(d);
		    Console.print("Dish (piatto) aggiunto con successo");
		}
		else
			Console.print("Dish (piatto) non aggiunto");
	}

		
	private static void addDishToSpecificMeal() {
		Console.print("A quale pasto (indice) vuoi inserire il piatto?");
		int pos= Console.readInt();
		
		if(pos<0 || pos >= meals.size())
			Console.print("Non abbiamo tutti quei tavoli!");
		
		else {
			for(int i=0; i<meals.size(); i++) //for(Meal m : meals) ...
				if(meals.get(i).tableNumber == pos) //if(m.tableNumber == pos)
					addDish(i);
		}
	}

	
	//Aggiungere un metodo che permetta di eliminare un meal a scelta dell'utente
	private static void deleteMeal() {
		Console.print("Quale meal vuoi eliminare? Inserisci la sua posizione");
		int pos= Console.readInt();
		boolean f= false;
		for (int i = 0; i < meals.size(); i++) { //for(Meal m : meals) ...
			if (meals.get(i).tableNumber == pos) { //if(m.tableNumber == pos)
				meals.remove(i);
				f= true;
			}	
		}
		
		if(!f)
			Console.print("Indice tavolo non valido");
	}
	
	
	//Rimuovere un piatto (dish) a scelta dentro un meal a scelta
	private static void deleteDishMealx() {
		Console.print("Quale pasto scegli? Inserisci il numero del tavolo");
		int m= Console.readInt();
		
		Console.print("Quale piatto vuoi eliminare? Inserisci il suo nome");
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
				Console.print("Piatto non presente nel pasto selezionato");
		}
		
	}
	
	
	//Stampare 3/4 piatti tipici e chiedere all'utente quale piatto tra questi vuole aggiungere a quale pasto
	private static void printTipici() {
		String[] tipici= {"Lasagna", "Pizza", "Carbonare", "Pasta"};
		String res= "Questi sono i piatti tipici...\n";
		for(int i=0; i<tipici.length; i++) //for(String s : tipici)
			res += tipici[i] + " "; //res += s + " "
		Console.print(res);
		
		Console.print("Quale tra questi piatti tipici vuoi aggiungere al pasto x ?");
		String pt= Console.readString();
		Dish dish= new Dish(pt, 20, 10, 12, 10);
		
		Console.print("A quale pasto vuoi aggiungere questo piatto tipico? Inserisci il numero del tavolo");
		int nt= Console.readInt();
		
		for(Meal meal : meals)
			if(meal.tableNumber == nt) {
				meal.dish.add(dish);
				Console.print("Piatto tipico aggiunto correttamente al tavolo " + nt);
			}
	}
	
	
	private static void printSummary() {
		if (meals.size() == 0)
			Console.print("Non ho Meal da stampare");
		else
			for (int i = 0; i < meals.size(); i++)
				Console.print(meals.get(i).toString() + "\n");
	}

	
	public static void main(String[] args) {
		String cmd = "";
		Console.print("Benvenuto al programma di gestione prenotazioni pasti, scrivi \"help\" per avere la lista di comandi");

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
				Console.print("Alla prossima");
				break;

			default:
				Console.print("Comando non valido");
			}
		} while (!cmd.equals("quit"));
	}

}
