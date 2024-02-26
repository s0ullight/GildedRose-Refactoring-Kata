package com.gildedrose;

import com.gildedrose.strategies.ItemStrategy;
import com.gildedrose.strategies.impl.AgedBrieStrategy;
import com.gildedrose.strategies.impl.BackstagePassesStrategy;
import com.gildedrose.strategies.impl.ConjuredItemStrategy;
import com.gildedrose.strategies.impl.LegendaryItemStrategy;
import com.gildedrose.strategies.impl.RegularItemStrategy;

class GildedRose {
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
        ItemStrategy itemStrategy = getStrategyForItem(item);
        itemStrategy.updateItemSellIn(item);
        itemStrategy.updateItemQuality(item);
    }

    private static ItemStrategy getStrategyForItem(Item item) {
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
