package org.example.controller;




import lombok.RequiredArgsConstructor;
import org.example.model.db.Client;
import org.example.model.entity.Album;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;



@Controller
@RequiredArgsConstructor
public class AlbumsController {
    RestTemplate rest = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/albums";
    private Client client;
    private final AuditController auditController;

    public void setClient(Client client){
        this.client = client;
    }

    public void read(String id){ //GET
        boolean isAllowed = client.getPrivilege().isAlbumsAllowed();
        if(isAllowed) {
        Album album = rest.getForObject(url + "/" + id, Album.class);
        System.out.println(album);
        auditController.addAuditRecord(client.getId(), isAllowed, "albums.read");
        }
        else {
            System.out.println("ACCESSING ALBUMS IS NOT ALLOWED VIA " + client.getUsername());
            auditController.addAuditRecord(client.getId(), isAllowed, "albums.read");
        }
    }

    public void postObject(long userId, long id, String title){ //POST
        boolean isAllowed = client.getPrivilege().isAlbumsAllowed();
        if(isAllowed) {
        Album post = new Album();
        post.setUserId(userId);
        post.setId(id);
        post.setTitle(title);

        Album postResponse = rest.postForObject(url, post, Album.class);
        System.out.println(postResponse);
        auditController.addAuditRecord(client.getId(), isAllowed, "albums.post");
        }
        else {
            System.out.println("ACCESSING ALBUMS IS NOT ALLOWED VIA " + client.getUsername());
            auditController.addAuditRecord(client.getId(), isAllowed, "albums.post");
        }
    }

    public void putObject(String id, long userId, long postId, String title) { //PUT
        boolean isAllowed = client.getPrivilege().isAlbumsAllowed();
        if(isAllowed) {
        String paramUrl = url + "/{pId}";
        Album post = new Album();
        post.setUserId(userId);
        post.setId(postId);
        post.setTitle(title);

        rest.put(paramUrl, post, id);
        System.out.println("Resource updated");
        auditController.addAuditRecord(client.getId(), isAllowed, "albums.put");
        }
        else {
            System.out.println("ACCESSING ALBUMS IS NOT ALLOWED VIA " + client.getUsername());
            auditController.addAuditRecord(client.getId(), isAllowed, "albums.put");
        }
    }
    public void delObject(String id){ //DELETE
        boolean isAllowed = client.getPrivilege().isAlbumsAllowed();
        if(isAllowed) {
        String paramUrl = url + "/{pId}";
        rest.delete(paramUrl, id);
        System.out.println("Object deleted");
        auditController.addAuditRecord(client.getId(), isAllowed, "albums.delete");
        }
        else {
            System.out.println("ACCESSING ALBUMS IS NOT ALLOWED VIA " + client.getUsername());
            auditController.addAuditRecord(client.getId(), isAllowed, "albums.delete");
        }
    }
}
