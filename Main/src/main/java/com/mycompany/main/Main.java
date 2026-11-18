package com.mycompany.main;
import java.util.Scanner;

public class Main {

    // 1. THE INTERFACE
    public interface IProduct {
        int GetTotalProductsSold();
        double GetAverageProductSold();
        String GetMostPopularProduct();
    }

    // 2. THE PRODUCT CLASS
    public static class Product implements IProduct {
        private String[] productNames = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        private int[][] sales = new int[3][3];
        private Scanner scanner = new Scanner(System.in);

        public Product() {}

        // Used for Unit Testing
        public Product(int[][] testSales) {
            this.sales = testSales;
        }

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
            return (double) GetTotalProductsSold() / (sales.length * sales[0].length);
        }

        @Override
        public String GetMostPopularProduct() {
            int maxSales = -1, maxIndex = -1;
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

    // 3. THE PRINTPRODUCT CLASS
    public static class PrintProduct {
        public void printFullReport(IProduct product) {
            System.out.println("PRODUCT SALES REPORT - 2026\n");
            System.out.println("*********************************");
            System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
            System.out.printf("AVERAGE PRODUCT SALES: %.0f\n", product.GetAverageProductSold());
            System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
        }
    }

    // 4. MAIN METHOD
    public static void main(String[] args) {
        Product productReport = new Product();
        PrintProduct printer = new PrintProduct();
        productReport.obtainSales();
        printer.printFullReport(productReport);
    }

  
    public static class ProductTests {

        @org.junit.jupiter.api.Test
        public void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
            // Arrange: Dummy data with a known total (10+10+10 + 20+20+20 + 30+30+30 = 180)
            int[][] dummySales = {
                {10, 10, 10},
                {20, 20, 20},
                {30, 30, 30}
            };
            Product product = new Product(dummySales);
            
            // Act
            int actualTotal = product.GetTotalProductsSold();
            
            // Assert
            org.junit.jupiter.api.Assertions.assertEquals(180, actualTotal, "The total sales calculation is incorrect.");
        }

        @org.junit.jupiter.api.Test
        public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
            // Arrange: Dummy data with a known total of 180. Average = 180 / 9 = 20
            int[][] dummySales = {
                {10, 10, 10},
                {20, 20, 20},
                {30, 30, 30}
            };
            Product product = new Product(dummySales);
            
            // Act
            double actualAverage = product.GetAverageProductSold();
            
            // Assert
            org.junit.jupiter.api.Assertions.assertEquals(20.0, actualAverage, 0.01, "The average sales calculation is incorrect.");
        }
    }
}
