package org.example.controller;

import org.example.model.db.Client;
import org.example.model.entity.Album;
import org.springframework.web.client.RestTemplate;


public class AlbumsController {
    RestTemplate rest = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/albums";

    Client client;

    public AlbumsController(Client client){
        this.client = client;
    }

    public void read(){ //GET ALL
        if(client.getPrivilege().isAlbumsAllowed()) {
            String response = rest.getForObject(url, String.class);
            System.out.println(response);
        }
        else System.out.println("ACCESSING ALBUMS IS NOT ALLOWED VIA " + client.getUsername());
    }

    public void read(String id){ //GET
        if(client.getPrivilege().isAlbumsAllowed()) {
        Album album = rest.getForObject(url + "/" + id, Album.class);
        System.out.println(album);
        }
        else System.out.println("ACCESSING ALBUMS IS NOT ALLOWED VIA " + client.getUsername());
    }

    public void postObject(long userId, long id, String title){ //POST
        if(client.getPrivilege().isAlbumsAllowed()) {
        Album post = new Album();
        post.setUserId(userId);
        post.setId(id);
        post.setTitle(title);

        Album postResponse = rest.postForObject(url, post, Album.class);
        System.out.println(postResponse);
        }
        else System.out.println("ACCESSING ALBUMS IS NOT ALLOWED VIA " + client.getUsername());
    }

    public void putObject(String id, long userId, long postId, String title) { //PUT
        if(client.getPrivilege().isAlbumsAllowed()) {
        String paramUrl = url + "/{pId}";
        Album post = new Album();
        post.setUserId(userId);
        post.setId(postId);
        post.setTitle(title);

        rest.put(paramUrl, post, id);
        System.out.println("Resource updated");
        }
        else System.out.println("ACCESSING ALBUMS IS NOT ALLOWED VIA " + client.getUsername());
    }
    public void delObject(String id){ //DELETE
        if(client.getPrivilege().isAlbumsAllowed()) {
        String paramUrl = url + "/{pId}";
        rest.delete(paramUrl, id);
        System.out.println("Object deleted");
        }
        else System.out.println("ACCESSING ALBUMS IS NOT ALLOWED VIA " + client.getUsername());
    }
}
