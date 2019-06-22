package com.alekso.planner.model;

public enum AccountType {
    CASH(0),
    CREDIT_CARD(1),
    DEBIT_CARD(2),
    E_MONEY(3);

    private int id;

    AccountType(int id) {
        this.id = id;
    }

    public static AccountType getById(int id) {
        for (AccountType t : values()) {
            if (id == t.id) return t;
        }

        return AccountType.CASH;
    }
}
