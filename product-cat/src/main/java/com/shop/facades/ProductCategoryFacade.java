package com.shop.facades;

import com.shop.entities.Category;
import com.shop.entities.Product;
import com.shop.objects.Item;
import com.shop.objects.ItemType;
import com.shop.services.ProductCategoryService;
import com.shop.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryFacade {

    @Autowired
    private ProductCategoryService service;

    /**
     *
     * @return all categories with subcategories and products
     */
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();

        Iterable<Category> categories = service.getAll();
        List<Category> topCategories = new ArrayList<Category>();
        Item parentItem = new Item();

        for (Category cat : categories) {
            if (cat.getParent() == null) {
                parentItem = Utils.createItem(null, cat, null, ItemType.CATEGORY.name());
                topCategories.add(cat);
            }
        }

        for (Category category : topCategories) {
            items = new ArrayList<>();
            recursiveTree(category, items);
        }

        items.add(0, parentItem);
        return items;
    }

    /**
     *
     * @param item
     * @return created category
     */
    public Category addCategory(Item item){
        return service.addCategory(item);
    }

    /**
     *
     * @param item
     * @return created product
     */
    public Product addProduct(Item item) {
        return service.addProduct(item);
    }

    private void recursiveTree(Category cat, List<Item> items) {
        if (cat.getSubcategories().size() > 0) {
            for (Category c : cat.getSubcategories()) {
                Item categoryItem = Utils.createItem(null, c, cat, ItemType.CATEGORY.name());
                items.add(categoryItem);

                for (Product p : c.getProducts()) {
                    Item productItem = Utils.createItem(p, null, c, ItemType.PRODUCT.name());
                    items.add(productItem);
                }

                recursiveTree(c, items);
            }

        }
    }

    /** delete category by id
     *
     * @param id
     */
    public void deleteCategory(long id) {
        service.deleteCategory(id);
    }

    /** delete product by id
     *
     * @param id
     */
    public void deleteProduct(long id) {
        service.deleteProduct(id);
    }
}
