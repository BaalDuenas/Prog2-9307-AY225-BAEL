import java.io.*;
import java.util.*;

public class LowPerformingProducts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File csvFile;

        System.out.println("=== LOW PERFORMING PRODUCTS DETECTION ===");
        System.out.println("Please enter the FULL PATH to your dataset (vgchartz-2024.csv)");
        System.out.println("Example (Windows): C:\\Users\\YourUser\\Documents\\Midterm-Lab-1\\vgchartz-2024.csv");
        System.out.println("Example (Mac/Linux): /Users/YourUser/Documents/Midterm-Lab-1/vgchartz-2024.csv\n");

        // Ask user for valid file path
        while (true) {
            System.out.print("Enter dataset file path: ");
            String path = scanner.nextLine().trim();
            csvFile = new File(path);

            if (!csvFile.exists()) {
                System.out.println("Error: File not found. Make sure the path is correct.");
            } else if (!csvFile.isFile()) {
                System.out.println("Error: Not a file. Please enter a valid file path ending in .csv");
            } else if (!path.toLowerCase().endsWith(".csv")) {
                System.out.println("Error: Invalid file type. This program requires a .csv file.");
            } else {
                break;  // Valid
            }
            System.out.println("Please try again.\n");
        }

        System.out.println("\nProcessing dataset...\n");

        List<Product> products = new ArrayList<>();
        double totalSales = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line = reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");
                if (cols.length >= 8) {
                    String title = cols[1].trim();
                    double sales = Double.parseDouble(cols[7].trim());
                    products.add(new Product(title, sales));
                    totalSales += sales;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Could not read the file. Ensure it is readable and in proper format.");
            e.printStackTrace();
            return;
        }

        if (products.isEmpty()) {
            System.out.println("No valid sales data found. Please check dataset contents.");
            return;
        }

        double averageSales = totalSales / products.size();

        System.out.printf("Average Total Sales: %.2f%n", averageSales);
        System.out.println("\nLOW PERFORMING PRODUCTS (below average):");

        for (Product p : products) {
            if (p.sales < averageSales) {
                System.out.printf("• %s — Sales: %.2f%n", p.title, p.sales);
            }
        }

        System.out.println("\nProcessing Complete.");
    }
}

class Product {
    String title;
    double sales;

    Product(String title, double sales) {
        this.title = title;
        this.sales = sales;
    }
}