package br.com.crud.model;

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
@SequenceGenerator(name = "PreVenda_SEQ", sequenceName = "PreVenda_SEQ")
@Table(name = "PreVenda")
@SuppressWarnings("serial")
public class PreVenda implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PreVenda_SEQ")
	@Column(name = "codigo")
	@Resolvable(colName = "Codigo")
	private Integer codigo;
	
	@Column(name = "valorTotal")
	@Resolvable(colName = "valorTotal")
	private Double valorTotal;
	
	@Column(name = "qtdItens")
	private Double qtdItens;
	
	@Column(name = "cancelado")
	private String cancelado;
	
	@Column(name = "causaCancel")
	private String causaCancel;
	
	@Column(name = "dataVenda")
	private Date dataVenda;
	
	@ManyToOne
	@JoinColumn(name = "cliente_codigo") //relacionamento do Cliente com as pre-vendas
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "Vendedor_codigo") //relacionamento do Vendedor com as pre-vendas
	private Vendedor Vendedor;
	
	
	
	

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return Vendedor;
	}

	public void setVendedor(Vendedor Vendedor) {
		this.Vendedor = Vendedor;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(Double qtdItens) {
		this.qtdItens = qtdItens;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public String getCausaCancel() {
		return causaCancel;
	}

	public void setCausaCancel(String causaCancel) {
		this.causaCancel = causaCancel;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	

	
	

}
