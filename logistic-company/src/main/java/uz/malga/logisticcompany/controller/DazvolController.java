package uz.malga.logisticcompany.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.malga.logisticcompany.payload.ApiResponse;
import uz.malga.logisticcompany.payload.DazvolDto;
import uz.malga.logisticcompany.service.DazvolService;
import uz.malga.logisticcompany.utils.AppConstants;

import java.util.UUID;

@RestController
@RequestMapping("/api/dazvol")
public class DazvolController {

    @Autowired
    DazvolService dazvolService;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody DazvolDto dto){
        ApiResponse apiResponse = dazvolService.saveOrEdit(dto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/allByPageable")
    public HttpEntity<?> allByPageable(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER)Integer page,
                                       @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE)Integer size,
                                       @RequestParam(value = "search",defaultValue = "all") String search,
                                       @RequestParam(value = "active",defaultValue = "true") boolean active,
    @RequestParam(value = "companyId") Long companyId
    ) throws IllegalAccessException {
        return ResponseEntity.ok(dazvolService.allByPageable(page,size,search,active,companyId));
    }

    @GetMapping("/getActive")
    public HttpEntity<?>allActive(@RequestParam(value = "companyId",defaultValue = "0") Long companyId,
                                  @RequestParam(value = "dazvolNameId",defaultValue = "0") Long dazvolNameId
                                  ){
        return ResponseEntity.ok(dazvolService.getAllByActive(companyId,dazvolNameId));
    }

    @GetMapping("/getSum")
    public HttpEntity<?>getSum(@RequestParam(value = "companyId",defaultValue = "0")Long companyId){
        return ResponseEntity.ok(dazvolService.sum(companyId));
    }

    @GetMapping("/getDazvolName")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(dazvolService.getAllDazvolsName());
    }

    @GetMapping("/getCompany")
    public HttpEntity<?> getCompany(){
        return ResponseEntity.ok(dazvolService.getAllCompany());
    }

    @GetMapping("/changeActive/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        return ResponseEntity.ok(dazvolService.changeActive(id));
    }

    @GetMapping("/delete/{id}")
    public HttpEntity<?>delete(@PathVariable UUID id){
        return ResponseEntity.ok(dazvolService.delete(id));
    }
}
