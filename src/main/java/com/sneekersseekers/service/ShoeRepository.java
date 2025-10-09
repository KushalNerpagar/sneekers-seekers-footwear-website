package com.sneekersseekers.service;

import com.sneekersseekers.model.Shoe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoeRepository {
    
    private static final List<Shoe> shoes = new ArrayList<>();
    
    static {
        // Initialize with sample shoes
        shoes.add(new Shoe(1, "Nike", "Air Max 270", "Running", 150.00, "https://example.com/nike-airmax270.jpg"));
        shoes.add(new Shoe(2, "Adidas", "Ultraboost 22", "Running", 180.00, "https://example.com/adidas-ultraboost22.jpg"));
        shoes.add(new Shoe(3, "Jordan", "Air Jordan 1", "Basketball", 170.00, "https://example.com/jordan-aj1.jpg"));
        shoes.add(new Shoe(4, "Converse", "Chuck Taylor All Star", "Casual", 65.00, "https://example.com/converse-chucktaylor.jpg"));
        shoes.add(new Shoe(5, "Vans", "Old Skool", "Skateboarding", 60.00, "https://example.com/vans-oldskool.jpg"));
        shoes.add(new Shoe(6, "Puma", "RS-X Reinvention", "Lifestyle", 120.00, "https://example.com/puma-rsx.jpg"));
        shoes.add(new Shoe(7, "New Balance", "990v5", "Running", 185.00, "https://example.com/newbalance-990v5.jpg"));
        shoes.add(new Shoe(8, "Reebok", "Classic Leather", "Lifestyle", 80.00, "https://example.com/reebok-classicleather.jpg"));
    }
    
    public List<Shoe> getAllShoes() {
        return new ArrayList<>(shoes);
    }
    
    public Shoe getShoeById(Integer id) {
        return shoes.stream()
                .filter(shoe -> shoe.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public List<Shoe> searchAndFilter(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllShoes();
        }
        
        String searchQuery = query.toLowerCase().trim();
        
        return shoes.stream()
                .filter(shoe -> shoe.getBrand().toLowerCase().contains(searchQuery) ||
                               shoe.getModel().toLowerCase().contains(searchQuery) ||
                               shoe.getCategory().toLowerCase().contains(searchQuery))
                .collect(Collectors.toList());
    }
}
