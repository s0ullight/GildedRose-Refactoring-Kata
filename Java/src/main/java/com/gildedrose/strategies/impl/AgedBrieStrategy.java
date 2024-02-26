package com.gildedrose.strategies.impl;

import com.gildedrose.Item;
import com.gildedrose.strategies.ItemStrategy;

public class AgedBrieStrategy implements ItemStrategy {
    private static final int AGED_BRIE_QUALITY_DELTA = 1;
    private static final int EXPIRED_AGED_BRIE_QUALITY_DELTA = 2;

    @Override
    public void updateItemQuality(Item item) {
        final int qualityDelta;
        if (isExpired(item)) {
            qualityDelta = EXPIRED_AGED_BRIE_QUALITY_DELTA;
        } else {
            qualityDelta = AGED_BRIE_QUALITY_DELTA;
        }
        item.quality = Math.min(item.quality + qualityDelta, MAX_QUALITY);
    }
    
}
