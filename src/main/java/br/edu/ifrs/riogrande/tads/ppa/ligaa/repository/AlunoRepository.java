package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;
import jakarta.annotation.PostConstruct;

@Repository // infraestrutura
public class AlunoRepository implements IRepository<Aluno> {

    private Map<UUID, Aluno> db = new HashMap<>();

    @Override
    public Aluno save(Aluno a) { // a id=f1323, desativado=true

        LocalDateTime agora = LocalDateTime.now();
        
        if (a.getId() == null) { // não é persistente
            a.setDataHoraCriacao(agora);
            a.setId(UUID.randomUUID()); // atribuir um ID
            a.setDesativado(false);
        }
        
        if (a.isDesativado() || (db.containsKey(a.getId()) && db.get(a.getId()).isDesativado())) {
            throw new EntidadeInativaException();
        }
        
        a.setDataHoraAlteracao(agora);
        
        db.put(a.getId(), a);
        
        return a;
    }

    public boolean cpfExists(String cpf) {
        return db.values().stream()
            // qualquer um coincide o CPF?
            .anyMatch(aluno -> cpf.equals(aluno.getCpf()));
    }


    @Override
    public boolean delete(Aluno e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Aluno> findAll() {
        return new ArrayList<Aluno>(db.values());
    }

    public Optional<Aluno> findByCpf(String cpf) {
        return db.values().stream()
            .filter(a -> cpf.equals(a.getCpf()))
            .findFirst();
    }

    @PostConstruct
    void popular() {
        var can = new Aluno();
        can.setCpf("11122233344");
        can.setDataHoraCriacao(LocalDateTime.now());
        can.setDataHoraAlteracao(LocalDateTime.now());
        can.setDesativado(false);
        can.setEnderecoEletronico("can.robert@gmail.com");
        can.setNome("Canrobert Junior");
        
        UUID id = UUID.randomUUID();
        
        db.put(id, can);
    }

}
