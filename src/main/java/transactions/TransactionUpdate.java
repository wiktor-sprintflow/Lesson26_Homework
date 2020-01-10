package transactions;

import java.sql.Date;
import java.util.Scanner;

public class TransactionUpdate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji, którą chcesz zaktualizować");
        long id= scanner.nextLong();
        scanner.nextLine();

        System.out.println("Podaj nowy typ transakcji (income / expense):");
        String typeString = (scanner.nextLine()).toUpperCase();

        System.out.println("Podaj nowy opis transakcji:");
        String description = scanner.nextLine();

        System.out.println("Podaj nową wartość transakcji");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj nową datę transakcji w formacie YYYY-MM-DD:");
        String dateString = scanner.nextLine();

        Transaction transaction = null;

        try {
            transaction = new Transaction(id, Type.valueOf(typeString), description, amount, Date.valueOf(dateString));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.update(transaction);
    }
}
