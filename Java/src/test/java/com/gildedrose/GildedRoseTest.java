package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void updateQualityDecreasesItemQuality() {
        String name = "foo";
        int sellIn = 1;
        int quality = 50;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertTrue(item.quality < quality);
    }

    @Test
    void updateQualityDecreasesItemQualityTwiceAsFastWhenItemIsExpired() {
        String name = "foo";
        int sellIn = 1;
        int quality = 50;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        int updatedQualityBeforeExpiry = item.quality;
        int qualityDecreaseBeforeExpiry = quality - updatedQualityBeforeExpiry;
        app.updateQuality();
        int updatedQualityAfterExpiry = item.quality;
        int qualityDecreaseAfterExpiry = updatedQualityBeforeExpiry - updatedQualityAfterExpiry;
        assertEquals(qualityDecreaseBeforeExpiry * 2, qualityDecreaseAfterExpiry);
    }

}
