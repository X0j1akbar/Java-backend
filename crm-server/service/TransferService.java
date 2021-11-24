package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.Transfer;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.TransferDto;
import uz.pdp.srmserver.repository.TransferRepository;
import uz.pdp.srmserver.utils.CommonUtills;

import java.text.ParseException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;


    public ApiResponse save(TransferDto transferDto){

        try {
            Transfer transfer=new Transfer();

            transfer.setAgentName(transferDto.getAgentName());
            transfer.setApproved(transferDto.isApproved());
            transfer.setAgentPhoneNumber(transferDto.getAgentPhoneNumber());
            transfer.setFromWarehouse(transferDto.getFromWarehouse());
            transfer.setSupplier(transferDto.getSupplier());
            transfer.setProductWithAmounts(transferDto.getProductWithAmounts());
            transfer.setToWarehouse(transferDto.getToWarehouse());

            transferRepository.save(transfer);

            return new ApiResponse("Saved",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("error",false);
    }

    public ApiResponse edit(UUID id,TransferDto transferDto){

        try {
            Transfer transfer=transferRepository.getById(id);

            transfer.setApproved(transferDto.isApproved());
            transfer.setAgentPhoneNumber(transferDto.getAgentPhoneNumber());
            transfer.setFromWarehouse(transferDto.getFromWarehouse());
            transfer.setSupplier(transferDto.getSupplier());
            transfer.setProductWithAmounts(transferDto.getProductWithAmounts());
            transfer.setToWarehouse(transferDto.getToWarehouse());
            transfer.setAgentName(transferDto.getAgentName());

            transferRepository.save(transfer);


            return new ApiResponse("edited", true);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("error",false);
    }


    public TransferDto getOne(UUID id){
        return getTransferDto(transferRepository.getById(id));
    }

    public ApiResponse getAll(Integer page, Integer size, String startDate, String endDate,Boolean approved) throws ParseException, IllegalAccessException {
        Page<Transfer> transferPage = transferRepository.findAllByApprovedAndCreatedAtBetween(approved,CommonUtills.getDateFromString(startDate), CommonUtills.getDateFromString(endDate), CommonUtills.getPageableByCreatedAtDesc(page, size));
        return new  ApiResponse(true , "ok",transferPage.getContent().stream().map(this::getTransferDto).collect(Collectors.toList()),transferPage.getTotalElements());

    }

    public ApiResponse delete(UUID id){
        try {
            transferRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ApiResponse("error",false);

    }




    public TransferDto getTransferDto(Transfer transfer){
        TransferDto dto=new TransferDto();

        dto.setAgentName(transfer.getAgentName());
        dto.setApproved(transfer.isApproved());
        dto.setId(transfer.getId());
        dto.setFromWarehouse(transfer.getFromWarehouse());
        dto.setSupplier(transfer.getSupplier());
        dto.setAgentPhoneNumber(transfer.getAgentPhoneNumber());
        dto.setToWarehouse(transfer.getToWarehouse());
        dto.setProductWithAmounts(transfer.getProductWithAmounts());

        return dto;
    }

}
