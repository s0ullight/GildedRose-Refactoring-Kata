package com.gildedrose.strategies.impl;

import com.gildedrose.Item;
import com.gildedrose.strategies.ItemStrategy;

public class RegularItemStrategy implements ItemStrategy {

    private static final int REGULAR_ITEM_QUALITY_DELTA = -1;
    private static final int EXPIRED_REGULAR_ITEM_QUALITY_DELTA = -2;

    @Override
    public void updateItemQuality(Item item) {
        final int qualityDelta;
        if (isExpired(item)) {
            qualityDelta = EXPIRED_REGULAR_ITEM_QUALITY_DELTA;
        } else {
            qualityDelta = REGULAR_ITEM_QUALITY_DELTA;
        }
        item.quality = Math.max(item.quality + qualityDelta, MIN_QUALITY);
    }
    
}
