package uz.pdp.srmserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.TransferDto;
import uz.pdp.srmserver.service.TransferService;
import uz.pdp.srmserver.utils.AppConstants;

import java.text.ParseException;
import java.util.UUID;

@RestController
@RequestMapping("/api/transfer/")
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping("save")
    public HttpEntity<?> save(@RequestBody TransferDto transferDto){
        ApiResponse save = transferService.save(transferDto);
        return ResponseEntity.ok(save);
    }

    @PutMapping("edit/{id}")
    public HttpEntity<?> edit(@PathVariable UUID id, @RequestBody TransferDto transferDto){
        ApiResponse edit = transferService.edit(id, transferDto);
        return ResponseEntity.ok(edit);
    }

    @GetMapping("getOne/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id){
        TransferDto one = transferService.getOne(id);
        return ResponseEntity.ok(one);
    }

    @GetMapping("getAll")
    public HttpEntity<?> getAll(
            @RequestParam(  value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
    @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
    @RequestParam(value = "startDate",defaultValue = AppConstants.START_DATE) String startDate,
    @RequestParam(value = "endDate",defaultValue = AppConstants.END_DATE) String endDate,
    @RequestParam Boolean approved
    ) throws ParseException, IllegalAccessException {
        ApiResponse all = transferService.getAll(page, size, startDate, endDate, approved);
        return ResponseEntity.ok(all);
    }
    @DeleteMapping("delete/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id){
        ApiResponse delete = transferService.delete(id);
        return ResponseEntity.ok(delete);

    }
}
