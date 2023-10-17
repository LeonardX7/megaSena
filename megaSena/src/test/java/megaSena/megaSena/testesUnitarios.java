package megaSena.megaSena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.*;

public class testesUnitarios {
	methods methods = new methods();
	utils utils = new utils();
	@Test
	public void testCaso10_GeracaoDe6Dezenas() {
	    int[] sorteioAleatorio = utils.gerarSorteioAleatorio();

	    assertEquals(6, sorteioAleatorio.length);
	}
	
	@Test
	public void testCaso10_GeracaoDe5Dezenas() {
	    int[] sorteioAleatorio = utils.gerarSorteioAleatorio();

	    assertEquals(5, sorteioAleatorio.length);
	}
	
    @Test
    public void testCaso9_InserindoMenosDe6Dezenas() {
        String entrada = "10 20 30";
        Sheet sheet = null;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        methods.caso9(sheet);

        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("Você deve inserir exatamente 6 dezenas."));
    }
}
