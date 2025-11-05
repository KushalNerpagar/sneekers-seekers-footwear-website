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
    private Map<Shoe, Integer> getCart(HttpSession session) {
        Map<Shoe, Integer> cart = (Map<Shoe, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashMap<>(); // Use LinkedHashMap to maintain insertion order
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    private double calculateTotal(Map<Shoe, Integer> cart) {
        return cart.entrySet().stream()
                   .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                   .sum();
    }
    
    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Integer id, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        Shoe shoeToAdd = shoeRepository.getShoeById(id);
        
        if (shoeToAdd != null) {
            Map<Shoe, Integer> cart = getCart(session);
            cart.put(shoeToAdd, cart.getOrDefault(shoeToAdd, 0) + 1);
        }
        
        return "redirect:/catalog#shoe-" + id;
    }
    
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
    
    @GetMapping("/remove-from-cart/{shoeId}")
    public String removeFromCart(@PathVariable Integer shoeId, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        Map<Shoe, Integer> cart = getCart(session);
        
        Shoe itemToRemove = new Shoe();
        itemToRemove.setId(shoeId);

        cart.remove(itemToRemove); 
        
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

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
