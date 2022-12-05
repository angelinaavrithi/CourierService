import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ParcelDialog extends Dialog implements ActionListener {

    private JPanel originPanel = new JPanel();
    private JLabel originLabel = new JLabel("Enter city of origin: ");
    private JTextField originField = new JTextField(12);

    private JPanel destinationPanel = new JPanel();
    private JLabel destinationLabel = new JLabel("Enter destination: ");
    private JTextField destinationField = new JTextField(12);

    protected JPanel shippingPanel = new JPanel();
    static String[] methods = { "Standard", "Express", "Next Day"};

    public static JComboBox<String> menu = new JComboBox<String>(methods);

    private JPanel buttonPanel = new JPanel();
    private JButton submitButton = new JButton("Submit Order");
    private JButton cancelButton = new JButton("Cancel");

    protected Courier theCourier;


    /**
     * Parcel Dialog
     * @param frame the frame
     * @param title the title
     * @param courier the courier
     */
    public ParcelDialog(JFrame frame, String title, Courier courier)
    {
        super(frame, title, courier);
        theCourier = courier;
        setLocationRelativeTo(null);
        setTitle(title);

        originPanel.add(originLabel);
        originPanel.add(originField);
        originPanel.setBorder(new TitledBorder("Origin Country Code"));

        destinationPanel.add(destinationLabel);
        destinationPanel.add(destinationField);
        destinationPanel.setBorder(new TitledBorder("Destination Country Code"));

        shippingPanel.add(menu);
        shippingPanel.setBorder(new TitledBorder("Shipping Method"));

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setBorder(new TitledBorder("Select"));

        submitButton.addActionListener(this);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        getContentPane().setLayout(new GridLayout(6,1));
        getContentPane().add(namePanel);
        getContentPane().add(addressPanel);
        getContentPane().add(originPanel);
        getContentPane().add(destinationPanel);
        getContentPane().add(shippingPanel);
        getContentPane().add(buttonPanel);
        pack();
        setVisible(true);
    }

    /**
     * What happens when the user fills the form and orders a new parcel
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String address = addressField.getText();
        String origin = originField.getText();
        String destination = destinationField.getText();

        if (name.equals("") || address.equals("") || origin.equals("") || destination.equals(""))
        {
            JOptionPane.showMessageDialog(this, "All fields should be entered!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if (!name.matches("[a-zA-Z _]*") || !address.matches("[A-Za-z0-9 _]*")) {
            JOptionPane.showMessageDialog(this, "Check name and address format.\nName: only letters\nAddress: only letters and numbers.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if (!origin.matches("[A-Z]{2}") || !destination.matches("[A-Z]{2}")) {
            JOptionPane.showMessageDialog(this, "Check country code format.\nCountry code can only include two uppercase letters.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            int clientID = theCourier.registerClient(name, address, origin).getId();
            theCourier.registerParcel(clientID, origin, destination);

            JOptionPane.showMessageDialog(this, "Order completed.", "Information", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }

    }

}
