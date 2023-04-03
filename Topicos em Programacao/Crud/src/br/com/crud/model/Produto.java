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
@SequenceGenerator(name = "Produto_SEQ", sequenceName = "Produto_SEQ")
@Table(name = "produto")
@SuppressWarnings("serial")

public class Produto implements Serializable {

	// criação na tabela
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Produto_SEQ")
	@Column(name = "codigo")
	@Resolvable(colName = "Codigo")
	private Integer codigo;

	@Column(name = "cancelado")
	private String cancelado;

	@Column(name = "causaCancel")
	private String causaCancel;

	@Column(name = "nomeProduto", length = 150)
	@Resolvable(colName = "Nome do Produto")
	private String nomeProduto;

	@Column(name = "qtdEstoqueAtual")
	@Resolvable(colName = "Quantidade no Estoque Atual")
	private Integer qtdEstoqueAtual;

	@Column(name = "valor")
	@Resolvable(colName = "Valor")
	private Double valor;

	// Getters and Setters
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getQtdEstoqueAtual() {
		return qtdEstoqueAtual;
	}

	public double getValor() {
		return valor;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public void setQtdEstoqueAtual(Integer qtdEstoqueAtual) {
		this.qtdEstoqueAtual = qtdEstoqueAtual;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	

	public String getCancelado() {
		return cancelado;
	}

	public String getCausaCancel() {
		return causaCancel;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public void setCausaCancel(String causaCancel) {
		this.causaCancel = causaCancel;
	}

	

}
