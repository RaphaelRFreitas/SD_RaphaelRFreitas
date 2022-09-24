package br.inatel.labs.labrest.client;

import br.inatel.labs.labrest.client.model.Curso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientPut {
    public static void main(String[] args) {
        Curso cursoExistente = new Curso(1, "Curso de Java", 80);

        ResponseEntity<Void> responseEntity = WebClient.create("http://localhost:8080")
                .put()
                .uri("/curso")
                .bodyValue(cursoExistente)
                .retrieve()
                .toBodilessEntity()
                .block();

        System.out.println("Status: " + responseEntity.getStatusCode());
    }
}
