package com.sneekersseekers.controller;

import com.sneekersseekers.model.Shoe;
import com.sneekersseekers.service.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    
    @Autowired
    private ShoeRepository shoeRepository;
    
    @GetMapping("/details/{id}")
    public String addToCart(@PathVariable Integer id, HttpSession session) {
        // Check if user is logged in
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        
        Shoe shoe = shoeRepository.getShoeById(id);
        if (shoe != null) {
            @SuppressWarnings("unchecked")
            List<Shoe> cart = (List<Shoe>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }
            cart.add(shoe);
            session.setAttribute("cart", cart);
        }
        
        return "redirect:/catalog";
    }
    
    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        // Check if user is logged in
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        
        @SuppressWarnings("unchecked")
        List<Shoe> cart = (List<Shoe>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        
        double total = cart.stream().mapToDouble(Shoe::getPrice).sum();
        
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        model.addAttribute("username", username);
        
        return "cart";
    }
    
    @GetMapping("/checkout")
    public String checkoutPage(Model model, HttpSession session) {
        // Check if user is logged in
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        
        @SuppressWarnings("unchecked")
        List<Shoe> cart = (List<Shoe>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }
        
        double total = cart.stream().mapToDouble(Shoe::getPrice).sum();
        
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        model.addAttribute("username", username);
        
        return "checkout";
    }
    
    @PostMapping("/checkout")
    public String processCheckout(@RequestParam Double paymentAmount, 
                                 Model model, 
                                 HttpSession session) {
        // Check if user is logged in
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        
        @SuppressWarnings("unchecked")
        List<Shoe> cart = (List<Shoe>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }
        
        double totalAmount = cart.stream().mapToDouble(Shoe::getPrice).sum();
        
        // CRITICAL LOGIC: Payment validation
        if (paymentAmount == null || !paymentAmount.equals(totalAmount)) {
            // Payment amount mismatch - return to checkout with error
            model.addAttribute("cart", cart);
            model.addAttribute("total", totalAmount);
            model.addAttribute("username", username);
            model.addAttribute("error", "Payment amount must be exactly $" + String.format("%.2f", totalAmount));
            return "checkout";
        }
        
        // Payment successful - clear cart and redirect to success
        session.removeAttribute("cart");
        return "redirect:/order-success";
    }
}
