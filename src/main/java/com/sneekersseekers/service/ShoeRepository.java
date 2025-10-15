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
        shoes.add(new Shoe(1,  "Air Max 270", "Nike", "Running", 150.00, "https://cdn.evrysz.net/473x340/18/nike-air-max-270-fn3412600.png"));
        shoes.add(new Shoe(2, "Ultraboost 22", "Adidas", "Running", 180.00, "/images/ultra-boost-22.webp"));
        shoes.add(new Shoe(3, "Air Jordan 1", "Jordan", "Basketball", 170.00, "https://images.vegnonveg.com/resized/1020X1200/13586/jordan-air-jordan-1-mid-whiteblack-light-smoke-grey-68831dfbaf2cc.jpg?format=webp"));
        shoes.add(new Shoe(4, "Chuck Taylor All Star", "Converse", "Casual", 65.00, "https://www.footlocker.ph/media/catalog/product/0/8/0803-CONM9166C00003H-1.jpg"));
        shoes.add(new Shoe(5, "Old Skool", "Vans", "Skateboarding", 60.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-DCk9rI468vndL561hO6sb3_ClWFBOBPn5YKgP8tkg6lcFs_WuSdhHlUxGCRqU_aE8mY&usqp=CAU"));
        shoes.add(new Shoe(6, "RS-X Reinvention", "Puma", "Lifestyle", 120.00, "https://media-us.landmarkshops.in/cdn-cgi/image/h=550,w=550,q=85,fit=cover/lifestyle/1000013890958-Black-Black-1000013890958_01-2100.jpg"));
        shoes.add(new Shoe(7, "990v5", "New Balance", "Running", 185.00, "https://image.goxip.com/qvEhtSqrEldv0551wUsF3MXB2Gg=/fit-in/500x500/filters:format(jpg):quality(80):fill(white)/https:%2F%2Fimages.stockx.com%2Fimages%2FNew-Balance-990-v5-Grey-GS-Product.jpg"));
        shoes.add(new Shoe(8, "Classic Leather", "Reebok", "Lifestyle", 80.00, "https://www.superkicks.in/cdn/shop/files/1_62_8f4d2fa3-767c-476d-9000-81378b319e83.jpg?v=1750409075"));
        shoes.add(new Shoe(9, "Stan Smith", "Adidas", "Lifestyle", 80.00, "https://assets.adidas.com/images/w_600,f_auto,q_auto/394c0844198b44b3944bad300bb4c37c_9366/Stan_Smith_Shoes_White_IE0467_01_standard.jpg"));
        shoes.add(new Shoe(10,"Samba", "Adidas","Lifestyle", 80.00, "https://assets.adidas.com/images/w_600,f_auto,q_auto/b19389122c51434eb5bea8bf0117da35_9366/Samba_OG_Shoes_White_B75806_07_standard.jpg"));
        shoes.add(new Shoe(11,"Busenitz", "Adidas","Lifestyle", 80.00, "https://35thnorth.com/cdn/shop/files/ScreenShot2024-01-16at6.44.29PM_1166x.png?v=1705459486"));
        shoes.add(new Shoe(12,"Campus 00S", "Adidas","Lifestyle", 80.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPk1wamKNv2mdCizv6QSZ-j7V5Dl_M1-DXKA&s"));
        shoes.add(new Shoe(13,"Ozweego", "Adidas","Lifestyle", 80.00, "https://www.superkicks.in/cdn/shop/files/1_2bade322-6489-43c0-ba60-e52b8fda3331.jpg?v=1687959237"));
        shoes.add(new Shoe(14," x Kodak Classic Nylon", "Reebok","Lifestyle", 80.00, "https://www.reebok.com/cdn/shop/files/100228780_SLC_eCom.jpg?v=1760360948&width=2000"));
        shoes.add(new Shoe(15,"FloatZig 1", "Reebok","Lifestyle", 80.00, "https://www.reebok.eu/cdn/shop/files/25351788_56880233_800.webp?v=1744776801&width=600"));
        shoes.add(new Shoe(16,"Nano X4", "Reebok","Lifestyle", 80.00, "https://cdn.shopify.com/s/files/1/0862/7834/0912/files/100074684_SLC_eCom-tif.png?v=1736436867"));
        shoes.add(new Shoe(17,"Reebok Zig Dynamica 5", "Reebok","Lifestyle", 80.00, "https://static.netshoes.com.br/produtos/tenis-reebok-zig-dynamica-5-masculino/10/D19-7604-010/D19-7604-010_zoom1.jpg?ts=1722004701"));
        shoes.add(new Shoe(18,"Nano Court", "Reebok","Lifestyle", 80.00, "https://reebok.ca/cdn/shop/files/100211615B1849_400x400.jpg?v=1719019425"));
        shoes.add(new Shoe(19,"Nano X2 TR Adventure", "Reebok","Lifestyle", 80.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4d63dlL6ss4oSihei1ZbwOlANxOBAFdFXoA&s"));
        shoes.add(new Shoe(20,"Answer DMX", "Reebok","Lifestyle", 80.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhnBMKOh9BXHwj2NZPOoEKaQGKQs8Pz3Rpdg&s"));
        shoes.add(new Shoe(21, "Velocity Nitro 4", "Puma", "Lifestyle", 120.00, "https://dynamic.zacdn.com/YTjcjaYHQJ_Y0VAc38j8lStvHkg=/filters:quality(70):format(webp)/https://static-ph.zacdn.com/p/puma-2290-2320283-1.jpg"));
        shoes.add(new Shoe(22, "MagMax Nitro", "Puma", "Lifestyle", 120.00, "https://images.hardloop.fr/798422-large_default/puma-magmax-nitro-running-shoes-mens.jpg"));
        shoes.add(new Shoe(23, "Deviate Nitro 3", "Puma", "Lifestyle", 120.00, "https://shop.therunningcompany.com.au/cdn/shop/files/Deviate-NITRO_-3-Running-Shoes-Women-2.jpg?v=1743640579&width=1445"));
        shoes.add(new Shoe(24, "ForeverRun Nitro 2", "Puma", "Lifestyle", 120.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyvOIvTq8kmHnWBA3hqQjrPbleCw-63mVxVyhoC72AdbagbxfoCR7hAuqDu9zNRlIll6Q&usqp=CAU"));
        shoes.add(new Shoe(25, "Jordan Spizike Low", "Jordan", "Lifestyle", 120.00, "https://www.superkicks.in/cdn/shop/files/1_4a501f46-0fbe-4d64-a565-7bbc723148de.jpg?v=1724672562"));
        shoes.add(new Shoe(26, "Jordan Luka 3", "Jordan", "Lifestyle", 120.00, "https://static.nbastore.in/resized/500X500/741/jordan-luka-3-pf-basketball-shoes-chlorine-bluehyper-pinkblack-blue-6722398e40b34.jpg?format=webp"));

        shoes.add(new Shoe(27, "6 Rings", "Jordan", "Lifestyle", 120.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJXDZ_NG935zBfsZ1kxGFIUb9r5fzOT0p_WJ9dCgqR7BuDsXbqO34rAF5FNMFR4EO5NQ0&usqp=CAU"));

        shoes.add(new Shoe(28, "Legacy 312 Low", "Jordan", "Lifestyle", 120.00, "https://static.nbastore.in/resized/500X500/709/air-jordan-legacy-312-low-sneakers-white-white-670571c65a979.jpg?format=webp"));

        shoes.add(new Shoe(29, "Jordan One Take 5", "Jordan", "Lifestyle", 120.00, "https://cdn.sportdepot.bg/files/catalog/detail/FD2338-104_01.jpg"));
        shoes.add(new Shoe(30, "Jordan Tatum 1", "Jordan", "Lifestyle", 120.00, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQldN0bA-PoMRhYnYDw4CA5SzFhWY1Bwm-sZA&s"));

        

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
