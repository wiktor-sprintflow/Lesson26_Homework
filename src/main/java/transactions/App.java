package transactions;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("Wybierze opcję:");
            System.out.println("1. Dodawanie transakcji");
            System.out.println("2. Modyfikacja transakcji");
            System.out.println("3. Usuwanie transakcji");
            System.out.println("4. Wyświetlenie przychodów");
            System.out.println("5. Wyświetlenie wydatków");
            System.out.println("0. Koniec");

            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    TransactionAdd.main(new String[0]);
                    break;
                case "2":
                    TransactionUpdate.main(new String[0]);
                    break;
                case "3":
                    TransactionDelete.main(new String[0]);
                    break;
                case "4":
                    TransactionSelectByType.main(new String[0], Type.INCOME);
                    break;
                case "5":
                    TransactionSelectByType.main(new String[0], Type.EXPENSE);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Błędny wybór!");
            }

        }
    }
}
