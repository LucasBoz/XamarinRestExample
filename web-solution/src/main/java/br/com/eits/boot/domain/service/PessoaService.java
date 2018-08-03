package br.com.eits.boot.domain.service;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManagerFactory;

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

	@Autowired
	private IPessoaRepository pessoaRepository;

	public List<Pessoa> merge(String dateString) {
	
		Long dateInteger = Long.parseLong(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateInteger);

		System.out.println(calendar.getTime());
		List<Pessoa> me = pessoaRepository.findPessoaPENSAR_NOME_MELHOR(calendar);
		
		System.out.println( me.size() );
		
		return me;
	}

	public List<BigInteger> listRemovedByTimestemp( Long milliseconds ){
		
		
		return this.pessoaRepository.listRemovedByTimestemp( 1530800846978L );
		

	}

	public Pessoa insert(Pessoa pessoa) {
		System.out.println(pessoa);
		return pessoaRepository.save(pessoa);
	}

	public Pessoa update(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

}
