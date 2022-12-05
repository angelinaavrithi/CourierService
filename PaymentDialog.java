import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentDialog extends JFrame implements ActionListener {

    protected JPanel balancePanel = new JPanel();
    private JTextArea balanceAmount = new JTextArea(1,3);
    private JLabel balanceLabel = new JLabel("\tYour balance is ");
    protected JPanel questionPanel = new JPanel();
    private JLabel questionLabel = new JLabel("\tWould you like to pay your balance now?");

    protected JPanel buttonPanel = new JPanel();
    protected JButton yesButton = new JButton("Yes");
    private JButton noButton = new JButton("Cancel");
    protected Client theClient;

    /**
     * Constructor for PaymentDialog
     * @param frame the frame
     * @param title the title
     * @param client the client
     */
    public PaymentDialog(BalanceDialog frame, String title, Client client) {
        super();
        theClient = client;
        setLocationRelativeTo(null);
        setTitle(title);

        balanceAmount.setEditable(false);
        balanceAmount.setText(client.balanceToString());

        balancePanel.add(balanceLabel);
        balancePanel.add(balanceAmount);
        questionPanel.add(questionLabel);

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        yesButton.addActionListener(this);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        getContentPane().setLayout(new GridLayout(3,1));
        getContentPane().add(balancePanel);
        getContentPane().add(questionPanel);
        getContentPane().add(buttonPanel);
        pack();
        setVisible(true);

    }

    /**
     * What happens when the user pays their balance
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        theClient.setBalance(0);
        JOptionPane.showMessageDialog(this, "Payment completed.",
                "Information", JOptionPane.INFORMATION_MESSAGE);
        CourierFrame.listParcels();
        CourierFrame.listClients();
        dispose();
    }
}
