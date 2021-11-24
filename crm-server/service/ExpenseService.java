package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.srmserver.entitiy.Expense;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.ExpenseDto;
import uz.pdp.srmserver.repository.ExpenseRepository;
import uz.pdp.srmserver.repository.ReportRepository;
import uz.pdp.srmserver.repository.ShopRepository;
import uz.pdp.srmserver.utils.CommonUtills;

import java.text.ParseException;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expense")
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    ShopRepository shopRepository;

    public ApiResponse save(ExpenseDto dto) {

        try {
            Expense expense = new Expense();
            expense.setDescription(dto.getDescription());
            expense.setShop(shopRepository.getById(dto.getShopId()));
            expense.setSum(dto.getSum());
            expense.setPayType(dto.getPayType());
            expense.setDescription(dto.getDescription());
            expense.setReport(reportRepository.getById(dto.getReportId()));

            expenseRepository.save(expense);

            return new ApiResponse("Saved", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("error", true);
    }

    public ApiResponse edit(UUID id, ExpenseDto dto) {
        try {

            Expense expense = expenseRepository.getById(id);

            expense.setReport(reportRepository.getById(dto.getReportId()));
            expense.setShop(shopRepository.getById(dto.getShopId()));
            expense.setSum(dto.getSum());
            expense.setPayType(dto.getPayType());
            expense.setDescription(dto.getDescription());
            expenseRepository.save(expense);

            return new ApiResponse("Edited", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("error", true);
    }

    public Expense getOne(UUID id) {
        return expenseRepository.getById(id);
    }

    public ApiResponse getAll(int page, int size, String startDate, String endDate) throws ParseException, IllegalAccessException {
        Page<Expense> expensePage = expenseRepository.findAllByCreatedAtBetween(CommonUtills.getDateFromString(startDate), CommonUtills.getDateFromString(endDate), CommonUtills.simplePageable(page, size));
        return new ApiResponse(true, "Ok",expensePage.getContent().stream().map(this::getExpenseDto).collect(Collectors.toList()),expensePage.getTotalElements());
    }


    public ApiResponse delete(UUID id){
        try {
            expenseRepository.deleteById(id);
            return new ApiResponse("dleted",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("error",false);
    }

    public ExpenseDto getExpenseDto(Expense expense){
        ExpenseDto expenseDto=new ExpenseDto();
      expenseDto.setId(expense.getId());
      expenseDto.setReport(expenseDto.getReport());
      expenseDto.setDescription(expense.getDescription());
      expenseDto.setPayType(expense.getPayType());
      expenseDto.setShop(expense.getShop());
      expenseDto.setSum(expense.getSum());
      return expenseDto;
    }

}
