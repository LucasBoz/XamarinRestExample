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
public class Pessoa extends AbstractEntity 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2805031793508895333L;

	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/**
	 * 
	 */
	private String nome;
	
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	public Pessoa(){ super(); }
	
	/**
	 * 
	 * @param id
	 * @param nome
	 */
	public Pessoa( Long id, String nome )
	{
		this.id = id;
		this.nome = nome;
	}
	
	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	
}
