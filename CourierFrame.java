import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CourierFrame extends JFrame implements ActionListener {
    protected static Courier theCourier = new Courier();

    private JMenuBar mainMenuBar;
    private JMenu fileMenu;
    private JMenuItem menuLoad, menuSave;
    private JFileChooser file;
    private JTabbedPane tabs;
    private JPanel controlPanel, buttonPanel;
    private JLabel imageSpace;
    private JButton parcelButton, paymentButton, exitButton;
    private static JTextArea textParcels;
    private static JTextArea textClients;
    private JScrollPane paneParcels, paneClients;

    /**
     * Creates courier frame
     */
    public CourierFrame() {


        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ACG Courier");

        file = new JFileChooser();

        // Menus
        mainMenuBar = new JMenuBar();
        setJMenuBar(mainMenuBar);
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        mainMenuBar.add(fileMenu);
        menuLoad = new JMenuItem("Load");
        menuSave = new JMenuItem("Save");
        fileMenu.add(menuLoad);
        fileMenu.add(menuSave);

        // Tabs
        tabs = new JTabbedPane();

        // Image
        controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        imageSpace = new JLabel();
        ImageIcon source = new ImageIcon("courier.png");
        Image image = source.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT);
        ImageIcon displayedImage = new ImageIcon(image);
        imageSpace.setIcon(displayedImage);
        controlPanel.add(imageSpace, BorderLayout.NORTH);

        // Buttons
        buttonPanel = new JPanel();
        parcelButton = new JButton("New Parcel");
        paymentButton = new JButton("Pay Balance");
        exitButton = new JButton("Exit");
        buttonPanel.setBorder(new TitledBorder("Enter an option"));
        buttonPanel.add(parcelButton);
        buttonPanel.add(paymentButton);
        buttonPanel.add(exitButton);
        controlPanel.add(buttonPanel, BorderLayout.CENTER);

        // Parcels and Clients
        textParcels = new JTextArea(30, 10);
        textClients = new JTextArea(30, 10);
        paneParcels = new JScrollPane(textParcels);
        paneClients = new JScrollPane(textClients);
        textParcels.setEditable(false);
        textClients.setEditable(false);
        textParcels.setBackground(Color.WHITE);
        textClients.setBackground(Color.WHITE);
        textParcels.setBorder(new TitledBorder("Parcel List"));
        textClients.setBorder(new TitledBorder("Client List"));

        tabs.add("Courier", controlPanel);
        tabs.add("Parcel List", paneParcels);
        tabs.add("Client List", paneClients);

        getContentPane().add(tabs);

        // ActionListeners
        parcelButton.addActionListener(this);
        paymentButton.addActionListener(this);
        exitButton.addActionListener(this);
        menuLoad.addActionListener(this);
        menuSave.addActionListener(this);

        setVisible(true);
    }

    /**
     * Load data from file
     * @param fileName name of the file
     */
    private void load(String fileName)
    {
        try
        {
            FileInputStream fileInput = new FileInputStream(fileName);
            ObjectInputStream objInput = new ObjectInputStream (fileInput);
            theCourier = (Courier) objInput.readObject();
            objInput.close();
            JOptionPane.showMessageDialog(this, "Loaded data.","Information", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog
                    (this, e.getMessage(), "Error while loading.", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Save data to file
     * @param fileName name of file
     */
    private void save(String fileName)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objOut = new ObjectOutputStream (fileOut);
            objOut.writeObject(theCourier);
            objOut.flush();
            objOut.close();
            JOptionPane.showMessageDialog(this, "Saved data.","Information", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog
                    (this, e.getMessage(), "Error while saving.", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * List the courier's parcels and their information
     */
    public static void listParcels() {
        textParcels.setText("\nFROM \tTO \tCODE \tCLIENT ID \tFEE");
        for (Parcel p : theCourier.getParcels()) {
            textParcels.append("\n"+p.getOrigin()+"\t"+p.getDestination()+"\t"+p.getParcelCode()+"\t"+p.getClientid()+"\t"+p.getFee()+"\t");
        }
    }

    /**
     * List the courier's clients and their information
     */
    public static void listClients() {
        textClients.setText("\nNAME \t\tCOUNTRY \tID  \tBALANCE");
        for (Client c : theCourier.getClients()) {
            textClients.append("\n"+c.getName()+"\t"+c.getCountry()+"\t"+c.getId()+"\t"+c.getBalance()+"\t");
        }
    }

    /**
     * What happens if the user clicks any of the buttons
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==parcelButton) {
            new ParcelDialog(this, "New Parcel", theCourier);
        }
        if (e.getSource()==paymentButton) {
            new BalanceDialog(this, "Payment Details", theCourier);
        }
        else if (e.getSource()==menuLoad)
        {
            if (file.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
                File fileName = file.getSelectedFile();
                String filePath = fileName.getAbsolutePath();
                load(filePath);
            }
        }
        else if (e.getSource()==menuSave)
        {
            if (file.showSaveDialog(this)==JFileChooser.APPROVE_OPTION)
            {
                File fileName = file.getSelectedFile();
                String filePath = fileName.getAbsolutePath();
                save(filePath);
            }
        }
        else if (e.getSource()==exitButton)
        {
            int answer = JOptionPane.showConfirmDialog(this, "Are you certain you want to exit?",
                    "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION)
                System.exit(0);
        }
        Courier.calculateBalance();
        listParcels();
        listClients();

    }


}
