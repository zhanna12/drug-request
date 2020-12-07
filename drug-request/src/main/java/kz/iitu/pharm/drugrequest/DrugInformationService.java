package kz.iitu.pharm.drugrequest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.util.Base64;

@Service
public class DrugInformationService {
    @Autowired
    private RestTemplate restTemplate;


    public Drug getDrugById(Long id) {

        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.getEncoder().encodeToString(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://drug-service/drugs/id/" + id,
                HttpMethod.GET, entity, Drug.class).getBody();
    }

}
