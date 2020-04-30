import java.io.File;
import java.util.Scanner;

/**
 * this class creates a Binary Search Tree with nodes that hold the GDP per Capita and Name of a country.
 * It then performs several steps:
 * 1. Insert nodes into a binary search tree.
 * 2. Print all nodes in the tree using inorder traversal.
 * 3. Delete countries "Australia", "Greece", and "Norway", then print the tree using preorder traversal.
 * 4. Search for countries "Sri Lanka", "North Cyprus", "Greece", and "Australia", and print the GDP per capita
 * of the found countries.
 * 5. Delete countries "Malawi", "Somalia", "Canada", "Tusisia", and "New Zealand", then print the tree
 * using postorder traversal.
 * 6. Print the bottom 5 and the top 5 countries by gdp.
 *
 * @author James Dell'Alba
 * @version 4/3/2020
 */
public class Project4 {
	public static void main(String[] args) throws java.io.FileNotFoundException {
		Scanner input = new Scanner(System.in);
		String fileName = "";
		do {
			System.out.println("enter the name of the file");
			fileName = input.next();
		} while (!fileName.equals("Countries4.csv"));

		File countries = new File(fileName);
		Scanner fileInput = new Scanner(countries);

		fileInput.useDelimiter(",|\n"); //set the delimiter to comma or newline

		String firstLine = fileInput.nextLine(); //move the cursor past the first line of the file

		//create an array of countries
		Country[] countryArray = new Country[155];
		for (int i = 0; i < 155; i++) {
			//fill the array with Country objects
			String name = fileInput.next();
			String code = fileInput.next();
			String capitol = fileInput.next();
			long population = Double.valueOf(fileInput.next()).longValue();
			long gdp = Double.valueOf(fileInput.next()).longValue();
			long happiness = Double.valueOf(fileInput.next()).longValue();
			countryArray[i] = new Country(name, code, capitol, population, gdp, happiness);
		}

		//step 1
		BinarySearchTree countryTree = new BinarySearchTree();
		for (int i = 0; i < 155; i++) {
			countryTree.insert(countryArray[i].getName(), countryArray[i].getGdpPerCapita());
		}

		//step 2
		countryTree.printInOrder();
		//step 3
		countryTree.delete("Australia");
		countryTree.delete("Greece");
		countryTree.delete("Norway");
		System.out.println("\nAustralia, Greece, and Norway have been deleted\n");
		countryTree.printPreOrder();

		//step 4
		System.out.printf("\nfinding Sri Lanka... ");
		System.out.printf("gdp per capita: " + countryTree.find("Sri Lanka") + "\n");
		System.out.printf("\nfinding North Cyprus... ");
		System.out.printf("North Cyprus gdp: " + countryTree.find("North Cyprus") + "\n");
		System.out.printf("\nfinding Greece... ");
		System.out.printf("Greece gdp: " + countryTree.find("Greece") + "\n");
		System.out.printf("\nfinding Australia... ");
		System.out.printf("Australia gdp: " + countryTree.find("Australia") + "\n");

		//step 5
		countryTree.delete("Malawi");
		countryTree.delete("Somalia");
		countryTree.delete("Canada");
		countryTree.delete("Tunisia");
		countryTree.delete("New Zealand");
		System.out.println("\nMalawi, Somalia, Canada, Tunisia, and New Zealand have been deleted\n");
		countryTree.printPostOrder();

		System.out.printf("\n\n");

		//step 6
		countryTree.printTopFive();
		System.out.printf("\n");
		countryTree.printBottomFive();
	}
}
