package com.gildedrose.strategies;

import com.gildedrose.Item;
import com.gildedrose.strategies.impl.AgedBrieStrategy;
import com.gildedrose.strategies.impl.BackstagePassesStrategy;
import com.gildedrose.strategies.impl.ConjuredItemStrategy;
import com.gildedrose.strategies.impl.LegendaryItemStrategy;
import com.gildedrose.strategies.impl.RegularItemStrategy;

public interface ItemStrategy {
    public static final int EXPIRY = 0;
    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;

    public static final String SULFURAS = "Sulfuras";
    public static final String BACKSTAGE_PASSES = "Backstage passes";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String CONJURED_ITEM = "Conjured";

    default void updateItemSellIn(Item item) {
        item.sellIn--;
    }

    void updateItemQuality(Item item);

    default boolean isExpired(Item item) {
        final boolean isExpired = item.sellIn < EXPIRY;
        return isExpired;
    }

    static void handleDayEndForItem(Item item) {
        ItemStrategy itemStrategy = getStrategyForItem(item);
        itemStrategy.updateItemSellIn(item);
        itemStrategy.updateItemQuality(item);
    }

    static ItemStrategy getStrategyForItem(Item item) {
        ItemStrategy itemStrategy;
        if (item.name.equals(AGED_BRIE)) {
            itemStrategy = new AgedBrieStrategy();
        } else if (item.name.startsWith(BACKSTAGE_PASSES)) {
            itemStrategy = new BackstagePassesStrategy();
        } else if (item.name.startsWith(SULFURAS)) {
            itemStrategy = new LegendaryItemStrategy();
        } else if (item.name.startsWith(CONJURED_ITEM)) {
            itemStrategy = new ConjuredItemStrategy();
        } else {
            itemStrategy = new RegularItemStrategy();
        }
        return itemStrategy;
    }
}
