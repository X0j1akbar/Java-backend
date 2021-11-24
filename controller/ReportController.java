package uz.pdp.srmserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.ReportDto;
import uz.pdp.srmserver.service.ReportService;
import uz.pdp.srmserver.utils.AppConstants;

import java.text.ParseException;
import java.util.UUID;

@RestController
@RequestMapping("/api/report/")
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("save")
    public HttpEntity<?>save(@RequestBody ReportDto reportDto){
        ApiResponse save = reportService.save(reportDto);
        return ResponseEntity.ok(save);
    }

    @PutMapping("{id}")
    public HttpEntity<?>edit(@PathVariable UUID id,@RequestBody ReportDto reportDto){
        ApiResponse edit = reportService.edit(id, reportDto);
        return ResponseEntity.ok(edit);
    }

    @GetMapping("all")
    public HttpEntity<?>getAll(
            @RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(value = "startDate",defaultValue = AppConstants.START_DATE) String startDate,
            @RequestParam(value = "endDate",defaultValue = AppConstants.END_DATE) String endDate,
            @RequestParam Boolean approved
    ) throws ParseException, IllegalAccessException {
        ApiResponse all = reportService.getAll(page, size, startDate, endDate, approved);
        return ResponseEntity.ok(all);
    }

    @GetMapping("{id}")
    public HttpEntity<?>getOne(@PathVariable UUID id){
        return ResponseEntity.ok(reportService.getOne(id));
    }

    @DeleteMapping("{id}")
    public HttpEntity<?>delete(@PathVariable UUID id){
        ApiResponse delete = reportService.delete(id);
        return ResponseEntity.ok(delete);
    }

}
