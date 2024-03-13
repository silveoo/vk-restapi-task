package org.example.controller;

import org.example.model.db.Client;
import org.example.model.entity.Address;
import org.example.model.entity.Company;
import org.example.model.entity.User;
import org.springframework.web.client.RestTemplate;

public class UsersController {
    RestTemplate rest = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/users";

    Client client;

    public UsersController(Client client){
        this.client = client;
    }

    public void read(){ //GET ALL
        if(client.getPrivilege().isUsersAllowed()) {
        String response = rest.getForObject(url, String.class);
        System.out.println(response);
        }
        else System.out.println("ACCESSING USERS IS NOT ALLOWED VIA " + client.getUsername());
    }

    public void read(String id){ //GET
        if(client.getPrivilege().isUsersAllowed()) {
        User user = rest.getForObject(url + "/" + id, User.class);
        System.out.println(user);
        }
        else System.out.println("ACCESSING USERS IS NOT ALLOWED VIA " + client.getUsername());
    }

    public void postObject(long id, String name, String username, String email, Address address, String phone, String website, Company company){ //POST
        if(client.getPrivilege().isUsersAllowed()) {
        User post = new User();
        post.setId(id);
        post.setName(name);
        post.setUsername(username);
        post.setEmail(email);
        post.setAddress(address);
        post.setPhone(phone);
        post.setWebsite(website);
        post.setCompany(company);

        User postResponse = rest.postForObject(url, post, User.class);
        System.out.println(postResponse);
        }
        else System.out.println("ACCESSING USERS IS NOT ALLOWED VIA " + client.getUsername());
    }

    public void putObject(String id, long userId, String name, String username, String email, Address address, String phone, String website, Company company) { //PUT
        if(client.getPrivilege().isUsersAllowed()) {
        String paramUrl = url + "/{pId}";
        User user = new User();
        user.setId(userId);
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);
        user.setWebsite(website);
        user.setCompany(company);

        rest.put(paramUrl, user, id);
        System.out.println("Resource updated");
        }
        else System.out.println("ACCESSING USERS IS NOT ALLOWED VIA " + client.getUsername());
    }
    public void delObject(String id){ //DELETE
        if(client.getPrivilege().isUsersAllowed()) {
        String paramUrl = url + "/{pId}";
        rest.delete(paramUrl, id);
        System.out.println("Object deleted");
        }
        else System.out.println("ACCESSING USERS IS NOT ALLOWED VIA " + client.getUsername());
    }
}
