package com.gildedrose;

public class CustomGildedRose {
    Item[] items;


    public CustomGildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (isNotAgeBrieAndNotBackstage(items[i])) {
                decreaseQualityByOneForSulfuras(items[i]);
            } else {
                processAgedBrieOrBackstage(items[i]);
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    isNameNotEqualsBackstage(items[i]);
                } else {
                    increaseQuanlityByOne(items[i]);
                }
            }
        }
    }

    private void isNameNotEqualsBackstage(Item item) {
        if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            decreaseQualityByOneForSulfuras(item);
        } else {
            item.quality = item.quality - item.quality;
        }
    }

    private void processAgedBrieOrBackstage(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.sellIn < 11) {
                    increaseQuanlityByOne(item);
                }

                if (item.sellIn < 6) {
                    increaseQuanlityByOne(item);
                }
            }
        }
    }

    private boolean isNotAgeBrieAndNotBackstage(Item item) {
        return !item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }


    private void decreaseQualityByOneForSulfuras(Item item) {
        if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality -= 1;
        }//end if (items[i].quality > 0) {
    }

    private void increaseQuanlityByOne(Item item) {
        if (item.quality < 50) {
            item.quality +=1;
        }
    }
}
