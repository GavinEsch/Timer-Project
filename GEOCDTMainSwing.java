import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GEOCDTMainSwing extends JPanel {

    private JMenuItem quitItem;

    public GEOCDTMainSwing(JMenuItem quitItem) {
        this.quitItem = quitItem;

        JPanel panel = new JPanel();
        panel.add(new GEOCDTPanelSwing());
        add(panel);
        panel.add(new GEOCDTPanelSwing());
        add(panel);
        panel.add(new GEOCDTPanelSwing());
        add(panel);

        quitItem.addActionListener(new Mylistener());
    }

    private class Mylistener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == quitItem){
                System.exit(1);
            }

        }

    }
}
