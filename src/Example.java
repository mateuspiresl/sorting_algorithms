/**
 * Classe dos dados.
 * 
 * @author Mateus Pires Lustosa
 */

import java.util.Random;

public class Example implements Comparable<Example> {

	private int id;
	private String name;

	public Example(int matricula, String name, int year)
	{
		this.id = matricula;
		this.name = name;
	}

	public int getId() { return id; }
	public String getName() { return name; }
	
	public int compareTo(Example that)
	{
		int id = this.id - that.id;
		if (id != 0) return id;
		
		return this.name.compareTo(that.name);
	}
	
	public String toString()
	{
		return "{ " + id + ", " + name + " }";
	}
	
	public static Example[] generateData(int length)
	{
		Example[] data = new Example[length];
		Random rnd = new Random();
		
		while (--length >= 0)
		{
			data[length] = new Example(
				100000 + rnd.nextInt(899999),
				getRandomName(rnd) + " " + getRandomName(rnd),
				1980 + rnd.nextInt(36)
			);
		}
		
		return data;
	}
	
	private static String getRandomName(Random rnd)
	{
		return names[rnd.nextInt(names.length)];
	}
	
	private static String[] names = new String[] {
		"Banana", "Maçã", "Laranja", "Morango", "Pêra", "Uva", "Mamão", "Cajú", "Goiaba", "Acerola", "Maracujá",
		"Uno", "Palio", "Civic", "Frontier", "Corsa", "Celta", "Hilux", "Montana", "Ferrari",
		"Branco", "Amarelo", "Vermelho", "Verde", "Laranja", "Azul", "Roxo", "Ciano", "Preto"
	};
	
}
