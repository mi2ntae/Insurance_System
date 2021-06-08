package main;

import business.Home;

public class Main {
	
	public static void main(String[] args) {
		Home home = new Home();
		home.initialize();
		home.start();
	}
}
