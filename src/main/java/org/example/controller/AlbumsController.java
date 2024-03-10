package org.example.controller;

import org.example.model.entity.Album;
import org.springframework.web.client.RestTemplate;

public class AlbumsController {
    RestTemplate rest = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/albums";

    public void read(){ //GET ALL
        String response = rest.getForObject(url, String.class);
        System.out.println(response);
    }

    public void read(String id){ //GET
        Album album = rest.getForObject(url + "/" + id, Album.class);
        System.out.println(album);
    }

    public void postObject(long userId, long id, String title){ //POST
        Album post = new Album();
        post.setUserId(userId);
        post.setId(id);
        post.setTitle(title);

        Album postResponse = rest.postForObject(url, post, Album.class);
        System.out.println(postResponse);
    }

    public void putObject(String id, long userId, long postId, String title) { //PUT
        String paramUrl = url + "/{pId}";
        Album post = new Album();
        post.setUserId(userId);
        post.setId(postId);
        post.setTitle(title);

        rest.put(paramUrl, post, id);
        System.out.println("Resource updated");
    }
    public void delObject(String id){ //DELETE
        String paramUrl = url + "/{pId}";
        rest.delete(paramUrl, id);
        System.out.println("Object deleted");
    }
}
