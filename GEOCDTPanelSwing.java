import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GEOCDTPanelSwing extends JPanel {

    private GeoCountDownTimer watch;
    private final Timer javaTimer;

    private final JButton startButton;
    private final JButton stopButton;
    private final JButton continueButton;

    private final JTextField yearsField;
    private final JTextField monthsField;
    private final JTextField daysField;
    private final JTextField daysParamField;
    private final JTextField dateParamField;

    private final JButton loadButton;
    private final JButton saveButton;
    private final JButton daysToGoButton;
    private final JButton futureDateButton;
    private final JButton newDateButton;
    private final JButton addButton;
    private final JButton subtractButton;

    private final JLabel daysToGoLabel;
    private final JLabel daysToGo;
    private final JLabel futureDateLabel;
    private final JLabel futureDate;
    private final JLabel lblTime;

    public GEOCDTPanelSwing() {

        javaTimer = new Timer(1000, new TimerListener());

        setLayout(new GridLayout(0, 2));
        setBackground(Color.lightGray);

        add(new JLabel("Original Date"));
        add(new JLabel());

        add(new JLabel("Year:"));
        yearsField = new JTextField("2022", 3);
        add(yearsField);

        add(new JLabel("Month:"));
        monthsField = new JTextField("10", 3);
        add(monthsField);

        add(new JLabel("Day:"));
        daysField = new JTextField("10", 3);
        add(daysField);

        watch = new GeoCountDownTimer(Integer.parseInt(yearsField.getText()),
                Integer.parseInt(monthsField.getText()),
                Integer.parseInt(daysField.getText()));

        startButton = new JButton("Start");
        add(startButton);

        stopButton = new JButton("Stop");
        add(stopButton);

        loadButton = new JButton("Load");
        add(loadButton);

        saveButton = new JButton("Save");
        add(saveButton);

        add(new JLabel("Enter days:"));
        daysParamField = new JTextField("0", 3);
        add(daysParamField);

        addButton = new JButton("Inc");
        add(addButton);

        subtractButton = new JButton("Dec");
        add(subtractButton);

        newDateButton = new JButton("Create New Date");
        add(newDateButton);

        dateParamField = new JTextField("10/10/2022", 3);
        add(dateParamField);

        daysToGoButton = new JButton("Days to Go");
        add(daysToGoButton);

        futureDateButton = new JButton("Future Date");
        add(futureDateButton);

        continueButton = new JButton("Continue");
        add(continueButton);
        add(new JLabel());

        add(new JLabel("Results"));
        add(new JLabel());

        daysToGoLabel = new JLabel("Days To Go: ");
        add(daysToGoLabel);

        daysToGo = new JLabel();
        add(daysToGo);

        futureDateLabel = new JLabel("Future Date: ");
        add(futureDateLabel);

        futureDate = new JLabel("");
        add(futureDate);

        add(new JLabel("Actual Date: "));

        lblTime = new JLabel("");
        add(lblTime);

        stopButton.addActionListener(new ButtonListener());
        startButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());
        saveButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        daysToGoButton.addActionListener(new ButtonListener());
        continueButton.addActionListener(new ButtonListener());
        subtractButton.addActionListener(new ButtonListener());
        newDateButton.addActionListener(new ButtonListener());
        futureDateButton.addActionListener(new ButtonListener());

    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            int mins, sec, milli, p;

            if (event.getSource() == stopButton) {
                javaTimer.stop();
            }

            if (event.getSource() == startButton) {
                try {
                    mins = Integer.parseInt(yearsField.getText());
                    sec = Integer.parseInt(monthsField.getText());
                    milli = Integer.parseInt(daysField.getText());
                    watch = new GeoCountDownTimer(mins, sec, milli);
                    javaTimer.start();
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == saveButton) {
                JFileChooser chooser = new JFileChooser();
                int status = chooser.showSaveDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                    String filename = chooser.getSelectedFile().getAbsolutePath();
                    watch.save(filename);
                }
            }

            if (event.getSource() == loadButton) {
                JFileChooser chooser = new JFileChooser();
                int status = chooser.showDialog(null, "Load File");
                if (status == JFileChooser.APPROVE_OPTION) {
                    String filename = chooser.getSelectedFile().getAbsolutePath();
                    watch.load(filename);
                }
            }

            if (event.getSource() == addButton) {
                try {
                    watch.inc(Integer.parseInt(daysParamField.getText()));
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == subtractButton) {
                try {
                    watch.dec(Integer.parseInt(daysParamField.getText()));
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == newDateButton) {
                try {
                    watch = new GeoCountDownTimer(dateParamField.getText());
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == daysToGoButton) {
                try {
                    daysToGo.setText(String.valueOf(watch.daysToGo(dateParamField.getText())));
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == futureDateButton) {
                try {
                    futureDate.setText((watch.daysInFuture(Integer.parseInt(daysParamField.getText())).toDateString()));
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == continueButton) {
                javaTimer.start();
            }

            lblTime.setText(watch.toString());
        }
    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                watch.dec(1);
                lblTime.setText(watch.toString());
            } catch (Exception exception) {

            }
        }
    }
}
