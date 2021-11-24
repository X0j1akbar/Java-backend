package uz.pdp.exesises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.exesises.payload.OrderDto;
import uz.pdp.exesises.payload.Response;
import uz.pdp.exesises.service.OrderService;

@RestController
@RequestMapping("/api/order/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody OrderDto dto){
        Response save = orderService.save(dto);
        return ResponseEntity.ok(save);
    }

    @GetMapping("{orderId}")
    public HttpEntity<?> getOne(@PathVariable Integer orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }


}
