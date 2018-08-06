package br.com.eits.boot.domain.service;

import java.util.Calendar;
import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.boot.domain.entity.Empresa;
import br.com.eits.boot.domain.repository.IEmpresaRepository;

@Service
@RemoteProxy
@Transactional
public class EmpresaService {

	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/

	//Repositories
	/**
	 * 
	 */
	@Autowired
	private IEmpresaRepository empresaRepository;

	
	/*-------------------------------------------------------------------
	 * 		 					SERVICES
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 * @return
	 */
	public List<Empresa> list() 
	{
		return empresaRepository.findAll();
	}

	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public List<Empresa> listChangesByDate( final String dateString ) 
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis( Long.parseLong(dateString));

		System.out.println(calendar.getTime());

		final List<Empresa> empresaList = empresaRepository.listChangesByDate(calendar);

		System.out.println(empresaList.size());

		return empresaList;
	}

}