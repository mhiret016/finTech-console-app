package org.example.FinTech.entities;

public class Account {
        private int id;
        private int userId;
        private String accountType;
        private double balance;

        public Account(int id, int userId, String accountType, double balance) {
            this.id = id;
            this.userId = userId;
            this.accountType = accountType;
            this.balance = balance;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }

        public String getAccountType() { return accountType; }
        public void setAccountType(String accountType) { this.accountType = accountType; }

        public double getBalance() { return balance; }
        public void setBalance(double balance) { this.balance = balance; }

        @Override
        public String toString() {
            return "Account {" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", accountType='" + accountType + '\'' +
                    ", balance=" + balance +
                    '}';
        }
}
