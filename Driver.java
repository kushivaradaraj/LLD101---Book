import java.util.*;

public class Driver {

    // 1. Total number of books by an author
    public static int getTotalBooksByAuthor(String author, List<Book> books) {
        int count = 0;
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                count++;
            }
        }
        return count;
    }

    // 2. Show all authors
    public static Set<String> getAllAuthors(List<Book> books) {
        Set<String> authors = new HashSet<>();
        for (Book b : books) {
            authors.add(b.getAuthor());
        }
        return authors;
    }

    // 3. Get books by an author
    public static List<String> getBooksByAuthor(String author, List<Book> books) {
        List<String> titles = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                titles.add(b.getTitle());
            }
        }
        return titles;
    }

    // 4. Books by rating
    public static List<Book> getBooksByRating(double rating, List<Book> books) {
        List<Book> res = new ArrayList<>();
        for (Book b : books) {
            if (b.getUserRating() == rating) {
                res.add(b);
            }
        }
        return res;
    }

    // 5. Prices of books by author
    public static Map<String, Double> getBooksAndPricesByAuthor(String author, List<Book> books) {
        Map<String, Double> data = new LinkedHashMap<>();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                data.put(b.getTitle(), b.getPrice());
            }
        }
        return data;
    }

    // Main program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Load dataset
        List<Book> books = DatasetReader.readBooksFromCSV("data.csv");

        System.out.println("==== Amazon Bestselling Books (2009-2019) ====");

        int choice;
        do {
            System.out.println("\nChoose a task:");
            System.out.println("1. Total number of books by an author");
            System.out.println("2. Show all authors in the dataset");
            System.out.println("3. Show all books by an author");
            System.out.println("4. Show all books with a given user rating");
            System.out.println("5. Show prices of all books by an author");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter author name: ");
                    String author1 = sc.nextLine();
                    System.out.println("Total books by " + author1 + ": " + getTotalBooksByAuthor(author1, books));
                    break;

                case 2:
                    System.out.println("\nAll Authors:");
                    getAllAuthors(books).forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter author name: ");
                    String author2 = sc.nextLine();
                    List<String> titles = getBooksByAuthor(author2, books);
                    if (titles.isEmpty()) {
                        System.out.println("No books found for " + author2);
                    } else {
                        System.out.println("Books by " + author2 + ": " + titles);
                    }
                    break;

                case 4:
                    System.out.print("Enter rating: ");
                    double rating = sc.nextDouble();
                    sc.nextLine();
                    List<Book> byRating = getBooksByRating(rating, books);
                    if (byRating.isEmpty()) {
                        System.out.println("No books found with rating " + rating);
                    } else {
                        byRating.forEach(Book::printDetails);
                    }
                    break;

                case 5:
                    System.out.print("Enter author name: ");
                    String author3 = sc.nextLine();
                    Map<String, Double> priceMap = getBooksAndPricesByAuthor(author3, books);
                    if (priceMap.isEmpty()) {
                        System.out.println("No books found for " + author3);
                    } else {
                        priceMap.forEach((title, price) -> System.out.println(title + " - $" + price));
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}
