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

	@Autowired
	private IEmpresaRepository empresaRepository; 
	

	public List<Empresa> list(){
		return empresaRepository.findAll();
	}
	
	public List<Empresa> merge( String dateString  ){
		
		Long dateInteger = Long.parseLong(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateInteger);

		System.out.println(calendar.getTime());

		return empresaRepository.findToMerge( calendar );
	}
	

	

}