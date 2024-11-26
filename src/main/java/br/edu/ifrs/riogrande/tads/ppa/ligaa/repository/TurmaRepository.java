package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Historico;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Turma;

@Repository
public class TurmaRepository implements IRepository<Turma> {

    private Map<String, Turma> db = new HashMap<>();

    @Override
    public Turma save(Turma t) {
        db.put(t.getCodigo(), t);
        return t;
    }

    @Override
    public boolean delete(Turma e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Turma> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public Optional<Turma> findByCodigo(String codigoTurma) {
        return Optional.ofNullable(db.get(codigoTurma));
    }

    private List<Turma> findByAluno(Aluno aluno) {
        return db.values().stream()
            .filter(t -> {
                return t.getMatriculas().stream()
                    .anyMatch(m -> m.getAluno().equals(aluno));
            }).toList();
    }

    public Historico findHistorico(Aluno aluno) {
        return new Historico(aluno, findByAluno(aluno));
    }
    
}
