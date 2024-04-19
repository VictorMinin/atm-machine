package com.solvd.atm.utils.atm;

import com.solvd.atm.models.Transaction;

import java.util.List;

public class PrettyFormatter {

    public static String formatReceipt(Transaction transaction) {
        String closeSpace = "==================================================\n";
        String cardSender = transaction.getSenderCard().getCardNumber();
        StringBuilder receipt = new StringBuilder();
        receipt.append(closeSpace)
                .append(formatLine("Receipt Number", String.valueOf(transaction.getTransactionId()), 46))
                .append(closeSpace)
                .append(formatLine("Transaction Type", transaction.getTransactionType().getTypeName(), 46))
                .append(closeSpace)
                .append(formatLine("Amount", String.valueOf(transaction.getAmount()), 46))
                .append(closeSpace)
                .append(formatLine("Card", "****-****-****-" + cardSender.substring(cardSender.length() - 4), 46))
                .append(closeSpace);
        if (transaction.getRecipientCard() != null) {
            String cardRecipient = transaction.getRecipientCard().getCardNumber();
            receipt.append(closeSpace)
                    .append(formatLine("Recipient", "****-****-****-" + cardRecipient.substring(cardRecipient.length() - 4), 46))
                    .append(closeSpace);
        }
        return receipt.toString();
    }

    public static <T> String formatTable(List<T> list, String listName) {
        String closeSpace = "\n====================================================================================================\n";
        StringBuilder table = new StringBuilder(centerAlign(listName, 96)).append(closeSpace);
        for (T item : list) {
            table.append(item.toString()).append("\n----------------------------------------------------------------------------------------------------\n");
        }
        return table.toString();
    }

    private static String formatLine(String label, String content, int width) {
        return "||" + centerAlign(label, width) + "||\n" + "||" + centerAlign(content, width) + "||\n";
    }

    private static String centerAlign(String text, int width) {
        int padding = (width - text.length()) / 2;
        String paddedText = String.format("%" + (padding + text.length()) + "s", text);
        return String.format("%-" + width + "s", paddedText);
    }
}
