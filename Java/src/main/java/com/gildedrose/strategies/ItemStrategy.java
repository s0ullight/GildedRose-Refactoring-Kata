package com.gildedrose.strategies;

import com.gildedrose.Item;

public interface ItemStrategy {
    public static final int EXPIRY = 0;
    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;

    default void updateItemSellIn(Item item) {
        item.sellIn--;
    }

    void updateItemQuality(Item item);

    default boolean isExpired(Item item) {
        final boolean isExpired = item.sellIn < EXPIRY;
        return isExpired;
    }
}
