package com.alekso.planner.model;

public enum TransactionType {
    EXPENSE_INCOME(0),
    TRANSFER(1);

    private int id;

    TransactionType(int id) {
        this.id = id;
    }

    public static TransactionType getById(int id) {
        for (TransactionType t : values()) {
            if (id == t.id) return t;
        }

        return TransactionType.EXPENSE_INCOME;
    }
}
