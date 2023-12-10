package Orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyTest {
    @Test
    void testGetName() {
        Buy buy = new Buy("SampleName", 100.0, System.currentTimeMillis());
        assertEquals("SampleName", buy.getName());
    }

    @Test
    void testGetTotalPrice() {
        Buy buy = new Buy("SampleName", 150.0, System.currentTimeMillis());
        assertEquals(150.0, buy.getTotalPrice());
    }

    @Test
    void testGetTime() {
        long currentTime = System.currentTimeMillis();
        Buy buy = new Buy("SampleName", 200.0, currentTime);
        assertEquals(currentTime, buy.getTime());
    }

}