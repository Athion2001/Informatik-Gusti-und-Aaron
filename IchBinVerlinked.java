import java.awt.Desktop;
import java.io.File;
public class IchBinVerlinked {
    public static void main(String[] args) {
        System.out.println("ich bin verlinked hihi und (schwul) es hat geklappt");
    }
    public void huhu() throws Exception {
            File bild = new File(
            "H:\\Pictures\\Screenshots\\Screenshot 2026-09-07 140321.png"
        );

        while (true) {
            Desktop.getDesktop().open(bild);

            Thread.sleep(2000); // 2 Sekunden warten
        }
    

}
}

