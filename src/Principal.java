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
        System.out.println("1 - Cadastrar Abastecimento");
        System.out.println("2 - Relatorio de Consumo");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    public static void cadastrarAbastecimento(Scanner entrada) {}

    public static double calcularConsumoMedio(double km, double lit) {
        if (lit == 0) return 0;
        return km / lit;
    }

    public static double calcularCustoPorKm(double valor, double km) {
        if (km == 0) return 0;
        return valor / km;
    }
    public static void exibirRelatorioConsumo() {}
}