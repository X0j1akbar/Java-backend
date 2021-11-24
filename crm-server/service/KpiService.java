package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.Kpi;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.KpiDto;
import uz.pdp.srmserver.repository.KpiRepository;
import uz.pdp.srmserver.repository.RoleRepository;
import uz.pdp.srmserver.repository.UserRepository;
import uz.pdp.srmserver.utils.CommonUtills;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KpiService {

    @Autowired
    KpiRepository kpiRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse save(KpiDto dto){

        try {
            Kpi kpi=new Kpi();
            kpi.setDescription(dto.getDescription());
            kpi.setName(dto.getName());
            kpi.setActive(dto.isActive());
            kpi.setRole(roleRepository.getById(dto.getRoleId()));
            kpi.setPercent(dto.getPercent());
            kpi.setMinSum(dto.getMinSum());
            kpi.setMaxSum(dto.getMaxSum());

            kpiRepository.save(kpi);

            return new ApiResponse("Saved",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }



    public ApiResponse getAll(Integer page, Integer size, String search) throws IllegalAccessException {
        List<Kpi> kpis = new ArrayList<>();
        long totalElements = 0;
        if (!search.equals("all")) {
            kpis = kpiRepository.findAllByNameContainingIgnoringCase(search);
        } else {
            if (size > 0) {
                Page<Kpi> kpiPage = kpiRepository.findAll(CommonUtills.getPageableByIdDesc(page, size));
                kpis = kpiPage.getContent();
                totalElements = kpiPage.getTotalElements();
            } else {
                kpis = kpiRepository.findAll();
            }
        }
        return new ApiResponse(true, "CategoryPage", kpis.stream().map(this::getKpiDto).collect(Collectors.toList()), totalElements);

    }
    public Kpi getOne(Integer id){
        return kpiRepository.getById(id);
    }

    public ApiResponse delete(Integer id){

        try {
            Kpi kpi=kpiRepository.getById(id);


            kpiRepository.delete(kpi);
            return new ApiResponse("Deleted!",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }
    public ApiResponse changeActive(Integer id) {
        Kpi byId = kpiRepository.getById(id);
        byId.setActive(!byId.isActive());
        kpiRepository.save(byId);
        return new ApiResponse(byId.isActive() ? "Activated" : "Blocked", true);
    }


    public KpiDto getKpiDto(Kpi kpi){
        KpiDto kpiDto=new KpiDto();
        kpiDto.setActive(kpi.isActive());
        kpiDto.setId(kpi.getId());
        kpiDto.setDescription(kpi.getDescription());
        kpiDto.setMaxSum(kpi.getMaxSum());
        kpiDto.setMinSum(kpi.getMinSum());
        kpiDto.setName(kpi.getName());
        kpiDto.setPercent(kpi.getPercent());
        kpiDto.setRole(RoleService.getRoleDto(kpi.getRole()));
        return kpiDto;
    }


}
