package com.gildedrose.strategies.impl;

import com.gildedrose.Item;
import com.gildedrose.strategies.ItemStrategy;

public class BackstagePassesStrategy implements ItemStrategy {

    private static final int BACKSTAGE_PASSES_THRESHOLD_TEN = 10;
    private static final int BACKSTAGE_PASSES_THRESHOLD_FIVE = 5;

    private static final int BACKSTAGE_PASSES_QUALITY_DELTA = 1;
    private static final int BACKSTAGE_PASSES_QUALITY_DELTA_TEN = 2;
    private static final int BACKSTAGE_PASSES_QUALITY_DELTA_FIVE = 3;

    @Override
    public void updateItemQuality(Item item) {
        final int qualityDelta;
        if (isExpired(item)) {
            qualityDelta = MIN_QUALITY - item.quality;
        } else if (item.sellIn < BACKSTAGE_PASSES_THRESHOLD_FIVE) {
            qualityDelta = BACKSTAGE_PASSES_QUALITY_DELTA_FIVE;
        } else if (item.sellIn < BACKSTAGE_PASSES_THRESHOLD_TEN) {
            qualityDelta = BACKSTAGE_PASSES_QUALITY_DELTA_TEN;
        } else {
            qualityDelta = BACKSTAGE_PASSES_QUALITY_DELTA;
        }
        item.quality = Math.min(item.quality + qualityDelta, MAX_QUALITY);
    }
    
}
