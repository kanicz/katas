import java.util.ArrayList;
import java.util.List;


public class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
    private static List<Item> items = null;

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("OMGHAI!");

        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item(AGED_BRIE, 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item(SULFURAS, 0, 80));
        items.add(new Item(BACKSTAGE, 15, 20));
        items.add(new Item(CONJURED_MANA_CAKE, 3, 6));

        updateQuality();
    }


    public static void updateQuality() {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String name = item.getName();
            if (!BACKSTAGE.equals(name) && !AGED_BRIE.equals(name)) {
                decreaseQuality(item, name);
            } else increaseQuality(item, name);

            decreaseSellIn(item, name);
        }

    }

    private static void decreaseSellIn(Item item, String name) {
        if (!SULFURAS.equals(name)) {
            item.setSellIn(item.getSellIn() - 1);
        }
    }

    private static void increaseQuality(Item item, String name) {
        if (BACKSTAGE.equals(name)) {
            if (item.getSellIn() <= 0) {
                item.setQuality(0);
            } else {
                increaseQualityBy(item, item.getSellIn() < 6 ? 3 : item.getSellIn() < 11 ? 2 : 1);
            }
        } else if (AGED_BRIE.equals(name)) {
            increaseQualityBy(item, 1);
        }
    }

    private static void decreaseQuality(Item item, String name) {
        int decreaseBy = item.getSellIn() <= 0 ? 2 : 1;
        if (CONJURED_MANA_CAKE.equals(name)) {
            decreaseBy = decreaseBy * 2;
        }
        decreaseQualityBy(item, decreaseBy);
    }


    private static void decreaseQualityBy(Item item, int i) {
        if (item.getQuality() > 0) {
            if (!SULFURAS.equals(item.getName())) {
                item.setQuality(item.getQuality() - i);
            }
        }
    }

    private static void increaseQualityBy(Item item, int i) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + i);
        }
    }

    public static void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }
}