package ru.job4j.grocery;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Food {

    private String name;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private float price;
    private float discount;

    public Food(String name, LocalDateTime creationDate, LocalDateTime expirationDate, float price, float discount) {
        this.name = name;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.price = price;
        setDiscount(discount);
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        if (discount < 0.01f || discount > 1f) {
            this.discount = 1;
        } else {
            this.discount = discount;
        }
    }
}
