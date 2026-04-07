import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public static CardLayout displayLayout;
    public static JPanel displayPanel;
    public static String currentPanel = "";

    public static JScrollPane TWLScrollPanel;
    public static JScrollPane KTLScrollPanel;
    public static JScrollPane TKLScrollPanel;
    public static JScrollPane ISLScrollPanel;

    MyFrame(){

        this.setTitle("Signaling Equipment Management System (SEMS)");
        this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(850,500));
        //this.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("src/softwareicon.png");
        this.setIconImage(image.getImage());

        //setLayout(null);
        //setBounds(100,100,645,470);
        MenuBar menuBar = new MenuBar();


        this.setJMenuBar(menuBar);

        displayLayout = new CardLayout();
        displayPanel = new JPanel(displayLayout);
        TWLPanel TWLPanel = new TWLPanel();
        TWLScrollPanel = new JScrollPane(TWLPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        KTLPanel KTLPanel = new KTLPanel();
        KTLScrollPanel = new JScrollPane(KTLPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        TKLPanel TKLPanel = new TKLPanel();
        TKLScrollPanel = new JScrollPane(TKLPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        ISLPanel ISLPanel = new ISLPanel();
        ISLScrollPanel = new JScrollPane(ISLPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        EmptyPanel emptyPanel = new EmptyPanel();

        displayPanel.add(emptyPanel, "");
        displayPanel.add(TWLScrollPanel, "TWL");
        displayPanel.add(KTLScrollPanel, "KTL");
        displayPanel.add(TKLScrollPanel, "TKL");
        displayPanel.add(ISLScrollPanel, "ISL");
        displayPanel.setPreferredSize(new Dimension(1000, 500));

        this.add(displayPanel, BorderLayout.CENTER);


        LinePanel linePanel = new LinePanel();
        this.add(linePanel, BorderLayout.SOUTH);




        ControlPanel controlPanel = new ControlPanel();
        this.add(controlPanel, BorderLayout.EAST);




        this.pack();
        //setLocationByPlatform(true);
        this.setVisible(true);
    }

    public static void changeDisplay(String display) {
        displayLayout.show(displayPanel, display);
        if (!display.equals("")) {
            currentPanel = display;
        }

    }
}
