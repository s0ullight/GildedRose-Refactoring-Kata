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
    void updateQualityDoesNotDecreaseItemSellInForSulfuras() {
        String name = "Sulfuras, Hand of Ragnaros";
        int sellIn = 1;
        int quality = 50;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(sellIn, item.sellIn);
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
        assertEquals(quality - 1, item.quality);
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
        assertEquals(1, qualityDecreaseBeforeExpiry);
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
        assertEquals(1, item.quality);
    }

    @Test
    void updateQualityIncreasesItemQualityTwiceAsFastForAgedBrieWhenExpired() {
        String name = "Aged Brie";
        int sellIn = 1;
        int quality = 0;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        Item item = app.items[0];
        app.updateQuality();
        int updatedQualityBeforeExpiry = item.quality;
        int qualityIncreaseBeforeExpiry = item.quality - quality;
        app.updateQuality();
        int qualityIncreaseAfterExpiry = item.quality - updatedQualityBeforeExpiry;
        assertEquals(1, qualityIncreaseBeforeExpiry);
        assertEquals(2, qualityIncreaseAfterExpiry);
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
        assertEquals(quality, item.quality);
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
        int sellIn = 11;
        int quality = 10;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(quality + 1, item.quality);
    }

    @Test
    void updateQualityIncreasesItemQualityByTwoInLastTenDaysForBackstagePasses() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        int sellInTen = 10;
        int sellInSix = 6;
        int quality = 10;
        Item itemToSellInTen = new Item(name, sellInTen, quality);
        Item itemToSellInSix = new Item(name, sellInSix, quality);
        Item[] items = new Item[] { itemToSellInTen, itemToSellInSix };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(quality + 2, itemToSellInTen.quality);
        assertEquals(quality + 2, itemToSellInSix.quality);
    }

    @Test
    void updateQualityIncreasesItemQualityByThreeInLastFiveDaysForBackstagePasses() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        int sellInFive = 5;
        int sellInOne = 1;
        int quality = 10;
        Item itemToSellInFive = new Item(name, sellInFive, quality);
        Item itemToSellInOne = new Item(name, sellInOne, quality);
        Item[] items = new Item[] { itemToSellInFive, itemToSellInOne };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(quality + 3, itemToSellInFive.quality);
        assertEquals(quality + 3, itemToSellInOne.quality);
    }

    @Test
    void updateQualityDropsItemQualityToZeroAfterExpiryForBackstagePasses() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        int sellIn = 0;
        int quality = 10;
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertEquals(0, item.quality);
    }

    @Test
    void updateQualityDecreasesItemQualityTwiceAsFastForConjuredItems() {
        String regularItemName = "foo";
        String conjuredItemName = "Conjured item";
        int sellIn = 1;
        int quality = 50;
        Item regularItem = new Item(regularItemName, sellIn, quality);
        Item conjuredItem = new Item(conjuredItemName, sellIn, quality);
        Item[] items = new Item[] { regularItem, conjuredItem };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        int qualityDecreaseForRegularItem = quality - regularItem.quality;
        int qualityDecreaseForConjuredItem = quality - conjuredItem.quality;
        assertEquals(qualityDecreaseForRegularItem * 2, qualityDecreaseForConjuredItem);
    }

    @Test
    void updateQualityDecreasesItemQualityTwiceAsFastForExpiredConjuredItems() {
        String regularItemName = "foo";
        String conjuredItemName = "Conjured item";
        int sellIn = 0;
        int quality = 50;
        Item regularItem = new Item(regularItemName, sellIn, quality);
        Item conjuredItem = new Item(conjuredItemName, sellIn, quality);
        Item[] items = new Item[] { regularItem, conjuredItem };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        int qualityDecreaseForRegularItem = quality - regularItem.quality;
        int qualityDecreaseForConjuredItem = quality - conjuredItem.quality;
        assertEquals(qualityDecreaseForRegularItem * 2, qualityDecreaseForConjuredItem);
    }

}
