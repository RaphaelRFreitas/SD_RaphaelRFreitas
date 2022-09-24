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

	@Test
	void dadoCursoValido_quandoPostCurso_entaoResponderComCursoCriado() {
		Curso cursoParaCriar = new Curso();
		cursoParaCriar.setDescricao("Curso de Teste");
		cursoParaCriar.setCargaHoraria(100);

		Curso cursoCriado = webTestClient.post().uri("/curso")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(cursoParaCriar)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(Curso.class).returnResult().getResponseBody();

		assertNotNull(cursoCriado);
		assertNotNull(cursoCriado.getId());
		assertEquals(cursoParaCriar.getDescricao(), cursoCriado.getDescricao());
		assertEquals(cursoParaCriar.getCargaHoraria(), cursoCriado.getCargaHoraria());
	}

	@Test
	void dadoCursoValido_quandoPutCurso_entaoResponderComCursoAtualizado() {
		Curso cursoParaAtualizar = new Curso();
		cursoParaAtualizar.setId(1L);
		cursoParaAtualizar.setDescricao("Curso de Teste");
		cursoParaAtualizar.setCargaHoraria(100);

		Curso cursoAtualizado = webTestClient.put().uri("/curso")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(cursoParaAtualizar)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Curso.class).returnResult().getResponseBody();

		assertNotNull(cursoAtualizado);
		assertEquals(cursoParaAtualizar.getId(), cursoAtualizado.getId());
		assertEquals(cursoParaAtualizar.getDescricao(), cursoAtualizado.getDescricao());
		assertEquals(cursoParaAtualizar.getCargaHoraria(), cursoAtualizado.getCargaHoraria());
	}

	@Test
	void dadoCursoIdValido_quandoDeleteCurso_entaoResponderComStatusNoContent() {
		long cursoIdValido = 1L;

		webTestClient.delete().uri("/curso/" + cursoIdValido)
			.exchange()
			.expectStatus().isNoContent();
	}

	@Test
	void dadoCursoIdInvalido_quandoDeleteCurso_entaoResponderComStatusNotFound() {
		long cursoIdInvalido = 99L;

		webTestClient.delete().uri("/curso/" + cursoIdInvalido)
			.exchange()
			.expectStatus().isNotFound();
	}
}

