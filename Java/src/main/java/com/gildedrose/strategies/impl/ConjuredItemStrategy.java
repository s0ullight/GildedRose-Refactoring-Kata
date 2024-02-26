package com.gildedrose.strategies.impl;

import com.gildedrose.Item;
import com.gildedrose.strategies.ItemStrategy;

public class ConjuredItemStrategy implements ItemStrategy {

    private static final int CONJURED_ITEM_QUALITY_DELTA = -2;
    private static final int EXPIRED_CONJURED_ITEM_QUALITY_DELTA = -4;

    @Override
    public void updateItemQuality(Item item) {
        final int qualityDelta;
        if (isExpired(item)) {
            qualityDelta = EXPIRED_CONJURED_ITEM_QUALITY_DELTA;
        } else {
            qualityDelta = CONJURED_ITEM_QUALITY_DELTA;
        }
        item.quality = Math.max(item.quality + qualityDelta, MIN_QUALITY);
    }
    
}
