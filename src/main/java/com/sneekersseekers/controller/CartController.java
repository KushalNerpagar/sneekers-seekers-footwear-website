package com.sneekersseekers.controller;

import com.sneekersseekers.model.Shoe;
import com.sneekersseekers.service.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private ShoeRepository shoeRepository;

    // --- Utility Methods (UPDATED FOR MAP) ---

    // Safely retrieves or creates a new cart map from the session
    private Map<Shoe, Integer> getCart(HttpSession session) {
        Map<Shoe, Integer> cart = (Map<Shoe, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashMap<>(); // Use LinkedHashMap to maintain insertion order
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    // Calculates the total price (price * quantity)
    private double calculateTotal(Map<Shoe, Integer> cart) {
        return cart.entrySet().stream()
                   .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                   .sum();
    }
    
    // --- 1. ADD TO CART (UPDATED FOR QUANTITY) ---
    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Integer id, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        Shoe shoeToAdd = shoeRepository.getShoeById(id);
        
        if (shoeToAdd != null) {
            Map<Shoe, Integer> cart = getCart(session);
            // If shoe is already in cart, increment quantity; otherwise, add with quantity 1
            cart.put(shoeToAdd, cart.getOrDefault(shoeToAdd, 0) + 1);
        }
        
        return "redirect:/catalog#shoe-" + id;
    }
    
    // --- 2. VIEW CART (UPDATED FOR MAP) ---
    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        Map<Shoe, Integer> cart = getCart(session);
        double total = calculateTotal(cart);
        
        model.addAttribute("cart", cart);
        model.addAttribute("total", String.format("%.2f", total)); 
        model.addAttribute("username", session.getAttribute("username"));
        
        return "cart";
    }
    
    // --- 3. REMOVE FROM CART (UPDATED FOR MAP) ---
    @GetMapping("/remove-from-cart/{shoeId}")
    public String removeFromCart(@PathVariable Integer shoeId, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        Map<Shoe, Integer> cart = getCart(session);
        
        Shoe itemToRemove = new Shoe();
        itemToRemove.setId(shoeId);

        // Removes the entire entry from the map
        cart.remove(itemToRemove); 
        
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    // --- 4. FINAL PURCHASE ACTION (UPDATED FOR MAP) ---
    @GetMapping("/order-placed")
    public String orderPlaced(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        Map<Shoe, Integer> finalCart = getCart(session);
        double finalTotal = calculateTotal(finalCart);
        
        session.removeAttribute("cart"); 
        model.addAttribute("finalTotal", String.format("%.2f", finalTotal));

        return "order-success"; 
    }
}
