package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

public class Matricula {
    static int contador;

    public enum Situacao {
        REGULAR,
        APROVEITAMENTO,
        CANCELADO,
        APROVADO,
        REPROVADO
    }

    private int numero;
    private Aluno aluno;
    private Situacao situacao;

    public Matricula(Aluno aluno) {
        this.setNumero(++contador);
        this.setSituacao(Situacao.REGULAR);
        this.setAluno(aluno);

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Matricula [numero=" + numero + ", aluno=" + aluno + ", situacao=" + situacao + "]";
    }

    
}
