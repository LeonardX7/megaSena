package megaSena.megaSena;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class utils {
    double parseValorPago(String valorPagoStr) {
        try {
            valorPagoStr = valorPagoStr.replaceAll("[^\\d.,]", "");
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
            Number number = numberFormat.parse(valorPagoStr);
            return number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return Double.MAX_VALUE;
        }
    }
    
    BigDecimal parseValorPagoBig(String valorPagoStr) {
        try {
            valorPagoStr = valorPagoStr.replaceAll("[^\\d.,]", "");
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
            Number number = numberFormat.parse(valorPagoStr);
            return new BigDecimal(number.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return new BigDecimal(Double.MAX_VALUE);
        }
    }
    
    public int verificarDezenasSorteadas(Sheet sheet) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite as 6 dezenas separadas por espaço: ");
        String entrada = scanner.nextLine();
        String[] numerosStr = entrada.split(" ");
        if (numerosStr.length != 6) {
            System.out.println("Você deve inserir exatamente 6 dezenas.");
            return -1;
        }

        int[] numerosInseridos = new int[6];
        for (int i = 0; i < 6; i++) {
            try {
                numerosInseridos[i] = Integer.parseInt(numerosStr[i]);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira números válidos.");
                return -1; 
            }
        }

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            boolean sorteioEncontrado = true;

            for (int columnIndex = 2; columnIndex <= 7; columnIndex++) {
                Cell cell = row.getCell(columnIndex);

                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    int numeroSorteado = (int) cell.getNumericCellValue();
                    if (Arrays.binarySearch(numerosInseridos, numeroSorteado) < 0) {
                        sorteioEncontrado = false;
                        break;
                    }
                }
            }

            if (sorteioEncontrado) {
                Cell numeroConcursoCell = row.getCell(0);
                int numeroConcurso = (int) numeroConcursoCell.getNumericCellValue();
                System.out.println("As dezenas foram sorteadas no concurso #" + numeroConcurso);
                return numeroConcurso;
            }
        }

        System.out.println("Essas dezenas nunca foram sorteadas.");
        return -1; 
    }
    
    public int[] gerarSorteioAleatorio() {
        Random random = new Random();
        int[] sorteio = new int[6];

        for (int i = 0; i < 6; i++) {
            sorteio[i] = random.nextInt(60) + 1;
        }

        return sorteio;
    }
    
    public int verificarDezenasSorteio(Sheet sheet, int[] numerosInseridos) {
        if (numerosInseridos == null || numerosInseridos.length != 6) {
            System.out.println("Por favor, insira 6 dezenas válidas.");
            return -1;
        }

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            boolean sorteioEncontrado = true;

            for (int columnIndex = 2; columnIndex <= 7; columnIndex++) {
                Cell cell = row.getCell(columnIndex);

                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    int numeroSorteado = (int) cell.getNumericCellValue();
                    if (Arrays.binarySearch(numerosInseridos, numeroSorteado) < 0) {
                        sorteioEncontrado = false;
                        break;
                    }
                }
            }

            if (sorteioEncontrado) {
                Cell numeroConcursoCell = row.getCell(0);
                int numeroConcurso = (int) numeroConcursoCell.getNumericCellValue();
                System.out.println("As dezenas foram sorteadas no concurso #" + numeroConcurso);
                return numeroConcurso; 
            }
        }

        System.out.println("Essas dezenas nunca foram sorteadas.");
        return -1; 
    }



}
