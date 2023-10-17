package megaSena.megaSena;

import java.util.Scanner;

public class megaSena {

    public static void main(String[] args) {
    	String caminhoArquivo = "C:\\Users\\Usuario\\Downloads\\Mega-Sena.xlsx";
        Scanner scanner = new Scanner(System.in);
    	methods methods = new methods();
    	
        System.out.println("Menu:");
        System.out.println("1. Número de vezes que cada número foi sorteado");
        System.out.println("2. Quantos concursos não houve apostador que acertou as 6 dezenas");
        System.out.println("3. Menor valor pago para apostas com 4 dezenas sorteadas");
        System.out.println("4. Menor valor pago para apostas com 5 dezenas sorteadas");
        System.out.println("5. Menor valor pago para apostas com 5 dezenas sorteadas");
        System.out.println("6. Maior valor pago para apostas com 4 dezenas sorteadas");
        System.out.println("7. Maior valor pago para apostas com 5 dezenas sorteadas");
        System.out.println("8. Maior valor pago para apostas com 6 dezenas sorteadas");
        System.out.println("9. Inserir 6 dezenas e verificar se já foram sorteadas");
        System.out.println("10. Gerar 6 dezenas aleatórias");
        System.out.println("11. Sair");
		System.out.print("Escolha uma opção: ");
		int opcao = scanner.nextInt();

        methods.lerArquivo(caminhoArquivo, opcao);
    }
}
