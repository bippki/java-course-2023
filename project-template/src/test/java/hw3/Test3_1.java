package hw3;

import edu.hw3.Task3_1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3_1 {
    @Test
    public void testOrdinalWork() {
        assertEquals("Svool dliow!", Task3_1.atbash("Hello world!"));
        assertEquals("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
            Task3_1.atbash("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler"));
    }

    @Test
    public void testRandomCharacters() {
        assertEquals("Умоляю, не меняй меня!", Task3_1.atbash("Умоляю, не меняй меня!"));
        assertEquals("Лучше выверни наизнанку этого -> What ?", Task3_1.atbash("Лучше выверни наизнанку этого -> Dszg ?"));
    }


    @Test
    public void testSpecialCharacters() {
        // Цифры и специальные символы не должны изменяться
        assertEquals("123 !@#", Task3_1.atbash("123 !@#"));
    }
}
