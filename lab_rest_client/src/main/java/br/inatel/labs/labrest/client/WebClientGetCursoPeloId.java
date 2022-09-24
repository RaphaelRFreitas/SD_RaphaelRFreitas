package br.inatel.labs.labrest.client;

import br.inatel.labs.labrest.client.model.Curso;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class WebClientGetCursoPeloId {
    public static void main(String[] args) {
        try {
            WebClient webClient = WebClient.create("http://localhost:8080");
            Mono<Curso> monoCurso = webClient.get()
                    .uri("/curso/9", 1)
                    .retrieve()
                    .bodyToMono(Curso.class);

            Curso curso = monoCurso.block();
            System.out.println("Curso: " + curso);
        } catch (WebClientResponseException e) {
            System.out.println("Status: " + e.getStatusCode());
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
