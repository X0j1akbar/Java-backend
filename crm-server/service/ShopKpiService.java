package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.GivenSalary;
import uz.pdp.srmserver.entitiy.ShopKpi;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.GivenSalaryDto;
import uz.pdp.srmserver.payload.ShopKpiDto;
import uz.pdp.srmserver.repository.ShopKpiRepository;
import uz.pdp.srmserver.repository.UserRepository;
import uz.pdp.srmserver.utils.CommonUtills;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShopKpiService {

    @Autowired
    ShopKpiRepository shopKpiRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResponse save(ShopKpiDto dto){

        try {
            ShopKpi shopKpi=new ShopKpi();
            shopKpi.setShop(dto.getShop());
            shopKpi.setKpis(dto.getKpis());

            shopKpiRepository.save(shopKpi);

            return new ApiResponse("Saved",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse edit(UUID id, ShopKpiDto dto){

        try {
            ShopKpi shopKpi=shopKpiRepository.getById(id);
            shopKpi.setShop(dto.getShop());
            shopKpi.setKpis(dto.getKpis());

            shopKpiRepository.save(shopKpi);

            return new ApiResponse("Edited",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse getAll(Integer size, Integer page, String startDate, String endDate){
        try {
            Page<ShopKpi> shopKpiPage=shopKpiRepository.findAllByCreatedAtBetween(CommonUtills.getDateFromString(startDate),CommonUtills.getDateFromString(endDate),CommonUtills.getPageableByCreatedAtDesc(page, size));
            return new ApiResponse(true,"ok",shopKpiPage.getContent().stream().map(this::getShopKpiDto).collect(Collectors.toList()),shopKpiPage.getTotalElements());

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ShopKpi getOne(UUID id){
        return shopKpiRepository.getById(id);
    }

    public ApiResponse delete(UUID id){

        try {
           shopKpiRepository.deleteById(id);
            return new ApiResponse("Deleted!",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ShopKpiDto getShopKpiDto(ShopKpi shopKpi){
        ShopKpiDto shopKpiDto=new ShopKpiDto();

        shopKpiDto.setShop(shopKpi.getShop());
        shopKpiDto.setKpis(shopKpi.getKpis());
        shopKpiDto.setId(shopKpi.getId());
        return shopKpiDto;
    }


}
