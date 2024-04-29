package model;

public interface Taxable {

    final double IVA = 0.19;
    final double RETEFUENTE = 0.04;

    public double calcularImpuesto(double valor);

}
