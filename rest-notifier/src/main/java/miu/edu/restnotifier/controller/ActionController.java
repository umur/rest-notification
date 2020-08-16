package miu.edu.restnotifier.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.*;
import miu.edu.restnotifier.servers.ServerList;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/actions")
public class ActionController {

    private final ServerList serverList;

    public ActionController(ServerList serverList) {
        this.serverList = serverList;
    }

    @GetMapping
    public String notifyServers() {
        final List<String> servers = serverList.getServerList();
        List<Thread> threadList = new ArrayList<>();
        List<Integer> roundTrips = new ArrayList<>();

        //prepare threads
        servers.stream().forEach(server -> {
            Runnable r = () -> {
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    HttpEntity<DataDto> entity = new HttpEntity<>(new DataDto("umur"));
                    long start = System.currentTimeMillis();
                    restTemplate.postForObject(server, entity, DataDto.class);
                    roundTrips.add((int) (System.currentTimeMillis() - start));
                } catch (ResourceAccessException ex) {
                    System.out.println("exception occurred");
                }
            };
            Thread t = new Thread(r);
            threadList.add(t);
        });

        //start threads
        threadList.stream().forEach(l -> {
            l.start();
            try {
                l.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //calculate average round trips

        double avg = roundTrips.stream().mapToInt((x) -> x).summaryStatistics().getAverage();
        System.out.println(avg);

        return "notifying . . .";
    }

}

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
class DataDto {
    private String data;
}
