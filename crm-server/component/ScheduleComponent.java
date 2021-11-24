package uz.pdp.srmserver.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.pdp.srmserver.entitiy.Category;
import uz.pdp.srmserver.repository.CategoryRepository;

import java.util.Date;

@Component
public class ScheduleComponent {
    @Autowired
    CategoryRepository categoryRepository;



//    @Scheduled(fixedRate = 5000, initialDelay = 10000)
//    public void scheduledCategory() throws InterruptedException {
////        Category category = new Category();
////        category.setName("Schedule");
////        category = categoryRepository.save(category);
////        System.out.println(category.getName());
////        System.out.println(new Date());
//    }
}
