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
        shoes.add(new Shoe(1, "Nike", "Air Max 270", "Running", 150.00, "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco,u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/8c399600-f983-4d92-b323-315cc869dbcd/AIR+JORDAN+MULE.png"));
        shoes.add(new Shoe(2, "Adidas", "Ultraboost 22", "Running", 180.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(3, "Jordan", "Air Jordan 1", "Basketball", 170.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(4, "Converse", "Chuck Taylor All Star", "Casual", 65.00, "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco,u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/8c399600-f983-4d92-b323-315cc869dbcd/AIR+JORDAN+MULE.png"));
        shoes.add(new Shoe(5, "Vans", "Old Skool", "Skateboarding", 60.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(6, "Puma", "RS-X Reinvention", "Lifestyle", 120.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(7, "New Balance", "990v5", "Running", 185.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(8, "Reebok", "Classic Leather", "Lifestyle", 80.00, "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco,u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/8c399600-f983-4d92-b323-315cc869dbcd/AIR+JORDAN+MULE.png"));
        shoes.add(new Shoe(9, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(10, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(11, "Reebok", "Classic Leather", "Lifestyle", 80.00, "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco,u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/8c399600-f983-4d92-b323-315cc869dbcd/AIR+JORDAN+MULE.png"));
        shoes.add(new Shoe(12, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(13, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(14, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(15, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(16, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(17, "Reebok", "Classic Leather", "Lifestyle", 80.00, "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco,u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/8c399600-f983-4d92-b323-315cc869dbcd/AIR+JORDAN+MULE.png"));
        shoes.add(new Shoe(18, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(19, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(20, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(21, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(22, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(23, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(24, "Reebok", "Classic Leather", "Lifestyle", 80.00, "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco,u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/8c399600-f983-4d92-b323-315cc869dbcd/AIR+JORDAN+MULE.png"));
        shoes.add(new Shoe(25, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(26, "Reebok", "Classic Leather", "Lifestyle", 80.00, "/images/ultra-boost-22.webp"));
        

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
