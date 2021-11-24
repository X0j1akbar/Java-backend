package uz.pdp.srmserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.entitiy.Sale;
import uz.pdp.srmserver.payload.request.SaleRequest;
import uz.pdp.srmserver.payload.response.PageableResponse;
import uz.pdp.srmserver.service.SaleService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/sale/")
@AllArgsConstructor
public class SaleController {

    private final SaleService saleService;


    @PostMapping
    @PreAuthorize("hasAuthority('ADD_SALE')")
    Sale save(@Valid @RequestBody SaleRequest request) {
        return saleService.save(request);
    }

    @GetMapping("all")
    @PreAuthorize("hasAuthority('GET_SALE')")
    PageableResponse<Sale> getAll(@RequestParam(defaultValue = "20") Integer size, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "false") boolean desc) {
        return saleService.getAll(PageRequest.of(page, size, Sort.by(desc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy != null ? sortBy : "id")));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('EDIT_SALE')")
    Sale update(@Valid @RequestBody SaleRequest request, @PathVariable UUID id) {
        return saleService.updateById(id, request);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('GET_SALE')")
    Sale getOne(@PathVariable UUID id) {
        return saleService.getByID(id);
    }
}
