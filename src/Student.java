/**
 * Classe dos dados.
 * 
 * @author Mateus Pires Lustosa
 */

import java.util.Random;

public class Student implements Comparable<Student> {

	private int id;
	private String name;
	private int year;

	public Student(int matricula, String name, int year)
	{
		this.id = matricula;
		this.name = name;
		this.year = year;
	}

	public int getId() { return id; }
	public String getName() { return name; }
	public int getYear() { return year; }
	
	public int compareTo(Student that)
	{
		return this.id - that.id;
	}
	
	public String toString()
	{
		return "{ " + id + ", " + name + ", " + year + " }";
	}
	
	public static Student[] generateData(int length)
	{
		Student[] data = new Student[length];
		Random rnd = new Random();
		
		while (--length >= 0)
		{
			data[length] = new Student(
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
