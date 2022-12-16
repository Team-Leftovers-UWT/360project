/*
 * @Author Masse G.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class Iteration2Test {
    @Test
    public void testUploadButtonActionListener() {
        Iteration2 uploader = new Iteration2();
        uploader.getImportButton().doClick();
        assertTrue(uploader.getImportButton().isEnabled());
    }

    @Test
    public void testSaveButtonActionListener() {
        Iteration2 uploader = new Iteration2();
        uploader.getExportButton().doClick();
        assertTrue(uploader.getExportButton().isEnabled());
    }

    @Test
    public void testDeleteButtonActionListener() {
        Iteration2 uploader = new Iteration2();
        uploader.getDeleteButton().doClick();
        assertTrue(uploader.getDeleteButton().isEnabled());
    }
}
