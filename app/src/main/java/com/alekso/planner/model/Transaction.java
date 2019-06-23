package com.alekso.planner.model;

public class Transaction {
    private long id;
    private long dt;
    private TransactionType type;
    private TransactionStatus status;
    private long accountId;
    private double amount;
    private double balance;
    private String comment;
    private boolean isVital;

    public Transaction(Builder builder) {
        id = builder.id;
        dt = builder.dt;
        type = builder.type;
        status = builder.status;
        accountId = builder.accountId;
        amount = builder.amount;
        balance = builder.balance;
        comment = builder.comment;
        isVital = builder.isVital;
    }

    public long getId() {
        return id;
    }

    public long getDt() {
        return dt;
    }

    public TransactionType getType() {
        return type;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public long getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getComment() {
        return comment;
    }

    public boolean isVital() {
        return isVital;
    }

    public static class Builder {
        private long id;
        private long dt;
        private TransactionType type;
        private TransactionStatus status;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setDt(long dt) {
            this.dt = dt;
            return this;
        }

        public Builder setType(TransactionType type) {
            this.type = type;
            return this;
        }

        public Builder setStatus(TransactionStatus status) {
            this.status = status;
            return this;
        }

        public Builder setAccountId(long accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setVital(boolean vital) {
            isVital = vital;
            return this;
        }

        private long accountId;
        private double amount;
        private double balance;
        private String comment;
        private boolean isVital;

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
