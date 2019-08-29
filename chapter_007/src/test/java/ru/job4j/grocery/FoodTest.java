package ru.job4j.grocery;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FoodTest {
    private Food food = new Food("1", LocalDateTime.now(), LocalDateTime.now().plusYears(1L), 10f, 1f);

    @Test
    public void whenSetDiscountOverOneThenNoDiscount() {
        this.food.setDiscount(1.1f);
        assertThat(this.food.getDiscount(), is(1f));
    }

    @Test
    public void whenSetDiscountBelowZeroThenNoDiscount() {
        this.food.setDiscount(-0.1f);
        assertThat(this.food.getDiscount(), is(1f));
    }
}