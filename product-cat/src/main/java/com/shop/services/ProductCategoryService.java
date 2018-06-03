package com.shop.services;


import com.shop.entities.Category;
import com.shop.entities.Product;
import com.shop.objects.Item;
import com.shop.repositories.CategoryRepository;
import com.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Category> getAll(){
        return categoryRepository.findAll();

    }

    public Category addCategory(Item item){
        Optional<Category> parent;
        parent = categoryRepository.findById(item.getParent());
        Category category = new Category();
        category.setName(item.getName());
        category.setParent(parent !=null ? parent.get() : null);
        return categoryRepository.save(category);
    }

    public Product addProduct(Item item) {
        Optional<Category> parent;
        parent = categoryRepository.findById(item.getParent());
        Product product = new Product();
        product.setName(item.getName());
        product.setPrice(item.getPrice());
        product.setCurrency(item.getCurrency());
        product.setCategory(parent !=null ? parent.get() : null);
        return productRepository.save(product);
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public void updateCategory(Item item, long id) {
        Optional<Category> cat = categoryRepository.findById(id);
        Category category = cat.get();
        if(category != null) {
            category.setName(item.getName());
            categoryRepository.save(category);
        }
    }

    public void updateProduct(Item item, long id) {
        Optional<Product> prod = productRepository.findById(id);
        Product product = prod.get();
        if(product != null) {
            product.setName(item.getName());
            product.setCurrency(item.getCurrency());
            product.setPrice(item.getPrice());
            productRepository.save(product);
        }
    }
}
