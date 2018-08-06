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
 * @author eits
 *
 */
public interface IPessoaRepository  extends JpaRepository<Pessoa, Long>
{

	/**
	 * Utilizado na sincronização de dados atualizados
	 * 
	 * @param date
	 * @return Lista de {@link Pessoa} que possui alteração após a data do parametro
	 */
	@Query("FROM Pessoa pessoa " 
			+ " WHERE "
			+ "  pessoa.created >= CAST (:date as date)  "
			+ " OR pessoa.updated >= CAST ( :date as date)"
			+ " OR '%'||:date||'%' = null "
			)
	List<Pessoa> listChangesByDate( @Param("date") Calendar date);
	
	/**
	 * Utilizado na sincronização de dados removidos
	 *  revision_type = 2 -> removed
	 * @param milliseconds
	 * @return Lista de IDs de todos os registros excluidos após a data do parametro
	 */
	@Query( nativeQuery = true,
			value = " select a.id " + 
					" from auditing.revision r, auditing.pessoa_audited a" + 
					" where" + 
					" a.revision = r.id " + 
					" and r.timestamp >= :milliseconds " + 
					" and a.revision_type = 2 " )
	List<BigInteger> listRemovedByTimestemp( @Param("milliseconds") long milliseconds );
	
}
