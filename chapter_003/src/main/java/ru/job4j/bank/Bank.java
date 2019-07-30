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
        for (Map.Entry<User, List<Account>> e : userData.entrySet()) {
            if (e.getKey().getPassport().equals(passport)) {
                e.getValue().add(a);
            }
        }
    }

    public void deleteAccountFromUser(String passport, Account a) {
        for (Map.Entry<User, List<Account>> e : userData.entrySet()) {
            if (e.getKey().getPassport().equals(passport)) {
                e.getValue().remove(a);
            }
        }
    }

    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<>();
        for (Map.Entry<User, List<Account>> e : userData.entrySet()) {
            if (e.getKey().getPassport().equals(passport)) {
                result = e.getValue();
            }
        }
        return result;
    }

    private Account findSourceAccount(String passport, String requisites) {
        Account result = null;
        for (Account a : getUserAccounts(passport)) {
            if (a.getRequisites().equals(requisites)) {
                result = a;
            }
        }
        return result;
    }

    public boolean transferMoney(String srcPassport,
                                 String srcRequisites,
                                 String destPassport,
                                 String destRequisites,
                                 double amount) {
        Account src = findSourceAccount(srcPassport, srcRequisites);
        Account dest = findSourceAccount(destPassport, destRequisites);
        return src != null && dest != null && src.transfer(dest, amount);
    }
}
