package uz.pdp.srmserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.CategoryDto;
import uz.pdp.srmserver.repository.CategoryRepository;
import uz.pdp.srmserver.service.CategoryService;
import uz.pdp.srmserver.utils.AppConstants;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;



    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.saveOrEdit(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?apiResponse.getMessage().equals("Saved")?201:202:409).body(apiResponse);
    }

    @GetMapping("/all")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/allByPageable")
    public HttpEntity<?> allByPageable(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER)Integer page,
                                       @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE)Integer size,
                                       @RequestParam(value = "search",defaultValue = "all") String search
    ) throws IllegalAccessException {
        return ResponseEntity.ok(categoryService.allByPageable(page,size,search));
    }

    @GetMapping("/changeActive/{id}")
    public HttpEntity<?> changeActive(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.changeActive(id));
    }

    @GetMapping("/delete/{id}")
    public HttpEntity<?> remove(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.remove(id));
    }

}
