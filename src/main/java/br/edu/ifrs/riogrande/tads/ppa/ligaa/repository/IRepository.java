package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.util.List;

public interface IRepository<ENTIDADE> { // generics
    
    public ENTIDADE save(ENTIDADE e);

    /**
     * Exclui uma determinada Entidade;
     * 
     * @param e a Entidade a ser excluída.
     * @return true se foi excluída e false caso não.
     */
    public boolean delete(ENTIDADE e);

    public List<ENTIDADE> findAll();

}
