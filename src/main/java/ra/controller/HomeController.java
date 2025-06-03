package ra.controller;

import ra.entity.Product;
import ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {
    @Autowired
    private ProductService productService;

    private static final int PAGE_SIZE = 5;

    @GetMapping
    public String listProduct(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Product> products = productService.findAll(page, PAGE_SIZE);
        long totalProducts = productService.count();

        int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "home";
    }

    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/home";
        }
        model.addAttribute("product", product);
        return "productdetail";
    }
}
