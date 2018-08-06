package br.com.eits.boot.domain.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eits.boot.domain.entity.Empresa;
import br.com.eits.boot.domain.service.EmpresaService;

/**
 * 
 * @author boz
 *
 */
@Component
@RestController
@RequestMapping("/empresa")
public class EmpresaRestController 
{
	
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/**
	 * 
	 */
	@Autowired
	private EmpresaService empresaService;

	
	/*-------------------------------------------------------------------
	 * 		 					RESOURCES
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	@GetMapping("/sync")
	public List<Empresa> sync( @RequestParam("date")  String date) 
	{
		return this.empresaService.listChangesByDate( date );
	}
	

}


