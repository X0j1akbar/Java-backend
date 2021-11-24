package uz.pdp.srmserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.BonusDto;
import uz.pdp.srmserver.payload.GivenSalaryDto;
import uz.pdp.srmserver.service.GivenSalaryService;
import uz.pdp.srmserver.utils.AppConstants;

import java.util.UUID;

@RestController
@RequestMapping("/api/givenSalary")
public class GivenSalaryController {

    @Autowired
    GivenSalaryService givenSalaryService;


    @PreAuthorize("hasAuthority('ADD_GIVEN_SALARY')")
    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody GivenSalaryDto dto) {
        ApiResponse apiResponse = givenSalaryService.save(dto);
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('EDIT_GIVEN_SALARY')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?>edit(@PathVariable UUID id,@RequestBody GivenSalaryDto dto){
        ApiResponse edit = givenSalaryService.edit(dto, id);
        return ResponseEntity.ok(edit);
    }

    @PreAuthorize("hasAuthority('GET_GIVEN_SALARY')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAll(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
                                @RequestParam(value = "startDate",defaultValue = AppConstants.START_DATE) String startDate,
                                @RequestParam(value = "endDate",defaultValue = AppConstants.END_DATE) String endDate,
                                @RequestParam Boolean approved){
        return ResponseEntity.ok(givenSalaryService.getAll(page,size,startDate,endDate,approved));
    }

    @PreAuthorize("hasAuthority('GET_ONE_GIVEN_SALARY')")
    @GetMapping("/getOne/{id}")
    public HttpEntity<?>getOne( @PathVariable UUID id){
        return ResponseEntity.ok(givenSalaryService.getOne(id));
    }

    @PreAuthorize("hasAuthority('DELETE_GIVEN_SALARY')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>delete(@PathVariable  UUID id){
        ApiResponse apiResponse= givenSalaryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }


}
