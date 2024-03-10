package org.example;


import org.example.auth.Auth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
        Auth a = new Auth();
        a.auth();
        /*
        System.out.println("<>===================Testing POST_CONTROLLER===================<>");
        PostsController pController = new PostsController();
        pController.postObject(10, 101, "dota 2", "dota 2 is the best game in history");
        pController.read("10");
        pController.putObject("1", 1, 1, "hello", "poka");
        pController.delObject("1");

        System.out.println("<>===================Testing ALBUMS_CONTROLLER===================<>");
        AlbumsController aController = new AlbumsController();
        aController.read("1");
        aController.postObject(1, 11, "me and my dog on a beach");
        aController.putObject("1", 1, 1, "changed");
        aController.delObject("1");

        System.out.println("<>===================Testing USERS_CONTROLLER===================<>");
        UsersController usersController = new UsersController();
        usersController.read("1");
        usersController.postObject(
                11, "Alesha", "Alex222", "alex222@gmail.com",
                new Address("Big Street", "Suite 542", "Saratov", "34324872", new Geo("-37", "42")),
                "85717631", "alex.net", new Company("Alex's company", "kdkd", "??????")
        );
        usersController.putObject(
                "1", 1, "Alesha", "Alex222", "alex222@gmail.com",
                new Address("Big Street", "Suite 542", "Saratov", "34324872", new Geo("-37", "42")),
                "85717631", "alex.net", new Company("Alex's company", "kdkd", "??????")
        );
        usersController.delObject("1");
         */
    }
}