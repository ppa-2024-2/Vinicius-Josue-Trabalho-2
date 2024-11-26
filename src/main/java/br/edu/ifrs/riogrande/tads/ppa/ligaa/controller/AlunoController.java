package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Turma;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.AlunoService;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.NovoAluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.TurmaService;

import org.springframework.web.bind.annotation.RequestParam;


// Rotear tudo "que tem a ver" com Aluno

@RestController
public class AlunoController {

    private final AlunoService alunoService;
    private final TurmaService turmaService;

    // o AlunoService será "injetado" pelo Spring (framework)
    public AlunoController(AlunoService alunoService,
                           TurmaService turmaService) {
        this.alunoService = alunoService;
        this.turmaService = turmaService;
    }
   
    // rotear
    @PostMapping(path = "/api/v1/alunos", 
                 consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void novoAluno(@RequestBody NovoAluno aluno) {

        System.out.println(aluno);
        alunoService.cadastrarAluno(aluno); // não deve ser IDEMPOTENTE
    }
    
    @GetMapping(path = "/api/v1/alunos/{cpf}") // identificador
    public ResponseEntity<Aluno> buscaCpf(@PathVariable("cpf") String cpf) {

        Aluno aluno = alunoService.buscarAluno(cpf);

        return ResponseEntity.ok(aluno); // 200
    }

    @GetMapping(path = "/api/v1/alunos")
    public ResponseEntity<List<Aluno>> buscaTodos() {
        return ResponseEntity.ok(alunoService.findAll()); // outras opções: 404 ou 204
    }
    // Padrão REST(ful) não se usa verbos nas URLs
    // HTTP VERB  /api/v1/alunos/matricular
    @PostMapping(path = "/api/v1/alunos/matricular")
    @ResponseStatus(code = HttpStatus.OK)
    public void matricular(@RequestBody RequisicaoMatriculaDTO req) {

        turmaService.matricular(req.cpf(), req.codigoTurma());
    }

    // public void novoAlunoV2(NovoAlunoV2 aluno)
}
