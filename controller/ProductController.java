package uz.pdp.srmserver.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.entitiy.Product;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.ProductDto;
import uz.pdp.srmserver.service.ProductService;
import uz.pdp.srmserver.utils.AppConstants;

import java.util.UUID;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @SneakyThrows
    @GetMapping("all")
    public HttpEntity<?> getAll(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
    @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
    @RequestParam(value = "search",defaultValue = "all") String search){
        ApiResponse all = productService.getAll(page, size,search);
        return ResponseEntity.ok(all);
    }

    @GetMapping("changeActive/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        return ResponseEntity.ok(productService.changeActive(id));
    }

    @GetMapping("changeExpired/{id}")
    public HttpEntity<?> changeExpired(@PathVariable UUID id){
        return ResponseEntity.ok(productService.changeExpired(id));
    }

    @PostMapping("saveOrEdit")
    public HttpEntity<?> save(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.save(productDto);
        return ResponseEntity.status(apiResponse.isSuccess()?apiResponse.getMessage().equals("Saved")?201:202:409).body(apiResponse);

    }


    @GetMapping("{id}")
    public HttpEntity<?>getOne(@PathVariable UUID id){
        Product one = productService.getOne(id);
        return ResponseEntity.ok(one);
    }

    @GetMapping("delete/{id}")
    public HttpEntity<?>delete(@PathVariable UUID id){

        ApiResponse delete = productService.delete(id);

        return ResponseEntity.ok(delete);
    }



}
