package br.com.crud.model;/*  Codigo Gerado Automaticamente Pelo Framework JAVA Facil
							* 
							*  Framework Desenvolvido por Eduardo Nicolini Sodre da Silva
							*  www.studiowebmaster.com.br - www.vipsystem.com.br
							*  classe gerada pelo framework em:17052013101234 
							*/

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.towel.el.annotation.Resolvable;

@Entity
@SequenceGenerator(name = "ContaReceber_SEQ", sequenceName = "ContaReceber_SEQ")
@Table(name = "contaReceber")
@SuppressWarnings("serial")
public class ContaReceber implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ContaReceber_SEQ")
	@Column(name = "codigo")
	@Resolvable(colName = "Código")
	private Integer codigo;

	@Column(name = "cancelado")
	private String cancelado;

	@Column(name = "nomeCartao")
	@Resolvable(colName = "Nome cartão")
	private String nomeCartao;

	@Column(name = "vencimento")
	private Date vencimento;

	@Column(name = "parcelas")
	@Resolvable(colName = "Parcelas")
	private Integer parcelas;

	

	@Column(name = "valor")
	@Resolvable(colName = "Valor")
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "vendaPre_codigo")
	private PreVenda venda;

	@ManyToOne
	@JoinColumn(name = "cliente_codigo")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "bandeira_codigo")
	private BandeiraCartao bandeira; // -> Tipo do cartao - credito / debito

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public PreVenda getVenda() {
		return venda;
	}

	public void setVenda(PreVenda venda) {
		this.venda = venda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public String getNomeCartao() {
		return nomeCartao;
	}

	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public BandeiraCartao getBandeira() {
		return bandeira;
	}

	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}

}
