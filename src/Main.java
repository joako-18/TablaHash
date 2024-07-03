import models.HashTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashTable hashTable1 = new HashTable(1000);
        HashTable hashTable2 = new HashTable(1000);

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\EsauP\\OneDrive\\Escritorio\\dataset-main\\dataset-main\\bussines.csv"))) {
            while (br.ready()) {
                String[] business = br.readLine().split(",");
                String key = business[0];
                String value = "Name=" + business[1] + ", Address=" + business[2] + ", City=" + business[3] + ", State=" + business[4];
                hashTable1.insert(key, value, 1);
                hashTable2.insert(key, value, 2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Insertar datos");
            System.out.println("2. Buscar datos");
            System.out.println("3. Ver todos los datos");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Ingresa el ID: ");
                    String key = scanner.nextLine();
                    System.out.print("Ingresa el Nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("Ingresa la Dirección: ");
                    String address = scanner.nextLine();
                    System.out.print("Ingresa la Ciudad: ");
                    String city = scanner.nextLine();
                    System.out.print("Ingresa el Estado: ");
                    String state = scanner.nextLine();
                    String value = "Name=" + name + ", Address=" + address + ", City=" + city + ", State=" + state;
                    hashTable1.insert(key, value, 1);
                    hashTable2.insert(key, value, 2);
                    System.out.println("Datos insertados correctamente en ambas tablas hash.");
                    break;

                case 2:
                    System.out.print("Ingresa el ID para buscar: ");
                    key = scanner.nextLine();
                    long retrievalStartTime1 = System.nanoTime();
                    String result1 = hashTable1.get(key, 1);
                    long retrievalEndTime1 = System.nanoTime();
                    long timeTaken1 = retrievalEndTime1 - retrievalStartTime1;

                    long retrievalStartTime2 = System.nanoTime();
                    String result2 = hashTable2.get(key, 2);
                    long retrievalEndTime2 = System.nanoTime();
                    long timeTaken2 = retrievalEndTime2 - retrievalStartTime2;

                    if (result1 != null) {
                        System.out.println("Datos encontrados en HashTable 1: " + result1);
                    } else {
                        System.out.println("No se encontraron datos para el ID proporcionado en HashTable 1.");
                    }
                    System.out.println("Tiempo de búsqueda en HashTable 1: " + timeTaken1 + " ns");

                    if (result2 != null) {
                        System.out.println("Datos encontrados en HashTable 2: " + result2);
                    } else {
                        System.out.println("No se encontraron datos para el ID proporcionado en HashTable 2.");
                    }
                    System.out.println("Tiempo de búsqueda en HashTable 2: " + timeTaken2 + " ns");
                    break;

                case 3:
                    System.out.println("Datos en HashTable 1:");
                    hashTable1.displayAll();
                    System.out.println("Datos en HashTable 2:");
                    hashTable2.displayAll();
                    break;

                case 4:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        } while (choice != 4);

        scanner.close();
    }
}