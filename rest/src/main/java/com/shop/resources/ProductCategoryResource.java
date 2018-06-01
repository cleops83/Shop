package com.shop.resources;

import com.shop.entities.Category;
import com.shop.entities.Product;
import com.shop.objects.Item;
import com.shop.facades.ProductCategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductCategoryResource {

    @Autowired
    private ProductCategoryFacade facade;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> listItems() {
        return ResponseEntity.ok(facade.getAll());
    }

    @PostMapping(path = "/add/category")
    public ResponseEntity<?> addCategory(@RequestBody Item item) {
        // save to database
        Category category = facade.addCategory(item);
        return ResponseEntity.ok("category created: " + category.getId());
    }

    @PostMapping(path = "/add/product")
    public ResponseEntity<?> addProduct(@RequestBody Item item) {
        Product product = facade.addProduct(item);
        return ResponseEntity.ok("product created: " + product.getId());
    }

    @DeleteMapping(path = "/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        facade.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(path = "/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        facade.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}