package uz.pdp.srmserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.SalaryDto;
import uz.pdp.srmserver.service.SalaryService;
import uz.pdp.srmserver.utils.AppConstants;

import java.text.ParseException;
import java.util.UUID;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    SalaryService salaryService;


    @PreAuthorize("hasAuthority('ADD_SALARY')")
    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody SalaryDto dto) {
        ApiResponse apiResponse = salaryService.save(dto);
        return ResponseEntity.ok(apiResponse);
    }
    @PreAuthorize("hasAuthority('EDIT_SALARY')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable UUID id,@RequestBody SalaryDto dto){
        ApiResponse edit = salaryService.edit(id, dto);
        return ResponseEntity.ok(edit);
    }

    @PreAuthorize("hasAuthority('GET_SALARY')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAll(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
                                @RequestParam(value = "startDate",defaultValue = AppConstants.START_DATE) String startDate,
                                @RequestParam(value = "endDate",defaultValue = AppConstants.END_DATE) String endDate) throws ParseException, IllegalAccessException {
        return ResponseEntity.ok(salaryService.getAll(page,size,startDate,endDate));
    }

    @PreAuthorize("hasAuthority('GET_ONE_SALARY')")
    @GetMapping("/getOne/{id}")
    public HttpEntity<?>getOne(@PathVariable UUID id){
        return ResponseEntity.ok(salaryService.getOne(id));
    }

    @PreAuthorize("hasAuthority('DELETE_SALARY')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>delete(@PathVariable UUID id){
        ApiResponse apiResponse= salaryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
}
