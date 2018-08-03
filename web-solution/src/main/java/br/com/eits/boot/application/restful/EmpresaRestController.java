package br.com.eits.boot.application.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eits.boot.domain.entity.Empresa;
import br.com.eits.boot.domain.service.EmpresaService;

@Component
@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {

	@Autowired
	private EmpresaService empresaService;


	
	@GetMapping("/merge")
	public List<Empresa> merge( @RequestParam("date")  String date) {
		System.out.println( "MERGE " + date );
		return this.empresaService.merge( date );
	}
	

}


