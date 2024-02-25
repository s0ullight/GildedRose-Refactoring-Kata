package com.gildedrose;

class GildedRose {
    private static final int EXPIRY = 0;
    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;

    private static final int BACKSTAGE_PASSES_THRESHOLD_TEN = 10;
    private static final int BACKSTAGE_PASSES_THRESHOLD_FIVE = 5;

    private static final int REGULAR_ITEM_QUALITY_DELTA = -1;
    private static final int EXPIRED_REGULAR_ITEM_QUALITY_DELTA = -2;

    private static final int AGED_BRIE_QUALITY_DELTA = 1;
    private static final int EXPIRED_AGED_BRIE_QUALITY_DELTA = 2;
    
    private static final int BACKSTAGE_PASSES_QUALITY_DELTA = 1;
    private static final int BACKSTAGE_PASSES_QUALITY_DELTA_TEN = 2;
    private static final int BACKSTAGE_PASSES_QUALITY_DELTA_FIVE = 3;

    private static final int CONJURED_ITEM_QUALITY_DELTA = -2;
    private static final int EXPIRED_CONJURED_ITEM_QUALITY_DELTA = -4;

    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String CONJURED_ITEM = "Conjured item";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            final int qualityDelta;

            if (!item.name.equals(SULFURAS)) {
                item.sellIn--;
            }

            final boolean isExpired = item.sellIn < EXPIRY;

            if(item.name.equals(AGED_BRIE)) {
                if(isExpired) {
                    qualityDelta = EXPIRED_AGED_BRIE_QUALITY_DELTA;
                } else {
                    qualityDelta = AGED_BRIE_QUALITY_DELTA;
                }
                item.quality = Math.min(item.quality + qualityDelta, MAX_QUALITY);
            } else if(item.name.equals(BACKSTAGE_PASSES)) {
                if(isExpired) {
                    qualityDelta = MIN_QUALITY - item.quality;
                } else if(item.sellIn < BACKSTAGE_PASSES_THRESHOLD_FIVE) {
                    qualityDelta = BACKSTAGE_PASSES_QUALITY_DELTA_FIVE;
                } else if(item.sellIn < BACKSTAGE_PASSES_THRESHOLD_TEN) {
                    qualityDelta = BACKSTAGE_PASSES_QUALITY_DELTA_TEN;
                } else {
                    qualityDelta = BACKSTAGE_PASSES_QUALITY_DELTA;
                }
                item.quality = Math.min(item.quality + qualityDelta, MAX_QUALITY);
            } else if(item.name.equals(SULFURAS)) {
                // Hey Alexa, play U Can't Touch This by MC Hammer
            } else if(item.name.equals(CONJURED_ITEM)) {
                if(isExpired) {
                    qualityDelta = EXPIRED_CONJURED_ITEM_QUALITY_DELTA;
                } else {
                    qualityDelta = CONJURED_ITEM_QUALITY_DELTA;
                }
                item.quality = Math.max(item.quality + qualityDelta, MIN_QUALITY);
            } else {
                if(isExpired) {
                    qualityDelta = EXPIRED_REGULAR_ITEM_QUALITY_DELTA;
                } else {
                    qualityDelta = REGULAR_ITEM_QUALITY_DELTA;
                }
                item.quality = Math.max(item.quality + qualityDelta, MIN_QUALITY);
            }
        }
    }
}
