package br.com.fiap.Vagou.vo;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public class SalarioVO {
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal value;

    private String moeda = "BRL";

    public SalarioVO() {}

    public SalarioVO(BigDecimal value, String moeda) {
        this.value = value;
        this.moeda = moeda;
    }

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }

    public String getMoeda() { return moeda; }
    public void setMoeda(String moeda) { this.moeda = moeda; }

    public boolean isValid() {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }
}