package ru.job4j.grocery;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class QualityControlTest {
    QualityControl qc = new QualityControl(new Warehouse(), new Shop(), new Trash());

    @Test
    public void whenGetRatioThenCorrectResponse() {
        Food f = new Food("1",
                LocalDateTime.now().minusMonths(6L),
                LocalDateTime.now().plusMonths(6L),
                10f,
                1f);
        assertThat((int) (this.qc.getExpirationRation(f) * 10), is(5));
    }

    @Test
    public void whenNegativeExpirationRatioThenTrash() {
        Food f = new Food("1",
                LocalDateTime.now(),
                LocalDateTime.now().minusDays(1L),
                10f,
                1f);
        this.qc.distributeFood(f);
        assertThat(this.qc.getTrash().getContainer().isEmpty(), is(false));
    }

    @Test
    public void when10PercentExpirationRatioThenShopWithDiscount() {
        Food f = new Food("1",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().plusMinutes(120L),
                10f,
                1f);
        this.qc.distributeFood(f);
        assertThat(this.qc.getShop().getContainer().isEmpty(), is(false));
        assertThat(f.getDiscount(), is(0.7f));
    }

    @Test
    public void when50PercentExpirationRatioThenShopWithoutDiscount() {
        Food f = new Food("1",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().plusDays(1L),
                10f,
                1f);
        this.qc.distributeFood(f);
        assertThat(this.qc.getShop().getContainer().isEmpty(), is(false));
        assertThat(f.getDiscount(), is(1f));
    }

    @Test
    public void when80PercentExpirationRatioThenWarehouse() {
        Food f = new Food("1",
                LocalDateTime.now().minusMinutes(20L),
                LocalDateTime.now().plusDays(200L),
                10f,
                1f);
        this.qc.distributeFood(f);
        assertThat(this.qc.getWarehouse().getContainer().isEmpty(), is(false));
        assertThat(f.getDiscount(), is(1f));
    }

}