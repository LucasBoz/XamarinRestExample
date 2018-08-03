package br.com.eits.boot.domain.entity;

import javax.persistence.Entity;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@DataTransferObject( javascript="Pessoa" )
@ToString(callSuper=true)
public class Pessoa extends AbstractEntity {
	
	String nome;
	
	public Pessoa(){ super(); }
	public Pessoa( Long id, String nome )
	{
		this.id = id;
		this.nome = nome;
	}
	
}
