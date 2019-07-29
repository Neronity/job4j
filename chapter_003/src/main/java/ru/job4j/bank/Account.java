package ru.job4j.bank;

import java.util.Objects;

public class Account {

    private double value;
    private String requisites;

    public Account(String requisites) {
        this.requisites = requisites;
        this.value = 0;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }

    public boolean transfer(Account targetAccount, double amount) {
        boolean result = false;
        if (this.value >= amount && amount > 0) {
            result = true;
            this.value -= amount;
            targetAccount.setValue(targetAccount.getValue() + amount);
        }
        return result;
    }
}
