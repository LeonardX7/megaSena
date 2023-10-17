package megaSena.megaSena;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class methods {
    Scanner scanner = new Scanner(System.in);
    utils utils = new utils();

    public void lerArquivo(String caminhoArquivo, int opcao) {
        try {
            FileInputStream arquivo = new FileInputStream(new File(caminhoArquivo));
            try (Workbook workbook = new XSSFWorkbook(arquivo)) {
				Sheet sheet = workbook.getSheetAt(0);

				Map < Integer, Integer > contagemNumeros = new HashMap < > ();
				int concursosSemAcertador = 0;
				double menorValorPago = Double.MAX_VALUE;
				BigDecimal maiorValorPago = new BigDecimal(Double.MIN_VALUE);
				
				switch (opcao) {
				    case 1:
				        contagemNumeros.clear();

				        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				            Row row = sheet.getRow(rowIndex);

				            for (int columnIndex = 2; columnIndex <= 7; columnIndex++) {
				                Cell cell = row.getCell(columnIndex);

				                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
				                    int numeroSorteado = (int) cell.getNumericCellValue();
				                    contagemNumeros.put(numeroSorteado, contagemNumeros.getOrDefault(numeroSorteado, 0) + 1);
				                }
				            }
				        }

				        for (int numero = 1; numero <= 60; numero++) {
				            int contagem = contagemNumeros.getOrDefault(numero, 0);
				            System.out.println("Número " + numero + " foi sorteado " + contagem + " vezes.");
				        }
				        break;

				    case 2:
				        concursosSemAcertador = 0;
				        
				        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				            Row row = sheet.getRow(rowIndex);
				            Cell cell = row.getCell(8);

				            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
				                int valorColunaI = (int) cell.getNumericCellValue();
				                if (valorColunaI == 0) {
				                    concursosSemAcertador++;
				                }
				            }
				        }
				        
				        System.out.println("Quantidade de concursos sem acertador: " + concursosSemAcertador);
				        break;

				    case 3:
				        menorValorPago = Double.MAX_VALUE; 

				        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				            Row row = sheet.getRow(rowIndex);
				            Cell cell = row.getCell(14); 

				            if (cell != null && cell.getCellType() == CellType.STRING) {
				                String valorPagoStr = cell.getStringCellValue();
				                double valorPago = utils.parseValorPago(valorPagoStr);
				                if (valorPago < menorValorPago) {
				                    menorValorPago = valorPago;
				                }
				            }
				        }

				        System.out.println("Menor valor pago para apostas com 4 dezenas sorteadas: R$" + menorValorPago);
				        break;
				        
				    case 4:
				        menorValorPago = Double.MAX_VALUE; 

				        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				            Row row = sheet.getRow(rowIndex);
				            Cell cell = row.getCell(12); 

				            if (cell != null && cell.getCellType() == CellType.STRING) {
				                String valorPagoStr = cell.getStringCellValue();
				                double valorPago = utils.parseValorPago(valorPagoStr);
				                if (valorPago < menorValorPago) {
				                    menorValorPago = valorPago;
				                }
				            }
				        }

				        System.out.println("Menor valor pago para apostas com 5 dezenas sorteadas: R$" + menorValorPago);
				        break;
				        
				    case 5:
				        menorValorPago = Double.MAX_VALUE; 

				        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				            Row row = sheet.getRow(rowIndex);
				            Cell cell = row.getCell(10); 

				            if (cell != null && cell.getCellType() == CellType.STRING) {
				                String valorPagoStr = cell.getStringCellValue();
				                double valorPago = utils.parseValorPago(valorPagoStr);
				                if (valorPago < menorValorPago) {
				                    menorValorPago = valorPago;
				                }
				            }
				        }

				        System.out.println("Menor valor pago para apostas com 6 dezenas sorteadas: R$" + menorValorPago);
				        break;  
				        
				    case 6:
				        maiorValorPago = new BigDecimal(Double.MIN_VALUE);

				        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				            Row row = sheet.getRow(rowIndex);
				            Cell cell = row.getCell(14);

				            if (cell != null && cell.getCellType() == CellType.STRING) {
				                String valorPagoStr = cell.getStringCellValue();
				                BigDecimal valorPago = utils.parseValorPagoBig(valorPagoStr);
				                if (valorPago.compareTo(maiorValorPago) > 0) {
				                    maiorValorPago = valorPago;
				                }
				            }
				        }
				        
				        System.out.println("Maior valor pago para apostas com 4 dezenas sorteadas: R$" + maiorValorPago);
				        break;

				    case 7:
				        maiorValorPago = new BigDecimal(Double.MIN_VALUE);

				        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				            Row row = sheet.getRow(rowIndex);
				            Cell cell = row.getCell(12);

				            if (cell != null && cell.getCellType() == CellType.STRING) {
				                String valorPagoStr = cell.getStringCellValue();
				                BigDecimal valorPago = utils.parseValorPagoBig(valorPagoStr);
				                if (valorPago.compareTo(maiorValorPago) > 0) {
				                    maiorValorPago = valorPago;
				                }
				            }
				        }
				        
				        System.out.println("Maior valor pago para apostas com 4 dezenas sorteadas: R$" + maiorValorPago);
				        break;
				        
				    case 8:
				        maiorValorPago = new BigDecimal(Double.MIN_VALUE);

				        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				            Row row = sheet.getRow(rowIndex);
				            Cell cell = row.getCell(10);

				            if (cell != null && cell.getCellType() == CellType.STRING) {
				                String valorPagoStr = cell.getStringCellValue();
				                BigDecimal valorPago = utils.parseValorPagoBig(valorPagoStr);
				                if (valorPago.compareTo(maiorValorPago) > 0) {
				                    maiorValorPago = valorPago;
				                }
				            }
				        }
				        
				        System.out.println("Maior valor pago para apostas com 4 dezenas sorteadas: R$" + maiorValorPago);
				        break;
				    case 9:
				    	utils.verificarDezenasSorteadas(sheet);
				    case 10:
				        int[] sorteioAleatorio = utils.gerarSorteioAleatorio();
				        System.out.print("Dezenas sorteadas aleatoriamente: ");
				        for (int numero : sorteioAleatorio) {
				            System.out.print(numero + " ");
				        }
				        System.out.println();
				        int numeroConcursoSorteado = utils.verificarDezenasSorteio(sheet, sorteioAleatorio);
				        if (numeroConcursoSorteado != -1) {
				            System.out.println("Número do concurso sorteado: #" + numeroConcursoSorteado);
				        }
				        break;

				    case 11:
					break;

				    default:
				        System.out.println("Opção inválida. Tente novamente.");
				        break;
				}
			}
            arquivo.close();
            System.out.println("Programa encerrado.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao ler o arquivo.");
        }
    }
}