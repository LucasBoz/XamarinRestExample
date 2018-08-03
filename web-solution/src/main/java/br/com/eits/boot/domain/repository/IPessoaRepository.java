package br.com.eits.boot.domain.repository;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.eits.boot.domain.entity.Pessoa;

/**
 * 
 * @author boz
 *
 */
public interface IPessoaRepository  extends JpaRepository<Pessoa, Long>{

	
	@Query("FROM Pessoa pessoa " 
			+ " WHERE "
			+ "  pessoa.created >= CAST (:date as date)  "
			+ " OR pessoa.updated >= CAST ( :date as date)"
			+ " OR '%'||:date||'%' = null "
			)
	List<Pessoa> findPessoaPENSAR_NOME_MELHOR( @Param("date") Calendar date);
	
	
	@Query( nativeQuery = true,
			value = " select a.id " + 
					" from auditing.revision r, auditing.user_audited a" + 
					" where" + 
					" a.revision = r.id " + 
					" and r.timestamp >= :milliseconds " + 
					" and a.revision_type = 1 " )
	List<BigInteger> listRemovedByTimestemp( @Param("milliseconds") long milliseconds );
	
}
