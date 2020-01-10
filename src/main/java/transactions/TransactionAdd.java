package transactions;

import java.sql.Date;
import java.util.Scanner;

public class TransactionAdd {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj typ (income / expense):");
        String typeString = (scanner.nextLine()).toUpperCase();

        System.out.println("Podaj opis:");
        String description = scanner.nextLine();

        System.out.println("Podaj wartość");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj datę w formacie YYYY-MM-DD:");
        String dateString = scanner.nextLine();

        Transaction transaction = null;
        try {
             transaction = new Transaction(Type.valueOf(typeString), description, amount, Date.valueOf(dateString));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.insert(transaction);

    }
}
