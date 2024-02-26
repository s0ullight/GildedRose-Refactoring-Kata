package com.gildedrose;

import java.util.Arrays;

import com.gildedrose.strategies.ItemStrategy;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(ItemStrategy::handleDayEndForItem);
    }
}
