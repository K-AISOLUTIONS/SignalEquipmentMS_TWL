import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener {

    public static JButton crossingDataButton;
    public static JButton switchDataButton;
    public static JButton floodgateDataButton;
    public static JButton signalButton; public static JButton signalDataButton;
    public static JButton impedanceBondButton; public static JButton impedanceBondDataButton;
    public static JButton beaconButton; public static JButton beaconDataButton;
    public static JButton tagButton; public static JButton tagDataButton;
    public static JButton sabButton; public static JButton sabDataButton;
    public static JButton boxButton; public static JButton boxDataButton;
    public static JButton otherButton; public static JButton otherDataButton;
    public static JButton allButton; public static JButton allDataButton;



    public static JComboBox<String> stationDropdown;
    public static JComboBox<String> equipmentDropdown;
    public static JComboBox<String> idDropdown;
    public static JButton searchButton;



    ControlPanel(){
        //this.setLayout(null);
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setLayout(new GridLayout(0, 2));
        this.setLayout(new GridLayout(0, 1));

        JLabel showEquipmentLabel = new JLabel("Show Equipment");
        showEquipmentLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        this.add(showEquipmentLabel);

        signalButton = new JButton("Show Signal");
        signalButton.addActionListener(this);
        signalButton.setEnabled(false);
        this.add(signalButton);


        impedanceBondButton = new JButton("Show Impedance Bond");
        impedanceBondButton.addActionListener(this);
        impedanceBondButton.setEnabled(false);
        this.add(impedanceBondButton);

        beaconButton = new JButton("Show Beacon");
        beaconButton.addActionListener(this);
        beaconButton.setEnabled(false);
        this.add(beaconButton);

        tagButton = new JButton("Show Tag");
        tagButton.addActionListener(this);
        tagButton.setEnabled(false);
        this.add(tagButton);

        sabButton = new JButton("Show SAB");
        sabButton.addActionListener(this);
        sabButton.setEnabled(false);
        this.add(sabButton);

        boxButton = new JButton("Show Box");
        boxButton.addActionListener(this);
        boxButton.setEnabled(false);
        this.add(boxButton);

        otherButton = new JButton("Show Other");
        otherButton.addActionListener(this);
        otherButton.setEnabled(false);
        this.add(otherButton);





        JLabel showLocationLabel = new JLabel("Show Equipment Location");
        showLocationLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        this.add(showLocationLabel);

        crossingDataButton = new JButton("Show Crossing Location");
        crossingDataButton.addActionListener(this);
        crossingDataButton.setEnabled(false);
        this.add(crossingDataButton);

        switchDataButton = new JButton("Show Switch Location");
        switchDataButton.addActionListener(this);
        switchDataButton.setEnabled(false);
        this.add(switchDataButton);


        floodgateDataButton = new JButton("Show Floodgate Location");
        floodgateDataButton.addActionListener(this);
        floodgateDataButton.setEnabled(false);
        this.add(floodgateDataButton);


        signalDataButton = new JButton("Show Signal Location");
        signalDataButton.addActionListener(this);
        signalDataButton.setEnabled(false);
        this.add(signalDataButton);


        impedanceBondDataButton = new JButton("Show Impedance Bond Location");
        impedanceBondDataButton.addActionListener(this);
        impedanceBondDataButton.setEnabled(false);
        this.add(impedanceBondDataButton);



        beaconDataButton = new JButton("Show Beacon Location");
        beaconDataButton.addActionListener(this);
        beaconDataButton.setEnabled(false);
        this.add(beaconDataButton);

        tagDataButton = new JButton("Show Tag Location");
        tagDataButton.addActionListener(this);
        tagDataButton.setEnabled(false);
        this.add(tagDataButton);


        sabDataButton = new JButton("Show SAB Location");
        sabDataButton.addActionListener(this);
        sabDataButton.setEnabled(false);
        this.add(sabDataButton);


        boxDataButton = new JButton("Show Box Location");
        boxDataButton.addActionListener(this);
        boxDataButton.setEnabled(false);
        this.add(boxDataButton);


        otherDataButton = new JButton("Show Other Location");
        otherDataButton.addActionListener(this);
        otherDataButton.setEnabled(false);
        this.add(otherDataButton);









        JLabel searchBarLabel = new JLabel("Search Equipment");
        searchBarLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(searchBarLabel);

        JLabel enterStationLabel = new JLabel("Choose the Station: ");
        this.add(enterStationLabel);
        stationDropdown = new JComboBox<>(new String[] {});
        stationDropdown.addActionListener(this);
        this.add(stationDropdown);

        JLabel enterEquipmentLabel = new JLabel("Choose the Equipment: ");
        this.add(enterEquipmentLabel);
        equipmentDropdown = new JComboBox<>(new String[] {});
        equipmentDropdown.addActionListener(this);
        this.add(equipmentDropdown);


        JLabel enterIdLabel = new JLabel("Choose the ID: ");
        this.add(enterIdLabel);
        idDropdown = new JComboBox<>(new String[] {});
        idDropdown.addActionListener(this);
        this.add(idDropdown);



        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        //searchButton.setEnabled(false);
        this.add(searchButton);




        //this.setBounds(1200,0,300,700);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.LIGHT_GRAY);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signalButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowSignal = !TWLPanel.isShowSignal;

                if (TWLPanel.isShowSignal){
                    signalButton.setText("Hide Signal");
                    signalDataButton.setEnabled(true);
                } else {
                    signalButton.setText("Show Signal");
                    signalDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowSignal = !KTLPanel.isShowSignal;

                if (KTLPanel.isShowSignal){
                    signalButton.setText("Hide Signal");
                    signalDataButton.setEnabled(true);
                } else {
                    signalButton.setText("Show Signal");
                    signalDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowSignal = !TKLPanel.isShowSignal;

                if (TKLPanel.isShowSignal){
                    signalButton.setText("Hide Signal");
                    signalDataButton.setEnabled(true);
                } else {
                    signalButton.setText("Show Signal");
                    signalDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowSignal = !ISLPanel.isShowSignal;

                if (ISLPanel.isShowSignal){
                    signalButton.setText("Hide Signal");
                    signalDataButton.setEnabled(true);
                } else {
                    signalButton.setText("Show Signal");
                    signalDataButton.setEnabled(false);
                }
            }

            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == impedanceBondButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowImpedanceBond = !TWLPanel.isShowImpedanceBond;

                if (TWLPanel.isShowImpedanceBond){
                    impedanceBondButton.setText("Hide Impedance Bond");
                    impedanceBondDataButton.setEnabled(true);
                } else {
                    impedanceBondButton.setText("Show Impedance Bond");
                    impedanceBondDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowImpedanceBond = !KTLPanel.isShowImpedanceBond;

                if (KTLPanel.isShowImpedanceBond){
                    impedanceBondButton.setText("Hide Impedance Bond");
                    impedanceBondDataButton.setEnabled(true);
                } else {
                    impedanceBondButton.setText("Show Impedance Bond");
                    impedanceBondDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowImpedanceBond = !TKLPanel.isShowImpedanceBond;

                if (TKLPanel.isShowImpedanceBond){
                    impedanceBondButton.setText("Hide Impedance Bond");
                    impedanceBondDataButton.setEnabled(true);
                } else {
                    impedanceBondButton.setText("Show Impedance Bond");
                    impedanceBondDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowImpedanceBond = !ISLPanel.isShowImpedanceBond;

                if (ISLPanel.isShowImpedanceBond){
                    impedanceBondButton.setText("Hide Impedance Bond");
                    impedanceBondDataButton.setEnabled(true);
                } else {
                    impedanceBondButton.setText("Show Impedance Bond");
                    impedanceBondDataButton.setEnabled(false);
                }
            }

            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == beaconButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowBeacon = !TWLPanel.isShowBeacon;

                if (TWLPanel.isShowBeacon){
                    beaconButton.setText("Hide Beacon");
                    beaconDataButton.setEnabled(true);
                } else {
                    beaconButton.setText("Show Beacon");
                    beaconDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowBeacon = !KTLPanel.isShowBeacon;

                if (KTLPanel.isShowBeacon){
                    beaconButton.setText("Hide Beacon");
                    beaconDataButton.setEnabled(true);
                } else {
                    beaconButton.setText("Show Beacon");
                    beaconDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowBeacon = !TKLPanel.isShowBeacon;

                if (TKLPanel.isShowBeacon){
                    beaconButton.setText("Hide Beacon");
                    beaconDataButton.setEnabled(true);
                } else {
                    beaconButton.setText("Show Beacon");
                    beaconDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowBeacon = !ISLPanel.isShowBeacon;

                if (ISLPanel.isShowBeacon){
                    beaconButton.setText("Hide Beacon");
                    beaconDataButton.setEnabled(true);
                } else {
                    beaconButton.setText("Show Beacon");
                    beaconDataButton.setEnabled(false);
                }
            }


            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == tagButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowTag = !TWLPanel.isShowTag;

                if (TWLPanel.isShowTag){
                    tagButton.setText("Hide Tag");
                    tagDataButton.setEnabled(true);
                } else {
                    tagButton.setText("Show Tag");
                    tagDataButton.setEnabled(false);
                }
            }

            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == sabButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowSab = !TWLPanel.isShowSab;

                if (TWLPanel.isShowSab){
                    sabButton.setText("Hide SAB");
                    sabDataButton.setEnabled(true);
                } else {
                    sabButton.setText("Show SAB");
                    sabDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowSab = !KTLPanel.isShowSab;

                if (KTLPanel.isShowSab){
                    sabButton.setText("Hide SAB");
                    sabDataButton.setEnabled(true);
                } else {
                    sabButton.setText("Show SAB");
                    sabDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowSab = !TKLPanel.isShowSab;

                if (TKLPanel.isShowSab){
                    sabButton.setText("Hide SAB");
                    sabDataButton.setEnabled(true);
                } else {
                    sabButton.setText("Show SAB");
                    sabDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowSab = !ISLPanel.isShowSab;

                if (ISLPanel.isShowSab){
                    sabButton.setText("Hide SAB");
                    sabDataButton.setEnabled(true);
                } else {
                    sabButton.setText("Show SAB");
                    sabDataButton.setEnabled(false);
                }
            }


            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == boxButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowBox = !TWLPanel.isShowBox;

                if (TWLPanel.isShowBox){
                    boxButton.setText("Hide Box");
                    boxDataButton.setEnabled(true);
                } else {
                    boxButton.setText("Show Box");
                    boxDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowBox = !KTLPanel.isShowBox;

                if (KTLPanel.isShowBox){
                    boxButton.setText("Hide Box");
                    boxDataButton.setEnabled(true);
                } else {
                    boxButton.setText("Show Box");
                    boxDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowBox = !TKLPanel.isShowBox;

                if (TKLPanel.isShowBox){
                    boxButton.setText("Hide Box");
                    boxDataButton.setEnabled(true);
                } else {
                    boxButton.setText("Show Box");
                    boxDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowBox = !ISLPanel.isShowBox;

                if (ISLPanel.isShowBox){
                    boxButton.setText("Hide Box");
                    boxDataButton.setEnabled(true);
                } else {
                    boxButton.setText("Show Box");
                    boxDataButton.setEnabled(false);
                }
            }


            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == otherButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowOther = !TWLPanel.isShowOther;
                if (TWLPanel.isShowOther){
                    otherButton.setText("Hide Other");
                    otherDataButton.setEnabled(true);
                } else {
                    otherButton.setText("Show Other");
                    otherDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowOther = !KTLPanel.isShowOther;
                if (KTLPanel.isShowOther){
                    otherButton.setText("Hide Other");
                    otherDataButton.setEnabled(true);
                } else {
                    otherButton.setText("Show Other");
                    otherDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowOther = !TKLPanel.isShowOther;
                if (TKLPanel.isShowOther){
                    otherButton.setText("Hide Other");
                    otherDataButton.setEnabled(true);
                } else {
                    otherButton.setText("Show Other");
                    otherDataButton.setEnabled(false);
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowOther = !ISLPanel.isShowOther;
                if (ISLPanel.isShowOther){
                    otherButton.setText("Hide Other");
                    otherDataButton.setEnabled(true);
                } else {
                    otherButton.setText("Show Other");
                    otherDataButton.setEnabled(false);
                }
            }


            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }


//        if (e.getSource() == allButton) {
//            TWLPanel.isShowAll = !TWLPanel.isShowAll;
//            if (TWLPanel.isShowAll){
//
//                allButton.setText("Hide All Equipment");
//                allDataButton.setEnabled(true);
//            } else {
//                allButton.setText("Show All Equipment");
//                allDataButton.setEnabled(false);
//            }
//            MyFrame.changeDisplay("");
//            MyFrame.changeDisplay(MyFrame.currentPanel);
//        }




        if (e.getSource() == crossingDataButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowCrossingData = !TWLPanel.isShowCrossingData;
                if (TWLPanel.isShowCrossingData){
                    crossingDataButton.setText("Hide Crossing Location");
                } else {
                    crossingDataButton.setText("Show Crossing Location");
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowCrossingData = !KTLPanel.isShowCrossingData;
                if (KTLPanel.isShowCrossingData){
                    crossingDataButton.setText("Hide Crossing Location");
                } else {
                    crossingDataButton.setText("Show Crossing Location");
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowCrossingData = !TKLPanel.isShowCrossingData;
                if (TKLPanel.isShowCrossingData){
                    crossingDataButton.setText("Hide Crossing Location");
                } else {
                    crossingDataButton.setText("Show Crossing Location");
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowCrossingData = !ISLPanel.isShowCrossingData;
                if (ISLPanel.isShowCrossingData){
                    crossingDataButton.setText("Hide Crossing Location");
                } else {
                    crossingDataButton.setText("Show Crossing Location");
                }
            }

            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == switchDataButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowSwitchData = !TWLPanel.isShowSwitchData;
                if (TWLPanel.isShowSwitchData){
                    switchDataButton.setText("Hide Switch Location");
                } else {
                    switchDataButton.setText("Show Switch Location");
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowSwitchData = !KTLPanel.isShowSwitchData;
                if (KTLPanel.isShowSwitchData){
                    switchDataButton.setText("Hide Switch Location");
                } else {
                    switchDataButton.setText("Show Switch Location");
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowSwitchData = !TKLPanel.isShowSwitchData;
                if (TKLPanel.isShowSwitchData){
                    switchDataButton.setText("Hide Switch Location");
                } else {
                    switchDataButton.setText("Show Switch Location");
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowSwitchData = !ISLPanel.isShowSwitchData;
                if (ISLPanel.isShowSwitchData){
                    switchDataButton.setText("Hide Switch Location");
                } else {
                    switchDataButton.setText("Show Switch Location");
                }
            }


            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == floodgateDataButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowFloodgateData = !TWLPanel.isShowFloodgateData;
                if (TWLPanel.isShowFloodgateData){
                    floodgateDataButton.setText("Hide Floodgate Location");
                } else {
                    floodgateDataButton.setText("Show Floodgate Location");
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowFloodgateData = !KTLPanel.isShowFloodgateData;
                if (KTLPanel.isShowFloodgateData){
                    floodgateDataButton.setText("Hide Floodgate Location");
                } else {
                    floodgateDataButton.setText("Show Floodgate Location");
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowFloodgateData = !TKLPanel.isShowFloodgateData;
                if (TKLPanel.isShowFloodgateData){
                    floodgateDataButton.setText("Hide Floodgate Location");
                } else {
                    floodgateDataButton.setText("Show Floodgate Location");
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowFloodgateData = !ISLPanel.isShowFloodgateData;
                if (ISLPanel.isShowFloodgateData){
                    floodgateDataButton.setText("Hide Floodgate Location");
                } else {
                    floodgateDataButton.setText("Show Floodgate Location");
                }
            }


            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == signalDataButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowSignalData = !TWLPanel.isShowSignalData;
                if (TWLPanel.isShowSignalData){
                    signalDataButton.setText("Hide Signal Location");
                } else {
                    signalDataButton.setText("Show Signal Location");
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowSignalData = !KTLPanel.isShowSignalData;
                if (KTLPanel.isShowSignalData){
                    signalDataButton.setText("Hide Signal Location");
                } else {
                    signalDataButton.setText("Show Signal Location");
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowSignalData = !TKLPanel.isShowSignalData;
                if (TKLPanel.isShowSignalData){
                    signalDataButton.setText("Hide Signal Location");
                } else {
                    signalDataButton.setText("Show Signal Location");
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowSignalData = !ISLPanel.isShowSignalData;
                if (ISLPanel.isShowSignalData){
                    signalDataButton.setText("Hide Signal Location");
                } else {
                    signalDataButton.setText("Show Signal Location");
                }
            }


            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == impedanceBondDataButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowImpedanceBondData = !TWLPanel.isShowImpedanceBondData;
                if (TWLPanel.isShowImpedanceBondData){
                    impedanceBondDataButton.setText("Hide Impedance Bond Location");
                } else {
                    impedanceBondDataButton.setText("Show Impedance Bond Location");
                }
            }

            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowImpedanceBondData = !KTLPanel.isShowImpedanceBondData;
                if (KTLPanel.isShowImpedanceBondData){
                    impedanceBondDataButton.setText("Hide Impedance Bond Location");
                } else {
                    impedanceBondDataButton.setText("Show Impedance Bond Location");
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowImpedanceBondData = !TKLPanel.isShowImpedanceBondData;
                if (TKLPanel.isShowImpedanceBondData){
                    impedanceBondDataButton.setText("Hide Impedance Bond Location");
                } else {
                    impedanceBondDataButton.setText("Show Impedance Bond Location");
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowImpedanceBondData = !ISLPanel.isShowImpedanceBondData;
                if (ISLPanel.isShowImpedanceBondData){
                    impedanceBondDataButton.setText("Hide Impedance Bond Location");
                } else {
                    impedanceBondDataButton.setText("Show Impedance Bond Location");
                }
            }


            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == beaconDataButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowBeaconData = !TWLPanel.isShowBeaconData;
                if (TWLPanel.isShowBeaconData){
                    beaconDataButton.setText("Hide Beacon Location");
                } else {
                    beaconDataButton.setText("Show Beacon Location");
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowBeaconData = !KTLPanel.isShowBeaconData;
                if (KTLPanel.isShowBeaconData){
                    beaconDataButton.setText("Hide Beacon Location");
                } else {
                    beaconDataButton.setText("Show Beacon Location");
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowBeaconData = !TKLPanel.isShowBeaconData;
                if (TKLPanel.isShowBeaconData){
                    beaconDataButton.setText("Hide Beacon Location");
                } else {
                    beaconDataButton.setText("Show Beacon Location");
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowBeaconData = !ISLPanel.isShowBeaconData;
                if (ISLPanel.isShowBeaconData){
                    beaconDataButton.setText("Hide Beacon Location");
                } else {
                    beaconDataButton.setText("Show Beacon Location");
                }
            }

            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == tagDataButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowTagData = !TWLPanel.isShowTagData;
                if (TWLPanel.isShowTagData){
                    tagDataButton.setText("Hide Tag Location");
                } else {
                    tagDataButton.setText("Show Tag Location");
                }
            }

            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == sabDataButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowSabData = !TWLPanel.isShowSabData;
                if (TWLPanel.isShowSabData){
                    sabDataButton.setText("Hide SAB Location");
                } else {
                    sabDataButton.setText("Show SAB Location");
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowSabData = !KTLPanel.isShowSabData;
                if (KTLPanel.isShowSabData){
                    sabDataButton.setText("Hide SAB Location");
                } else {
                    sabDataButton.setText("Show SAB Location");
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowSabData = !TKLPanel.isShowSabData;
                if (TKLPanel.isShowSabData){
                    sabDataButton.setText("Hide SAB Location");
                } else {
                    sabDataButton.setText("Show SAB Location");
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowSabData = !ISLPanel.isShowSabData;
                if (ISLPanel.isShowSabData){
                    sabDataButton.setText("Hide SAB Location");
                } else {
                    sabDataButton.setText("Show SAB Location");
                }
            }

            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == boxDataButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowBoxData = !TWLPanel.isShowBoxData;
                if (TWLPanel.isShowBoxData){
                    boxDataButton.setText("Hide Box Location");
                } else {
                    boxDataButton.setText("Show Box Location");
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowBoxData = !KTLPanel.isShowBoxData;
                if (KTLPanel.isShowBoxData){
                    boxDataButton.setText("Hide Box Location");
                } else {
                    boxDataButton.setText("Show Box Location");
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowBoxData = !TKLPanel.isShowBoxData;
                if (TKLPanel.isShowBoxData){
                    boxDataButton.setText("Hide Box Location");
                } else {
                    boxDataButton.setText("Show Box Location");
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowBoxData = !ISLPanel.isShowBoxData;
                if (ISLPanel.isShowBoxData){
                    boxDataButton.setText("Hide Box Location");
                } else {
                    boxDataButton.setText("Show Box Location");
                }
            }



            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }

        if (e.getSource() == otherDataButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.isShowOtherData = !TWLPanel.isShowOtherData;
                if (TWLPanel.isShowOtherData){
                    otherDataButton.setText("Hide Other Location");
                } else {
                    otherDataButton.setText("Show Other Location");
                }
            }
            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.isShowOtherData = !KTLPanel.isShowOtherData;
                if (KTLPanel.isShowOtherData){
                    otherDataButton.setText("Hide Other Location");
                } else {
                    otherDataButton.setText("Show Other Location");
                }
            }
            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.isShowOtherData = !TKLPanel.isShowOtherData;
                if (TKLPanel.isShowOtherData){
                    otherDataButton.setText("Hide Other Location");
                } else {
                    otherDataButton.setText("Show Other Location");
                }
            }
            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.isShowOtherData = !ISLPanel.isShowOtherData;
                if (ISLPanel.isShowOtherData){
                    otherDataButton.setText("Hide Other Location");
                } else {
                    otherDataButton.setText("Show Other Location");
                }
            }

            MyFrame.changeDisplay("");
            MyFrame.changeDisplay(MyFrame.currentPanel);
        }













        if (e.getSource() == stationDropdown) {
            if (!String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())).equals("")) {
                equipmentDropdown.removeAllItems();
                String[] equipmentList = {"Track Circuit", "Platform", "Signal", "Switch", "Crossing", "Impedance Bond", "Beacon", "Tag", "SAB", "Box", "Others"};
                for (int i = 0; i < equipmentList.length; i++) {
                    equipmentDropdown.addItem(equipmentList[i]);
                }
            }
        }

        if (e.getSource() == equipmentDropdown) {
            String selected = String.valueOf(equipmentDropdown.getItemAt(equipmentDropdown.getSelectedIndex()));
            switch (selected) {
                case "Track Circuit":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.trackIds.length; i++) {
                            if (TWLPanel.trackStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                //System.out.println(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())));
                                idDropdown.addItem(TWLPanel.trackIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("KTL")) {
                        for (int i = 0; i < KTLPanel.trackIds.length; i++) {
                            if (KTLPanel.trackStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(KTLPanel.trackIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("TKL")) {
                        for (int i = 0; i < TKLPanel.trackIds.length; i++) {
                            if (TKLPanel.trackStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TKLPanel.trackIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("ISL")) {
                        for (int i = 0; i < ISLPanel.trackIds.length; i++) {
                            if (ISLPanel.trackStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(ISLPanel.trackIds[i]);
                            }
                        }
                    }


                    break;
                case "Platform":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.platformNames.length; i++) {
                            if (TWLPanel.platformStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TWLPanel.platformNames[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("KTL")) {
                        for (int i = 0; i < KTLPanel.platformNames.length; i++) {
                            if (KTLPanel.platformStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(KTLPanel.platformNames[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("TKL")) {
                        for (int i = 0; i < TKLPanel.platformNames.length; i++) {
                            if (TKLPanel.platformStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TKLPanel.platformNames[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("ISL")) {
                        for (int i = 0; i < ISLPanel.platformNames.length; i++) {
                            if (ISLPanel.platformStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(ISLPanel.platformNames[i]);
                            }
                        }
                    }


                    break;
                case "Signal":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.signalIds.length; i++) {
                            if (TWLPanel.signalStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TWLPanel.signalIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("KTL")) {
                        for (int i = 0; i < KTLPanel.signalIds.length; i++) {
                            if (KTLPanel.signalStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(KTLPanel.signalIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("TKL")) {
                        for (int i = 0; i < TKLPanel.signalIds.length; i++) {
                            if (TKLPanel.signalStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TKLPanel.signalIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("ISL")) {
                        for (int i = 0; i < ISLPanel.signalIds.length; i++) {
                            if (ISLPanel.signalStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(ISLPanel.signalIds[i]);
                            }
                        }
                    }


                    break;
                case "Switch":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.switchIds.length; i++) {
                            if (TWLPanel.switchStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TWLPanel.switchIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("KTL")) {
                        for (int i = 0; i < KTLPanel.switchIds.length; i++) {
                            if (KTLPanel.switchStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(KTLPanel.switchIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("TKL")) {
                        for (int i = 0; i < TKLPanel.switchIds.length; i++) {
                            if (TKLPanel.switchStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TKLPanel.switchIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("ISL")) {
                        for (int i = 0; i < ISLPanel.switchIds.length; i++) {
                            if (ISLPanel.switchStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(ISLPanel.switchIds[i]);
                            }
                        }
                    }

                    break;
                case "Crossing":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.crossingIds.length; i++) {
                            if (TWLPanel.crossingStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TWLPanel.crossingIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("KTL")) {
                        for (int i = 0; i < KTLPanel.crossingIds.length; i++) {
                            if (KTLPanel.crossingStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(KTLPanel.crossingIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("TKL")) {
                        for (int i = 0; i < TKLPanel.crossingIds.length; i++) {
                            if (TKLPanel.crossingStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TKLPanel.crossingIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("ISL")) {
                        for (int i = 0; i < ISLPanel.crossingIds.length; i++) {
                            if (ISLPanel.crossingStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(ISLPanel.crossingIds[i]);
                            }
                        }
                    }

                    break;
                case "Impedance Bond":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.impedanceBondIds.length; i++) {
                            if (TWLPanel.impedanceBondStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TWLPanel.impedanceBondIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("KTL")) {
                        for (int i = 0; i < KTLPanel.impedanceBondIds.length; i++) {
                            if (KTLPanel.impedanceBondStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(KTLPanel.impedanceBondIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("TKL")) {
                        for (int i = 0; i < TKLPanel.impedanceBondIds.length; i++) {
                            if (TKLPanel.impedanceBondStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TKLPanel.impedanceBondIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("ISL")) {
                        for (int i = 0; i < ISLPanel.impedanceBondIds.length; i++) {
                            if (ISLPanel.impedanceBondStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(ISLPanel.impedanceBondIds[i]);
                            }
                        }
                    }

                    break;
                case "Beacon":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.beaconIds.length; i++) {
                            if (TWLPanel.beaconStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TWLPanel.beaconIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("KTL")) {
                        for (int i = 0; i < KTLPanel.beaconIds.length; i++) {
                            if (KTLPanel.beaconStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(KTLPanel.beaconIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("TKL")) {
                        for (int i = 0; i < TKLPanel.beaconIds.length; i++) {
                            if (TKLPanel.beaconStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TKLPanel.beaconIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("ISL")) {
                        for (int i = 0; i < ISLPanel.beaconIds.length; i++) {
                            if (ISLPanel.beaconStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(ISLPanel.beaconIds[i]);
                            }
                        }
                    }

                    break;
                case "Tag":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.tagIds.length; i++) {
                            if (TWLPanel.tagStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TWLPanel.tagIds[i]);
                            }
                        }
                    }

                    break;
                case "SAB":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.sabIds.length; i++) {
                            if (TWLPanel.sabStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TWLPanel.sabIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("KTL")) {
                        for (int i = 0; i < KTLPanel.sabIds.length; i++) {
                            if (KTLPanel.sabStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(KTLPanel.sabIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("TKL")) {
                        for (int i = 0; i < TKLPanel.sabIds.length; i++) {
                            if (TKLPanel.sabStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TKLPanel.sabIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("ISL")) {
                        for (int i = 0; i < ISLPanel.sabIds.length; i++) {
                            if (ISLPanel.sabStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(ISLPanel.sabIds[i]);
                            }
                        }
                    }

                    break;
                case "Box":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.boxIds.length; i++) {
                            if (TWLPanel.boxStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TWLPanel.boxIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("KTL")) {
                        for (int i = 0; i < KTLPanel.boxIds.length; i++) {
                            if (KTLPanel.boxStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(KTLPanel.boxIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("TKL")) {
                        for (int i = 0; i < TKLPanel.boxIds.length; i++) {
                            if (TKLPanel.boxStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TKLPanel.boxIds[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("ISL")) {
                        for (int i = 0; i < ISLPanel.boxIds.length; i++) {
                            if (ISLPanel.boxStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(ISLPanel.boxIds[i]);
                            }
                        }
                    }

                    break;
                case "Others":
                    idDropdown.removeAllItems();
                    if (MyFrame.currentPanel.equals("TWL")) {
                        for (int i = 0; i < TWLPanel.additionalNames.length; i++) {
                            if (TWLPanel.additionalStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TWLPanel.additionalNames[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("KTL")) {
                        for (int i = 0; i < KTLPanel.additionalNames.length; i++) {
                            if (KTLPanel.additionalStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(KTLPanel.additionalNames[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("TKL")) {
                        for (int i = 0; i < TKLPanel.additionalNames.length; i++) {
                            if (TKLPanel.additionalStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(TKLPanel.additionalNames[i]);
                            }
                        }
                    }
                    if (MyFrame.currentPanel.equals("ISL")) {
                        for (int i = 0; i < ISLPanel.additionalNames.length; i++) {
                            if (ISLPanel.additionalStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
                                idDropdown.addItem(ISLPanel.additionalNames[i]);
                            }
                        }
                    }

                    break;
                default:
                    idDropdown.removeAllItems();
                    break;
            }
        }

        if (e.getSource() == idDropdown) {

        }

        if (e.getSource() == searchButton) {
            if (MyFrame.currentPanel.equals("TWL")) {
                TWLPanel.selectedStation = String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex()));
                TWLPanel.selectedEquipment = String.valueOf(equipmentDropdown.getItemAt(equipmentDropdown.getSelectedIndex()));
                TWLPanel.selectedId = String.valueOf(idDropdown.getItemAt(idDropdown.getSelectedIndex()));
                switch (TWLPanel.selectedEquipment) {
                    case "Track Circuit":
                        for (int i = 0; i < TWLPanel.trackIds.length; i++) {
                            if (TWLPanel.trackStations[i].equals(TWLPanel.selectedStation) && TWLPanel.trackIds[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();
//                            verticalScrollBar.setValue(verticalScrollBar.getMinimum());
//                            horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)((TWLPanel.trackLocationsLeft[i] + TWLPanel.trackLocationsLeft[i])/2));
                                if (TWLPanel.trackSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TWLPanel.upTrack);
                                }
                                if (TWLPanel.trackSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TWLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Platform":
                        for (int i = 0; i < TWLPanel.platformStations.length; i++) {
                            if (TWLPanel.platformStations[i].equals(TWLPanel.selectedStation) && TWLPanel.platformNames[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)((TWLPanel.platformMidPtsUp[i] + TWLPanel.platformMidPtsDn[i])/2));
                                verticalScrollBar.setValue(TWLPanel.upTrack + TWLPanel.upDnDistance/2);


                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Signal":
                        for (int i = 0; i < TWLPanel.signalIds.length; i++) {
                            if (TWLPanel.signalStations[i].equals(TWLPanel.selectedStation) && TWLPanel.signalIds[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TWLPanel.signalLocations[i]));
                                if (TWLPanel.signalSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TWLPanel.upTrack);
                                }
                                if (TWLPanel.signalSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TWLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Switch":
                        for (int i = 0; i < TWLPanel.switchIds.length; i++) {
                            if (TWLPanel.switchStations[i].equals(TWLPanel.selectedStation) && TWLPanel.switchIds[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TWLPanel.switchLocations[i]));
                                if (TWLPanel.switchSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TWLPanel.upTrack);
                                }
                                if (TWLPanel.switchSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TWLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Crossing":
                        for (int i = 0; i < TWLPanel.crossingIds.length; i++) {
//                        if (TWLPanel.crossingStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
//                            idDropdown.addItem(TWLPanel.crossingIds[i]);
//                        }
                            if (TWLPanel.crossingStations[i].equals(TWLPanel.selectedStation) && TWLPanel.crossingIds[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TWLPanel.crossingInterceptForSearchX[i]));
                                verticalScrollBar.setValue((int)(TWLPanel.crossingInterceptForSearchY[i]));
//                            if (TWLPanel.switchSides[i].equals("UP")) {
//                                verticalScrollBar.setValue((int)(TWLPanel.crossingInterceptForSearchY[i]));
//                            }
//                            if (TWLPanel.switchSides[i].equals("DN")) {
//                                verticalScrollBar.setValue((int)(TWLPanel.crossingInterceptForSearchY[i]));
//                            }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Impedance Bond":
                        for (int i = 0; i < TWLPanel.impedanceBondIds.length; i++) {
                            if (TWLPanel.impedanceBondStations[i].equals(TWLPanel.selectedStation) && TWLPanel.impedanceBondIds[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TWLPanel.impedanceBondLocations[i]));
                                if (TWLPanel.impedanceBondSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TWLPanel.upTrack);
                                }
                                if (TWLPanel.impedanceBondSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TWLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Beacon":
                        for (int i = 0; i < TWLPanel.beaconIds.length; i++) {
                            if (TWLPanel.beaconStations[i].equals(TWLPanel.selectedStation) && TWLPanel.beaconIds[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TWLPanel.beaconLocations[i]));
                                if (TWLPanel.beaconSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TWLPanel.upTrack);
                                }
                                if (TWLPanel.beaconSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TWLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Tag":
                        for (int i = 0; i < TWLPanel.tagIds.length; i++) {
                            if (TWLPanel.tagStations[i].equals(TWLPanel.selectedStation) && TWLPanel.tagIds[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TWLPanel.tagLocations[i]));
                                if (TWLPanel.tagSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TWLPanel.upTrack);
                                }
                                if (TWLPanel.tagSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TWLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "SAB":
                        for (int i = 0; i < TWLPanel.sabIds.length; i++) {
                            if (TWLPanel.sabStations[i].equals(TWLPanel.selectedStation) && TWLPanel.sabIds[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TWLPanel.sabLocations[i]));
                                if (TWLPanel.sabSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TWLPanel.upTrack);
                                }
                                if (TWLPanel.sabSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TWLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Box":
                        for (int i = 0; i < TWLPanel.boxIds.length; i++) {
                            if (TWLPanel.boxStations[i].equals(TWLPanel.selectedStation) && TWLPanel.boxIds[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TWLPanel.boxLocations[i]));
                                if (TWLPanel.boxSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TWLPanel.upTrack);
                                }
                                if (TWLPanel.boxSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TWLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Others":
                        for (int i = 0; i < TWLPanel.additionalNames.length; i++) {
                            if (TWLPanel.additionalStations[i].equals(TWLPanel.selectedStation) && TWLPanel.additionalNames[i].equals(TWLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TWLPanel.offsetX = -700; TWLPanel.offsetY= -350;
                                TWLPanel.startPanX = 0; TWLPanel.startPanY = 0;
                                TWLPanel.scaleX = 1; TWLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TWLPanel.additionalLocations[i]));
                                if (TWLPanel.additionalSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TWLPanel.upTrack);
                                }
                                if (TWLPanel.additionalSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TWLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }

            if (MyFrame.currentPanel.equals("KTL")) {
                KTLPanel.selectedStation = String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex()));
                KTLPanel.selectedEquipment = String.valueOf(equipmentDropdown.getItemAt(equipmentDropdown.getSelectedIndex()));
                KTLPanel.selectedId = String.valueOf(idDropdown.getItemAt(idDropdown.getSelectedIndex()));
                switch (KTLPanel.selectedEquipment) {
                    case "Track Circuit":
                        for (int i = 0; i < KTLPanel.trackIds.length; i++) {
                            if (KTLPanel.trackStations[i].equals(KTLPanel.selectedStation) && KTLPanel.trackIds[i].equals(KTLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.KTLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.KTLScrollPanel.getHorizontalScrollBar();
//                            verticalScrollBar.setValue(verticalScrollBar.getMinimum());
//                            horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());

                                KTLPanel.offsetX = -700; KTLPanel.offsetY= -350;
                                KTLPanel.startPanX = 0; KTLPanel.startPanY = 0;
                                KTLPanel.scaleX = 1; KTLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)((KTLPanel.trackLocationsLeft[i] + KTLPanel.trackLocationsLeft[i])/2));
                                if (KTLPanel.trackSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(KTLPanel.upTrack);
                                }
                                if (KTLPanel.trackSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(KTLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Platform":
                        for (int i = 0; i < KTLPanel.platformStations.length; i++) {
                            if (KTLPanel.platformStations[i].equals(KTLPanel.selectedStation) && KTLPanel.platformNames[i].equals(KTLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.KTLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.KTLScrollPanel.getHorizontalScrollBar();

                                KTLPanel.offsetX = -700; KTLPanel.offsetY= -350;
                                KTLPanel.startPanX = 0; KTLPanel.startPanY = 0;
                                KTLPanel.scaleX = 1; KTLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)((KTLPanel.platformMidPtsUp[i] + KTLPanel.platformMidPtsDn[i])/2));
                                verticalScrollBar.setValue(KTLPanel.upTrack + KTLPanel.upDnDistance/2);


                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Signal":
                        for (int i = 0; i < KTLPanel.signalIds.length; i++) {
                            if (KTLPanel.signalStations[i].equals(KTLPanel.selectedStation) && KTLPanel.signalIds[i].equals(KTLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.KTLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.KTLScrollPanel.getHorizontalScrollBar();

                                KTLPanel.offsetX = -700; KTLPanel.offsetY= -350;
                                KTLPanel.startPanX = 0; KTLPanel.startPanY = 0;
                                KTLPanel.scaleX = 1; KTLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(KTLPanel.signalLocations[i]));
                                if (KTLPanel.signalSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(KTLPanel.upTrack);
                                }
                                if (KTLPanel.signalSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(KTLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Switch":
                        for (int i = 0; i < KTLPanel.switchIds.length; i++) {
                            if (KTLPanel.switchStations[i].equals(KTLPanel.selectedStation) && KTLPanel.switchIds[i].equals(KTLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.KTLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.KTLScrollPanel.getHorizontalScrollBar();

                                KTLPanel.offsetX = -700; KTLPanel.offsetY= -350;
                                KTLPanel.startPanX = 0; KTLPanel.startPanY = 0;
                                KTLPanel.scaleX = 1; KTLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(KTLPanel.switchLocations[i]));
                                if (KTLPanel.switchSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(KTLPanel.upTrack);
                                }
                                if (KTLPanel.switchSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(KTLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Crossing":
                        for (int i = 0; i < KTLPanel.crossingIds.length; i++) {

                            if (KTLPanel.crossingStations[i].equals(KTLPanel.selectedStation) && KTLPanel.crossingIds[i].equals(KTLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.KTLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.KTLScrollPanel.getHorizontalScrollBar();

                                KTLPanel.offsetX = -700; KTLPanel.offsetY= -350;
                                KTLPanel.startPanX = 0; KTLPanel.startPanY = 0;
                                KTLPanel.scaleX = 1; KTLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(KTLPanel.crossingInterceptForSearchX[i]));
                                verticalScrollBar.setValue((int)(KTLPanel.crossingInterceptForSearchY[i]));


                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Impedance Bond":
                        for (int i = 0; i < KTLPanel.impedanceBondIds.length; i++) {
                            if (KTLPanel.impedanceBondStations[i].equals(KTLPanel.selectedStation) && KTLPanel.impedanceBondIds[i].equals(KTLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.KTLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.KTLScrollPanel.getHorizontalScrollBar();

                                KTLPanel.offsetX = -700; KTLPanel.offsetY= -350;
                                KTLPanel.startPanX = 0; KTLPanel.startPanY = 0;
                                KTLPanel.scaleX = 1; KTLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(KTLPanel.impedanceBondLocations[i]));
                                if (KTLPanel.impedanceBondSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(KTLPanel.upTrack);
                                }
                                if (KTLPanel.impedanceBondSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(KTLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Beacon":
                        for (int i = 0; i < KTLPanel.beaconIds.length; i++) {
                            if (KTLPanel.beaconStations[i].equals(KTLPanel.selectedStation) && KTLPanel.beaconIds[i].equals(KTLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.KTLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.KTLScrollPanel.getHorizontalScrollBar();

                                KTLPanel.offsetX = -700; KTLPanel.offsetY= -350;
                                KTLPanel.startPanX = 0; KTLPanel.startPanY = 0;
                                KTLPanel.scaleX = 1; KTLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(KTLPanel.beaconLocations[i]));
                                if (KTLPanel.beaconSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(KTLPanel.upTrack);
                                }
                                if (KTLPanel.beaconSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(KTLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "SAB":
                        for (int i = 0; i < KTLPanel.sabIds.length; i++) {
                            if (KTLPanel.sabStations[i].equals(KTLPanel.selectedStation) && KTLPanel.sabIds[i].equals(KTLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.KTLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.KTLScrollPanel.getHorizontalScrollBar();

                                KTLPanel.offsetX = -700; KTLPanel.offsetY= -350;
                                KTLPanel.startPanX = 0; KTLPanel.startPanY = 0;
                                KTLPanel.scaleX = 1; KTLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(KTLPanel.sabLocations[i]));
                                if (KTLPanel.sabSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(KTLPanel.upTrack);
                                }
                                if (KTLPanel.sabSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(KTLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Box":
                        for (int i = 0; i < KTLPanel.boxIds.length; i++) {
                            if (KTLPanel.boxStations[i].equals(KTLPanel.selectedStation) && KTLPanel.boxIds[i].equals(KTLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.KTLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.KTLScrollPanel.getHorizontalScrollBar();

                                KTLPanel.offsetX = -700; KTLPanel.offsetY= -350;
                                KTLPanel.startPanX = 0; KTLPanel.startPanY = 0;
                                KTLPanel.scaleX = 1; KTLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(KTLPanel.boxLocations[i]));
                                if (KTLPanel.boxSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(KTLPanel.upTrack);
                                }
                                if (KTLPanel.boxSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(KTLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Others":
                        for (int i = 0; i < KTLPanel.additionalNames.length; i++) {
                            if (KTLPanel.additionalStations[i].equals(KTLPanel.selectedStation) && KTLPanel.additionalNames[i].equals(KTLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.KTLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.KTLScrollPanel.getHorizontalScrollBar();

                                KTLPanel.offsetX = -700; KTLPanel.offsetY= -350;
                                KTLPanel.startPanX = 0; KTLPanel.startPanY = 0;
                                KTLPanel.scaleX = 1; KTLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(KTLPanel.additionalLocations[i]));
                                if (KTLPanel.additionalSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(KTLPanel.upTrack);
                                }
                                if (KTLPanel.additionalSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(KTLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }



            if (MyFrame.currentPanel.equals("TKL")) {
                TKLPanel.selectedStation = String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex()));
                TKLPanel.selectedEquipment = String.valueOf(equipmentDropdown.getItemAt(equipmentDropdown.getSelectedIndex()));
                TKLPanel.selectedId = String.valueOf(idDropdown.getItemAt(idDropdown.getSelectedIndex()));
                switch (TKLPanel.selectedEquipment) {
                    case "Track Circuit":
                        for (int i = 0; i < TKLPanel.trackIds.length; i++) {
                            if (TKLPanel.trackStations[i].equals(TKLPanel.selectedStation) && TKLPanel.trackIds[i].equals(TKLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TKLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TKLScrollPanel.getHorizontalScrollBar();
//                            verticalScrollBar.setValue(verticalScrollBar.getMinimum());
//                            horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());

                                TKLPanel.offsetX = -700; TKLPanel.offsetY= -350;
                                TKLPanel.startPanX = 0; TKLPanel.startPanY = 0;
                                TKLPanel.scaleX = 1; TKLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)((TKLPanel.trackLocationsLeft[i] + TKLPanel.trackLocationsLeft[i])/2));
                                if (TKLPanel.trackSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TKLPanel.upTrack);
                                }
                                if (TKLPanel.trackSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TKLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Platform":
                        for (int i = 0; i < TKLPanel.platformStations.length; i++) {
                            if (TKLPanel.platformStations[i].equals(TKLPanel.selectedStation) && TKLPanel.platformNames[i].equals(TKLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TKLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TKLScrollPanel.getHorizontalScrollBar();

                                TKLPanel.offsetX = -700; TKLPanel.offsetY= -350;
                                TKLPanel.startPanX = 0; TKLPanel.startPanY = 0;
                                TKLPanel.scaleX = 1; TKLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)((TKLPanel.platformMidPtsUp[i] + TKLPanel.platformMidPtsDn[i])/2));
                                verticalScrollBar.setValue(TKLPanel.upTrack + TKLPanel.upDnDistance/2);


                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Signal":
                        for (int i = 0; i < TKLPanel.signalIds.length; i++) {
                            if (TKLPanel.signalStations[i].equals(TKLPanel.selectedStation) && TKLPanel.signalIds[i].equals(TKLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TKLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TKLScrollPanel.getHorizontalScrollBar();

                                TKLPanel.offsetX = -700; TKLPanel.offsetY= -350;
                                TKLPanel.startPanX = 0; TKLPanel.startPanY = 0;
                                TKLPanel.scaleX = 1; TKLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TKLPanel.signalLocations[i]));
                                if (TKLPanel.signalSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TKLPanel.upTrack);
                                }
                                if (TKLPanel.signalSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TKLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Switch":
                        for (int i = 0; i < TKLPanel.switchIds.length; i++) {
                            if (TKLPanel.switchStations[i].equals(TKLPanel.selectedStation) && TKLPanel.switchIds[i].equals(TKLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TWLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TWLScrollPanel.getHorizontalScrollBar();

                                TKLPanel.offsetX = -700; TKLPanel.offsetY= -350;
                                TKLPanel.startPanX = 0; TKLPanel.startPanY = 0;
                                TKLPanel.scaleX = 1; TKLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TKLPanel.switchLocations[i]));
                                if (TKLPanel.switchSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TKLPanel.upTrack);
                                }
                                if (TKLPanel.switchSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TKLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Crossing":
                        for (int i = 0; i < TKLPanel.crossingIds.length; i++) {
//                        if (TWLPanel.crossingStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
//                            idDropdown.addItem(TWLPanel.crossingIds[i]);
//                        }
                            if (TKLPanel.crossingStations[i].equals(TKLPanel.selectedStation) && TKLPanel.crossingIds[i].equals(TKLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TKLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TKLScrollPanel.getHorizontalScrollBar();

                                TKLPanel.offsetX = -700; TKLPanel.offsetY= -350;
                                TKLPanel.startPanX = 0; TKLPanel.startPanY = 0;
                                TKLPanel.scaleX = 1; TKLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TKLPanel.crossingInterceptForSearchX[i]));
                                verticalScrollBar.setValue((int)(TKLPanel.crossingInterceptForSearchY[i]));
//                            if (TWLPanel.switchSides[i].equals("UP")) {
//                                verticalScrollBar.setValue((int)(TWLPanel.crossingInterceptForSearchY[i]));
//                            }
//                            if (TWLPanel.switchSides[i].equals("DN")) {
//                                verticalScrollBar.setValue((int)(TWLPanel.crossingInterceptForSearchY[i]));
//                            }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Impedance Bond":
                        for (int i = 0; i < TKLPanel.impedanceBondIds.length; i++) {
                            if (TKLPanel.impedanceBondStations[i].equals(TKLPanel.selectedStation) && TKLPanel.impedanceBondIds[i].equals(TKLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TKLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TKLScrollPanel.getHorizontalScrollBar();

                                TKLPanel.offsetX = -700; TKLPanel.offsetY= -350;
                                TKLPanel.startPanX = 0; TKLPanel.startPanY = 0;
                                TKLPanel.scaleX = 1; TKLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TKLPanel.impedanceBondLocations[i]));
                                if (TKLPanel.impedanceBondSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TKLPanel.upTrack);
                                }
                                if (TKLPanel.impedanceBondSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TKLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Beacon":
                        for (int i = 0; i < TKLPanel.beaconIds.length; i++) {
                            if (TKLPanel.beaconStations[i].equals(TKLPanel.selectedStation) && TKLPanel.beaconIds[i].equals(TKLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TKLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TKLScrollPanel.getHorizontalScrollBar();

                                TKLPanel.offsetX = -700; TKLPanel.offsetY= -350;
                                TKLPanel.startPanX = 0; TKLPanel.startPanY = 0;
                                TKLPanel.scaleX = 1; TKLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TKLPanel.beaconLocations[i]));
                                if (TKLPanel.beaconSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TKLPanel.upTrack);
                                }
                                if (TKLPanel.beaconSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TKLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "SAB":
                        for (int i = 0; i < TKLPanel.sabIds.length; i++) {
                            if (TKLPanel.sabStations[i].equals(TKLPanel.selectedStation) && TKLPanel.sabIds[i].equals(TKLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TKLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TKLScrollPanel.getHorizontalScrollBar();

                                TKLPanel.offsetX = -700; TKLPanel.offsetY= -350;
                                TKLPanel.startPanX = 0; TKLPanel.startPanY = 0;
                                TKLPanel.scaleX = 1; TKLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TKLPanel.sabLocations[i]));
                                if (TKLPanel.sabSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TKLPanel.upTrack);
                                }
                                if (TKLPanel.sabSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TKLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Box":
                        for (int i = 0; i < TKLPanel.boxIds.length; i++) {
                            if (TKLPanel.boxStations[i].equals(TKLPanel.selectedStation) && TKLPanel.boxIds[i].equals(TKLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TKLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TKLScrollPanel.getHorizontalScrollBar();

                                TKLPanel.offsetX = -700; TKLPanel.offsetY= -350;
                                TKLPanel.startPanX = 0; TKLPanel.startPanY = 0;
                                TKLPanel.scaleX = 1; TKLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TKLPanel.boxLocations[i]));
                                if (TKLPanel.boxSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TKLPanel.upTrack);
                                }
                                if (TKLPanel.boxSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TKLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Others":
                        for (int i = 0; i < TKLPanel.additionalNames.length; i++) {
                            if (TKLPanel.additionalStations[i].equals(TKLPanel.selectedStation) && TKLPanel.additionalNames[i].equals(TKLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.TKLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.TKLScrollPanel.getHorizontalScrollBar();

                                TKLPanel.offsetX = -700; TKLPanel.offsetY= -350;
                                TKLPanel.startPanX = 0; TKLPanel.startPanY = 0;
                                TKLPanel.scaleX = 1; TKLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(TKLPanel.additionalLocations[i]));
                                if (TKLPanel.additionalSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(TKLPanel.upTrack);
                                }
                                if (TKLPanel.additionalSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(TKLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }



            if (MyFrame.currentPanel.equals("ISL")) {
                ISLPanel.selectedStation = String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex()));
                ISLPanel.selectedEquipment = String.valueOf(equipmentDropdown.getItemAt(equipmentDropdown.getSelectedIndex()));
                ISLPanel.selectedId = String.valueOf(idDropdown.getItemAt(idDropdown.getSelectedIndex()));
                switch (ISLPanel.selectedEquipment) {
                    case "Track Circuit":
                        for (int i = 0; i < ISLPanel.trackIds.length; i++) {
                            if (ISLPanel.trackStations[i].equals(ISLPanel.selectedStation) && ISLPanel.trackIds[i].equals(ISLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();
//                            verticalScrollBar.setValue(verticalScrollBar.getMinimum());
//                            horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());

                                ISLPanel.offsetX = -700; ISLPanel.offsetY= -350;
                                ISLPanel.startPanX = 0; ISLPanel.startPanY = 0;
                                ISLPanel.scaleX = 1; ISLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)((ISLPanel.trackLocationsLeft[i] + ISLPanel.trackLocationsLeft[i])/2));
                                if (ISLPanel.trackSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(ISLPanel.upTrack);
                                }
                                if (ISLPanel.trackSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(ISLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Platform":
                        for (int i = 0; i < ISLPanel.platformStations.length; i++) {
                            if (ISLPanel.platformStations[i].equals(ISLPanel.selectedStation) && ISLPanel.platformNames[i].equals(ISLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();

                                ISLPanel.offsetX = -700; ISLPanel.offsetY= -350;
                                ISLPanel.startPanX = 0; ISLPanel.startPanY = 0;
                                ISLPanel.scaleX = 1; ISLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)((ISLPanel.platformMidPtsUp[i] + ISLPanel.platformMidPtsDn[i])/2));
                                verticalScrollBar.setValue(ISLPanel.upTrack + ISLPanel.upDnDistance/2);


                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Signal":
                        for (int i = 0; i < ISLPanel.signalIds.length; i++) {
                            if (ISLPanel.signalStations[i].equals(ISLPanel.selectedStation) && ISLPanel.signalIds[i].equals(ISLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();

                                ISLPanel.offsetX = -700; ISLPanel.offsetY= -350;
                                ISLPanel.startPanX = 0; ISLPanel.startPanY = 0;
                                ISLPanel.scaleX = 1; ISLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(ISLPanel.signalLocations[i]));
                                if (ISLPanel.signalSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(ISLPanel.upTrack);
                                }
                                if (ISLPanel.signalSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(ISLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Switch":
                        for (int i = 0; i < ISLPanel.switchIds.length; i++) {
                            if (ISLPanel.switchStations[i].equals(ISLPanel.selectedStation) && ISLPanel.switchIds[i].equals(ISLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();

                                ISLPanel.offsetX = -700; ISLPanel.offsetY= -350;
                                ISLPanel.startPanX = 0; ISLPanel.startPanY = 0;
                                ISLPanel.scaleX = 1; ISLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(ISLPanel.switchLocations[i]));
                                if (ISLPanel.switchSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(ISLPanel.upTrack);
                                }
                                if (ISLPanel.switchSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(ISLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Crossing":
                        for (int i = 0; i < ISLPanel.crossingIds.length; i++) {
//                        if (TWLPanel.crossingStations[i].equals(String.valueOf(stationDropdown.getItemAt(stationDropdown.getSelectedIndex())))) {
//                            idDropdown.addItem(TWLPanel.crossingIds[i]);
//                        }
                            if (ISLPanel.crossingStations[i].equals(ISLPanel.selectedStation) && ISLPanel.crossingIds[i].equals(ISLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();

                                ISLPanel.offsetX = -700; ISLPanel.offsetY= -350;
                                ISLPanel.startPanX = 0; ISLPanel.startPanY = 0;
                                ISLPanel.scaleX = 1; ISLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(ISLPanel.crossingInterceptForSearchX[i]));
                                verticalScrollBar.setValue((int)(ISLPanel.crossingInterceptForSearchY[i]));
//                            if (TWLPanel.switchSides[i].equals("UP")) {
//                                verticalScrollBar.setValue((int)(TWLPanel.crossingInterceptForSearchY[i]));
//                            }
//                            if (TWLPanel.switchSides[i].equals("DN")) {
//                                verticalScrollBar.setValue((int)(TWLPanel.crossingInterceptForSearchY[i]));
//                            }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Impedance Bond":
                        for (int i = 0; i < ISLPanel.impedanceBondIds.length; i++) {
                            if (ISLPanel.impedanceBondStations[i].equals(ISLPanel.selectedStation) && ISLPanel.impedanceBondIds[i].equals(ISLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();

                                ISLPanel.offsetX = -700; ISLPanel.offsetY= -350;
                                ISLPanel.startPanX = 0; ISLPanel.startPanY = 0;
                                ISLPanel.scaleX = 1; ISLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(ISLPanel.impedanceBondLocations[i]));
                                if (ISLPanel.impedanceBondSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(ISLPanel.upTrack);
                                }
                                if (ISLPanel.impedanceBondSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(ISLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Beacon":
                        for (int i = 0; i < ISLPanel.beaconIds.length; i++) {
                            if (ISLPanel.beaconStations[i].equals(ISLPanel.selectedStation) && ISLPanel.beaconIds[i].equals(ISLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();

                                ISLPanel.offsetX = -700; ISLPanel.offsetY= -350;
                                ISLPanel.startPanX = 0; ISLPanel.startPanY = 0;
                                ISLPanel.scaleX = 1; ISLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(ISLPanel.beaconLocations[i]));
                                if (ISLPanel.beaconSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(ISLPanel.upTrack);
                                }
                                if (ISLPanel.beaconSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(ISLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "SAB":
                        for (int i = 0; i < ISLPanel.sabIds.length; i++) {
                            if (ISLPanel.sabStations[i].equals(ISLPanel.selectedStation) && ISLPanel.sabIds[i].equals(ISLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();

                                ISLPanel.offsetX = -700; ISLPanel.offsetY= -350;
                                ISLPanel.startPanX = 0; ISLPanel.startPanY = 0;
                                ISLPanel.scaleX = 1; ISLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(ISLPanel.sabLocations[i]));
                                if (ISLPanel.sabSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(ISLPanel.upTrack);
                                }
                                if (ISLPanel.sabSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(ISLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Box":
                        for (int i = 0; i < ISLPanel.boxIds.length; i++) {
                            if (ISLPanel.boxStations[i].equals(ISLPanel.selectedStation) && ISLPanel.boxIds[i].equals(ISLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();

                                ISLPanel.offsetX = -700; ISLPanel.offsetY= -350;
                                ISLPanel.startPanX = 0; ISLPanel.startPanY = 0;
                                ISLPanel.scaleX = 1; ISLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(ISLPanel.boxLocations[i]));
                                if (ISLPanel.boxSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(ISLPanel.upTrack);
                                }
                                if (ISLPanel.boxSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(ISLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    case "Others":
                        for (int i = 0; i < ISLPanel.additionalNames.length; i++) {
                            if (ISLPanel.additionalStations[i].equals(ISLPanel.selectedStation) && ISLPanel.additionalNames[i].equals(ISLPanel.selectedId)) {
                                JScrollBar verticalScrollBar = MyFrame.ISLScrollPanel.getVerticalScrollBar();
                                JScrollBar horizontalScrollBar = MyFrame.ISLScrollPanel.getHorizontalScrollBar();

                                ISLPanel.offsetX = -700; ISLPanel.offsetY= -350;
                                ISLPanel.startPanX = 0; ISLPanel.startPanY = 0;
                                ISLPanel.scaleX = 1; ISLPanel.scaleY = 1;

                                horizontalScrollBar.setValue((int)(ISLPanel.additionalLocations[i]));
                                if (ISLPanel.additionalSides[i].equals("UP")) {
                                    verticalScrollBar.setValue(ISLPanel.upTrack);
                                }
                                if (ISLPanel.additionalSides[i].equals("DN")) {
                                    verticalScrollBar.setValue(ISLPanel.dnTrack);
                                }

                                MyFrame.changeDisplay("");
                                MyFrame.changeDisplay(MyFrame.currentPanel);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }





        }
    }
}
