package ru.job4j.grocery;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class QualityControl {

    protected final Storage warehouse;
    protected final Storage shop;
    protected final Storage trash;

    public QualityControl(Storage warehouse, Storage shop, Storage trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void distributeFood(Food food) {
        float expirationRatio = getExpirationRation(food);
        if (expirationRatio >= 1) {
            this.trash.addToContainer(food);
        } else if (expirationRatio < 0.25f) {
            food.setDiscount(0.7f);
            this.shop.addToContainer(food);
        } else if (expirationRatio < 0.75f) {
            this.shop.addToContainer(food);
        } else {
            this.warehouse.addToContainer(food);
        }
    }

    float getExpirationRation(Food food) {
        long expirationMilli = food.getExpirationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long fullLifeSpan = expirationMilli
                - food.getCreationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long remainingLifeSpan = expirationMilli
                - LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return (float) remainingLifeSpan /
                fullLifeSpan;
    }

    public Storage getWarehouse() {
        return this.warehouse;
    }

    public Storage getShop() {
        return this.shop;
    }

    public Storage getTrash() {
        return this.trash;
    }
}
