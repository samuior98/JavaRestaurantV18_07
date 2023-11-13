package com.generation.restaurant.entities;

public class User 
{
	public String username;
	public String password;
	public int level;//livello di accesso
	                 //nei programmi veri
	                 //un utente non può fare tutto
	                 //alcuni utenti sono admin e possono
	                 //altri normali user, e possono fare solo alcune cose
	 				 //GUEST, e possono fare pochissimo
	//level 0 - admin
	//level 1 - standard
	//level 2 - guest
	
	//non avere tipo di ritorno
	//Chiamarsi con lo stesso identico nome della classe
	//un costruttore verrà sempre invocato preceduto da new
	public User() {}
	
	public User(String us,String pass,int lvl)
	{
		username = us;
		password = pass;
		level = lvl;
	}
	
	public String toString()
	{
		return username + " pass: "+password + " lvl: "+level;
	}
}
