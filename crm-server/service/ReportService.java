package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.Report;
import uz.pdp.srmserver.entitiy.User;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.ReportDto;
import uz.pdp.srmserver.repository.ReportRepository;
import uz.pdp.srmserver.repository.UserRepository;
import uz.pdp.srmserver.utils.CommonUtills;

import java.text.ParseException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;


    @Autowired
    UserRepository userRepository;

    public ApiResponse save(ReportDto reportDto){

        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User approver = userRepository.findByUsername(username);
            Report report=new Report();
            report.setShop(reportDto.getShop());
            report.setApproved(reportDto.isApproved());
            report.setRejects(reportDto.getRejects());
            report.setExpenses(reportDto.getExpenses());
            report.setApprover(approver);
            report.setCloseDebts(reportDto.getCloseDebts());
            report.setSales(reportDto.getSales());
            report.setStatus(reportDto.getStatus());


            reportRepository.save(report);
            return new ApiResponse("Saved!", true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error!",false);
    }

    public ApiResponse edit(UUID id,ReportDto reportDto){


        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User approver = userRepository.findByUsername(username);
            Report report=reportRepository.getById(id);
            report.setApproved(reportDto.isApproved());
            report.setRejects(reportDto.getRejects());
            report.setShop(reportDto.getShop());
            report.setApprover(approver);
            report.setCloseDebts(reportDto.getCloseDebts());
            report.setSales(reportDto.getSales());
            report.setStatus(reportDto.getStatus());
            report.setExpenses(reportDto.getExpenses());



            reportRepository.save(report);
            return new ApiResponse("Saved", true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error!",false);
    }

    public ReportDto getOne(UUID id){
        return getReportDto(reportRepository.getById(id));
    }

    public ApiResponse getAll(int page, int size, String startDate, String endDate, Boolean approved) throws IllegalAccessException, ParseException {
        Page<Report> reportPage = reportRepository.findAllByApprovedAndCreatedAtBetween(approved, CommonUtills.getDateFromString(startDate),CommonUtills.getDateFromString(endDate),CommonUtills.getPageableByCreatedAtDesc(page, size));
        return new ApiResponse(true,"ok",reportPage.getContent().stream().map(this::getReportDto).collect(Collectors.toList()),reportPage.getTotalElements());
    }

    public ApiResponse delete(UUID id){
        try {
            reportRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("error",false);
    }

    public ReportDto getReportDto(Report report){
        ReportDto reportDto=new ReportDto();

        reportDto.setApproved(report.isApproved());
        reportDto.setId(report.getId());
        reportDto.setSales(report.getSales());
        reportDto.setApprover(report.getApprover());
        reportDto.setRejects(report.getRejects());
        reportDto.setShop(report.getShop());
        reportDto.setExpenses(report.getExpenses());
        reportDto.setStatus(report.getStatus());
        reportDto.setCloseDebts(report.getCloseDebts());
        return reportDto;
    }

}
