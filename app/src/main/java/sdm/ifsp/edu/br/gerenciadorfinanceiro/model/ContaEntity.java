package sdm.ifsp.edu.br.gerenciadorfinanceiro.model;

public class ContaEntity {

    private String descricao;
    private Long saldo;

    public ContaEntity() {
    }

    public ContaEntity(String descricao, Long saldo) {
        this.descricao = descricao;
        this.saldo = saldo;
    }

    public ContaEntity(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ContaEntity{").append("descricao='").append(descricao).append(", saldo=");
        sb.append(saldo).append("}");
       return sb.toString();
    }
}
