package uz.malga.logisticcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.malga.logisticcompany.payload.TirDto;
import uz.malga.logisticcompany.repository.TirRepository;
import uz.malga.logisticcompany.service.TirService;
import uz.malga.logisticcompany.utils.AppConstants;

import java.util.UUID;

@RestController
@RequestMapping("/api/tir")
public class TirController {

    @Autowired
    TirService tirService;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody TirDto dto){
        return ResponseEntity.ok(tirService.save(dto));
    }

    @GetMapping("/allByPageable")
    public HttpEntity<?> allByPageable(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER)Integer page,
                                       @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE)Integer size,
                                       @RequestParam(value = "search",defaultValue = "all") String search,
                                       @RequestParam(value = "active",defaultValue = "true") boolean active,
                                       @RequestParam(value = "companyId") Long companyId
    ) throws IllegalAccessException {
        return ResponseEntity.ok(tirService.allByPageable(page,size,search,active,companyId));
    }

    @GetMapping("/getActive")
    public HttpEntity<?>allActive(@RequestParam(value = "companyId",defaultValue = "0") Long companyId
    ){
        return ResponseEntity.ok(tirService.getAllByActive(companyId));
    }

    @GetMapping("/changeActive/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        return ResponseEntity.ok(tirService.changeActive(id));
    }

    @GetMapping("/delete/{id}")
    public HttpEntity<?>delete(@PathVariable UUID id){
        return ResponseEntity.ok(tirService.delete(id));
    }


}
