package uz.malga.logisticcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.malga.logisticcompany.payload.DazvolSaleDto;
import uz.malga.logisticcompany.payload.TirSaleDto;
import uz.malga.logisticcompany.service.DazvolSaleService;
import uz.malga.logisticcompany.service.TirSaleService;
import uz.malga.logisticcompany.utils.AppConstants;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tirSale")
public class TirSaleController {

    @Autowired
    TirSaleService tirSaleService;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody TirSaleDto dto){
        return ResponseEntity.ok(tirSaleService.saveOrEdit(dto));
    }

    @GetMapping("/allByPageable")
    public HttpEntity<?> allByPageable(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER)Integer page,
                                       @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE)Integer size,
                                       @RequestParam(value = "search",defaultValue = "all") String search,
                                       @RequestParam(value = "active",defaultValue = "true") boolean active,
                                       @RequestParam(value = "companyId") Long companyId
    ) throws IllegalAccessException {
        return ResponseEntity.ok(tirSaleService.allByPageable(page,size,search,companyId));
    }

    @GetMapping("/delete")
    public HttpEntity<?>delete(@RequestParam(value = "ids",defaultValue = "0") List<UUID> id){
        return ResponseEntity.ok(tirSaleService.delete(id));
    }
}
