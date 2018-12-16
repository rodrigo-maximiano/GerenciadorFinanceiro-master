package sdm.ifsp.edu.br.gerenciadorfinanceiro.model;

import java.util.Date;

public class TransacaoEntity {

    private Integer id;
    private ContaEntity conta;
    private String operacao;
    private String tipoOperacao;
    private String descricao;
    private Long valor;
    private Date dtCriacao;

    public TransacaoEntity() {
    }

    public TransacaoEntity(ContaEntity conta, String operacao, String tipoOperacao, Long valor, String descricao, Date dtCriacao) {
        this.conta = conta;
        this.operacao = operacao;
        this.tipoOperacao = tipoOperacao;
        this.valor = valor;
        this.descricao = descricao;
        this.dtCriacao = dtCriacao;
    }

    public ContaEntity getConta() {
        return conta;
    }

    public void setConta(ContaEntity conta) {
        this.conta = conta;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TransacaoEntity{");
        sb.append("id=").append(id);
        sb.append(", conta=").append(conta);
        sb.append(", operacao=").append(operacao);
        sb.append(", tipoOperacao=").append(tipoOperacao);
        sb.append(", descricao=").append(descricao);
        sb.append(", valor=").append(valor);
        sb.append(", dtCriacao=").append(dtCriacao);
        return sb.toString();
    }
}
