package com.mycompany.Main;

import java.util.Scanner;

public class Main {


    public interface IProduct {
        int GetTotalProductsSold();
        double GetAverageProductSold();
        String GetMostPopularProduct();
    }

    
    public static class Product implements IProduct {
        private String[] productNames = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        private int[][] sales = new int[3][3];
        private Scanner scanner = new Scanner(System.in);

        // Default constructor for the running application
        public Product() {
        }

        // Overloaded constructor used specifically for Unit Testing
        public Product(int[][] testSales) {
            this.sales = testSales;
        }

        // Method to get data from the user
        public void obtainSales() {
            for (int i = 0; i < productNames.length; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print("Enter number of sales for month " + (j + 1) + " for " + productNames[i] + " mattress: ");
                    sales[i][j] = scanner.nextInt();
                }
                System.out.println(); 
            }
        }

        @Override
        public int GetTotalProductsSold() {
            int total = 0;
            for (int i = 0; i < sales.length; i++) {
                for (int j = 0; j < sales[i].length; j++) {
                    total += sales[i][j];
                }
            }
            return total;
        }

        @Override
        public double GetAverageProductSold() {
            int total = GetTotalProductsSold();
            int totalEntries = sales.length * sales[0].length; // 3 products * 3 months = 9
            return (double) total / totalEntries;
        }

        @Override
        public String GetMostPopularProduct() {
            int maxSales = -1;
            int maxIndex = -1;

            for (int i = 0; i < sales.length; i++) {
                int productTotal = 0;
                for (int j = 0; j < sales[i].length; j++) {
                    productTotal += sales[i][j];
                }
                if (productTotal > maxSales) {
                    maxSales = productTotal;
                    maxIndex = i;
                }
            }
            return productNames[maxIndex] + "(" + maxSales + ")";
        }
    }


    public static class PrintProduct {
        
        public void printTotalProductSales(IProduct product) {
            System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
        }

        public void printAverageProductSales(IProduct product) {
            // Using %.0f to format the average exactly like the sample output (e.g., 17 instead of 17.0)
            System.out.printf("AVERAGE PRODUCT SALES: %.0f\n", product.GetAverageProductSold());
        }

        public void printMostPopularProduct(IProduct product) {
            System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
        }

        // Helper method to print the full report header and call the individual print methods
        public void printFullReport(IProduct product) {
            System.out.println("PRODUCT SALES REPORT - 2026\n");
            System.out.println("*********************************");
            printTotalProductSales(product);
            printAverageProductSales(product);
            printMostPopularProduct(product);
        }
    }


    // 4. MAIN METHOD (Application Entry Point)
  
    public static void main(String[] args) {
        Product productReport = new Product();
        PrintProduct printer = new PrintProduct();
        
        // 1. Get input from user
        productReport.obtainSales();
        
        // 2. Print the report using the PrintProduct class
        printer.printFullReport(productReport);
    }

    
    
    
}