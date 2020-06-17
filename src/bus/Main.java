package bus;

/**
 *
 * @author chiedo
 */
import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.JFrame;

public class Main implements Runnable{
    private final JFrame frame;
    public Main(JFrame frm){
        this.frame=frm;
    }//constructor closed
    public void run(){
        frame.setVisible(true);
    }//run method closed
    public static void main(String args[]) throws SQLException{
        new FormSplash(3000);
        EventQueue.invokeLater(new Main(new LoginScreen()));
    }//main method closed

}//class closed
