package br.com.eits.boot.domain.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.eits.boot.domain.entity.Empresa;

/**
 * 
 * @author eits
 *
 */
public interface IEmpresaRepository extends JpaRepository<Empresa, Long> 
{

	/**
	 * 
	 * @param date
	 * @return
	 */
	@Query("FROM Empresa empresa" 
			+ " WHERE "
			+ "  empresa.created >= CAST (:date as date)  "
			+ " OR empresa.updated >= CAST ( :date as date)"
			+ " OR '%'||:date||'%' = null "
			)
	List<Empresa> listChangesByDate( @Param("date") Calendar date);
	
	
	
}
