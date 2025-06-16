import java.util.Scanner;

public class Atendimento {
    private Scanner sc = new Scanner(System.in);
    private Patio patio = Patio.getInstance();

    public void fluxo() {
        System.out.println("\n=== Módulo de Atendimento ===");
        System.out.println("1 - Entrada de veículo");
        System.out.println("2 - Saída de veículo");
        System.out.println("3 - Voltar");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();
        
        switch(opcao) {
            case 1:
                registrarEntrada();
                break;
            case 2:
                registrarSaida();
                break;
            case 3:
                return;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void registrarEntrada() {
        System.out.print("Placa do veículo: ");
        String placa = sc.next().toUpperCase();
        
        Veiculo veiculo = patio.buscarVeiculo(placa);
        
        // Se veículo não existe, cadastrar
        if(veiculo == null) {
            veiculo = cadastrarVeiculo(placa);
            if(veiculo == null) return;
        }
        
        // Buscar vaga disponível
        Vaga vaga = patio.buscarVagaLivre(veiculo.getTipo());
        if(vaga == null) {
            System.out.println("Nenhuma vaga disponível para este tipo!");
            return;
        }
        
        // Ocupar vaga
        if(vaga.ocupar(veiculo)) {
            System.out.println("Veículo estacionado na vaga " + vaga.getId());
        } else {
            System.out.println("Erro ao ocupar vaga!");
        }
    }

    private Veiculo cadastrarVeiculo(String placa) {
        System.out.println("=== Cadastro de Novo Veículo ===");
        System.out.print("Modelo: ");
        String modelo = sc.next();
        System.out.print("Cor: ");
        String cor = sc.next();
        System.out.print("Marca: ");
        String marca = sc.next();
        System.out.print("Tipo (moto/carro/utilitário): ");
        Veiculo.Tipo tipo = Veiculo.Tipo.valueOf(sc.next().toLowerCase());
        
        System.out.print("CPF do proprietário: ");
        String cpf = sc.next();
        Cliente cliente = patio.buscarCliente(cpf);
        
        if(cliente == null) {
            System.out.println("Cliente não encontrado! Cadastre o cliente primeiro.");
            return null;
        }
        
        Veiculo novo = new Veiculo(placa, modelo, cor, marca, tipo);
        cliente.adicionarVeiculo(novo);
        return novo;
    }

    private void registrarSaida() {
        System.out.print("Placa do veículo: ");
        String placa = sc.next().toUpperCase();
        
        Veiculo veiculo = patio.buscarVeiculo(placa);
        if(veiculo == null) {
            System.out.println("Veículo não encontrado!");
            return;
        }
        
        // Procurar vaga ocupada pelo veículo
        Vaga vagaOcupada = null;
        for(Vaga v : patio.getVaga()) {
            if(v.getEstado() == Vaga.Estado.OCUPADA && 
               v.getVeiculo() != null && 
               v.getVeiculo().getPlaca().equals(placa)) {
                vagaOcupada = v;
                break;
            }
        }
        
        if(vagaOcupada == null) {
            System.out.println("Veículo não está estacionado!");
            return;
        }
        
        float valor = vagaOcupada.liberar();
        System.out.printf("Valor a pagar: R$ %.2f%n", valor);
        System.out.println("Saída registrada!");
    }
}