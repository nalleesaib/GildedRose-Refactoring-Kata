package com.gildedrose;


import org.assertj.core.api.Assertions;
import org.junit.Test;

public class GildedRoseTest {

    private static final String SULFURAS="Sulfuras, Hand of Ragnaros";
    private static final String EXLIXIR="Elixir of the Mongoose";
    private static final String AGED_BRIE="Aged Brie";
    private static final String BACKSTAGE="Backstage passes to a TAFKAL80ETC concert";

    private Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6) };

    @Test
    public void foo() {
        // Given
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Assertions.assertThat(app.items[0].name).isEqualTo("foo");
    }

    /*
        Test using name Sulfuras -> should return
    */
    @Test
    public void testSulfurasQuality0() {
        // Given
        Item[] items = new Item[] { new Item(SULFURAS, 0, 0) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Assertions.assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    public void testElixirQuality15() {
        // Given
        Item[] items = new Item[] { new Item(EXLIXIR, 0, 15) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Assertions.assertThat(app.items[0].quality).isEqualTo(13);
    }
    @Test
    public void testAgedBrieQuality15() {
        // Given
        Item[] items = new Item[] { new Item(AGED_BRIE, 0, 15) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Assertions.assertThat(app.items[0].quality).isEqualTo(17);
    }

    @Test
    public void testBackstageSelling11Quality15() {
        // Given
        Item[] items = new Item[] { new Item(BACKSTAGE, 11, 15) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Assertions.assertThat(app.items[0].quality).isEqualTo(16);
    }

    @Test
    public void testBackstageSelling5Quality15() {
        // Given
        Item[] items = new Item[] { new Item(BACKSTAGE, 5, 15) };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Assertions.assertThat(app.items[0].quality).isEqualTo(18);
    }

    @Test
    public void testElixirSellingLessThan1Quality15() {
        // Given
        Item[] items = new Item[]{new Item(EXLIXIR, -1, 15)};
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        Assertions.assertThat(app.items[0].quality).isEqualTo(13);
        Assertions.assertThat(app.items[0].sellIn).isEqualTo(-2);
    }
}
