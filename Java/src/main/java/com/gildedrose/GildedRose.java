package com.gildedrose;

import com.gildedrose.strategies.ItemStrategy;
import com.gildedrose.strategies.impl.AgedBrieStrategy;
import com.gildedrose.strategies.impl.BackstagePassesStrategy;
import com.gildedrose.strategies.impl.LegendaryItemStrategy;

class GildedRose {
    private static final int EXPIRY = 0;
    private static final int MIN_QUALITY = 0;

    private static final int REGULAR_ITEM_QUALITY_DELTA = -1;
    private static final int EXPIRED_REGULAR_ITEM_QUALITY_DELTA = -2;

    private static final int CONJURED_ITEM_QUALITY_DELTA = -2;
    private static final int EXPIRED_CONJURED_ITEM_QUALITY_DELTA = -4;

    private static final String SULFURAS = "Sulfuras";
    private static final String BACKSTAGE_PASSES = "Backstage passes";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String CONJURED_ITEM = "Conjured";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            handleDayEndForItem(item);
        }
    }

    private static void handleDayEndForItem(Item item) {
        updateItemSellIn(item);
        updateItemQuality(item);
    }

    private static void updateItemSellIn(Item item) {
        if (!item.name.startsWith(SULFURAS)) {
            item.sellIn--;
        }
    }

    private static void updateItemQuality(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            updateItemQualityForAgedBrie(item);
        } else if (item.name.startsWith(BACKSTAGE_PASSES)) {
            updateItemQualityForBackstagePasses(item);
        } else if (item.name.startsWith(SULFURAS)) {
            updateItemQualityForLegendaryItem(item);
        } else if (item.name.startsWith(CONJURED_ITEM)) {
            updateItemQualityForConjuredItem(item);
        } else {
            updateItemQualityForRegularItem(item);
        }
    }

    private static void updateItemQualityForAgedBrie(Item item) {
        ItemStrategy itemStrategy = new AgedBrieStrategy();
        itemStrategy.updateItemQuality(item);
    }

    private static void updateItemQualityForBackstagePasses(Item item) {
        ItemStrategy itemStrategy = new BackstagePassesStrategy();
        itemStrategy.updateItemQuality(item);
    }

    private static void updateItemQualityForLegendaryItem(Item item) {
        ItemStrategy itemStrategy = new LegendaryItemStrategy();
        itemStrategy.updateItemQuality(item);
    }

    private static void updateItemQualityForConjuredItem(Item item) {
        final int qualityDelta;
        if (isExpired(item)) {
            qualityDelta = EXPIRED_CONJURED_ITEM_QUALITY_DELTA;
        } else {
            qualityDelta = CONJURED_ITEM_QUALITY_DELTA;
        }
        item.quality = Math.max(item.quality + qualityDelta, MIN_QUALITY);
    }

    private static void updateItemQualityForRegularItem(Item item) {
        final int qualityDelta;
        if (isExpired(item)) {
            qualityDelta = EXPIRED_REGULAR_ITEM_QUALITY_DELTA;
        } else {
            qualityDelta = REGULAR_ITEM_QUALITY_DELTA;
        }
        item.quality = Math.max(item.quality + qualityDelta, MIN_QUALITY);
    }

    private static boolean isExpired(Item item) {
        final boolean isExpired = item.sellIn < EXPIRY;
        return isExpired;
    }
}
