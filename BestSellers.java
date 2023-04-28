import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

public class BestSellers {
    // private variables
    private String[] Year;
    private String[] Week;
    private String[] Rank;
    private String[] Title_ID;
    private String[] Title;
    private String[] Author;

    public BestSellers(int size) {

        // constructor
        if (size <= 0)
            size = 1;

        Year = new String[size];
        Week = new String[size];
        Rank = new String[size];
        Title_ID = new String[size];
        Title = new String[size];
        Author = new String[size];
    }

    public void read_file(String filename) throws IOException {
        int Count = 0;

        FileInputStream fileStream = new FileInputStream(filename);
        Scanner fileScnr = new Scanner(fileStream);

        // read the file
        while (fileScnr.hasNext() && Count < Year.length) {
            String line = fileScnr.nextLine().trim();
            String item[] = line.split("\t");

            Year[Count] = item[0];
            Week[Count] = item[1];
            Rank[Count] = item[2];
            Title_ID[Count] = item[3];
            Title[Count] = item[4];
            Author[Count] = item[5];

            Count++;
        }

        fileScnr.close();
    }

    public void find_title(String title) {
        int titleCount = 0;

        System.out.println("\n\nPrinting title:\n\n");
        System.out.println("Year" + "\t" + "Week" + "\t\t" + "Rank" + "\t" + "Title ID" + "\t\t\t" + "Title" + "\t"
                + "Author" + "\t");

        // print line if the title matches String title
        for (int i = 0; i < Title.length; i++) {
            if (title.equals(Title[i])) {
                System.out.print(Year[i] + "\t");
                System.out.print(Week[i] + "\t");
                System.out.print(Rank[i] + "\t");
                System.out.print(Title_ID[i] + "\t");
                System.out.printf("%30s", Title[i] + "\t");
                System.out.println(Author[i] + "\t");
                titleCount++;
            }
        }

        // error message
        if (titleCount == 0)
            System.out.println("Book title could not be found.");
    }

    public void find_author(String author) {
        int authorCount = 0;

        System.out.println("\n\nPrinting author:\n\n");
        System.out.println("Year" + "\t" + "Week" + "\t\t" + "Rank" + "\t" + "Title ID" + "\t\t\t" + "Title" + "\t"
                + "Author" + "\t");

        // print line if the author matches String author
        for (int i = 0; i < Title.length; i++) {
            if (author.equals(Author[i])) {
                System.out.print(Year[i] + "\t");
                System.out.print(Week[i] + "\t");
                System.out.print(Rank[i] + "\t");
                System.out.print(Title_ID[i] + "\t");
                System.out.printf("%30s", Title[i] + "\t");
                System.out.println(Author[i] + "\t");
                authorCount++;
            }
        }

        // error message
        if (authorCount == 0)
            System.out.println("Author could not be found.");
    }

    public void find_week(String start_date, String end_date) {
        boolean weekFound = false;

        if (end_date.equals("same"))
            end_date = start_date;

        System.out.println("\n\nPrinting week range:\n\n");
        System.out.println("Year" + "\t" + "Week" + "\t\t" + "Rank" + "\t" + "Title ID" + "\t\t\t" + "Title" + "\t"
                + "Author" + "\t");

        for (int i = 0; i < Week.length; i++) {

            if (Week[i].compareTo(start_date) >= 0 && Week[i].compareTo(end_date) <= 0) {
                System.out.print(Year[i] + "\t");
                System.out.print(Week[i] + "\t");
                System.out.print(Rank[i] + "\t");
                System.out.print(Title_ID[i] + "\t");
                System.out.printf("%30s", Title[i] + "\t");
                System.out.println(Author[i] + "\t");
                weekFound = true;
            }
        }

        // error message
        if (weekFound == false)
            System.out.println("\nOne or more of the dates could not be found.");

    }

    public void best_selling_title() {

        // create book titles hash map to store book name and corresponding count of
        // appearances
        HashMap<String, Integer> bookTitles = new HashMap<String, Integer>();

        for (int i = 0; i < Title.length; i++) {
            bookTitles.putIfAbsent(Title[i], 1);
            if (bookTitles.containsKey(Title[i])) {
                bookTitles.put(Title[i], bookTitles.get(Title[i]) + 1);
            }
        }

        // iterate through hash map to find highest value
        int maxValue = Collections.max(bookTitles.values());
        ArrayList<String> best_selling_titles = new ArrayList<>();

        for (HashMap.Entry<String, Integer> entry : bookTitles.entrySet()) {
            if (entry.getValue() == maxValue) {
                best_selling_titles.add(entry.getKey());
            }
        }

        String authorName = "";
        int i = 0;

        // find author name
        while (authorName == "") {
            if (Title[i] == best_selling_titles.get(0)) {
                authorName = Author[i];
            }
            i++;
        }

        System.out.print(
                "\n\nThe best selling book in this dataset is \"" + best_selling_titles.get(0) + "\" by " + authorName
                        + ". ");
        System.out
                .println("It was on the bestseller list for " + bookTitles.get(best_selling_titles.get(0)) + " weeks!");

    }

    public void most_popular_author() {

        // create hash map to store author name and corresponding count of appearances
        HashMap<String, Integer> authorNames = new HashMap<String, Integer>();

        for (int i = 0; i < Author.length; i++) {
            authorNames.putIfAbsent(Author[i], 1);
            if (authorNames.containsKey(Author[i])) {
                authorNames.put(Author[i], authorNames.get(Author[i]) + 1);
            }
        }

        // iterate through hash map to find highest value
        int maxValue = Collections.max(authorNames.values());
        ArrayList<String> best_selling_authors = new ArrayList<>();

        for (HashMap.Entry<String, Integer> entry : authorNames.entrySet()) {
            if (entry.getValue() == maxValue) {
                best_selling_authors.add(entry.getKey());
            }
        }

        System.out.print("\n\nThe most popular author in this dataset is " + best_selling_authors.get(0));
        System.out.println(
                ". They were on the bestseller list for " + authorNames.get(best_selling_authors.get(0)) + " weeks!");

    }

    public void least_popular_authors() {

        // create hash map to store author name and corresponding count of appearances
        HashMap<String, Integer> authorNames = new HashMap<String, Integer>();

        for (int i = 0; i < Author.length; i++) {
            authorNames.putIfAbsent(Author[i], 1);
            if (authorNames.containsKey(Author[i])) {
                authorNames.put(Author[i], authorNames.get(Author[i]) + 1);
            }
        }

        // iterate through hash map to find keys with value of 1
        ArrayList<String> least_popular_authors = new ArrayList<>();

        for (HashMap.Entry<String, Integer> entry : authorNames.entrySet()) {
            if (entry.getValue() == 1) {
                least_popular_authors.add(entry.getKey());
            }
        }

        System.out.println("\n\nThe following authors have only appeared on the NYT Bestsellers list once: ");

        for (int i = 0; i < Author.length; i++) {

            if (least_popular_authors.contains(Author[i])) {
                System.out.print(Week[i] + "\t");
                System.out.printf("%30s", Title[i] + "\t\t\t");
                System.out.println(Author[i] + "\t");
            }
        }
    }

    public void most_enduring_author() {

        HashMap<String, Integer> authorNames = new HashMap<String, Integer>();

        // create authorNames hash map
        for (int i = 0; i < Author.length; i++) {
            authorNames.putIfAbsent(Author[i], 1);
            if (authorNames.containsKey(Author[i])) {
                authorNames.put(Author[i], authorNames.get(Author[i]) + 1);
            }
        }

        // create two hash maps that will find the author's first and last week "index"
        HashMap<String, Integer> authorFirstWeek = new HashMap<String, Integer>();
        HashMap<String, Integer> authorLastWeek = new HashMap<String, Integer>();

        for (HashMap.Entry<String, Integer> entry : authorNames.entrySet()) {
            if (entry.getValue() > 1 && !authorFirstWeek.containsKey(entry.getKey())) {
                authorFirstWeek.put(entry.getKey(), -1);
                authorLastWeek.put(entry.getKey(), -1);
            }
        }

        int weekNumber = 0;

        // assign the first and last week number
        for (int i = 0; i < Author.length; i++) {
            if (authorFirstWeek.containsKey(Author[i]) && authorFirstWeek.get(Author[i]) == -1) {
                authorFirstWeek.put(Author[i], weekNumber);
            }
            if (i > 0 && Week[i].compareTo(Week[i - 1]) > 0) {
                weekNumber++;
            }
        }

        for (int i = Author.length - 1; i >= 0; i--) {
            if (authorLastWeek.containsKey(Author[i]) && authorLastWeek.get(Author[i]) == -1) {
                authorLastWeek.put(Author[i], weekNumber);
            }
            if (i < Author.length - 1 && Week[i].compareTo(Week[i + 1]) < 0) {
                weekNumber--;
            }
        }

        // create new hash map with each author's week range
        HashMap<String, Integer> authorWeekRange = new HashMap<String, Integer>();

        for (HashMap.Entry<String, Integer> entry : authorFirstWeek.entrySet()) {
            String key = entry.getKey();
            authorWeekRange.put(entry.getKey(), authorLastWeek.get(key) - authorFirstWeek.get(key));
        }

        int maxValue = Collections.max(authorWeekRange.values());

        // find author with most
        String mostEnduringAuthor = "";

        for (HashMap.Entry<String, Integer> entry : authorWeekRange.entrySet()) {
            if (entry.getValue() == maxValue) {
                mostEnduringAuthor = entry.getKey().toString();
            }
        }

        String week1 = "";
        String week2 = "";

        // get strings for first and last week
        int i = 0;
        while (week1 == "") {
            if (Author[i] == mostEnduringAuthor) {
                week1 = Week[i];
            }
            i++;
        }

        i = Author.length - 1;
        while (week2 == "") {
            if (Author[i] == mostEnduringAuthor) {
                week2 = Week[i];
            }
            i--;
        }

        System.out.print("\n\nThe most enduring author is " + mostEnduringAuthor + ". ");
        System.out.print(
                "The author's first appearance is on " + week1 + " and the last appeareance is on " + week2 + ".");

        // somewhere in the code the most enduring author is getting removed from the
        // Author array

    }
}