package uz.pdp.exesises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.exesises.service.CategoryService;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("{productId}")
    public HttpEntity<?> getOne(@PathVariable Integer productId){
        return ResponseEntity.ok(categoryService.getCategoryByProductId(productId));
    }


}
