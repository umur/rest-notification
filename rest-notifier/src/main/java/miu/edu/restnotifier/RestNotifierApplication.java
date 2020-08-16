package miu.edu.restnotifier;

import miu.edu.restnotifier.controller.ActionController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class RestNotifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestNotifierApplication.class, args);
    }

}

//@Component
//class Runner implements CommandLineRunner{
//
//    private final ActionController actionController;
//
//    public Runner(ActionController actionController) {
//        this.actionController = actionController;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        actionController.notifyServers();
//    }
//}