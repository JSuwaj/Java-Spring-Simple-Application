package lab3.galerie.galleries.event.repository;

import lab3.galerie.galleries.entity.ArtGallery;
import lab3.galerie.galleries.event.dto.CreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class EventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public EventRepository(@Value("${artGalleries.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(ArtGallery artGallery) {
        restTemplate.delete("/artGalleries/{adress}", artGallery.getAdress());
    }

    public void create(ArtGallery artGallery) {
        restTemplate.postForLocation("/artGalleries", CreateRequest.entityToDtoMapper().apply(artGallery));
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
