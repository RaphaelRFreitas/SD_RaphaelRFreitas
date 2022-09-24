package br.inatel.labs.labrest.client;

import br.inatel.labs.labrest.client.model.Curso;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class WebClientGetCurso {
    public static void main(String[] args) {
        List<Curso> listaCurso = new ArrayList<>();

        Flux<Curso> fluxCurso = WebClient.create("http://localhost:8080")
                .get()
                .uri("/curso")
                .retrieve()
                .bodyToFlux(Curso.class);

        fluxCurso.subscribe(c -> listaCurso.add(c));
        fluxCurso.blockLast(); // Bloqueia a execução até o último elemento ser processado

        System.out.println("Lista de cursos: " + listaCurso);
    }
}
