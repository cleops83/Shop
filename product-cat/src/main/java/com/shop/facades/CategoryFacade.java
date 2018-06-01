package com.shop.facades;

import com.shop.entities.Category;
import com.shop.entities.Product;
import com.shop.objects.Item;
import com.shop.objects.ItemType;
import com.shop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryFacade {

    @Autowired
    private CategoryService categoryService;

    List<Item> items;

    public List<Item> getAll() {
        Iterable<Category> categories = categoryService.getAll();
        List<Category> topCategories = new ArrayList<Category>();
        Item parentItem = new Item();

        for (Category cat : categories) {
            if (cat.getParent() == null) {
                parentItem.setId(cat.getId());
                parentItem.setName(cat.getName());
                parentItem.setParent(null);
                parentItem.setType(ItemType.CATEGORY.name());
                topCategories.add(cat);
            }
        }

        for (Category category : topCategories) {
            items = new ArrayList<Item>();
            recursiveTree(category);
        }

        items.add(0, parentItem);
        return items;
    }

    public void recursiveTree(Category cat) {
        if (cat.getSubcategories().size() > 0) {
            for (Category c : cat.getSubcategories()) {
                Item categoryItem = new Item();
                categoryItem.setId(c.getId());
                categoryItem.setName(c.getName());
                categoryItem.setType(ItemType.CATEGORY.name());
                categoryItem.setParent(cat.getId());
                items.add(categoryItem);

                for (Product p : c.getProducts()) {
                    Item productItem = new Item();
                    productItem.setId(p.getId());
                    productItem.setName(p.getName());
                    productItem.setType(ItemType.PRODUCT.name());
                    productItem.setParent(categoryItem.getId());
                    items.add(productItem);
                }

                recursiveTree(c);
            }

        }
    }
}
