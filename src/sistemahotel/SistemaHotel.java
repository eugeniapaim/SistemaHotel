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
    private static HashMap<String, Hospede> hospedesCadastrados = new HashMap<>();

    public static void main(String[] args) {
        int opcaoMenuPrincipal;

        do {
            System.out.println("Bem-vindo ao Sistema do Hotel");
            System.out.println("1 - Sou Cliente");
            System.out.println("2 - Sou Funcionário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
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
                    System.out.println("Opção inválida! Tente novamente.");
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

        // Autenticação simplificada: vamos buscar hóspede pelo email e validar senha
        // Supondo que a senha seja o CPF do hóspede para exemplo (pode adaptar depois)
        Hospede hospede = autenticarHospede(email, senha);

        if (hospede != null) {
            System.out.println("Login realizado com sucesso, " + hospede.getNome() + "!\n");
            int opcaoCliente;
            do {
                System.out.println("=== Menu Cliente ===");
                System.out.println("1 - Fazer Check-in (Confirmar Reserva)");
                System.out.println("2 - Ver Status da Reserva");
                System.out.println("0 - Logout");
                System.out.print("Escolha uma opção: ");
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
                        System.out.println("Opção inválida.");
                }
            } while (opcaoCliente != 0);
        } else {
            System.out.println("Falha na autenticação. Email ou senha incorretos.\n");
        }
    }

    private static Hospede autenticarHospede(String email, String senha) {
        Hospede hospede = hospedesCadastrados.get(email.toLowerCase());
        if (hospede != null && hospede.getCpf().equals(senha)) {
            return hospede;
        }
        return null;
    }

    private static void fazerCheckIn(Hospede hospede) {
        System.out.println("\n=== Fazer Check-in ===");
        System.out.print("Digite o número da reserva: ");
        int numeroReserva = Integer.parseInt(scanner.nextLine());
        Reserva reserva = Reserva.buscarReserva(numeroReserva);

        if (reserva != null && reserva.getHospede().equals(hospede)) {
            try {
                reserva.confirmarReserva();
                System.out.println("Check-in realizado com sucesso!");
            } catch (Reserva.QuartoIndisponivelException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Reserva não encontrada para este hóspede.");
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
            System.out.println("\n=== Menu Funcionário ===");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Fazer Reserva para Cliente");
            System.out.println("3 - Cadastrar Funcionário");
            System.out.println("4 - Alterar Reserva do Cliente");
            System.out.println("5 - Cancelar Reserva");
            System.out.println("6 - Listar Reservas");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opção: ");
            opcaoFuncionario = Integer.parseInt(scanner.nextLine());

            switch (opcaoFuncionario) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    fazerReservaParaCliente();
                    break;
                case 3:
                    cadastrarFuncionario();
                    break;
                case 4:
                    alterarReserva();
                    break;
                case 5:
                    cancelarReserva();
                    break;
                case 6:
                    listarReservas();
                    break;
                case 0:
                    System.out.println("Logout realizado.\n");
                    break;
                default:
                    System.out.println("Opção inválida.");
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
        int numReservas = (int) (Math.random() * 100);
        System.out.println("O numero da sua reserva eh: "+numReservas);

        Hospede hospede = new Hospede(nome, cpf, email, numReservas);
        // Armazena o hóspede no HashMap para autenticação futura
        hospedesCadastrados.put(email.toLowerCase(), hospede);
    }

    private static void fazerReservaParaCliente() {
        System.out.println("\n=== Fazer Reserva para Cliente ===");
        System.out.print("Numero da reserva: ");
        int numReserva = Integer.parseInt(scanner.nextLine());

        System.out.print("Numero do quarto: ");
        int numQuarto = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Email do cliente: ");
        String emailCliente = scanner.nextLine();

        // Para simplificar, criando um hóspede novo (pode buscar um existente)
        Hospede hospede = hospedesCadastrados.get(emailCliente.toLowerCase());
        
        if(hospede == null){
            System.out.println("Cliente nao encontrado!");
            return;
        }

        // Criar o quarto (deveria buscar um quarto existente em uma lista)
        Quarto quarto = new Quarto(numQuarto, TipoQuarto.SIMPLES, 150);

        // Criar reserva com status pendente e 1 dia (exemplo)
        Reserva reserva = new Reserva(numReserva, quarto, hospede, StatusReserva.PENDENTE, 1);

        System.out.println("Reserva criada com sucesso para o cliente " + nomeCliente);
    }

    private static void cadastrarFuncionario() {
        System.out.println("\n=== Cadastro de Funcionário ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();

        Funcionario funcionario = new Funcionario(nome, cpf, email, cargo);
        System.out.println("Funcionário cadastrado com sucesso: " + funcionario.getNome());
        // Implementar armazenamento real conforme necessário
    }

    private static void alterarReserva() {
        System.out.println("\n=== Alterar Reserva ===");
        System.out.print("Número da reserva: ");
        int numReserva = Integer.parseInt(scanner.nextLine());
        Reserva reserva = Reserva.buscarReserva(numReserva);

        if (reserva != null) {
            System.out.print("Novo número de dias: ");
            int dias = Integer.parseInt(scanner.nextLine());
            reserva.setDias(dias);
            System.out.println("Reserva alterada com sucesso.");
        } else {
            System.out.println("Reserva não encontrada.");
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
                        " - Hóspede: " + r.getHospede().getNome() +
                        " - Quarto: " + r.getQuarto().getNumero() +
                        " - Dias: " + r.getDias()+
                        " - Status: " + r.getStatus()+
                        " - Valor: " + r.calcularPrecoTotal());
            }
        }
    }
}

