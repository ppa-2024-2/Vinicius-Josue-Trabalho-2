package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Historico;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Turma;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Matricula.Situacao;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.DisciplinaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.ProfessorRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.TurmaRepository;
import jakarta.annotation.PostConstruct;

@Service
public class TurmaService {

    

    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    
    public TurmaService
        (
            TurmaRepository turmaRepository,
            DisciplinaRepository disciplinaRepository,
            AlunoRepository alunoRepository,
            ProfessorRepository professorRepository
        ) {
        this.turmaRepository = turmaRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public Matricula matricular(String cpf, String codigoTurma) { 
        // turma existe?
        var turma = turmaRepository.findByCodigo(codigoTurma)
            .orElseThrow(() -> new NotFoundException());

        var aluno = alunoRepository.findByCpf(cpf)
            .orElseThrow(() -> new NotFoundException());
            
        Historico historico = turmaRepository.findHistorico(aluno);

        return turma.matricular(aluno, historico);
    }

    @PostConstruct
    void popular() { // seed
        var can = alunoRepository.findByCpf("11122233344").orElseThrow();
        var ppa = disciplinaRepository.findByCodigo("ppa").orElseThrow();
        var marcio = professorRepository.findBySiape("1810497").orElseThrow();
        
        var ppa20232 = new Turma();
        ppa20232.setCodigo("ppa-2023-2");
        ppa20232.setDisciplina(ppa);
        ppa20232.setProfessor(marcio);
        ppa20232.setSemestre("2023-2");
        ppa20232.setVagas(10);

        var ppa20242 = new Turma();
        ppa20242.setCodigo("ppa-2024-2");
        ppa20242.setDisciplina(ppa);
        ppa20242.setProfessor(marcio);
        ppa20242.setSemestre("2024-2");
        ppa20242.setVagas(1);

        var mat = new Matricula(can);
        ppa20232.getMatriculas().add(mat);

        turmaRepository.save(ppa20232);
        turmaRepository.save(ppa20242);

        System.out.println(ppa20232);
        System.out.println(ppa20242);
    }

    
}
