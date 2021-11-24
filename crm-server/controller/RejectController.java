package uz.pdp.srmserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.entitiy.Reject;
import uz.pdp.srmserver.payload.request.RejectRequest;
import uz.pdp.srmserver.payload.response.PageableResponse;
import uz.pdp.srmserver.service.RejectService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/reject/")
@AllArgsConstructor
public class RejectController {

    private final RejectService rejectService;
    

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('EDIT_REJECT')")
    Reject update(@Valid @RequestBody RejectRequest request, @PathVariable UUID id) {
        return rejectService.updateById(id, request);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_REJECT')")
    Reject save(@Valid @RequestBody RejectRequest request) {
        return rejectService.save(request);
    }

    @GetMapping("all")
    @PreAuthorize("hasAuthority('GET_REJECT')")
    PageableResponse<Reject> getAll(@RequestParam(defaultValue = "20") Integer size, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "false") boolean desc) {
        return rejectService.getAll(PageRequest.of(page, size, Sort.by(desc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy != null ? sortBy : "id")));
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('APPROVED_REJECT')")
    Reject update(@RequestParam(defaultValue = "false") Boolean approved, @PathVariable UUID id) {
        return rejectService.setApproved(id, approved);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('GET_REJECT')")
    Reject getOne(@PathVariable UUID id) {
        return rejectService.getByID(id);
    }
}
