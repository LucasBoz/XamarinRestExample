package br.com.eits.boot.domain.entity;

import javax.persistence.Entity;

import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(callSuper = true)
public class Empresa extends AbstractEntity {

	String nome;
	String cnpj;
	
	Empresa(){}
	Empresa( String asd, String asdd ){
		nome = asd;
		cnpj = asdd;
	}
	
}
