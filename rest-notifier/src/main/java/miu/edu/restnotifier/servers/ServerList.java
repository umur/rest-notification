package miu.edu.restnotifier.servers;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class ServerList {

    private List<String> serverList = new ArrayList<>();

    public ServerList(){
        serverList.add("http://localhost:8081/api/actions");
    }


}
