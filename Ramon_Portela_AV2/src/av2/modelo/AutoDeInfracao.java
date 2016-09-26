package av2.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AutoDeInfracao {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	private Processo processo;
	
	private int gravidade;
	private boolean atenuante;
	private boolean agravante;
	private BigDecimal multa;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	public int getGravidade() {
		return gravidade;
	}
	public void setGravidade(int gravidade) {
		this.gravidade = gravidade;
	}
	public boolean isAtenuante() {
		return atenuante;
	}
	public void setAtenuante(boolean atenuante) {
		this.atenuante = atenuante;
	}
	public boolean isAgravante() {
		return agravante;
	}
	public void setAgravante(boolean agravante) {
		this.agravante = agravante;
	}
	public BigDecimal getMulta() {
		return multa;
	}
	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}
}
