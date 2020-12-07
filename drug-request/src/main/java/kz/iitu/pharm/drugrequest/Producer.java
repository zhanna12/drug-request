package kz.iitu.pharm.drugrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final String TOPIC = "drug_requests";

    @Autowired
    private KafkaTemplate<String, DrugRequest> kafkaTemplate;

    public String drugRequestNotify(DrugRequest drugRequest) {
        System.out.println(String.format("#### -> Producing drug request to notification service -> %s", drugRequest));
        this.kafkaTemplate.send(TOPIC, drugRequest);
        return "Successfully";
    }
}
