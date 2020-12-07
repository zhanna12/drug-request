package kz.iitu.pharm.drugrequest;

import kz.iitu.pharm.drugrequest.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/drug/request")
public class DrugRequestController {

    private final Producer producer;
    private DrugInformationService drugInformationService;

    @Autowired
    public DrugRequestController(Producer producer, DrugInformationService drugInformationService) {
        this.producer = producer;
        this.drugInformationService = drugInformationService;
    }

    @GetMapping
    public String sendMessageToKafkaTopic2(@RequestParam("userId") Long userId,
                                           @RequestParam("drugId") Long drugId) {

        DrugRequest drugRequest = new DrugRequest(userId, drugInformationService.getDrugById(drugId));
        this.producer.drugRequestNotify(drugRequest);
        return "Your request sent successful!";
    }
}
