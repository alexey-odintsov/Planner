package com.alekso.planner.model;

public class Account {
    private long id;
    private long currencyId;

    public String getName() {
        return name;
    }

    private String name;
    private AccountType type;

    public Account(long id, long currencyId, String name, int typeId) {
        this.id = id;
        this.currencyId = currencyId;
        this.name = name;
        this.type = AccountType.getById(typeId);
    }
}
