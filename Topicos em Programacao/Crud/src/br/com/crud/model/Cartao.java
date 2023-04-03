package br.com.crud.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.towel.el.annotation.Resolvable;
@Entity
@SequenceGenerator(name = "Cliente_SEQ", sequenceName = "Cliente_SEQ")
@Table(name = "cartao")
@SuppressWarnings("serial")
public class Cartao implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Cliente_SEQ")
	@Column(name = "codigo")
	@Resolvable(colName = "Codigo")
	private Integer codigo;
	
	@Column(name = "cancelado")
	private String cancelado;
	
	@Column(name = "causaCancel")
	private String causaCancel;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "bandeiraCartao")
	@Resolvable(colName = "Bandeira Cartão")
	private String bandeiraCartao;

	
	//getters and setters
	
	public Integer getCodigo() {
		return codigo;
	}

	public String getCancelado() {
		return cancelado;
	}

	public String getCausaCancel() {
		return causaCancel;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public void setCausaCancel(String causaCancel) {
		this.causaCancel = causaCancel;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setBandeiraCartao(String bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}
	
	
}
