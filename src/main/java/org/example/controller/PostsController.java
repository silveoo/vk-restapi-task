package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.db.Client;
import org.example.model.entity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class PostsController {
    RestTemplate rest = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/posts";

    private Client client;
    private final AuditController auditController;

    public void setClient(Client client){
        this.client = client;
    }

    public void read(String id){ //GET
        boolean isAllowed = client.getPrivilege().isPostsAllowed();
        if(isAllowed) {
        auditController.addAuditRecord(client.getId(), isAllowed, "posts.read");
        Post post = rest.getForObject(url + "/" + id, Post.class);
        System.out.println(post);
        }
        else {
            auditController.addAuditRecord(client.getId(), isAllowed, "posts.read");
            System.out.println("ACCESSING POSTS IS NOT ALLOWED VIA " + client.getUsername());
        }
    }

    public void postObject(long userId, long id, String title, String body){ //POST
        boolean isAllowed = client.getPrivilege().isPostsAllowed();
        if(isAllowed) {
        auditController.addAuditRecord(client.getId(), isAllowed, "posts.post");
        Post post = new Post();
        post.setUserId(userId);
        post.setId(id);
        post.setTitle(title);
        post.setBody(body);

        Post postResponse = rest.postForObject(url, post, Post.class);
        System.out.println(postResponse);
        }
        else {
            auditController.addAuditRecord(client.getId(), isAllowed, "posts.post");
            System.out.println("ACCESSING POSTS IS NOT ALLOWED VIA " + client.getUsername());
        }
    }

     public void putObject(String id, long userId, long postId, String title, String body) { //PUT
         boolean isAllowed = client.getPrivilege().isPostsAllowed();
         if(isAllowed) {
         auditController.addAuditRecord(client.getId(), isAllowed, "posts.put");
         String paramUrl = url + "/{pId}";
         Post post = new Post();
         post.setUserId(userId);
         post.setId(postId);
         post.setTitle(title);
         post.setBody(body);

         rest.put(paramUrl, post, id);
         System.out.println("Resource updated");
         }
         else {
             auditController.addAuditRecord(client.getId(), isAllowed, "posts.put");
             System.out.println("ACCESSING POSTS IS NOT ALLOWED VIA " + client.getUsername());
         }
     }
     public void delObject(String id){ //DELETE
         boolean isAllowed = client.getPrivilege().isPostsAllowed();
         if(isAllowed) {
         auditController.addAuditRecord(client.getId(), isAllowed, "posts.delete");
        String paramUrl = url + "/{pId}";
        rest.delete(paramUrl, id);
         System.out.println("Object deleted");
        }
        else {
             auditController.addAuditRecord(client.getId(), isAllowed, "posts.delete");
            System.out.println("ACCESSING POSTS IS NOT ALLOWED VIA " + client.getUsername());
         }
     }
}
