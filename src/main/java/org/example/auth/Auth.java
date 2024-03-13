package org.example.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.controller.AlbumsController;
import org.example.controller.PostsController;
import org.example.controller.UsersController;
import org.example.model.db.Client;
import org.example.model.entity.Address;
import org.example.model.entity.Company;
import org.example.model.entity.Geo;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class Auth {
    private final AlbumsController albumsController;
    private final Client client;
    private final PostsController postsController;
    private final UsersController usersController;
    public void test(Client client){
        System.out.println("<>===================Testing POST_CONTROLLER===================<>");
        postsController.setClient(client);
        postsController.postObject(10, 101, "dota 2", "dota 2 is the best game in history");
        postsController.read("10");
        postsController.putObject("1", 1, 1, "hello", "poka");
        postsController.delObject("1");

        System.out.println("<>===================Testing USERS_CONTROLLER===================<>");
        usersController.setClient(client);
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
        albumsController.setClient(client);
        albumsController.read("1");
        albumsController.postObject(1, 11, "me and my dog on a beach");
        albumsController.putObject("1", 1, 1, "changed");
        albumsController.delObject("1");
    }
}
