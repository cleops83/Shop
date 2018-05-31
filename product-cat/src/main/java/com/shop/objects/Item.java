package com.shop.objects;

import java.io.Serializable;

public class Item implements Serializable {
    private Long id;
    private String name;
    private Item parent;
    private String type;

    public Item(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    public Item getParent() {
        return parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
