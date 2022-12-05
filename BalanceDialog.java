import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BalanceDialog extends Dialog implements ActionListener {

    private JPanel buttonPanel = new JPanel();
    private JButton submitButton = new JButton("Check Balance");
    private JButton cancelButton = new JButton("Cancel");

    protected Courier theCourier;


    /**
     * Constructor for BalanceDialog
     * @param frame the frame
     * @param title the title
     * @param courier the courier
     */
    public BalanceDialog(JFrame frame, String title, Courier courier)
    {
        super(frame, title, courier);
        theCourier = courier;
        setLocationRelativeTo(null);
        setTitle(title);

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.setBorder(new TitledBorder("Name"));

        addressPanel.add(addressLabel);
        addressPanel.add(addressField);
        addressPanel.setBorder(new TitledBorder("Address"));

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

        getContentPane().setLayout(new GridLayout(3,1));
        getContentPane().add(namePanel);
        getContentPane().add(addressPanel);
        getContentPane().add(buttonPanel);
        pack();
        setVisible(true);
    }

    /**
     * What happens when the user fills the form and checks the balance
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String address = addressField.getText();

        if (name.equals("") || address.equals(""))
        {
            JOptionPane.showMessageDialog(this, "All fields should be entered!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if (!name.matches("[a-zA-Z _]*") || !address.matches("[A-Za-z0-9 _]*")) {
            JOptionPane.showMessageDialog(this, "Check name and address format.\nName: only letters\nAddress: only letters and numbers.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            Client client = Courier.findClient(name, address);
            if (client==null){
                JOptionPane.showMessageDialog(this, "Client not found!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
            else {
                new PaymentDialog(this, "Payment Details", client);
                dispose();
            }
        }
    }

}
