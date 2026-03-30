package recifecultural.schedule.domain;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Evento {
    private final UUID id;
    private UUID promotor;
    private UUID local;

    private String titulo;
    private String descricaoCurta;
    private String descricaoLonga;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    private final List<LocalDateTime> datasApresentacao;

    private URI enderecoIngresso;

    private BigDecimal precoInteira;
    private BigDecimal precoMeia;
    private String precoSocial;

    public Evento(
            UUID promotor,
            UUID local,
            String titulo,
            String descricaoCurta,
            String descricaoLonga,
            LocalDateTime dataInicio,
            LocalDateTime dataFim,
            URI enderecoIngresso,
            BigDecimal precoInteira,
            BigDecimal precoMeia,
            String precoSocial
    ) {
        this.id = UUID.randomUUID();
        this.promotor = promotor;
        this.local = local;
        setTitulo(titulo);
        this.descricaoCurta = descricaoCurta;
        this.descricaoLonga = descricaoLonga;

        validarDatas(dataInicio, dataFim);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

        this.datasApresentacao = new ArrayList<>();
        this.enderecoIngresso = enderecoIngresso;

        validarPrecos(precoInteira, precoMeia);
        this.precoInteira = precoInteira;
        this.precoMeia = precoMeia;
        this.precoSocial = precoSocial;
    }

    public void setTitulo(String titulo) {
        if(titulo == null || titulo.trim().equals("")) {
            throw new IllegalArgumentException("Título não pode estar em branco");
        }
        this.titulo = titulo;
    }

    public void adicionarDataApresentacao(LocalDateTime dataHora) {
        if (dataHora == null) throw new IllegalArgumentException("Data de apresentação não pode ser nula.");
        if (dataHora.isBefore(dataInicio) || dataHora.isAfter(dataFim)) {
            throw new IllegalArgumentException("A data deve estar entre a data de início e a de fim do evento.");
        }
        this.datasApresentacao.add(dataHora);
    }

    public void removerDataApresentacao(LocalDateTime dataHora) {
        this.datasApresentacao.remove(dataHora);
    }

    public boolean isApresentacaoUnica() {
        return this.datasApresentacao.size() == 1;
    }

    public void atualizarPrecos(BigDecimal precoInteira, BigDecimal precoMeia, String precoSocial) {
        validarPrecos(precoInteira, precoMeia);
        this.precoInteira = precoInteira;
        this.precoMeia = precoMeia;
        this.precoSocial = precoSocial;
    }

    private void validarDatas(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio != null && fim != null && fim.isBefore(inicio)) {
            throw new IllegalArgumentException("A data de fim não pode ser antes da data de início do evento.");
        }
    }

    private void validarPrecos(BigDecimal inteira, BigDecimal meia) {
        if (inteira != null && inteira.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("O preço da entrada não pode ser negativo.");
        if (meia != null && meia.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("O preço da meia entrada não pode ser negativo.");
    }

    public UUID getId() { return id; }
    public UUID getPromotor() { return promotor; }
    public UUID getLocal() { return local; }
    public String getTitulo() { return titulo; }
    public String getDescricaoCurta() { return descricaoCurta; }
    public String getDescricaoLonga() { return descricaoLonga; }
    public LocalDateTime getDataInicio() { return dataInicio; }
    public LocalDateTime getDataFim() { return dataFim; }
    public URI getEnderecoIngresso() { return enderecoIngresso; }
    public BigDecimal getPrecoInteira() { return precoInteira; }
    public BigDecimal getPrecoMeia() { return precoMeia; }
    public String getPrecoSocial() { return precoSocial; }

    public List<LocalDateTime> getDatasApresentacao() {
        return Collections.unmodifiableList(datasApresentacao);
    }
}