package bus;

/**
 *
 * @author chiedo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class FormSplash {

    private final JLabel SplashImage;
    private final JLabel SplashText;
    private final JWindow window;
    private final JPanel panel;
    public FormSplash(int duration) {
        window=new JWindow();               
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds((screen.width - 490) / 2, (screen.height - 300) / 2,400, 300);        
        
        panel=(JPanel)window.getContentPane();
        SplashImage = new JLabel(new ImageIcon(ClassLoader.getSystemResource("images/splash.jpg")));
        SplashText=new JLabel("Bus Route",SwingConstants.CENTER);
        panel.add(SplashImage, BorderLayout.CENTER);
        panel.add(SplashText,BorderLayout.SOUTH);
        
        window.setVisible(true);
        try{
            Thread.sleep(duration);            
        }catch(Exception ex){            
        }//try catch closed
        window.setVisible(false);
        window.dispose();
    }//constructr closed

}//class closed
