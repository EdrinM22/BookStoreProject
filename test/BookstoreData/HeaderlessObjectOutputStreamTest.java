package BookstoreData;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HeaderlessObjectOutputStreamTest {

    @Test
    void testWriteStreamHeaderOverride() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             HeaderlessObjectOutputStream headerlessObjectOutputStream = new HeaderlessObjectOutputStream(
                     byteArrayOutputStream)) {

            headerlessObjectOutputStream.writeStreamHeader();

        } catch (IOException e) {
            fail("IOException should not be thrown for writing stream header");
        }
    }

    @Test
    void testWriteObjectWithoutHeader() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             HeaderlessObjectOutputStream headerlessObjectOutputStream = new HeaderlessObjectOutputStream(
                     byteArrayOutputStream)) {

            headerlessObjectOutputStream.writeObject("Test Object");

        } catch (IOException e) {
            fail("IOException should not be thrown for writing object without header");
        }
    }

    @Test
    void testWriteMultipleObjectsWithoutHeader() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             HeaderlessObjectOutputStream headerlessObjectOutputStream = new HeaderlessObjectOutputStream(
                     byteArrayOutputStream)) {

            headerlessObjectOutputStream.writeObject("Object 1");
            headerlessObjectOutputStream.writeObject("Object 2");
            headerlessObjectOutputStream.writeObject("Object 3");

        } catch (IOException e) {
            fail("IOException should not be thrown for writing multiple objects without header");
        }
    }

    @Test
    void testWriteStreamHeaderNotCalled() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             HeaderlessObjectOutputStream headerlessObjectOutputStream = new HeaderlessObjectOutputStream(
                     byteArrayOutputStream)) {

            headerlessObjectOutputStream.writeStreamHeader();

        } catch (IOException e) {
            fail("IOException should not be thrown for direct invocation of writeStreamHeader");
        }
    }

}