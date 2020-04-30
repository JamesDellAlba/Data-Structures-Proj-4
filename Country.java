/**
 * This class holds data about each country in the array of countries. Name of the country,
 * country code, capital of the country, population of the country, GDP of the country, and happiness rank of the country.
 *
 * @author James Dell'Alba
 * @version 2/17/2020
 */
public class Country {

	private String name;
	private String code;
	private String capitol;
	private long population;
	private long grossDomesticProduct;
	private long happinessRank;
	private long gdpPerCapita;

	/**
	 * Constructor for the country class
	 * Inputs are country name, country code, country capitol, country population, country GDP, and happiness rank
	 * Constructs a country object with each of the above inputs as data fields
	 * No default constructor is included because it is unnecessary
	 */
	public Country(String name, String code, String capitol, long population, long grossDomesticProduct, long happinessRank) {
		this.name = name;
		this.code = code;
		this.capitol = capitol;
		this.population = population;
		this.grossDomesticProduct = grossDomesticProduct;
		this.happinessRank = happinessRank;
		this.gdpPerCapita = this.grossDomesticProduct / this.population;
	}

	/**
	 * getter for country name
	 * no input, returns the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * getter for country code
	 * no input, returns the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * getter for country capitol
	 * no input, returns the capitol
	 */
	public String getCapitol() {
		return this.capitol;
	}

	/**
	 * getter for country population
	 * no input, returns the population
	 */
	public long getPopulation() {
		return this.population;
	}

	/**
	 * getter for GDP
	 * no input, returns the GDP
	 */
	public long getGrossDomesticProduct() {
		return this.grossDomesticProduct;
	}

	/**
	 * getter for happiness rank
	 * no input, returns the happiness rank
	 */
	public long getHappinessRank() {
		return this.happinessRank;
	}

	/**
	 * setter for name
	 * input is the new name, returns void
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * setter for code
	 * input is the new code, returns void
	 */
	public void setCode(String newCode) {
		this.code = newCode;
	}

	/**
	 * setter for capitol
	 * input is the new capitol, returns void
	 */
	public void setCapitol(String newCapitol) {
		this.capitol = newCapitol;
	}

	/**
	 * setter for population
	 * input is the new population, returns void
	 */
	public void setPopulation(long newPop) {
		this.population = newPop;
	}

	/**
	 * setter for GDP
	 * input is the new GDP, returns void
	 */
	public void setGrossDomesticProduct(long newGDP) {
		this.grossDomesticProduct = newGDP;
	}

	/**
	 * setter for happiness rank
	 * input is the new rank, returns void
	 */
	public void setHappinessRank(long newHappiness) {
		this.happinessRank = newHappiness;
	}

	/**
	 * setter for GDP per capita
	 * input is the new GDP per capita, returns void
	 */
	public void setGdpPerCapita(long newGDPperCapita) {
		this.gdpPerCapita = newGDPperCapita;
	}

	/**
	 * getter for GDP per capita
	 * no input, returns the GDP per capita
	 */
	public long getGdpPerCapita() {
		return this.gdpPerCapita;
	}

	/**
	 * check the alphabetical order of two strings
	 * string1 comes before string2: return 1
	 * string2 comes before string1: return -1
	 * if they are the same string (i continues all the way to the end): return 0
	 *
	 * @param string1 the first string to compare
	 * @param string2 the second string to compare
	 */
	public int compareStrings(String string1, String string2) {
		for (int i = 0; (i < string1.length() && i < string2.length()); i++) {
			if (string1.charAt(i) < string2.charAt(i)) {
				return 1;
			}
			else if (string1.charAt(i) > string2.charAt(i)) {
				return -1;
			}
		}
		return 0;
	}

	/**
	 * print the contents of a country
	 * no input, returns void after printing
	 */
	public void printCountry() {
		System.out.printf("%-33s %-10s %-20s %-15d %-16d %-10d %-10d\n", this.getName(), this.getCode(), this.getCapitol(),
				this.getPopulation(), this.getGrossDomesticProduct(), this.getGdpPerCapita(), this.getHappinessRank());
	}
}