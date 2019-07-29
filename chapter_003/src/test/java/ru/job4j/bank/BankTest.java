package ru.job4j.bank;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {

    @Test
    public void whenGetUserDataThenSuccess() {
        Bank b = new Bank();
        User u1 = new User("Vasya", "1234");
        b.addUser(u1);
        assertThat(b.getUserData().containsKey(u1), is(true));
    }

    @Test
    public void whenDeleteUserThenSuccess() {
        Bank b = new Bank();
        User u1 = new User("Vasya", "1234");
        b.addUser(u1);
        b.deleteUser(u1);
        assertThat(b.getUserData().containsKey(u1), is(false));
    }

    @Test
    public void whenAddAccountToUserThenSuccess() {
        Bank b = new Bank();
        User u1 = new User("Vasya", "1234");
        b.addUser(u1);
        Account a = new Account("acc1");
        b.addAccountToUser("1234", a);
        assertThat(b.getUserData().get(u1).get(0), is(a));
    }

    @Test
    public void whenDeleteAccountFromUserThenSuccess() {
        Bank b = new Bank();
        User u1 = new User("Vasya", "1234");
        b.addUser(u1);
        Account a = new Account("acc1");
        b.addAccountToUser("1234", a);
        b.deleteAccountFromUser("1234", a);
        assertThat(b.getUserData().get(u1).size(), is(0));
    }

    @Test
    public void whenGetUserAccountsThenSuccess() {
        Bank b = new Bank();
        User u1 = new User("Vasya", "1234");
        b.addUser(u1);
        Account a = new Account("acc1");
        b.addAccountToUser("1234", a);
        List<Account> result = b.getUserAccounts("1234");
        assertThat(result.contains(a), is(true));
    }

    @Test
    public void whenTransferFullValueThenSuccess() {
        Bank b = new Bank();
        User u1 = new User("Vasya", "1234");
        User u2 = new User("Vitya", "1235");
        b.addUser(u1);
        b.addUser(u2);
        Account a1 = new Account("account1");
        Account a2 =  new Account("account2");
        a1.setValue(200.00);
        b.addAccountToUser("1234", a1);
        b.addAccountToUser("1235", a2);
        b.transferMoney("1234", a1, "1235", a2, 200.00);
        assertThat(a1.getValue(), is(0.00));
        assertThat(a2.getValue(), is(200.00));
    }
}
