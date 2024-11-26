package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;
// new AlunoService()

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoRepository;

@Service // qualificando o objeto
public class AlunoService {

    // dependência
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void cadastrarAluno(NovoAluno novoAluno) {

        if (alunoRepository.cpfExists(novoAluno.getCpf())) {
            throw new IllegalStateException("CPF já existe: " + novoAluno.getCpf());
        }

        // ONDE FICAM AS REGRAS DE DOMÍNIO
        Aluno aluno = new Aluno();

        aluno.setCpf(novoAluno.getCpf());
        aluno.setNome(novoAluno.getNome());
        aluno.setLogin(novoAluno.getEnderecoEletronico());
        aluno.setEnderecoEletronico(novoAluno.getEnderecoEletronico());

        alunoRepository.save(aluno);        
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAluno(@NonNull String cpf) {
        return new Aluno();
    }
 
}
