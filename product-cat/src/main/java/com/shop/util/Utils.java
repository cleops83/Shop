package com.shop.util;

import com.shop.entities.Category;
import com.shop.entities.Product;
import com.shop.objects.Item;
import com.shop.objects.ItemType;

public class Utils {


    public static Item createItem(Product product, Category category, Category parent, String type) {
        Item item = new Item();
        if (type.equals(ItemType.CATEGORY.name())) {
            item.setId(category.getId());
            item.setName(category.getName());
        } else {
            item.setId(product.getId());
            item.setName(product.getName());
        }
        item.setType(type);
        item.setParent(parent != null ? parent.getId(): null);

        return item;
    }
}
