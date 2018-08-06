package br.com.eits.boot.domain.service;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.boot.domain.entity.Pessoa;
import br.com.eits.boot.domain.repository.IPessoaRepository;

@Service
@RemoteProxy
@Transactional
public class PessoaService {

	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/

	//Repositories
	/**
	 * 
	 */
	@Autowired
	private IPessoaRepository pessoaRepository;

	
	/*-------------------------------------------------------------------
	 * 		 					SERVICES
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public List<Pessoa> listChangesByDate(final String dateString) 
	{
	
		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis( Long.parseLong(dateString) );

		final List<Pessoa> pessoaList = pessoaRepository.listChangesByDate(calendar);
		
		System.out.println( pessoaList.size() );
		
		return pessoaList;
	}
	
	/**
	 * 
	 * @param dateString
	 * @return 
	 */
	public List<BigInteger> listRemovedByTimestemp(final String dateString) 
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis( Long.parseLong(dateString) );

		return pessoaRepository.listRemovedByTimestemp( calendar.getTimeInMillis());

	}

	/**
	 * 
	 * @param milliseconds
	 * @return
	 */
	public List<BigInteger> listRemovedByMilliseconds( Long milliseconds )
	{
		return this.pessoaRepository.listRemovedByTimestemp( milliseconds );
	}

	/**
	 * 
	 * @param pessoa
	 * @return
	 */
	public Pessoa insert(final Pessoa pessoa) 
	{
		return pessoaRepository.save(pessoa);
	}

	/**
	 * 
	 * @param pessoa
	 * @return
	 */
	public Pessoa update(Pessoa pessoa) 
	{
		return pessoaRepository.save(pessoa);
	}
	
	/**
	 * 
	 * @param pessoaId
	 */
	public void remove(long pessoaId) 
	{
		pessoaRepository.deleteById( pessoaId );
	}

}
