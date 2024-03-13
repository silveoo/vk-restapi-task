package org.example.controller;

import org.example.model.db.Client;
import org.example.model.entity.Post;
import org.springframework.web.client.RestTemplate;

public class PostsController {
    RestTemplate rest = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/posts";

    Client client;

    public PostsController(Client client){
        this.client = client;
    }

    public void read(){ //GET ALL
        if(client.getPrivilege().isPostsAllowed()) {
            String response = rest.getForObject(url, String.class);
            System.out.println(response);
        }
        else System.out.println("ACCESSING POSTS IS NOT ALLOWED VIA " + client.getUsername());
    }

    public void read(String id){ //GET
        if(client.getPrivilege().isPostsAllowed()) {
        Post post = rest.getForObject(url + "/" + id, Post.class);
        System.out.println(post);
        }
        else System.out.println("ACCESSING POSTS IS NOT ALLOWED VIA " + client.getUsername());
    }

    public void postObject(long userId, long id, String title, String body){ //POST
        if(client.getPrivilege().isPostsAllowed()) {
        Post post = new Post();
        post.setUserId(userId);
        post.setId(id);
        post.setTitle(title);
        post.setBody(body);

        Post postResponse = rest.postForObject(url, post, Post.class);
        System.out.println(postResponse);
        }
        else System.out.println("ACCESSING POSTS IS NOT ALLOWED VIA " + client.getUsername());
    }

     public void putObject(String id, long userId, long postId, String title, String body) { //PUT
         if(client.getPrivilege().isPostsAllowed()) {
         String paramUrl = url + "/{pId}";
         Post post = new Post();
         post.setUserId(userId);
         post.setId(postId);
         post.setTitle(title);
         post.setBody(body);

         rest.put(paramUrl, post, id);
         System.out.println("Resource updated");
         }
         else System.out.println("ACCESSING POSTS IS NOT ALLOWED VIA " + client.getUsername());
     }
     public void delObject(String id){ //DELETE
        if(client.getPrivilege().isPostsAllowed()) {
        String paramUrl = url + "/{pId}";
        rest.delete(paramUrl, id);
         System.out.println("Object deleted");
        }
        else System.out.println("ACCESSING POSTS IS NOT ALLOWED VIA " + client.getUsername());
     }
}
