package uz.pdp.srmserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.CustomerDto;
import uz.pdp.srmserver.service.CustomerService;
import uz.pdp.srmserver.utils.AppConstants;


@RestController
@RequestMapping("/api/customer/")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @PostMapping("save")
    public HttpEntity<?> save(@RequestBody CustomerDto dto) {

        ApiResponse apiResponse = customerService.save(dto);

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("edit/{id}")
    public HttpEntity<?>edit(@PathVariable Integer id, @RequestBody CustomerDto dto){
        ApiResponse edit = customerService.edit(id, dto);
        return ResponseEntity.ok(edit);
    }

    @GetMapping("all")
    public HttpEntity<?> getAll(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
                                @RequestParam Boolean active) throws IllegalAccessException {
        return ResponseEntity.ok(customerService.getAll(page, size, active));

    }

    @GetMapping("{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        return ResponseEntity.ok(customerService.getOne(id));
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse=customerService.delete(id);

        return ResponseEntity.status(apiResponse.getMessage().equals("Deleted")?201:202).body(apiResponse);
    }
}
