package br.inatel.labs.labrest.server.servive;

import br.inatel.labs.labrest.server.model.Curso;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CursoService {
    private List<Curso> cursos = new ArrayList<>();

    @PostConstruct
    public void init(){
        Curso c1 = new Curso(1L, "Java", 40);
        Curso c2 = new Curso(2L, "Python", 30);
        Curso c3 = new Curso(3L, "C#", 20);
        Curso c4 = new Curso(4L, "C++", 10);

        cursos.add(c1);
        cursos.add(c2);
        cursos.add(c3);
        cursos.add(c4);
    }

    public List<Curso> pesquisarCurso(){
        return cursos;
    }

    public Optional<Curso> pesquisarCursoPorId(Long cursoId){
        Optional<Curso> opCurso = cursos.stream().filter(c -> c.getId().equals(cursoId)).findFirst();
        return opCurso;
    }

    public Curso criarCurso(Curso curso){
        Long id = new Random().nextLong();
        curso.setId(id);
        cursos.add(curso);
        return curso;
    }

    public void atualizarCurso(Curso curso){
        cursos.remove(curso);
        cursos.add(curso);
    }

    public void deletarCurso(Curso curso){
        cursos.remove(curso);
    }

    public List<Curso> pesquisarCursoPorFragDescri(String fragDescri){
        if(Objects.isNull(fragDescri) || fragDescri.isBlank()){
            return List.of();
        }
        else {
            List<Curso> cursosEncontrados = cursos.stream().filter(c -> c.getDescricao().trim().toLowerCase().contains(fragDescri.trim().toLowerCase())).collect(Collectors.toList());
            return cursosEncontrados;
        }
    }
 }
