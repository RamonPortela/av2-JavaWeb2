package av2.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import av2.DAO.DAO;
import av2.modelo.Fornecedor;
import av2.modelo.Processo;

@ManagedBean
@SessionScoped
public class ProcessoBean {

	private Processo processo;
	
	private Integer processoId;
	
	private boolean editar;
	private boolean podeInserir;
	
	public Integer getProcessoId() {
		return processoId;
	}
	
	public void setProcessoId(Integer processoId){
		this.processoId = processoId;
	}
	
	public Processo getProcesso(){
		
		if(processo == null)
			return new Processo();
		
		return processo;
	}
	
	public void setProcesso(Processo processo){
		this.processo = processo;
	}
	
	public List<Processo> getProcessos(){
		return new DAO<Processo>(Processo.class).listar();
	}
	
	public boolean getEditar(){
		return editar;
	}
	
	public String editarProcesso(boolean editar, Processo processo){
		if(processo == null)
			this.processo = new Processo();
		else
			this.processo = processo;
		
		this.editar = editar;
		
		return "cadastro-processo.xhtml?faces-redirect=true";
	}
	
	public boolean getPodeInserir(){
		return podeInserir;
	}
	
	public void setPodeInserir(boolean podeInserir){
		this.podeInserir = podeInserir;
	}
	public void deletarProcesso(Processo processo){
		addMessage("Ação concluída", "Processo de número: " + processo.getNumeroProcesso() + ", excluído com sucesso!");
		
		new DAO<Processo>(Processo.class).remover(processo);
	}
	
	public String salvar(){		
		if(processo.getId() != null)
			new DAO<Processo>(Processo.class).atualizar(processo, processo.getFornecedor().getCnpj());
		else{
			new DAO<Processo>(Processo.class).adicionar(processo, processo.getFornecedor().getCnpj());
		}
		
		podeInserir = false;
		
		return "processo.xhtml";
	}
	
	public void pesquisarFornecedor(String cnpj){
		@SuppressWarnings("unused")
		Fornecedor f = new DAO<Fornecedor>(Fornecedor.class).buscarCnpj(cnpj);
		
		if(f != null){
			this.processo.setFornecedor(f);
			podeInserir = true;
		}
		else{
			this.processo.setFornecedor(new Fornecedor());
			podeInserir = false;
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg2').show();");
	}
	
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
}
