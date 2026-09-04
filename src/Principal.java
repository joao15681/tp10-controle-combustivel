java
import java.util.Scanner;

public class Principal {
    static final int CAPACIDADE = 20;
    static String[] placas = new String[CAPACIDADE];
    static double[] litros = new double[CAPACIDADE];
    static double[] valoresPagos = new double[CAPACIDADE];
    static double[] quilometragens = new double[CAPACIDADE];
    static int totalRegistros = 0;

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcao = -1;
        do {
            exibirCabecalho();
            exibirMenu();
            try {
                opcao = Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }
            switch (opcao) {
                case 1: cadastrarAbastecimento(entrada); break;
                case 2: exibirRelatorioConsumo(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
        entrada.close();
    }

    public static void exibirCabecalho() {
        System.out.println("=== CONTROLE DE COMBUSTIVEL ===");
    }

    public static void exibirMenu() {
        System.out.println("*** SISTEMA DE FROTA E COMBUSTIVEL ***");
        System.out.println("[1] Cadastrar Novo Abastecimento");
        System.out.println("[2] Exibir Eficiencia e Relatorio");
        System.out.println("[0] Fechar Sistema");
        System.out.print("Opcao desejada: ");
    }

    public static void cadastrarAbastecimento(Scanner entrada) {
        if (totalRegistros >= CAPACIDADE) {
            System.out.println("Erro: Capacidade maxima de registros atingida!");
            return;
        }
        System.out.println("\nCadastro de Abastecimento");

        System.out.print("Digite a placa do veiculo: ");
        String placa = entrada.nextLine();

        double lit = -1;
        while (lit <= 0) {
            System.out.print("Digite a quantidade de litros: ");
            try {
                lit = Double.parseDouble(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida!");
            }
        }

        double valor = -1;
        while (valor <= 0) {
            System.out.print("Digite o valor total pago (R$): ");
            try {
                valor = Double.parseDouble(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida!");
            }
        }

        double km = -1;
        while (km <= 0) {
            System.out.print("Digite a quilometragem percorrida (km): ");
            try {
                km = Double.parseDouble(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida!");
            }
        }

        placas[totalRegistros] = placa;
        litros[totalRegistros] = lit;
        valoresPagos[totalRegistros] = valor;
        quilometragens[totalRegistros] = km;
        totalRegistros++;

        System.out.println("Abastecimento cadastrado com sucesso!\n");
    }

    public static double calcularConsumoMedio(double km, double lit) {
        if (lit == 0) return 0;
        return km / lit;
    }

    public static double calcularCustoPorKm(double valor, double km) {
        if (km == 0) return 0;
        return valor / km;
    }

    public static void exibirRelatorioConsumo() {
        if (totalRegistros == 0) {
            System.out.println("\nNenhum abastecimento cadastrado ainda.\n");
            return;
        }

        System.out.println("\n==========================================================================");
        System.out.println("                       RELATORIO DE CONSUMO E FROTA                       ");
        System.out.println("==========================================================================");
        System.out.printf("%-10s | %-10s | %-12s | %-10s | %-10s | %-10s\n", 
                          "PLACA", "LITROS", "VALOR (R$)", "KM", "KM/L", "R$/KM");
        System.out.println("--------------------------------------------------------------------------");

        double totalLitros = 0;
        double totalValor = 0;
        double totalKm = 0;
        int indiceMaisEconomico = 0;
        double melhorKmL = -1;

        for (int i = 0; i < totalRegistros; i++) {
            double kmL = calcularConsumoMedio(quilometragens[i], litros[i]);
            double rsKm = calcularCustoPorKm(valoresPagos[i], quilometragens[i]);

            totalLitros += litros[i];
            totalValor += valoresPagos[i];
            totalKm += quilometragens[i];

            if (kmL > melhorKmL) {
                melhorKmL = kmL;
                indiceMaisEconomico = i;
            }

            System.out.printf("%-10s | %-10.2f | R$ %-9.2f | %-10.2f | %-10.2f | R$ %-8.2f\n",
                              placas[i], litros[i], valoresPagos[i], quilometragens[i], kmL, rsKm);
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("TOTAIS FROTA: Litros: %.2fL | Custo: R$ %.2f | Km Total: %.2f km\n", 
                          totalLitros, totalValor, totalKm);
        System.out.println("VEICULO MAIS ECONOMICO: " + placas[indiceMaisEconomico] + 
                           " (" + String.format("%.2f", melhorKmL) + " km/L)");
        System.out.println("==========================================================================\n");
    }
}