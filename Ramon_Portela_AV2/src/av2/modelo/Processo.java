package av2.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Processo {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	private String relatoFiscalizacao;
	private Date dataRelato;
	private String fiscalResponsavel;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getRelatoFiscalizacao() {
		return relatoFiscalizacao;
	}
	public void setRelatoFiscalizacao(String relatoFiscalizacao) {
		this.relatoFiscalizacao = relatoFiscalizacao;
	}
	public Date getDataRelato() {
		return dataRelato;			
	}
	public void setDataRelato(Date dataRelato) {
		this.dataRelato = dataRelato;
	}
	public String getFiscalResponsavel() {
		return fiscalResponsavel;
	}
	public void setFiscalResponsavel(String fiscalResponsavel) {
		this.fiscalResponsavel = fiscalResponsavel;
	}
	public String getNumeroProcesso(){
		
		Calendar calendario = new GregorianCalendar();
		calendario.setTime(this.dataRelato);
		
		return String.valueOf(calendario.get(Calendar.YEAR)) + String.valueOf(calendario.get(Calendar.MONTH) + 1)
				+ String.valueOf(calendario.get(Calendar.DAY_OF_MONTH)) + "-" + String.valueOf(calendario.get(Calendar.HOUR_OF_DAY))
				+ String.valueOf(calendario.get(Calendar.MINUTE)) + String.valueOf(calendario.get(Calendar.SECOND))
				+ "-" + this.getFornecedor().getCnpj();
	}
	
	public Processo(){
		this.fornecedor = new Fornecedor();
	}
	
}
