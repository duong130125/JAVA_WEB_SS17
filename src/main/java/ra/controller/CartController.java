package ra.controller;

import ra.entity.ProductCart;
import ra.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductCartService productCartService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        List<ProductCart> cartItems = productCartService.findCartByCustomerId(customerId);
        model.addAttribute("cartItems", cartItems);

        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("totalAmount", totalAmount);

        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") int productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {

        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        List<ProductCart> existingItems = productCartService.findCart(customerId, productId);
        if (existingItems.isEmpty()) {
            ProductCart productCart = new ProductCart();
            productCart.setCustomerId(customerId);
            productCart.setProductId(productId);
            productCart.setQuantity(quantity);
            productCartService.save(productCart);
        } else {
            ProductCart existingCartItem = existingItems.get(0);
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            productCartService.update(existingCartItem);
        }

        return "redirect:/cart";
    }

    @PostMapping("/update/{id}")
    public String updateCartItem(@PathVariable("id") int id,
                                 @RequestParam("quantity") int quantity,
                                 HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        ProductCart cartItem = productCartService.findById(id);
        if (cartItem != null && cartItem.getCustomerId() == customerId) {
            cartItem.setQuantity(quantity);
            productCartService.update(cartItem);
        }

        return "redirect:/cart";
    }

    @PostMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable("id") int id, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        ProductCart cartItem = productCartService.findById(id);
        if (cartItem != null && cartItem.getCustomerId() == customerId) {
            productCartService.delete(id);
        }

        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        productCartService.clearCartByCustomerId(customerId);
        return "redirect:/cart";
    }
}
