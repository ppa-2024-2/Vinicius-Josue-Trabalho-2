package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Disciplina;
import jakarta.annotation.PostConstruct;

@Repository // stereotype (estereótipo)
public class DisciplinaRepository implements IRepository<Disciplina> {

    private Map<String, Disciplina> db = new HashMap<>();

    @Override
    public Disciplina save(Disciplina e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean delete(Disciplina e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Disciplina> findAll() {
        return new ArrayList<>(db.values());
    }

    public Optional<Disciplina> findByCodigo(String codigo) {
        return Optional.ofNullable(db.get(codigo));
    }

    @PostConstruct
    void popular() {
        var ppp = new Disciplina();
        ppp.setAulasSemanais(4);
        ppp.setCargaHoraria(66);
        ppp.setNome("Princípios e Padrões de Projeto");

        var ppa = new Disciplina();
        ppa.setAulasSemanais(4);
        ppa.setCargaHoraria(66);
        ppa.setNome("Princípios e Padrões de Arquitetura");
        ppa.getPreRequisitos().add(ppp);

        db.put("ppa", ppa);
        db.put("ppp", ppp);
    }
    
}
