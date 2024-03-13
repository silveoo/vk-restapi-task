package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.db.Client;
import org.example.model.entity.Address;
import org.example.model.entity.Company;
import org.example.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class UsersController {
    RestTemplate rest = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/users";

    Client client;
    private final AuditController auditController;

    public void setClient(Client client) {
        this.client = client;
    }

    public void read(String id){ //GET
        boolean isAllowed = client.getPrivilege().isUsersAllowed();
        if(isAllowed) {
        auditController.addAuditRecord(client.getId(), isAllowed, "users.read");
        User user = rest.getForObject(url + "/" + id, User.class);
        System.out.println(user);
        }
        else {
            auditController.addAuditRecord(client.getId(), isAllowed, "users.read");
            System.out.println("ACCESSING USERS IS NOT ALLOWED VIA " + client.getUsername());
        }
    }

    public void postObject(long id, String name, String username, String email, Address address, String phone, String website, Company company){ //POST
        boolean isAllowed = client.getPrivilege().isUsersAllowed();
        if(isAllowed) {
        auditController.addAuditRecord(client.getId(), isAllowed, "users.post");
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
        else {
            auditController.addAuditRecord(client.getId(), isAllowed, "users.post");
            System.out.println("ACCESSING USERS IS NOT ALLOWED VIA " + client.getUsername());
        }
    }

    public void putObject(String id, long userId, String name, String username, String email, Address address, String phone, String website, Company company) { //PUT
        boolean isAllowed = client.getPrivilege().isUsersAllowed();
        if(isAllowed) {
        auditController.addAuditRecord(client.getId(), isAllowed, "users.put");
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
        else {
            auditController.addAuditRecord(client.getId(), isAllowed, "users.put");
            System.out.println("ACCESSING USERS IS NOT ALLOWED VIA " + client.getUsername());
        }
    }
    public void delObject(String id){ //DELETE
        boolean isAllowed = client.getPrivilege().isUsersAllowed();
        if(isAllowed) {
        auditController.addAuditRecord(client.getId(), isAllowed, "users.delete");
        String paramUrl = url + "/{pId}";
        rest.delete(paramUrl, id);
        System.out.println("Object deleted");
        }
        else {
            auditController.addAuditRecord(client.getId(), isAllowed, "users.delete");
            System.out.println("ACCESSING USERS IS NOT ALLOWED VIA " + client.getUsername());
        }
    }
}
