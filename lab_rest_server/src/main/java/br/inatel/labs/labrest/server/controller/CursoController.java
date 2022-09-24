package br.inatel.labs.labrest.server.controller;

import br.inatel.labs.labrest.server.exeption.CursoNaoEncontradoExeption;
import br.inatel.labs.labrest.server.model.Curso;
import br.inatel.labs.labrest.server.servive.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService servico;

    @GetMapping
    public List<Curso> listar(){
        List<Curso> cursos = servico.pesquisarCurso();
        return cursos;
    }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable("id") Long cursoId){
        Optional<Curso> opCurso = servico.pesquisarCursoPorId(cursoId);
        if(opCurso.isPresent()){
            return opCurso.get();
        }
        else {
            throw new CursoNaoEncontradoExeption(cursoId);
        }
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso criar(@RequestBody Curso curso){
        Curso cursoCriado = servico.criarCurso(curso);
        return cursoCriado;
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void atualizar(@RequestBody Curso curso){
        servico.atualizarCurso(curso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deletar(@PathVariable("id") Long cursoId){
        Optional<Curso> opCurso = servico.pesquisarCursoPorId(cursoId);
        if (opCurso.isPresent()){
            Curso removCurso = opCurso.get();
            servico.deletarCurso(removCurso);
        }
        else {
            throw new CursoNaoEncontradoExeption(cursoId);
        }
    }

    @GetMapping("/pesquisa")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Curso> pesquisar(@RequestParam("descricao") String fragDescri){
        List<Curso> cursosEncontrados = servico.pesquisarCursoPorFragDescri(fragDescri);
        return cursosEncontrados;
    }
}
