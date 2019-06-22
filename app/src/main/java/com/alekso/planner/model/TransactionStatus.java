package com.alekso.planner.model;

public enum TransactionStatus {
    PLANNED(0),
    SUBMITTED(1);

    private int id;

    TransactionStatus(int id) {
        this.id = id;
    }

    public static TransactionStatus getById(int id) {
        for (TransactionStatus t : values()) {
            if (id == t.id) return t;
        }

        return TransactionStatus.PLANNED;
    }
}
