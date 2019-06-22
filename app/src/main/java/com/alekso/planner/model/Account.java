package com.alekso.planner.model;

public class Account {
    private long id;
    private long currencyId;
    private String name;
    private AccountType type;

    public Account(long id, long currencyId, String name, int typeId) {
        this.id = id;
        this.currencyId = currencyId;
        this.name = name;
        this.type = AccountType.getById(typeId);
    }

    public String getName() {
        return name;
    }
}
