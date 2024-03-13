package org.example.auth;

import org.example.controller.AlbumsController;
import org.example.controller.PostsController;
import org.example.controller.UsersController;
import org.example.model.db.Client;
import org.example.model.entity.Address;
import org.example.model.entity.Company;
import org.example.model.entity.Geo;

public class Auth {
    Client client;

    public Auth(Client client){
        this.client = client;
    }

    public void test(){
        System.out.println("<>===================Testing POST_CONTROLLER===================<>");
        PostsController pController = new PostsController(client);
        pController.postObject(10, 101, "dota 2", "dota 2 is the best game in history");
        pController.read("10");
        pController.putObject("1", 1, 1, "hello", "poka");
        pController.delObject("1");

        System.out.println("<>===================Testing USERS_CONTROLLER===================<>");
        UsersController usersController = new UsersController(client);
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

        System.out.println("<>===================Testing ALBUMS_CONTROLLER===================<>");
        AlbumsController aController = new AlbumsController(client);
        aController.read("1");
        aController.postObject(1, 11, "me and my dog on a beach");
        aController.putObject("1", 1, 1, "changed");
        aController.delObject("1");
    }
}
