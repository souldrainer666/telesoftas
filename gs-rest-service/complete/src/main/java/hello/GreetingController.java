package hello;

import gildedrose.GildedRose;
import gildedrose.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GildedRose app;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/list")
    public List<Item> list() {
        return Arrays.asList(app.getItems());
    }

    @RequestMapping("/details")
    public List<Item> details(@RequestParam(value = "id", defaultValue = "0") String itemId, @RequestParam(value = "name", defaultValue = "-") String name) {
        List<Item> items = Arrays.asList(app.getItems());
        if (!itemId.equals("0")) {
            if (!itemId.matches("\\d+")) {
                return Collections.emptyList();
            }
            int id = Integer.parseInt(itemId);
            if (id > items.size()) {
                return Collections.emptyList();
            }
            for (Item item : items) {
                if (item.getId() == id) {
                    return Collections.singletonList(item);
                }
            }
        }
        if (!name.equals("-")) {
            List<Item> result = new ArrayList<>();
            for (Item item : items) {
                if (item.getName().equals(name)) {
                    result.add(item);
                }
            }
            return result;
        }
        return list();
    }
}
