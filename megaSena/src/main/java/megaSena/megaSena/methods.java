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

                switch (opcao) {
                    case 1:
                        caso1(sheet);
                        break;

                    case 2:
                        caso2(sheet);
                        break;

                    case 3:
                        caso3(sheet);
                        break;

                    case 4:
                        caso4(sheet);
                        break;

                    case 5:
                        caso5(sheet);
                        break;

                    case 6:
                        caso6(sheet);
                        break;

                    case 7:
                        caso7(sheet);
                        break;

                    case 8:
                        caso8(sheet);
                        break;

                    case 9:
                        caso9(sheet);
                        break;

                    case 10:
                        caso10(sheet);
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

    private void caso1(Sheet sheet) {
        Map<Integer, Integer> contagemNumeros = new HashMap<>();

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            for (int columnIndex = 3; columnIndex <= 8; columnIndex++) {
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
    }

    private void caso2(Sheet sheet) {
        int concursosSemAcertador = 0;

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
    }

    private void caso3(Sheet sheet) {
        double menorValorPago = Double.MAX_VALUE;

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
    }

    private void caso4(Sheet sheet) {
        double menorValorPago = Double.MAX_VALUE;

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
    }

    private void caso5(Sheet sheet) {
        double menorValorPago = Double.MAX_VALUE;

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
    }

    private void caso6(Sheet sheet) {
        BigDecimal maiorValorPago = new BigDecimal(Double.MIN_VALUE);

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
    }

    private void caso7(Sheet sheet) {
        BigDecimal maiorValorPago = new BigDecimal(Double.MIN_VALUE);

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
    }

    private void caso8(Sheet sheet) {
        BigDecimal maiorValorPago = new BigDecimal(Double.MIN_VALUE);

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
    }
    
    void caso9(Sheet sheet) {
    	System.out.print("Digite as 6 dezenas separadas por espaço: ");
        String entrada = scanner.nextLine();
        String[] numerosStr = entrada.split(" ");
        int[] numerosInseridos = new int[numerosStr.length];

        for (int i = 0; i < numerosStr.length; i++) {
            try {
                numerosInseridos[i] = Integer.parseInt(numerosStr[i]);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira números válidos.");
                break;
            }
        }

        int resultado = utils.verificarDezenasSorteadas(sheet, numerosInseridos);
    }

    private void caso10(Sheet sheet) {
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
    }
}
