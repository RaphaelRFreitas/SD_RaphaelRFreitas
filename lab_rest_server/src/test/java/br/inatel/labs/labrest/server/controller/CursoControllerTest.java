package br.inatel.labs.labrest.server.controller;

import static org.junit.jupiter.api.Assertions.*;

import br.inatel.labs.labrest.server.model.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CursoControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void deveListarCursos() {
		webTestClient.get().uri("/curso")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBody().returnResult();
	}

	@Test
	void dadoCursoIdValido_quandoGetCursoPorId_entaoResponderComCursoValido() {
		long cursoIdValido = 1L;

		Curso cursoRespondido = webTestClient.get().uri("/curso/" + cursoIdValido)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Curso.class).returnResult().getResponseBody();

		assertNotNull(cursoRespondido);
		assertEquals(cursoIdValido, cursoRespondido.getId());
	}

	@Test
	void dadoCursoIdInvalido_quandoGetCursoPorId_entaoRespondeComStatusNotFound() {
		long cursoIdInvalido = 99L;

		webTestClient.get().uri("/curso/" + cursoIdInvalido)
			.exchange()
			.expectStatus().isNotFound();
	}
}

