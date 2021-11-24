package uz.pdp.srmserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.entitiy.Defect;
import uz.pdp.srmserver.payload.request.DefectRequest;
import uz.pdp.srmserver.payload.response.PageableResponse;
import uz.pdp.srmserver.service.DefectService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/defect/")
@AllArgsConstructor
public class DefectController {

    private final DefectService defectService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_DEFECT')")
    Defect save(@Valid @RequestBody DefectRequest request) {
        return defectService.save(request);
    }

    @GetMapping("all")
    @PreAuthorize("hasAuthority('GET_DEFECT')")
    PageableResponse<Defect> getAll(@RequestParam(defaultValue = "20") Integer size, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "false") boolean desc) {
        return defectService.getAll(PageRequest.of(page, size, Sort.by(desc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy != null ? sortBy : "id")));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('EDIT_DEFECT')")
    Defect update(@Valid @RequestBody DefectRequest request, @PathVariable UUID id) {
        return defectService.updateById(id, request);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('GET_DEFECT')")
    Defect getOne(@PathVariable UUID id) {
        return defectService.getByID(id);
    }
}
