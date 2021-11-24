package uz.pdp.srmserver.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.ExpenseDto;
import uz.pdp.srmserver.service.ExpenseService;
import uz.pdp.srmserver.utils.AppConstants;

import java.util.UUID;

@RestController
@RequestMapping("/api/expense/")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;


    @PostMapping("save")
    public HttpEntity<?> save(@RequestBody ExpenseDto expenseDto) {
        ApiResponse save = expenseService.save(expenseDto);
        return ResponseEntity.ok(save);
    }


    @SneakyThrows
    @GetMapping("all")
    public HttpEntity<?> getAll(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
                                @RequestParam(value = "startDate", defaultValue = AppConstants.START_DATE) String startDate,
                                @RequestParam(value = "endDate", defaultValue = AppConstants.END_DATE) String endDate) {
        ApiResponse all = expenseService.getAll(page, size, startDate, endDate);
        return ResponseEntity.ok(all);
    }

    @PutMapping("edit/{id}")
    public HttpEntity<?>edit(@PathVariable UUID id,@RequestBody ExpenseDto expenseDto){
        ApiResponse edit = expenseService.edit(id, expenseDto);
        return ResponseEntity.ok(edit);
    }
    @GetMapping("{id}")
    public HttpEntity<?>getOne(@PathVariable UUID id){
        return ResponseEntity.ok(expenseService.getOne(id));
    }

    @DeleteMapping("delete/{id}")
    public HttpEntity<?>delete(@PathVariable UUID id){
        ApiResponse delete = expenseService.delete(id);
        return ResponseEntity.ok(delete);
    }

}
