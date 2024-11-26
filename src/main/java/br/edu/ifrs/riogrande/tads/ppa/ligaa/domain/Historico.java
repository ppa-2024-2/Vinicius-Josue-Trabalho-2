package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import java.util.List;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Matricula.Situacao;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.DomainException;

// Value Object => Objeto de Valor 
// DTO => VO => DTO
public record Historico(Aluno aluno, List<Turma> turmas) {

    public boolean aprovadoEm(Disciplina disciplina) {
        // FIXME: considerar a disciplina
        return turmas.stream().flatMap(t -> t.getMatriculas().stream())
            .anyMatch(m -> m.getAluno().equals(aluno) && m.getSituacao().equals(Situacao.APROVADO));
    }

    public List<Turma> turmasAnterioresDaDisciplina(Disciplina disciplina, Turma turma) {
    return turmas.stream()
        .filter(t -> t.getDisciplina().equals(turma.getDisciplina()))
        .toList();
    }

    public boolean reprovadoAnteriormente(List<Turma> turmasAnterioresDaDisciplina) {
        return turmasAnterioresDaDisciplina.stream().flatMap(t -> t.getMatriculas().stream())
        .anyMatch(m -> m.getAluno().equals(aluno) && m.getSituacao().equals(Situacao.REPROVADO));
    }

    public void deveEstarAprovadoEm(Turma turma, Aluno aluno) {
        if (this.aprovadoEm(turma.getDisciplina())) {
            throw new DomainException("Aluno " + aluno.getCpf() + " já aprovado na disciplina " + turma.getDisciplina().getNome());
        }
    }

}
