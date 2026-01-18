package com.cnam.ecommerce.api;

import com.cnam.ecommerce.api.dto.CreateOrderItem;
import com.cnam.ecommerce.api.dto.CreateOrderRequest;
import com.cnam.ecommerce.model.Order;
import com.cnam.ecommerce.model.OrderItem;
import com.cnam.ecommerce.repo.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Order create(@Valid @RequestBody CreateOrderRequest req) {
        Order order = new Order();
        order.setCustomerName(req.getCustomerName());
        order.setCustomerEmail(req.getCustomerEmail());

        for (CreateOrderItem i : req.getItems()) {
            OrderItem item = new OrderItem();
            item.setProductId(i.getProductId());
            item.setQuantity(i.getQuantity());
            item.setUnitPrice(i.getUnitPrice());
            order.addItem(item);
        }

        return repo.save(order);
    }

    @GetMapping
    public List<Order> all() {
        return repo.findAll();
    }
}
