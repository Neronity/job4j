package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

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
        getUserEntry(passport).ifPresent(e -> e.getValue().add(a));
    }

    public void deleteAccountFromUser(String passport, Account a) {
        getUserEntry(passport).ifPresent(userListEntry -> userListEntry.getValue().remove(a));
    }

    public List<Account> getUserAccounts(String passport) {
        return getUserEntry(passport)
                .map(Map.Entry::getValue)
                .orElse(new ArrayList<>());
    }

    private Account findSourceAccount(String passport, String requisites) {
        return getUserAccounts(passport)
                .stream()
                .filter(a -> a.getRequisites().equals(requisites))
                .findFirst().orElse(null);
    }

    private Optional<Map.Entry<User, List<Account>>> getUserEntry(String passport) {
        return userData.entrySet()
                .stream()
                .filter(e -> e.getKey().getPassport().equals(passport))
                .findFirst();
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
