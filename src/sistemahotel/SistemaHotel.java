/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemahotel;

import java.util.HashMap;
import java.util.Random;
import sistemahotel.Reserva.QuartoIndisponivelException;

/**
 *
 * @author 2024020399
 */
import java.util.Scanner;

public class SistemaHotel {
    Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);
    private static HospedeDAO hospedeDAO = new HospedeDAO();
    

    public static void main(String[] args) {
    Hospede h1 = new Hospede("Maria Silva", "12345678900", "maria");
    Hospede h2 = new Hospede("João Souza", "98765432100", "joao");
    Hospede h3 = new Hospede("Ana Costa", "45678912300", "ana");
    Hospede h4 = new Hospede("Carlos Lima", "78912345600", "carlos");
    

    hospedeDAO.inserir(h1);
    hospedeDAO.inserir(h2);
    hospedeDAO.inserir(h3);
    hospedeDAO.inserir(h4);
    
    int opcaoMenuPrincipal;

        do {
            System.out.println("Bem-vindo ao Sistema do Hotel".toUpperCase());
            System.out.println("1 - Sou Cliente");
            System.out.println("2 - Sou Funcionario");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcaoMenuPrincipal = Integer.parseInt(scanner.nextLine());

            switch (opcaoMenuPrincipal) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuFuncionario();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Obrigado!");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        } while (opcaoMenuPrincipal != 0);

        scanner.close();
    }

    private static void menuCliente() {
        System.out.println("\n=== Login do Cliente ===");
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        Hospede hospede = autenticarHospede(email, senha);

        if (hospede != null) {
            System.out.println("Login realizado com sucesso, " + hospede.getNome() + "!\n");
            int opcaoCliente;
            do {
                System.out.println("=== Menu Cliente ===");
                System.out.println("1 - Fazer Check-in (Confirmar Reserva)");
                System.out.println("2 - Ver Status da Reserva");
                System.out.println("0 - Logout");
                System.out.print("Escolha uma opcao: ");
                opcaoCliente = Integer.parseInt(scanner.nextLine());

                switch (opcaoCliente) {
                    case 1:
                        fazerCheckIn(hospede);
                        break;
                    case 2:
                        mostrarReservasDoHospede(hospede);
                        break;
                    case 0:
                        System.out.println("Logout realizado.\n");
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                }
            } while (opcaoCliente != 0);
        } else {
            System.out.println("Falha na autenticacao. Email ou senha incorretos.\n");
        }
    }

    private static Hospede autenticarHospede(String email, String senha) {
        Hospede hospede = hospedeDAO.buscarPorEmail(email);
        if (hospede != null && hospede.getCpf().equals(senha)) {
            return hospede;
        }
        return null;
    }

    private static void fazerCheckIn(Hospede hospede) {
        System.out.println("\n=== Fazer Check-in ===");
        System.out.print("Digite o numero da reserva: ");
        int numeroReserva = Integer.parseInt(scanner.nextLine());
        Reserva reserva = Reserva.buscarReserva(numeroReserva);
        if (reserva.getStatus() == StatusReserva.CANCELADA) {
        System.out.println("Nao eh possivel fazer check-in em uma reserva CANCELADA.");
        return;
        }

        if (reserva != null && reserva.getHospede().equals(hospede)) {
            try {
                reserva.confirmarReserva();
                System.out.println("Check-in realizado com sucesso!");
            } catch (Reserva.QuartoIndisponivelException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Reserva nao encontrada para este hospede.");
        }
    }

    private static void mostrarReservasDoHospede(Hospede hospede) {
        System.out.println("\n=== Minhas Reservas ===");
        boolean encontrou = false;
        for (Reserva reserva : Reserva.getReservas().values()) {
            if (reserva.getHospede().equals(hospede)) {
                System.out.println("Reserva #" + reserva.getNumeroReserva() +
                        " - Quarto: " + reserva.getQuarto().getNumero() +
                        " - Status: " + reserva.getStatus());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma reserva encontrada.");
        }
    }

    private static void menuFuncionario() {
        int opcaoFuncionario;
        do {
            System.out.println("\n=== Menu Funcionario ===");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Fazer Reserva para Cliente");
            System.out.println("3 - Alterar Reserva do Cliente");
            System.out.println("4 - Cancelar Reserva");
            System.out.println("5 - Listar Reservas");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opcao: ");
            opcaoFuncionario = Integer.parseInt(scanner.nextLine());

            switch (opcaoFuncionario) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    fazerReservaParaCliente();
                    break;
                case 3:
                    alterarReserva();
                    break;
                case 4:
                    cancelarReserva();
                    break;
                case 5:
                    listarReservas();
                    break;
                case 0:
                    System.out.println("Logout realizado.\n");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcaoFuncionario != 0);
    }

    private static void cadastrarCliente() {
        System.out.println("\n=== Cadastro de Cliente ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Hospede hospede = new Hospede(nome, cpf, email);
        // Armazena o hóspede no HashMap para autenticação futura
        hospedeDAO.inserir(hospede);
    }

    private static void fazerReservaParaCliente() {
    System.out.println("\n=== Fazer Reserva para Cliente ===");

    System.out.print("Numero da reserva: ");
    int numReserva = Integer.parseInt(scanner.nextLine());

    System.out.print("Numero do quarto: ");
    int numQuarto = Integer.parseInt(scanner.nextLine());

    System.out.println("Escolha o tipo de quarto:");
    System.out.println("1 - Simples");
    System.out.println("2 - Luxo");
    System.out.println("3 - Suite");
    System.out.print("Opcao: ");
    int tipoEscolhido = Integer.parseInt(scanner.nextLine());

    TipoQuarto tipo;
    double preco;
    switch (tipoEscolhido) {
        case 1:
            tipo = TipoQuarto.SIMPLES;
            preco = 100;
            break;
        case 2:
            tipo = TipoQuarto.LUXO;
            preco = 200;
            break;
        case 3:
            tipo = TipoQuarto.SUITE;
            preco = 300;
            break;
        default:
            System.out.println("Opcao invalida. Tipo padrao SIMPLES selecionado.");
            tipo = TipoQuarto.SIMPLES;
            preco = 100;
    }

    System.out.print("Quantos dias o cliente ficara hospedado? ");
    int dias = Integer.parseInt(scanner.nextLine());

    System.out.print("Email do cliente: ");
    String emailCliente = scanner.nextLine();

    Hospede hospede = hospedeDAO.buscarPorEmail(emailCliente);

    if (hospede == null) {
        System.out.println("Cliente nao encontrado!");
        return;
    }

    Quarto quarto = new Quarto(numQuarto, tipo, preco);
    Reserva reserva = new Reserva(numReserva, quarto, hospede, StatusReserva.PENDENTE, dias);
    hospedeDAO.adicionarReserva(reserva);

    System.out.println("Reserva criada com sucesso para " + hospede.getNome());
    }

       
    private static void cadastrarFuncionario() {
        System.out.println("\n=== Cadastro de Funcionario ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        


        Funcionario funcionario = new Funcionario(nome, cpf, email, cargo);
        System.out.println("Funcionario cadastrado com sucesso: " + funcionario.getNome());
        
    }

    private static void alterarReserva() {
        System.out.println("\n=== Alterar Reserva ===");
        System.out.print("Numero da reserva: ");
        int numReserva = Integer.parseInt(scanner.nextLine());
        Reserva reserva = Reserva.buscarReserva(numReserva);

        if (reserva != null) {
            System.out.print("Novo número de dias: ");
            int dias = Integer.parseInt(scanner.nextLine());
            reserva.setDias(dias);
            System.out.println("Escolha o tipo de quarto:");
            System.out.println("1 - Simples");
            System.out.println("2 - Luxo");
            System.out.println("3 - Suite");
            System.out.print("Opcao: ");
            int tipoEscolhido = Integer.parseInt(scanner.nextLine());

            TipoQuarto tipo;
            double preco;
            switch (tipoEscolhido) {
                case 1:
                    tipo = TipoQuarto.SIMPLES;
                    break;
                case 2:
                    tipo = TipoQuarto.LUXO;
                    break;
                case 3:
                    tipo = TipoQuarto.SUITE;
                    break;
                default:
                    System.out.println("Opcao invalida. Tipo padrao SIMPLES selecionado.");
                    tipo = TipoQuarto.SIMPLES;
                
            System.out.println("Reserva alterada com sucesso.");
            }
        }else{
            System.out.println("Reserva nao encontrada.");
        }
    }

    private static void cancelarReserva() {
        System.out.println("\n=== Cancelar Reserva ===");
        System.out.print("Número da reserva: ");
        int numReserva = Integer.parseInt(scanner.nextLine());
        Reserva reserva = Reserva.buscarReserva(numReserva);

        if (reserva != null) {
            reserva.cancelarReserva();
            System.out.println("Reserva cancelada com sucesso.");
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    private static void listarReservas() {
        System.out.println("\n=== Lista de Reservas ===");
        if (Reserva.getReservas().isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
        } else {
            for (Reserva r : Reserva.getReservas().values()) {
                
                System.out.println("Reserva #" + r.getNumeroReserva() +
                        "\n - Hospede: " + r.getHospede().getNome() +
                        "\n - Quarto: " + r.getQuarto().getNumero() +
                        "\n - Dias: " + r.getDias()+
                        "\n - Status: " + r.getStatus()+
                        "\n - Valor: " + r.calcularPrecoTotal());
            }
        }
    }
}

