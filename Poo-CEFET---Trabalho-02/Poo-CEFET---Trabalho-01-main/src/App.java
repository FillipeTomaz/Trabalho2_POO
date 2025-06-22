import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Patio patio = Patio.getInstance();
        Atendimento atendimento = new Atendimento();
        init(patio);
        exibirMenuPrincipal(patio, atendimento);
    }

    public static void init(Patio patio) {
        // Cadastrar vagas iniciais
        for(int i = 0; i < 10; i++) {
            patio.adicionarVaga(Veiculo.Tipo.carro);
        }
    }

    public static void exibirMenuPrincipal(Patio patio, Atendimento atendimento) {
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
                    patio.menuGestao();
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