package br.com.eits.boot.domain.rest;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eits.boot.domain.entity.Pessoa;
import br.com.eits.boot.domain.service.PessoaService;

/**
 * 
 * @author boz
 *
 */
@Component
@RestController
@RequestMapping("/pessoa")
public class PessoaRestContoller 
{
	
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	@Autowired
	private PessoaService pessoaService;
	
	/*-------------------------------------------------------------------
	 * 		 					RESOURCES
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	@GetMapping("/sync")
	public List<Pessoa> sync(@RequestParam("date") String date)
	{
		return this.pessoaService.listChangesByDate(date);
	}
	
	/**
	 * 
	 * @param pessoaId
	 */
	@GetMapping("/syncDeleted")
	public List<BigInteger> mergeDeleted(@RequestParam("date") String date)
	{
		return this.pessoaService.listRemovedByTimestemp(date);
	}

	/**
	 * 
	 * @param pessoa
	 * @return
	 */
	@PostMapping("/insert")
	public Pessoa insert(@RequestBody Pessoa pessoa) 
	{
		return this.pessoaService.insert(pessoa);
	}
	
	/**
	 * 
	 * @param pessoa
	 * @return
	 */
	@PutMapping("/update")
	public Pessoa update(@RequestBody Pessoa pessoa)
	{
		return this.pessoaService.update(pessoa);
	}

	/**
	 * 
	 * @param pessoaId
	 */
	@GetMapping("/delete")
	public void delete(@RequestParam("id") long pessoaId)
	{
		this.pessoaService.remove(pessoaId);
	}
	
	

}
