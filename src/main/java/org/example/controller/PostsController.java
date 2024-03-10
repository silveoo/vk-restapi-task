package org.example.controller;

import org.example.model.entity.Post;
import org.springframework.web.client.RestTemplate;

public class PostsController {
    RestTemplate rest = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/posts";

    public void read(){ //GET ALL
        String response = rest.getForObject(url, String.class);
        System.out.println(response);
    }

    public void read(String id){ //GET
        Post post = rest.getForObject(url + "/" + id, Post.class);
        System.out.println(post);
    }

    public void postObject(long userId, long id, String title, String body){ //POST
        Post post = new Post();
        post.setUserId(userId);
        post.setId(id);
        post.setTitle(title);
        post.setBody(body);

        Post postResponse = rest.postForObject(url, post, Post.class);
        System.out.println(postResponse);
    }

     public void putObject(String id, long userId, long postId, String title, String body) { //PUT
        String paramUrl = url + "/{pId}";
         Post post = new Post();
         post.setUserId(userId);
         post.setId(postId);
         post.setTitle(title);
         post.setBody(body);

         rest.put(paramUrl, post, id);
         System.out.println("Resource updated");
     }
     public void delObject(String id){ //DELETE
        String paramUrl = url + "/{pId}";
        rest.delete(paramUrl, id);
         System.out.println("Object deleted");
     }
}
