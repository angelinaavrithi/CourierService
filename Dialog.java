import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Parent class of ParcelDialog and BalanceDialog (but not PaymentDialog)
 */
public class Dialog extends JDialog{
    protected JPanel namePanel = new JPanel();
    protected JLabel nameLabel = new JLabel("Enter client name:");
    protected JTextField nameField = new JTextField(12);

    protected JPanel addressPanel = new JPanel();
    protected JLabel addressLabel = new JLabel("Enter client address:");
    protected JTextField addressField = new JTextField(12);

    protected Courier theCourier;


    /**
     * Constructor for Dialog
     * @param frame the frame
     * @param title the title
     * @param courier the courier
     */
    public Dialog(JFrame frame, String title, Courier courier) {
        super(frame, true);
        theCourier = courier;
        setLocationRelativeTo(null);
        setTitle(title);

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.setBorder(new TitledBorder("Full Name"));

        addressPanel.add(addressLabel);
        addressPanel.add(addressField);
        addressPanel.setBorder(new TitledBorder("Address (street, number)"));

    }

}
