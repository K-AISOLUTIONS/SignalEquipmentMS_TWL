import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EmptyPanel extends JPanel{
    EmptyPanel(){



        //this.setSize(1200,700);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.WHITE);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

    }
    public void actionPerformed(ActionEvent e) {

    }
}
