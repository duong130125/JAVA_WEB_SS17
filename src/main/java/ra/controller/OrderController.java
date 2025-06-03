package ra.controller;

import ra.entity.Order;
import ra.entity.OrderItem;
import ra.entity.ProductCart;
import ra.service.OrderService;
import ra.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductCartService productCartService;

    @GetMapping("checkout")
    public String checkoutForm(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";

        List<ProductCart> cartItems = productCartService.findCartByCustomerId(customerId);
        double total = cartItems.stream().mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity()).sum();

        Order order = new Order();
        order.setTotalMoney(total);

        model.addAttribute("order", order);
        return "checkout";
    }

    @PostMapping("checkout")
    public String processCheckout(@ModelAttribute("order") Order order, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";

        order.setCustomerId(customerId);
        order.setStatus("Đang xử lý");

        List<ProductCart> cartItems = productCartService.findCartByCustomerId(customerId);
        List<OrderItem> orderItems = cartItems.stream().map(cart -> {
            OrderItem item = new OrderItem();
            item.setProduct(cart.getProduct());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getProduct().getPrice());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        orderService.save(order);
        productCartService.clearCartByCustomerId(customerId);

        return "orderSuccess";
    }
}
