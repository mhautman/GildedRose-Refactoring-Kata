package com.gildedrose;

class GildedRose {
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "backstage passes to a tafkal80etc concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "sulfuras, hand of ragnaros";
    public static final String AGED_BRIE = "aged brie";
    public static final int ITEM_MAX_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isALegendaryItem(item)) {
                continue;
            }

            if (isNotAMaturationItem(item) && isNotAtLowestQuality(item)) {
                item.quality--;
            } else {
                increaseQualityOfMaturationItems(item);
            }

            item.sellIn--;

            if (item.sellIn < 0) {
                itemSellDateHasPassed(item);
            }
        }
    }

    private void increaseQualityOfMaturationItems(Item item) {
        if (isNotAtMaxQuality(item)) {
            item.quality++;

            if (isABackStagePass(item) && isNotAtMaxQuality(item)) {
                inCreaseQualityOfBackStagePass(item);
            }
        }
    }

    private void inCreaseQualityOfBackStagePass(Item item) {
        if (item.sellIn < 11) {
            item.quality++;
        }
        if (item.sellIn < 6 && isNotAtMaxQuality(item)) {
            item.quality++;
        }
    }

    private void itemSellDateHasPassed(Item item) {
        if (isABackStagePass(item)) {
            item.quality = 0;
        }

        if (isAMatureCheese(item) && isNotAtMaxQuality(item)) {
            item.quality++;
        } else {
            if (isNotAtLowestQuality(item)) {
                item.quality--;
            }
        }
    }

    private boolean isALegendaryItem(Item item) {
        return SULFURAS_HAND_OF_RAGNAROS.equalsIgnoreCase(item.name);
    }

    private boolean isNotAMaturationItem(Item item) {
        return !isAMatureCheese(item) && !isABackStagePass(item);
    }

    private boolean isNotAtLowestQuality(Item item) {
        return item.quality > 0;
    }

    private boolean isAMatureCheese(Item item) {
        return AGED_BRIE.equalsIgnoreCase(item.name);
    }

    private boolean isABackStagePass(Item item) {
        return BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT.equalsIgnoreCase(item.name);
    }

    private boolean isNotAtMaxQuality(Item item) {
        return item.quality < ITEM_MAX_QUALITY;
    }


}
