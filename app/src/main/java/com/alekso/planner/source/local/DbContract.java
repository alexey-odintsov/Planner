package com.alekso.planner.source.local;

import android.provider.BaseColumns;

public final class DbContract {
    public static abstract class CurrencyEntry implements BaseColumns {
        public static final String TABLE = "currencies";
        public static final String C_NAME = "name";
        public static final String C_SYMBOL = "symbol";
    }

    public static abstract class AccountEntry implements BaseColumns {
        public static final String TABLE = "accounts";
        public static final String C_NAME = "name";
        public static final String C_CURRENCY_ID = "currency_id";
        public static final String C_TYPE = "type";
    }

    public static abstract class TransactionEntry implements BaseColumns {
        public static final String TABLE = "transactions";
        public static final String C_DATETIME = "datetime";
        public static final String C_TYPE = "type";
        public static final String C_ACCOUNT_ID = "account_id";
        public static final String C_AMOUNT = "amount";
        public static final String C_BALANCE = "balance";
        public static final String C_STATUS = "status";
        public static final String C_COMMENT = "comment";
        public static final String C_IS_VITAL = "is_vital";
    }
}
