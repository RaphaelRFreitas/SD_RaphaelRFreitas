package br.inatel.labs.labrest.client;

import br.inatel.labs.labrest.client.model.Curso;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientPost {
    public static void main(String[] args) {
        Curso novoCurso = new Curso();
        novoCurso.setDescricao("Curso de WebFlux");
        novoCurso.setCargaHoraria(80);

        Curso cursoRetornado = WebClient.create("http://localhost:8080")
                .post()
                .uri("/curso")
                .bodyValue(novoCurso)
                .retrieve()
                .bodyToMono(Curso.class)
                .block();

        System.out.println("Curso salvo: " + cursoRetornado);

    }
}
