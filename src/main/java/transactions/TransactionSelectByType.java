package transactions;

import java.util.List;

public class TransactionSelectByType {
    public static void main(String[] args, Type type) {
        TransactionDao transactionDao = new TransactionDao();
        List<Transaction> transactionList = transactionDao.findAll(type);

        for (Transaction transaction : transactionList) {
            System.out.println(transaction);
        }
    }
 }
