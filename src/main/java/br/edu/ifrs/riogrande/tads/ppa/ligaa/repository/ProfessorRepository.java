package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Professor;
import jakarta.annotation.PostConstruct;

@Repository
public class ProfessorRepository implements IRepository<Professor> {

    private Map<String, Professor> db = new HashMap<>();

    @Override
    public Professor save(Professor e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean delete(Professor e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Professor> findAll() {
        return new ArrayList<>(db.values());
    }

    public Optional<Professor> findBySiape(String siape) {
        return Optional.ofNullable(db.get(siape));
    }
    
    @PostConstruct
    void popular() {
        var marcio = new Professor();
        marcio.setNome("Marcio");
        marcio.setSiape("1810497");
        marcio.setFormacao("Análise de Sistemas");
        marcio.setDesativado(false);

        db.put(marcio.getSiape(), marcio);
    }
}
