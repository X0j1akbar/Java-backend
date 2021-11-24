package uz.pdp.srmserver.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.GivenSalaryDto;
import uz.pdp.srmserver.payload.KpiDto;
import uz.pdp.srmserver.service.KpiService;
import uz.pdp.srmserver.utils.AppConstants;

import java.util.UUID;

@RestController
@RequestMapping("/api/kpi")
public class KpiController {

    @Autowired
    KpiService kpiService;



    @PreAuthorize("hasAuthority('ADD_KPI')")
    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody KpiDto dto) {
        ApiResponse apiResponse = kpiService.save(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201:409).body(apiResponse);
    }

    @SneakyThrows
    @PreAuthorize("hasAuthority('GET_KPI')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAll(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
                                @RequestParam(value = "search",defaultValue = "all")String search ){
        return ResponseEntity.ok(kpiService.getAll(page,size,search));
    }

    @PreAuthorize("hasAuthority('GET_ONE_KPI')")
    @GetMapping("/getOne/{id}")
    public HttpEntity<?>getOne( @PathVariable Integer id){
        return ResponseEntity.ok(kpiService.getOne(id));
    }

    @PreAuthorize("hasAuthority('DELETE_KPI')")
    @GetMapping("/delete/{id}")
    public HttpEntity<?>delete(@PathVariable Integer id){
        ApiResponse apiResponse= kpiService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @GetMapping("/changeActive/{id}")
    public HttpEntity<?> changeActive(@PathVariable Integer id){
        return ResponseEntity.ok(kpiService.changeActive(id));
    }


}


