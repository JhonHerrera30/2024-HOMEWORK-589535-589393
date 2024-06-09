package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole implements IO{
	
	private Scanner scanner;
	
	public IOConsole (Scanner s) {
		this.scanner=s;
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	public String leggiRiga() {
		String riga = scanner.nextLine();
		//scannerDiLinee.close();
		return riga;
	}
}