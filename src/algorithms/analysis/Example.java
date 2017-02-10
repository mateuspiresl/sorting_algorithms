package algorithms.analysis;
import java.util.Random;

/**
 * Data class for exemplification of use.
 * Holds simple data and provides method to automatically generate
 * random data.
 * 
 * @author Mateus Pires Lustosa
 */
public class Example implements Comparable<Example> {

	private int id;
	private String name;

	/**
	 * Constructor.
	 * @param id the ID.
	 * @param name the name.
	 */
	public Example(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	/**
	 * Returns the ID.
	 * @return the ID.
	 */
	public int getId() { return id; }
	/**
	 * Returns the name.
	 * @return the name.
	 */
	public String getName() { return name; }
	
	/**
	 * Comparable method.
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Example that)
	{
		int id = this.id - that.id;
		if (id != 0) return id;
		
		return this.name.compareTo(that.name);
	}
	
	/**
	 * String representation method.
	 * @see Object#toString()
	 */
	@Override
	public String toString()
	{
		return "{ " + id + ", " + name + " }";
	}
	
	/**
	 * Generates random Example data.
	 * @param length the amount of data to generate.
	 * @return the data array.
	 */
	public static Example[] generateData(int length)
	{
		Example[] data = new Example[length];
		Random rnd = new Random();
		
		while (--length >= 0)
		{
			data[length] = new Example(
				1 + rnd.nextInt(Integer.MAX_VALUE),
				getRandomName(rnd) + " " + getRandomName(rnd)
			);
		}
		
		return data;
	}
	
	/**
	 * Returns an random name from the random names list.
	 * @param rnd the Random instance to use.
	 * @return a random name.
	 */
	private static String getRandomName(Random rnd) {
		return names[rnd.nextInt(names.length)];
	}
	
	// Random names list
	public static final String[] names = new String[] {
			"Bradford", "Lawson", "Frank", "Robinson", "Dennis", "Fernandez", "Woodrow", "Daniel", 
			"Vickie", "Douglas", "Ted", "Cole", "Joel", "Jackson", "Harriet", "Blair", 
			"Keith", "Waters", "Antonia", "Shelton", "Lori", "Jefferson", "Roy", "Owens", 
			"Eula", "Kelly", "Courtney", "Carpenter", "Gerald", "Reid", "Joy", "Mcbride", 
			"Margaret", "Greer", "Rosemary", "Foster", "Latoya", "Keller", "Tara", "Gutierrez", 
			"Lance", "Hamilton", "Robert", "Swanson", "Jeffrey", "Jennings", "Stuart", "Flowers", 
			"Dianne", "Harrington", "Roberto", "Lucas", "Amy", "Cain", "Conrad", "Norman", 
			"Penny", "Ward", "Ramon", "Stevens", "Terry", "Salazar", "Claudia", "Rodriguez", 
			"Theodore", "Tucker", "Ian", "Hayes", "Nathaniel", "Wright", "Paula", "Martinez", 
			"Kara", "Bass", "Salvador", "Terry", "Wendy", "Jacobs", "Yolanda", "Francis", 
			"Virginia", "Benson", "Irma", "Wilson", "Edna", "Strickland", "Eileen", "Hale", 
			"Rolando", "Peters", "Charlotte", "Blake", "Chester", "Paul", "Marvin", "Wilkerson", 
			"Dwayne", "Lee", "Clinton", "Patterson"
	};
	
}
