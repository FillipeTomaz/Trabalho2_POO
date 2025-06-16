public class Veiculo {

    enum Tipo{
        moto,
        carro,
        utilitário
    }

    String placa;
    String modelo;
    String cor;
    String marca;
    Tipo tipo;


    public Veiculo(String placa, String modelo, String cor, String marca, Veiculo.Tipo tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.marca = marca;
        this.tipo = tipo;
    }



    
    
}
