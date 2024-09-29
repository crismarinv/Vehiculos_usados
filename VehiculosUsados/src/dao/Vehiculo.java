package dao;

public class Vehiculo {

    private String placa;
    private String tipo;
    private String marca;
    private String modelo;
    private int ano;
    private int numeroEjes;
    private double cilindrada;
    private double valor;

    public Vehiculo(String placa, String tipo, String marca, String modelo, int ano, int numeroEjes, double cilindrada, double valor) {
        this.placa = placa;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.numeroEjes = numeroEjes;
        this.cilindrada = cilindrada;
        this.valor = valor;
    }

    // Getters
    public String getPlaca() {
        return placa;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public int getNumeroEjes() {
        return numeroEjes;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public double getValor() {
        return valor;
    }

    // Setters
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setNumeroEjes(int numeroEjes) {
        this.numeroEjes = numeroEjes;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", tipo='" + tipo + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", numeroEjes=" + numeroEjes +
                ", cilindrada=" + cilindrada +
                ", valor=" + valor +
                '}';
    }
}