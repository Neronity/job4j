package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<User, List<Account>> userData = new HashMap<>();

    public Map<User, List<Account>> getUserData() {
        return userData;
    }

    public void addUser(User u) {
        userData.putIfAbsent(u, new ArrayList<>());
    }

    public void deleteUser(User u) {
        userData.remove(u);
    }

    public void addAccountToUser(String passport, Account a) {
        userData.get(new User(passport)).add(a);
    }

    public void deleteAccountFromUser(String passport, Account a) {
        userData.get(new User(passport)).remove(a);
    }

    public List<Account> getUserAccounts(String passport) {
        return userData.get(new User(passport));
    }

    public boolean transferMoney(String srcPassport,
                                 Account srcAccount,
                                 String destPassport,
                                 Account destAccount,
                                 double amount) {
        boolean result = false;
        List<Account> srcAccounts = getUserAccounts(srcPassport);
        List<Account> destAccounts = getUserAccounts(destPassport);
        int srcIndex = srcAccounts.indexOf(srcAccount);
        int destIndex = destAccounts.indexOf(destAccount);
        if (srcIndex != -1 && destIndex != -1) {
            result = srcAccounts.get(srcIndex).transfer(destAccounts.get(destIndex), amount);
        }
        return result;
    }
}
