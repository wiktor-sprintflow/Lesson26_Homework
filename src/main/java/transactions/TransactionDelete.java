package transactions;

import java.util.Scanner;

public class TransactionDelete {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji");
        Long id = scanner.nextLong();
        scanner.nextLine();

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.deleteById(id);
        System.out.println("Usunięto transakcję!");
    }
}
