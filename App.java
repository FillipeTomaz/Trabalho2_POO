import java.util.Scanner;

public class App {
    public static Gestão gestao = new Gestão();
    public static Atendimento atendimento = new Atendimento();
    
    public static void main(String[] args) throws Exception {
        init();
        exibirMenuPrincipal();
    }

    public static void init() {
        // Inicialização com dados de exemplo
        gestao.Estacionamento = new Vagas[50];
        gestao.tarifa = 5.0f;
        gestao.hora = 2.0f;
        
        // Cadastrar vagas iniciais
        for(int i = 0; i < 10; i++) {
            gestao.Estacionamento[i] = new Vagas(i+1, Veiculo.Tipo.carro, false, null);
        }
    }

    public static void exibirMenuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1 - Módulo de Gestão");
            System.out.println("2 - Módulo de Atendimento");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch(opcao) {
                case 1:
                    gestao.menuGestao();
                    break;
                case 2:
                    atendimento.fluxo();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while(opcao != 3);
    }
}
