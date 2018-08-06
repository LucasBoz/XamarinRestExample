package br.com.eits.boot.domain.entity;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 
 * @author eits
 *
 */
@Data
@Entity
@Audited
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
public class Empresa extends AbstractEntity 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1627200635516009076L;
	
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	private String nome;
	
	/**
	 * 
	 */
	private String cnpj;
	
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	public Empresa(){ super(); }
	
	/**
	 * 
	 * @param nome
	 * @param cnpj
	 */
	public Empresa( String nome, String cnpj)
	{
		this.nome = nome;
		this.cnpj = cnpj;
	}
	
	
	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	
	
	
}
