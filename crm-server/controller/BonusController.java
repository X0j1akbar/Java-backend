package uz.pdp.srmserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.BonusDto;
import uz.pdp.srmserver.service.BonusService;
import uz.pdp.srmserver.utils.AppConstants;

import java.util.UUID;

@RestController
@RequestMapping("/api/bonus")
public class BonusController {

    @Autowired
    BonusService bonusService;

    @PreAuthorize("hasAnyAuthority('ADD_BONUS')")
    @PostMapping("/save")
    public HttpEntity<?> saveOrEdit(@RequestBody BonusDto dto) {
        ApiResponse apiResponse = bonusService.save(dto);
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('ADD_BONUS')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> saveOrEdit(@PathVariable UUID id, @RequestBody BonusDto dto) {
        ApiResponse apiResponse = bonusService.edit(id,dto);
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('GET_BONUS')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAll(
            @RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(value = "startDate",defaultValue = AppConstants.START_DATE) String startDate,
            @RequestParam(value = "endDate",defaultValue = AppConstants.END_DATE) String endDate,
            @RequestParam Boolean approved) throws IllegalAccessException {
        return ResponseEntity.ok(bonusService.getAll(page,size,startDate,endDate,approved));
    }

    @PreAuthorize("hasAuthority('GET_ONE_BONUS')")
    @GetMapping("/getOne/{id}")
    public HttpEntity<?>getOne(@PathVariable UUID id){
        return ResponseEntity.ok(bonusService.getOne(id));
    }

    @PreAuthorize("hasAuthority('DELETE_BONUS')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>delete(@PathVariable UUID id){
        ApiResponse apiResponse= bonusService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

}
