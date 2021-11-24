package uz.pdp.srmserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.GivenSalaryDto;
import uz.pdp.srmserver.payload.ShopKpiDto;
import uz.pdp.srmserver.service.ShopKpiService;
import uz.pdp.srmserver.utils.AppConstants;

import java.util.UUID;

@RestController
@RequestMapping("/api/shopKpi")
public class ShopKpiController {


    @Autowired
    ShopKpiService shopKpiService;

    @PreAuthorize("hasAuthority('ADD_SHOP_KPI')")
    @PostMapping("/save")
    public HttpEntity<?> save (@RequestBody ShopKpiDto dto) {
        ApiResponse apiResponse = shopKpiService.save(dto);
        return ResponseEntity.ok(apiResponse) ;
    }
    @PreAuthorize("hasAuthority('EDIT_SHOP_KPI')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable UUID id,@RequestBody ShopKpiDto dto){
        ApiResponse edit = shopKpiService.edit(id,dto);
        return ResponseEntity.ok(edit);
    }

    @PreAuthorize("hasAuthority('GET_SHOP_KPI')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAll(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
                                @RequestParam(value = "startDate",defaultValue = AppConstants.START_DATE) String startDate,
                                @RequestParam(value = "endDate",defaultValue = AppConstants.END_DATE) String endDate){
        return ResponseEntity.ok(shopKpiService.getAll(size,page,startDate,endDate));
    }

    @PreAuthorize("hasAuthority('GET_ONE_SHOP_KPI')")
    @GetMapping("/getOne/{id}")
    public HttpEntity<?>getOne(@PathVariable UUID id){
        return ResponseEntity.ok(shopKpiService.getOne(id));
    }

    @PreAuthorize("hasAuthority('DELETE_SHOP_KPI')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>delete(@PathVariable UUID id){
        ApiResponse apiResponse= shopKpiService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

}
