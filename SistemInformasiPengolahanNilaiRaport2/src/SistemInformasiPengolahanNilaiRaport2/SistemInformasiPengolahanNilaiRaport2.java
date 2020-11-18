
package SistemInformasiPengolahanNilaiRaport2;

import sistem.Login;
import sistem.Screen;

/**
 *
 * @author fidia
 */
public class SistemInformasiPengolahanNilaiRaport2 {

    
    public static void main(String[] args) {
        Screen a = new Screen ();
        a.setVisible(true);
        Login b = new Login();
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(25);
                a.txtProgress.setText("Loading.. "+Integer.toString(i)+"%");
                a.mainProgress.setValue(i);
                if (i==100) {
                    a.setVisible(false);
                    b.setVisible(true);
                }
            }
        } catch (Exception e) {
        }
    }
    
}
