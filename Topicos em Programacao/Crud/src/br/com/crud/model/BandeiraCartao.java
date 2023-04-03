package br.com.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.towel.el.annotation.Resolvable;

@Entity
@SequenceGenerator(name = "BandeiraCartao_SEQ", sequenceName = "BandeiraCartao_SEQ")
@Table(name = "bandeiraCartao")
@SuppressWarnings("serial")

public class BandeiraCartao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BandeiraCartao_SEQ")
	@Column(name = "codigo")
	@Resolvable(colName = "codigo")
	private Integer codigo;

	@Column(name = "cancelado")
	@Resolvable(colName = "Cancelado")
	private String cancelado;

	@Column(name = "causaCancel")
	@Resolvable(colName = "causaCancel")
	private String causaCancel;

	@Column(name = "descricao")
	@Resolvable(colName = "Descricao")
	private String descricao;

	@Column(name = "contaDescricao")
	@Resolvable(colName = "Conta Descricao")
	private String contaDescricao;

	@Column(name = "creditoDia")
	private Integer creditoDia;

	@Column(name = "debitoDia")
	private Integer debitoDia;

	@Column(name = "parceladoDia")
	private Integer parceladoDia;

	@Column(name = "tipoCartao")
	@Resolvable(colName = "Tipo do Cartão")
	private Integer tipoCartao;
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getCancelado() {
		return cancelado;
	}
	
	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getContaDescricao() {
		return contaDescricao;
	}
	
	public void setContaDescricao(String contaDescricao) {
		this.contaDescricao = contaDescricao;
	}
	
	public Integer getCredidoDia() {
		return creditoDia;
	}
	
	public void setCreditoDia(Integer creditoDia) {
		this.creditoDia = creditoDia;
	}
	
	public Integer getDebitoDia() {
		return debitoDia;
	}
	
	public void setDebitoDia(Integer debitoDia) {
		this.debitoDia = debitoDia;
	}
	
	public Integer getParceladoDia() {
		return parceladoDia;
	}
	
	public void setParceladoDia(Integer parceladoDia) {
		this.parceladoDia = parceladoDia;
	}
	
	public Integer getTipoCartao() {
		return tipoCartao;
	}
	
	public void setTipoCartao(Integer tipoCartao) {
		this.tipoCartao = tipoCartao;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
