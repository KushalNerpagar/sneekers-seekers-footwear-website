package com.sneekersseekers.controller;

import com.sneekersseekers.model.Shoe;
import com.sneekersseekers.service.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CatalogController {
    
    @Autowired
    private ShoeRepository shoeRepository;
    
    @GetMapping("/catalog")
    public String catalog(@RequestParam(required = false) String search, 
                         Model model, 
                         HttpSession session) {
        
        // Check if user is logged in
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        
        List<Shoe> shoes;
        if (search != null && !search.trim().isEmpty()) {
            shoes = shoeRepository.searchAndFilter(search);
            model.addAttribute("searchQuery", search);
        } else {
            shoes = shoeRepository.getAllShoes();
        }
        
        model.addAttribute("shoes", shoes);
        model.addAttribute("username", username);
        
        return "catalog";
    }
}
