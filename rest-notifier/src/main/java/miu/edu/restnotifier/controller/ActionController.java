package miu.edu.restnotifier.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.*;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/actions")
public class ActionController {

    private static final String server2= "http://localhost:8081/api/actions";

    @GetMapping
    public String notifyServers(){
        System.out.println("notifying");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DataDto> entity = new HttpEntity<>(new DataDto("umur"));
        restTemplate.postForObject(server2, entity, DataDto.class);

        return "notifying . . .";
    }

}
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
class DataDto{
    private String data;
}
