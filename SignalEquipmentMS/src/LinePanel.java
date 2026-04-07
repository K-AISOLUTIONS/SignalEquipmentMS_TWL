import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LinePanel extends JPanel implements ActionListener {

    public static JButton TWLButton; public static JButton KTLButton; public static JButton TKLButton; public static JButton ISLButton;

    LinePanel(){
        //this.setLayout(null);
        TWLButton = new JButton("Tsuen Wan Line");
        TWLButton.addActionListener(this);
        TWLButton.setEnabled(false);
        this.add(TWLButton);

        KTLButton = new JButton("Kwun Tong Line");
        KTLButton.addActionListener(this);
        KTLButton.setEnabled(false);
        this.add(KTLButton);

        TKLButton = new JButton("Tseung Kwan O Line");
        TKLButton.addActionListener(this);
        TKLButton.setEnabled(false);
        this.add(TKLButton);

        ISLButton = new JButton("Island Line");
        ISLButton.addActionListener(this);
        ISLButton.setEnabled(false);
        this.add(ISLButton);


        //this.setBounds(0,700,1200,100);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.GRAY);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == TWLButton) {
            MyFrame.changeDisplay("TWL");

            if (TWLPanel.isShowSignal){
                ControlPanel.signalButton.setText("Hide Signal");
                ControlPanel.signalDataButton.setEnabled(true);
            } else {
                ControlPanel.signalButton.setText("Show Signal");
                ControlPanel.signalDataButton.setEnabled(false);
            }
            if (TWLPanel.isShowImpedanceBond){
                ControlPanel.impedanceBondButton.setText("Hide Impedance Bond");
                ControlPanel.impedanceBondDataButton.setEnabled(true);
            } else {
                ControlPanel.impedanceBondButton.setText("Show Impedance Bond");
                ControlPanel.impedanceBondDataButton.setEnabled(false);
            }
            if (TWLPanel.isShowBeacon){
                ControlPanel.beaconButton.setText("Hide Beacon");
                ControlPanel.beaconDataButton.setEnabled(true);
            } else {
                ControlPanel.beaconButton.setText("Show Beacon");
                ControlPanel.beaconDataButton.setEnabled(false);
            }
            if (TWLPanel.isShowSab){
                ControlPanel.sabButton.setText("Hide SAB");
                ControlPanel.sabDataButton.setEnabled(true);
            } else {
                ControlPanel.sabButton.setText("Show SAB");
                ControlPanel.sabDataButton.setEnabled(false);
            }
            if (TWLPanel.isShowBox){
                ControlPanel.boxButton.setText("Hide Box");
                ControlPanel.boxDataButton.setEnabled(true);
            } else {
                ControlPanel.boxButton.setText("Show Box");
                ControlPanel.boxDataButton.setEnabled(false);
            }
            if (TWLPanel.isShowOther){
                ControlPanel.otherButton.setText("Hide Other");
                ControlPanel.otherDataButton.setEnabled(true);
            } else {
                ControlPanel.otherButton.setText("Show Other");
                ControlPanel.otherDataButton.setEnabled(false);
            }
            if (TWLPanel.isShowCrossingData){
                ControlPanel.crossingDataButton.setText("Hide Crossing Location");
            } else {
                ControlPanel.crossingDataButton.setText("Show Crossing Location");
            }
            if (TWLPanel.isShowSwitchData){
                ControlPanel.switchDataButton.setText("Hide Switch Location");
            } else {
                ControlPanel.switchDataButton.setText("Show Switch Location");
            }
            if (TWLPanel.isShowFloodgateData){
                ControlPanel.floodgateDataButton.setText("Hide Floodgate Location");
            } else {
                ControlPanel.floodgateDataButton.setText("Show Floodgate Location");
            }
            if (TWLPanel.isShowSignalData){
                ControlPanel.signalDataButton.setText("Hide Signal Location");
            } else {
                ControlPanel.signalDataButton.setText("Show Signal Location");
            }
            if (TWLPanel.isShowImpedanceBondData){
                ControlPanel.impedanceBondDataButton.setText("Hide Impedance Bond Location");
            } else {
                ControlPanel.impedanceBondDataButton.setText("Show Impedance Bond Location");
            }
            if (TWLPanel.isShowBeaconData){
                ControlPanel.beaconDataButton.setText("Hide Beacon Location");
            } else {
                ControlPanel.beaconDataButton.setText("Show Beacon Location");
            }
            if (TWLPanel.isShowSabData){
                ControlPanel.sabDataButton.setText("Hide SAB Location");
            } else {
                ControlPanel.sabDataButton.setText("Show SAB Location");
            }
            if (TWLPanel.isShowBoxData){
                ControlPanel.boxDataButton.setText("Hide Box Location");
            } else {
                ControlPanel.boxDataButton.setText("Show Box Location");
            }
            if (TWLPanel.isShowOtherData){
                ControlPanel.otherDataButton.setText("Hide Other Location");
            } else {
                ControlPanel.otherDataButton.setText("Show Other Location");
            }

            ControlPanel.stationDropdown.removeAllItems();
            for (int i = 0; i < TWLPanel.platformStations.length; i++) {
                ControlPanel.stationDropdown.addItem(TWLPanel.platformStations[i]);

            }

        }
        if (e.getSource() == KTLButton) {
            MyFrame.changeDisplay("KTL");

            if (KTLPanel.isShowSignal){
                ControlPanel.signalButton.setText("Hide Signal");
                ControlPanel.signalDataButton.setEnabled(true);
            } else {
                ControlPanel.signalButton.setText("Show Signal");
                ControlPanel.signalDataButton.setEnabled(false);
            }
            if (KTLPanel.isShowImpedanceBond){
                ControlPanel.impedanceBondButton.setText("Hide Impedance Bond");
                ControlPanel.impedanceBondDataButton.setEnabled(true);
            } else {
                ControlPanel.impedanceBondButton.setText("Show Impedance Bond");
                ControlPanel.impedanceBondDataButton.setEnabled(false);
            }
            if (KTLPanel.isShowBeacon){
                ControlPanel.beaconButton.setText("Hide Beacon");
                ControlPanel.beaconDataButton.setEnabled(true);
            } else {
                ControlPanel.beaconButton.setText("Show Beacon");
                ControlPanel.beaconDataButton.setEnabled(false);
            }
            if (KTLPanel.isShowSab){
                ControlPanel.sabButton.setText("Hide SAB");
                ControlPanel.sabDataButton.setEnabled(true);
            } else {
                ControlPanel.sabButton.setText("Show SAB");
                ControlPanel.sabDataButton.setEnabled(false);
            }
            if (KTLPanel.isShowBox){
                ControlPanel.boxButton.setText("Hide Box");
                ControlPanel.boxDataButton.setEnabled(true);
            } else {
                ControlPanel.boxButton.setText("Show Box");
                ControlPanel.boxDataButton.setEnabled(false);
            }
            if (KTLPanel.isShowOther){
                ControlPanel.otherButton.setText("Hide Other");
                ControlPanel.otherDataButton.setEnabled(true);
            } else {
                ControlPanel.otherButton.setText("Show Other");
                ControlPanel.otherDataButton.setEnabled(false);
            }
            if (KTLPanel.isShowCrossingData){
                ControlPanel.crossingDataButton.setText("Hide Crossing Location");
            } else {
                ControlPanel.crossingDataButton.setText("Show Crossing Location");
            }
            if (KTLPanel.isShowSwitchData){
                ControlPanel.switchDataButton.setText("Hide Switch Location");
            } else {
                ControlPanel.switchDataButton.setText("Show Switch Location");
            }
            if (KTLPanel.isShowFloodgateData){
                ControlPanel.floodgateDataButton.setText("Hide Floodgate Location");
            } else {
                ControlPanel.floodgateDataButton.setText("Show Floodgate Location");
            }
            if (KTLPanel.isShowSignalData){
                ControlPanel.signalDataButton.setText("Hide Signal Location");
            } else {
                ControlPanel.signalDataButton.setText("Show Signal Location");
            }
            if (KTLPanel.isShowImpedanceBondData){
                ControlPanel.impedanceBondDataButton.setText("Hide Impedance Bond Location");
            } else {
                ControlPanel.impedanceBondDataButton.setText("Show Impedance Bond Location");
            }
            if (KTLPanel.isShowBeaconData){
                ControlPanel.beaconDataButton.setText("Hide Beacon Location");
            } else {
                ControlPanel.beaconDataButton.setText("Show Beacon Location");
            }
            if (KTLPanel.isShowSabData){
                ControlPanel.sabDataButton.setText("Hide SAB Location");
            } else {
                ControlPanel.sabDataButton.setText("Show SAB Location");
            }
            if (KTLPanel.isShowBoxData){
                ControlPanel.boxDataButton.setText("Hide Box Location");
            } else {
                ControlPanel.boxDataButton.setText("Show Box Location");
            }
            if (KTLPanel.isShowOtherData){
                ControlPanel.otherDataButton.setText("Hide Other Location");
            } else {
                ControlPanel.otherDataButton.setText("Show Other Location");
            }

            ControlPanel.stationDropdown.removeAllItems();
            //ControlPanel.stationDropdown.addItem("trial KTL");
            for (int i = 0; i < KTLPanel.platformStations.length; i++) {
                ControlPanel.stationDropdown.addItem(KTLPanel.platformStations[i]);
            }
        }
        if (e.getSource() == TKLButton) {
            MyFrame.changeDisplay("TKL");

            if (TKLPanel.isShowSignal){
                ControlPanel.signalButton.setText("Hide Signal");
                ControlPanel.signalDataButton.setEnabled(true);
            } else {
                ControlPanel.signalButton.setText("Show Signal");
                ControlPanel.signalDataButton.setEnabled(false);
            }
            if (TKLPanel.isShowImpedanceBond){
                ControlPanel.impedanceBondButton.setText("Hide Impedance Bond");
                ControlPanel.impedanceBondDataButton.setEnabled(true);
            } else {
                ControlPanel.impedanceBondButton.setText("Show Impedance Bond");
                ControlPanel.impedanceBondDataButton.setEnabled(false);
            }
            if (TKLPanel.isShowBeacon){
                ControlPanel.beaconButton.setText("Hide Beacon");
                ControlPanel.beaconDataButton.setEnabled(true);
            } else {
                ControlPanel.beaconButton.setText("Show Beacon");
                ControlPanel.beaconDataButton.setEnabled(false);
            }
            if (TKLPanel.isShowSab){
                ControlPanel.sabButton.setText("Hide SAB");
                ControlPanel.sabDataButton.setEnabled(true);
            } else {
                ControlPanel.sabButton.setText("Show SAB");
                ControlPanel.sabDataButton.setEnabled(false);
            }
            if (TKLPanel.isShowBox){
                ControlPanel.boxButton.setText("Hide Box");
                ControlPanel.boxDataButton.setEnabled(true);
            } else {
                ControlPanel.boxButton.setText("Show Box");
                ControlPanel.boxDataButton.setEnabled(false);
            }
            if (TKLPanel.isShowOther){
                ControlPanel.otherButton.setText("Hide Other");
                ControlPanel.otherDataButton.setEnabled(true);
            } else {
                ControlPanel.otherButton.setText("Show Other");
                ControlPanel.otherDataButton.setEnabled(false);
            }
            if (TKLPanel.isShowCrossingData){
                ControlPanel.crossingDataButton.setText("Hide Crossing Location");
            } else {
                ControlPanel.crossingDataButton.setText("Show Crossing Location");
            }
            if (TKLPanel.isShowSwitchData){
                ControlPanel.switchDataButton.setText("Hide Switch Location");
            } else {
                ControlPanel.switchDataButton.setText("Show Switch Location");
            }
            if (TKLPanel.isShowFloodgateData){
                ControlPanel.floodgateDataButton.setText("Hide Floodgate Location");
            } else {
                ControlPanel.floodgateDataButton.setText("Show Floodgate Location");
            }
            if (TKLPanel.isShowSignalData){
                ControlPanel.signalDataButton.setText("Hide Signal Location");
            } else {
                ControlPanel.signalDataButton.setText("Show Signal Location");
            }
            if (TKLPanel.isShowImpedanceBondData){
                ControlPanel.impedanceBondDataButton.setText("Hide Impedance Bond Location");
            } else {
                ControlPanel.impedanceBondDataButton.setText("Show Impedance Bond Location");
            }
            if (TKLPanel.isShowBeaconData){
                ControlPanel.beaconDataButton.setText("Hide Beacon Location");
            } else {
                ControlPanel.beaconDataButton.setText("Show Beacon Location");
            }
            if (TKLPanel.isShowSabData){
                ControlPanel.sabDataButton.setText("Hide SAB Location");
            } else {
                ControlPanel.sabDataButton.setText("Show SAB Location");
            }
            if (TKLPanel.isShowBoxData){
                ControlPanel.boxDataButton.setText("Hide Box Location");
            } else {
                ControlPanel.boxDataButton.setText("Show Box Location");
            }
            if (TKLPanel.isShowOtherData){
                ControlPanel.otherDataButton.setText("Hide Other Location");
            } else {
                ControlPanel.otherDataButton.setText("Show Other Location");
            }

            ControlPanel.stationDropdown.removeAllItems();
            //ControlPanel.stationDropdown.addItem("trial TKL");
            for (int i = 0; i < TKLPanel.platformStations.length; i++) {
                ControlPanel.stationDropdown.addItem(TKLPanel.platformStations[i]);
            }
        }
        if (e.getSource() == ISLButton) {
            MyFrame.changeDisplay("ISL");

            if (ISLPanel.isShowSignal){
                ControlPanel.signalButton.setText("Hide Signal");
                ControlPanel.signalDataButton.setEnabled(true);
            } else {
                ControlPanel.signalButton.setText("Show Signal");
                ControlPanel.signalDataButton.setEnabled(false);
            }
            if (ISLPanel.isShowImpedanceBond){
                ControlPanel.impedanceBondButton.setText("Hide Impedance Bond");
                ControlPanel.impedanceBondDataButton.setEnabled(true);
            } else {
                ControlPanel.impedanceBondButton.setText("Show Impedance Bond");
                ControlPanel.impedanceBondDataButton.setEnabled(false);
            }
            if (ISLPanel.isShowBeacon){
                ControlPanel.beaconButton.setText("Hide Beacon");
                ControlPanel.beaconDataButton.setEnabled(true);
            } else {
                ControlPanel.beaconButton.setText("Show Beacon");
                ControlPanel.beaconDataButton.setEnabled(false);
            }
            if (ISLPanel.isShowSab){
                ControlPanel.sabButton.setText("Hide SAB");
                ControlPanel.sabDataButton.setEnabled(true);
            } else {
                ControlPanel.sabButton.setText("Show SAB");
                ControlPanel.sabDataButton.setEnabled(false);
            }
            if (ISLPanel.isShowBox){
                ControlPanel.boxButton.setText("Hide Box");
                ControlPanel.boxDataButton.setEnabled(true);
            } else {
                ControlPanel.boxButton.setText("Show Box");
                ControlPanel.boxDataButton.setEnabled(false);
            }
            if (ISLPanel.isShowOther){
                ControlPanel.otherButton.setText("Hide Other");
                ControlPanel.otherDataButton.setEnabled(true);
            } else {
                ControlPanel.otherButton.setText("Show Other");
                ControlPanel.otherDataButton.setEnabled(false);
            }
            if (ISLPanel.isShowCrossingData){
                ControlPanel.crossingDataButton.setText("Hide Crossing Location");
            } else {
                ControlPanel.crossingDataButton.setText("Show Crossing Location");
            }
            if (ISLPanel.isShowSwitchData){
                ControlPanel.switchDataButton.setText("Hide Switch Location");
            } else {
                ControlPanel.switchDataButton.setText("Show Switch Location");
            }
            if (ISLPanel.isShowFloodgateData){
                ControlPanel.floodgateDataButton.setText("Hide Floodgate Location");
            } else {
                ControlPanel.floodgateDataButton.setText("Show Floodgate Location");
            }
            if (ISLPanel.isShowSignalData){
                ControlPanel.signalDataButton.setText("Hide Signal Location");
            } else {
                ControlPanel.signalDataButton.setText("Show Signal Location");
            }
            if (ISLPanel.isShowImpedanceBondData){
                ControlPanel.impedanceBondDataButton.setText("Hide Impedance Bond Location");
            } else {
                ControlPanel.impedanceBondDataButton.setText("Show Impedance Bond Location");
            }
            if (ISLPanel.isShowBeaconData){
                ControlPanel.beaconDataButton.setText("Hide Beacon Location");
            } else {
                ControlPanel.beaconDataButton.setText("Show Beacon Location");
            }
            if (ISLPanel.isShowSabData){
                ControlPanel.sabDataButton.setText("Hide SAB Location");
            } else {
                ControlPanel.sabDataButton.setText("Show SAB Location");
            }
            if (ISLPanel.isShowBoxData){
                ControlPanel.boxDataButton.setText("Hide Box Location");
            } else {
                ControlPanel.boxDataButton.setText("Show Box Location");
            }
            if (ISLPanel.isShowOtherData){
                ControlPanel.otherDataButton.setText("Hide Other Location");
            } else {
                ControlPanel.otherDataButton.setText("Show Other Location");
            }


            ControlPanel.stationDropdown.removeAllItems();
            //ControlPanel.stationDropdown.addItem("trial ISL");
            for (int i = 0; i < ISLPanel.platformStations.length; i++) {
                ControlPanel.stationDropdown.addItem(ISLPanel.platformStations[i]);
            }
        }
    }
}


