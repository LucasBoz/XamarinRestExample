package br.com.eits.boot.application.restful;


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



@Component
@RestController
@RequestMapping("/pessoa")
public class PessoaRestContoller {

	
	@Autowired
	private PessoaService pessoaService;
	
//	@Override
//	public List<Pessoa> listAll() 
//	{
//		return this.pessoaService.merge(null);
//	}
//	
//	@Override
//	public List<BigInteger> listRemovedByTimestemp(Long milliseconds )
//	{
//		List<BigInteger> listRemov = this.pessoaService.listRemovedByTimestemp(milliseconds );
//		return listRemov;
//	}

	@GetMapping("/merge")
	public List<Pessoa> merge( @RequestParam("date")  String date) {
		System.out.println( "MERGE " + date );
		return this.pessoaService.merge( date );
	}
	

	@PostMapping("/insert")
	public Pessoa insert( @RequestBody Pessoa pessoa) {
		System.out.println( pessoa );
		return this.pessoaService.insert(pessoa);
	}

	
	@PutMapping("/update")
	public Pessoa update( @RequestBody Pessoa pessoa) {
		System.out.println( pessoa );
		return this.pessoaService.update(pessoa);
	
	}
	
	@PutMapping("/update2")
	public String update2( @RequestBody String pessoa) {
		System.out.println( pessoa );
		return pessoa + " aaaaaa";
	}
//
//	@Override
//	public Pessoa delete(long pessoaId) {
//		System.out.println( "DELETE" + pessoaId );
//		return null;
//	}


}
