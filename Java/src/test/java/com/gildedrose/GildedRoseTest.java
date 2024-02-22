package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateQualityDoesNotChangeItemName() {
        String name = "foo";
        int sellIn = 0;
        int quality = 0;
        Item[] items = new Item[] {new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(name, item.name);
    }

    @Test
    void updateQualityDecreasesItemSellIn() {
        String name = "foo";
        int sellIn = 1;
        int quality = 50;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(sellIn - 1, item.sellIn);
    }

}
