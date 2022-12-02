package com.proyecto.Gpsi;

import java.util.ArrayList;
import java.util.Scanner;

public class java2 {
	public static void main(String[] args) {
		/*
		 * Creamos un ArrayList, recordemos que un ArrayList puede ser de cualquier
		 * tipo; para no complicar las cosas haremos un ArrayList de tipo String
		 */
		ArrayList<String> numGuias = new ArrayList<>();

		numGuias.add("1000971636");
		numGuias.add("10009716361");
		numGuias.add("10009716362");
		numGuias.add("10009716363");

		// Método 1
		for (String guia : numGuias) {
			System.out.println(guia);
		}

		while (true) {

			System.out.println("Empezamos el programa");

			System.out.println("Por favor introduzca su ID por teclado:");

			String entradaTeclado = "";

			Scanner entradaEscaner = new Scanner(System.in); // Creación de un objeto Scanner

			entradaTeclado = entradaEscaner.nextLine(); // Invocamos un método sobre un objeto Scanner

			

			System.out.println("");
			System.out.println("");
			System.out.println("");
			
			boolean existe = false;
			
			
			for (String guia : numGuias) {
				System.out.println(guia);
				if(guia != entradaTeclado){

					System.out.println("registro ingresado");
					
				}
			}

			

			

			System.out.println("");
			System.out.println("");
			System.out.println("");


		}
		

	}
}
