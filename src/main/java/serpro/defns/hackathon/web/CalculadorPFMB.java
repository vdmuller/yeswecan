package serpro.defns.hackathon.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import serpro.defns.hackathon.enums.TipoFuncaoEnum;
import serpro.defns.hackathon.servicos.CalculadorPFBO;
import serpro.defns.hackathon.vo.EntidadeVO;

@ViewController
public class CalculadorPFMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private CalculadorPFBO calculadorBO = new CalculadorPFBO();

	private String texto;
	private int valorTotal;

	private int totalALI;
	private int totalAIE;
	private int totalEE;
	private int totalSE;
	private int totalCE;

	private List<EntidadeVO> entidadesALI;
	private List<EntidadeVO> entidadesAIE;
	private List<EntidadeVO> entidadesEE;
	private List<EntidadeVO> entidadesSE;
	private List<EntidadeVO> entidadesCE;

	public String getTexto() {
		return texto;
	}

	@PostConstruct
	private void init() {
		texto = "";
		setValorTotal(0);
		entidadesALI = new ArrayList<>();
		entidadesAIE = new ArrayList<>();
		entidadesEE = new ArrayList<>();
		entidadesSE = new ArrayList<>();
		entidadesCE = new ArrayList<>();
	}

	public void processar() {
		List<EntidadeVO> entidades = calculadorBO.processarTexto(getTexto());

		entidadesALI = entidades.stream()
		        .filter(p -> p.getTipo().equals(TipoFuncaoEnum.ALI))
		        .collect(Collectors.toList());
		entidadesAIE = entidades.stream()
		        .filter(p -> p.getTipo().equals(TipoFuncaoEnum.AIE))
		        .collect(Collectors.toList());
		entidadesEE = entidades.stream()
		        .filter(p -> p.getTipo().equals(TipoFuncaoEnum.EE))
		        .collect(Collectors.toList());
		entidadesSE = entidades.stream()
		        .filter(p -> p.getTipo().equals(TipoFuncaoEnum.SE))
		        .collect(Collectors.toList());
		entidadesCE = entidades.stream()
		        .filter(p -> p.getTipo().equals(TipoFuncaoEnum.CE))
		        .collect(Collectors.toList());

		atualizarTotal();
	}

	public void atualizarTotal() {
		valorTotal = 0;

		totalALI = 0;
		totalAIE = 0;
		totalEE = 0;
		totalSE = 0;
		totalCE = 0;

		entidadesALI.stream().forEach(p -> totalALI += p.getPontos());
		entidadesAIE.stream().forEach(p -> totalAIE += p.getPontos());
		entidadesEE.stream().forEach(p -> totalEE += p.getPontos());
		entidadesSE.stream().forEach(p -> totalSE += p.getPontos());
		entidadesCE.stream().forEach(p -> totalCE += p.getPontos());

		valorTotal = totalALI + totalAIE + totalEE + totalSE + totalCE;
	}

	public void processarComplexidade(EntidadeVO entidade) {
		calculadorBO.calcularPF(entidade);
		atualizarTotal();
	}

	public void removerEntidadeALI(EntidadeVO entidade) {
		entidadesALI.remove(entidade);
		atualizarTotal();
	}

	public void removerEntidadeAIE(EntidadeVO entidade) {
		entidadesAIE.remove(entidade);
		atualizarTotal();
	}

	public void removerEntidadeEE(EntidadeVO entidade) {
		entidadesEE.remove(entidade);
		atualizarTotal();
	}

	public void removerEntidadeSE(EntidadeVO entidade) {
		entidadesSE.remove(entidade);
		atualizarTotal();
	}

	public void removerEntidadeCE(EntidadeVO entidade) {
		entidadesCE.remove(entidade);
		atualizarTotal();
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<EntidadeVO> getEntidadesALI() {
		return entidadesALI;
	}

	public void setEntidadesALI(List<EntidadeVO> entidadesALI) {
		this.entidadesALI = entidadesALI;
	}

	public List<EntidadeVO> getEntidadesAIE() {
		return entidadesAIE;
	}

	public void setEntidadesAIE(List<EntidadeVO> entidadesAIE) {
		this.entidadesAIE = entidadesAIE;
	}

	public List<EntidadeVO> getEntidadesEE() {
		return entidadesEE;
	}

	public void setEntidadesEE(List<EntidadeVO> entidadesEE) {
		this.entidadesEE = entidadesEE;
	}

	public List<EntidadeVO> getEntidadesSE() {
		return entidadesSE;
	}

	public void setEntidadesSE(List<EntidadeVO> entidadesSE) {
		this.entidadesSE = entidadesSE;
	}

	public List<EntidadeVO> getEntidadesCE() {
		return entidadesCE;
	}

	public void setEntidadesCE(List<EntidadeVO> entidadesCE) {
		this.entidadesCE = entidadesCE;
	}

	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getTotalALI() {
		return totalALI;
	}

	public void setTotalALI(int totalALI) {
		this.totalALI = totalALI;
	}

	public int getTotalAIE() {
		return totalAIE;
	}

	public void setTotalAIE(int totalAIE) {
		this.totalAIE = totalAIE;
	}

	public int getTotalEE() {
		return totalEE;
	}

	public void setTotalEE(int totalEE) {
		this.totalEE = totalEE;
	}

	public int getTotalSE() {
		return totalSE;
	}

	public void setTotalSE(int totalSE) {
		this.totalSE = totalSE;
	}

	public int getTotalCE() {
		return totalCE;
	}

	public void setTotalCE(int totalCE) {
		this.totalCE = totalCE;
	}

}
