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
@SequenceGenerator(name = "Crediario_SEQ", sequenceName = "Crediario_SEQ")
@Table(name = "crediario")
@SuppressWarnings("serial")

public class Crediario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Crediario_SEQ")
	@Column(name = "codigo")
	@Resolvable(colName = "codigo")
	private Integer codigo;

	@Column(name = "cancelado")
	private String cancelado;

	@Column(name = "descricao")
	@Resolvable(colName = "Descrição")
	private String descricao;

	@Column(name = "qtdDias")
	@Resolvable(colName = "Quantidade de dias")
	private Integer qtdDias;

	@Column(name = "vencimento")
	@Resolvable(colName = "Vencimento")
	private Date vencimento;

	@Column(name = "parcelas")
	@Resolvable(colName = "ParcelasX")
	private Integer parcelas;

	@Column(name = "valor")
	@Resolvable(colName = "Valor R$")
	private Double valor;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(Integer qtdDias) {
		this.qtdDias = qtdDias;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
