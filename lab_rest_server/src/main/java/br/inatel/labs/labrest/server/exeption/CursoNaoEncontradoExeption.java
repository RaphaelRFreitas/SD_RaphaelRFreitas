package br.inatel.labs.labrest.server.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/*
* Excepion para cursos não encontrados
 */
public class CursoNaoEncontradoExeption extends ResponseStatusException {
    public CursoNaoEncontradoExeption(long cursoId) {
        super(HttpStatus.NOT_FOUND, String.format("Curso com id %s não encontrado", cursoId));
    }
}
