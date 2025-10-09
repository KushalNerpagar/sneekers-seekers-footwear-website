package com.sneekersseekers.controller;

import com.sneekersseekers.model.Shoe;
import com.sneekersseekers.service.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private ShoeRepository shoeRepository;

    // --- Utility Methods ---

    // Safely retrieves or creates a new cart list from the session
    private List<Shoe> getCart(HttpSession session) {
        return Optional.ofNullable((List<Shoe>) session.getAttribute("cart"))
                       .orElseGet(ArrayList::new);
    }

    // Calculates the total price of items in the cart
    private double calculateTotal(List<Shoe> cart) {
        return cart.stream().mapToDouble(Shoe::getPrice).sum();
    }
    
    // --- 1. ADD TO CART ---
    // NOTE: Changed mapping from /details/{id} to /add-to-cart/{id} for clarity 
    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Integer id, HttpSession session) {
        // Optional: Check if user is logged in
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        Shoe shoeToAdd = shoeRepository.getShoeById(id);
        
        if (shoeToAdd != null) {
            List<Shoe> cart = getCart(session);
            cart.add(shoeToAdd);
            session.setAttribute("cart", cart); 
        }
        
        // Redirect to the cart view after adding
        // return "redirect:/catalog"; 
        return "redirect:/catalog#shoe-" + id;
    }
    
    // --- 2. VIEW CART ---
    // NOTE: This now handles the view for what was previously /checkout
    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        List<Shoe> cart = getCart(session);
        double total = calculateTotal(cart);
        
        model.addAttribute("cart", cart);
        // Using total for the cart view template
        model.addAttribute("total", String.format("%.2f", total)); 
        model.addAttribute("username", session.getAttribute("username"));
        
        return "cart";
    }
    
    // --- 3. REMOVE FROM CART ---
    // @GetMapping("/remove-from-cart/{shoeId}")
    // public String removeFromCart(@PathVariable Integer shoeId, HttpSession session) {
    //     if (session.getAttribute("username") == null) {
    //         return "redirect:/login";
    //     }
        
    //     List<Shoe> cart = getCart(session);
        
    //     // Use the Shoe model's equals/hashCode method to remove the correct item
    //     Shoe itemToRemove = new Shoe();
    //     itemToRemove.setId(shoeId);

    //     // Removes the first matching item found
    //     cart.remove(itemToRemove); 
        
    //     session.setAttribute("cart", cart);
    //     return "redirect:/cart";
    // }

    // Inside CartController.java

@GetMapping("/remove-from-cart/{shoeId}")
public String removeFromCart(@PathVariable Integer shoeId, HttpSession session) {
    
    // 1. Check if the user is logged in
    if (session.getAttribute("username") == null) {
        return "redirect:/login";
    }
    
    // 2. Get the current cart from the session
    List<Shoe> cart = getCart(session); // Assumes your getCart() utility method exists
    
    // 3. Create a temporary Shoe object with the ID to remove
    // This works because you implemented equals() and hashCode() in your Shoe.java model
    Shoe itemToRemove = new Shoe();
    itemToRemove.setId(shoeId);

    // 4. Remove the item from the list
    cart.remove(itemToRemove); 
    
    // 5. Save the updated list back to the session
    session.setAttribute("cart", cart);
    
    // 6. Redirect the user back to the cart page to see the changes
    return "redirect:/cart";
}

    // --- 4. FINAL PURCHASE ACTION (SIMPLIFIED PAYMENT) ---
    @GetMapping("/order-placed")
    public String orderPlaced(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        List<Shoe> finalCart = getCart(session);
        double finalTotal = calculateTotal(finalCart);
        
        // CRITICAL: Clear the cart upon successful order
        session.removeAttribute("cart"); 
        
        // Pass details for the success message
        model.addAttribute("finalTotal", String.format("%.2f", finalTotal));

        // Redirects to the 'order-success.html' template
        return "order-success"; 
    }
}