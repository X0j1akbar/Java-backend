package uz.pdp.srmserver.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.srmserver.entitiy.Report;
import uz.pdp.srmserver.entitiy.Shop;
import uz.pdp.srmserver.entitiy.enums.ReportStatus;
import uz.pdp.srmserver.repository.ReportRepository;

@Component
@AllArgsConstructor
public class DataGetterUtils {

    private final ReportRepository reportRepository;

    public Report getCurrentReport(Shop shop) {
        Report report = reportRepository.findByShopAndStatus(shop, ReportStatus.IN_PROGRESS).orElse(null);

        if (report == null) {
            report = new Report();
            report.setShop(shop);
            report = reportRepository.save(report);
        }

        return report;
    }
}
