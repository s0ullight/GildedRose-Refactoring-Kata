package com.gildedrose;

class GildedRose {
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    private static final int BACKSTAGE_PASSES_THRESHOLD_FIVE = 6;
    private static final int BACKSTAGE_PASSES_THRESHOLD_TEN = 11;
    private static final int EXPIRY = 0;
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (item.name.equals(AGED_BRIE) || item.name.equals(BACKSTAGE_PASSES)) {
                if (item.quality < MAX_QUALITY) {
                    item.quality++;

                    if (item.name.equals(BACKSTAGE_PASSES)) {
                        if (item.sellIn < BACKSTAGE_PASSES_THRESHOLD_TEN) {
                            if (item.quality < MAX_QUALITY) {
                                item.quality++;
                            }
                        }

                        if (item.sellIn < BACKSTAGE_PASSES_THRESHOLD_FIVE) {
                            if (item.quality < MAX_QUALITY) {
                                item.quality++;
                            }
                        }
                    }
                }
            } else {
                if (item.quality > MIN_QUALITY) {
                    if (!item.name.equals(SULFURAS)) {
                        item.quality--;
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn--;
            }

            if(item.sellIn < EXPIRY) {
                if(item.name.equals(AGED_BRIE)) {
                    if(item.quality < MAX_QUALITY) {
                        item.quality++;
                    }
                } else if(item.name.equals(BACKSTAGE_PASSES)) {
                    item.quality = MIN_QUALITY;
                } else if(item.quality > MIN_QUALITY) {
                    if(!item.name.equals(SULFURAS)) {
                        item.quality--;
                    }
                }
            }
        }
    }
}
