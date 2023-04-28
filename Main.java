import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        int menuInput = -1;
        String userInput;
        String userInput2;

        Scanner scnr = new Scanner(System.in);

        // create new BestSellers object
        BestSellers bestsellers = create_object();

        bestsellers.best_selling_title();
        bestsellers.most_popular_author();
        bestsellers.least_popular_authors();
        bestsellers.most_enduring_author();


        while (menuInput != 4) {
            print_menu();
            menuInput = scnr.nextInt();
            scnr.nextLine();

            if (menuInput == 1) {
                System.out.println("\nPlease provide the author name:");
                userInput = scnr.nextLine();
                bestsellers.find_author(userInput);

            } else if (menuInput == 2) {
                System.out.println("\nPlease provide the book title (ex: \"THE HOBBIT\"):");
                userInput = scnr.nextLine();
                bestsellers.find_title(userInput);
            } else if (menuInput == 3) {
                System.out.println("\nPlease provide the start date(YYYY-MM-DD):");
                userInput = scnr.nextLine();
                System.out
                        .println("\nPlease provide the end date(YYYY-MM-DD) or type \"same\" to output only one week:");
                userInput2 = scnr.nextLine();
                bestsellers.find_week(userInput, userInput2);
            } else if (menuInput == 4) {
                System.out.println("\nGoodbye!");
            } else {
                System.out.println("\nPlease provide a valid number!");
            }

        }

        scnr.close();
    }

    public static void print_menu() {
        System.out.println("\n\nWhat would you like to do? (1,2,3,4)");
        System.out.println("1: Search by Author");
        System.out.println("2: Search by Title");
        System.out.println("3: Search by Week");
        System.out.println("4: Quit");
    }

    public static BestSellers create_object() throws IOException {
        Scanner scnr = new Scanner(System.in);

        int fileSize;

        //System.out.println("\nWelcome to Daria's Bestseller Database!");

        // get array size
        //System.out.println("\nTo start, please provide a valid array size (1..60387):");
        //fileSize = scnr.nextInt();
        fileSize = 60387;
        //scnr.nextLine();

        // error checking
        while (fileSize < 1 || fileSize > 60387) {
            System.out.println("\nPlease provide a valid array size (1..60387):");
            fileSize = scnr.nextInt();
            scnr.nextLine();
        }

        // create new BestSellers object
        BestSellers bestsellers = new BestSellers(fileSize);
        bestsellers.read_file("nyt_best_sellers.tsv");

        return bestsellers;
    }
}