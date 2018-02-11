package hello;

import gildedrose.GildedRose;
import gildedrose.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public GildedRose gildedRose() {
        Item[] items = new Item[]{
                new Item(1, "+5 Dexterity Vest", 10, 20), //
                new Item(2, "Aged Brie", 2, 0), //
                new Item(3, "Elixir of the Mongoose", 5, 7), //
                new Item(4, "Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item(5, "Sulfuras, Hand of Ragnaros", -1, 80),
                new Item(6, "Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item(7, "Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item(8, "Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item(9, "Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose(items);

        int days = 2;

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
        return app;
    }
}
