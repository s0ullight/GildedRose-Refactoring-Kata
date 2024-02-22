package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    void updateQualityDoesNotResultInNegativeItemQuality() {
        String name = "foo";
        int sellIn = 1;
        int quality = 0;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertTrue(item.quality >= 0);
    }

    @Test
    void updateQualityIncreasesItemQualityForAgedBrie() {
        String name = "Aged Brie";
        int sellIn = 1;
        int quality = 0;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertTrue(item.quality > 0);
    }

    @Test
    void updateQualityDoesNotIncreaseItemQualityBeyondFifty() {
        String name = "Aged Brie";
        int sellIn = 1;
        int quality = 50;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertFalse(item.quality > quality);
    }

    @Test
    void updateQualityDoesNotDecreaseItemQualityForSulfuras() {
        String name = "Sulfuras, Hand of Ragnaros";
        int sellIn = 1;
        int quality = 80;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(quality, item.quality);
    }

    @Test
    void updateQualityIncreasesItemQualityForBackstagePasses() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        int sellIn = 20;
        int quality = 0;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertTrue(quality < item.quality);
    }

    @Test
    void updateQualityIncreasesItemQualityByTwoInLastTenDaysForBackstagePasses() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        int sellIn = 10;
        int quality = 0;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(quality + 2, item.quality);
    }

}
