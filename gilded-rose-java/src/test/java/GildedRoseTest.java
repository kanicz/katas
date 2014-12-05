import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {


    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    @Test
    public void sellInDecreases() {
        //given
        int initialSellIn = 5;
        Item item = new Item("foo", initialSellIn, 10);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getSellIn()).isEqualTo(--initialSellIn);
    }

    @Test
    public void qualityDecreases() {
        //given
        int initialQuality = 5;
        Item item = new Item("foo", 10, initialQuality);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getQuality()).isEqualTo(--initialQuality);
    }

    @Test
    public void qualityDecreasesTwiceAsFastIsSellDateHasPassed() {
        //given
        int initialQuality = 5;
        Item item = new Item("foo", 0, initialQuality);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getQuality()).isEqualTo(initialQuality - 2);
    }

    @Test
    public void qualityIsNeverNegative() {
        //given
        Item item = new Item("foo", 0, 0);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getQuality()).isNotNegative();
    }

    @Test
    public void agedBrieQualityIncreasesWithAge() {
        //given
        Item item = new Item(AGED_BRIE, 5, 0);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getQuality()).isEqualTo(1);
    }

    @Test
    public void qualityIsNeverAbove50() {
        //given
        Item item = new Item(AGED_BRIE, 5, 50);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getQuality()).isEqualTo(50);
    }

    @Test
    public void sulfurasNeverDecreasesInQuality() {
        //given
        Item item = new Item(SULFURAS, 5, 10);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getQuality()).isEqualTo(10);
    }

    @Test
    public void sulfurasHasNeverBeSOld() {
        //given
        Item item = new Item(SULFURAS, 5, 10);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getSellIn()).isEqualTo(5);
    }

    @Test
    public void backstagePassesQualityIncreasesBy2WhenThereIs10DaysToSellIn() {
        //given
        Item item = new Item(BACKSTAGE_PASSES, 10, 5);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getQuality()).isEqualTo(7);
    }


    @Test
    public void backstagePassesQualityIncreasesBy3WhenThereIs5DaysToSellIn() {
        //given
        Item item = new Item(BACKSTAGE_PASSES, 5, 5);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getQuality()).isEqualTo(8);
    }

    @Test
    public void backstagePassesQualityDropsTo0AfterTheConcert() {
        //given
        Item item = new Item(BACKSTAGE_PASSES, 0, 5);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getQuality()).isEqualTo(0);
    }

    @Test
    @Ignore
    public void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() {
        //given
        Item item = new Item(CONJURED_MANA_CAKE, 5, 5);
        GildedRose.addItem(item);

        //when
        GildedRose.updateQuality();

        //then
        assertThat(item.getQuality()).isEqualTo(3);
    }


}
