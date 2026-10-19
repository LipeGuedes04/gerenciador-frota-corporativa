package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Carro;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Carro> frota = new ArrayList<>();

		System.out.println("========== LOCADORA ===========");

		int escolha = 0;

		while (escolha != 5) {

			System.out.println("\n=== MENU PRINCIPAL ===");
			System.out.println("1 - Cadastrar Novo Carro");
			System.out.println("2 - Listar Frota Completa");
			System.out.println("3 - Simular Aluguel por Placa");
			System.out.println("4 - Confirmar Locação por Placa");
			System.out.println("5 - Sair do programa");
			System.out.print("Escolha uma opção: ");
			escolha = sc.nextInt();
			sc.nextLine();

			switch (escolha) {
			case 1:
				System.out.println("\n--- CADASTRO DE VEÍCULO ---");
				System.out.print("Qual o modelo do carro: ");
				String modelo = sc.nextLine();
				System.out.print("Qual a placa do carro: ");
				String placa = sc.nextLine();
				System.out.print("Qual o preço da diária: ");
				double diaria = sc.nextDouble();
				sc.nextLine();

				frota.add(new Carro(modelo, placa, diaria));
				System.out.println("✅ Veículo cadastrado com sucesso!");
				break;

			case 2:
				System.out.println("\n--- FROTA CADASTRADA ---");
				if (frota.isEmpty()) {
					System.out.println("⚠️ A frota está vazia. Cadastre um carro primeiro!");
				} else {
					for (Carro c : frota) {
						System.out.println(c);
					}
				}
				break;

			case 3:
				System.out.println("\n--- SIMULAR ALUGUEL POR PLACA ---");
				if (frota.isEmpty()) {
					System.out.println("⚠️ A frota está vazia. Cadastre um carro primeiro!");
				} else {
					System.out.print("Digite a placa do veículo que deseja simular: ");
					String placaBusca = sc.nextLine();

					Carro carroEncontrado = null;

					for (Carro c : frota) {
						if (c.getPlaca().equalsIgnoreCase(placaBusca)) {
							carroEncontrado = c;
							break;
						}
					}

					if (carroEncontrado == null) {
						System.out.println("❌ Erro: Veículo com a placa " + placaBusca + " não foi encontrado.");
					} else {
						System.out.print("Quantos dias a pessoa quer alugar? ");
						int dias = sc.nextInt();
						sc.nextLine();

						double valorTotal = carroEncontrado.calcularAluguel(dias);
						System.out.printf("Valor total do aluguel simulado: R$ %.2f%n", valorTotal);
					}
				}
				break;

			case 4:
				System.out.println("\n--- CONFIRMAR LOCAÇÃO POR PLACA ---");
				if (frota.isEmpty()) {
					System.out.println("⚠️ A frota está vazia. Cadastre um carro primeiro!");
				} else {
					System.out.print("Digite a placa do veículo que deseja ALUGAR: ");
					String placaBusca = sc.nextLine();

					Carro carroEncontrado = null;

					for (Carro c : frota) {
						if (c.getPlaca().equalsIgnoreCase(placaBusca)) {
							carroEncontrado = c;
							break;
						}
					}

					if (carroEncontrado == null) {
						System.out.println("❌ Erro: Veículo com a placa " + placaBusca + " não foi encontrado.");
					} else {

						if (carroEncontrado.alugarCarro()) {
							System.out.println("✅ Carro alugado com sucesso!");
						} else {
							System.out.println("❌ Erro: Este carro já está alugado!");
						}
					}
				}
				break;

			case 5:
				System.out.println("Saindo do sistema... Obrigado!");
				break;

			default:
				System.out.println("❌ Opção inválida!");
				break;
			}
		}

		sc.close();
	}
}
