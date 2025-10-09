package com.sneekersseekers.model;

public class Shoe {
    private Integer id;
    private String brand;
    private String model;
    private String category;
    private Double price;
    private String imageUrl;

    // Default constructor
    public Shoe() {}

    // Constructor with all fields
    public Shoe(Integer id, String brand, String model, String category, Double price, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
