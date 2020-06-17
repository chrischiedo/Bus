package bus;

/**
 *
 * @author chiedo
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class BusDetailsUpdate extends JInternalFrame {

    private final JLabel BusNo;
    private final JLabel RegNo;
    private final JLabel Model;
    private final JLabel Capacity;
    private final JLabel DP;
    private final JLabel Ins;
    private final JLabel DI;
    private final JLabel DE;
    private final JTextField txtBusNo;
    private final JTextField txtRegNo;
    private final JTextField txtModel;
    private final JTextField txtCapacity;
    private final JTextField txtIns;
    private final JButton btnUpdate;
    private final JButton btnSearch;
    private JButton Load;
    private final JButton btnClear;
    private final JButton btnDelete;
    String busNo, regNo, model, capacity, db, is, ie, id;
    private final JButton btnCancel;
    private final JPanel fieldsPanel;
    private final JPanel buttonPanel;
    private final DateButton date_bought;
    private final DateButton date_ins;
    private final DateButton date_expiry;
    private static final JTextArea txtInfo = new JTextArea(15, 40);
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public BusDetailsUpdate(String regNo, String busNo, String model, String capacity,
                            String db, String is, String ie, String id) {
        super("Update Bus Details",false,true,false,true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));

        BusNo = new JLabel(" Bus Number ");
        RegNo = new JLabel(" Reg Number ");
        Model = new JLabel(" Model.: ");
        Capacity = new JLabel(" Capacity ");
        DP = new JLabel(" Date Purchased");
        Ins = new JLabel(" Insurance Status");
        DI = new JLabel(" Date Insured");
        DE = new JLabel(" Insurance Expiry Date");

        txtBusNo = new JTextField(10);
        txtRegNo = new JTextField(10);
        txtModel = new JTextField(10);
        txtCapacity = new JTextField(10);
        txtIns = new JTextField(10);

        date_bought = new DateButton();
        date_ins = new DateButton();
        date_expiry = new DateButton();

        txtBusNo.setText(busNo);
        txtRegNo.setText(regNo);
        txtModel.setText(model);
        txtCapacity.setText(capacity);
        date_bought.setText(db);
        txtIns.setText(is);
        date_ins.setText(ie);
        date_expiry.setText(id);

        date_ins.setForeground(Color.red);
        date_bought.setForeground(Color.red);
        date_expiry.setForeground(Color.red);

        btnUpdate = new JButton("Update", new ImageIcon(ClassLoader.getSystemResource("images/update.png")));
        btnSearch = new JButton("Search", new ImageIcon(ClassLoader.getSystemResource("images/search.png")));
        btnClear = new JButton("Clear", new ImageIcon(ClassLoader.getSystemResource("images/clear.png")));
        btnDelete = new JButton("Delete", new ImageIcon(ClassLoader.getSystemResource("images/delete.png")));
        btnCancel = new JButton("Cancel", new ImageIcon(ClassLoader.getSystemResource("images/exit.png")));

        fieldsPanel = new JPanel(new GridLayout(8, 2));
        fieldsPanel.setPreferredSize(new Dimension(400, 250));
        fieldsPanel.add(BusNo);
        fieldsPanel.add(txtBusNo);
        fieldsPanel.add(RegNo);
        fieldsPanel.add(txtRegNo);
        fieldsPanel.add(Model);
        fieldsPanel.add(txtModel);
        fieldsPanel.add(Capacity);
        fieldsPanel.add(txtCapacity);
        fieldsPanel.add(DP);
        fieldsPanel.add(date_bought);
        fieldsPanel.add(Ins);
        fieldsPanel.add(txtIns);
        fieldsPanel.add(DI);
        fieldsPanel.add(date_ins);
        fieldsPanel.add(DE);
        fieldsPanel.add(date_expiry);

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        
        getContentPane().add(fieldsPanel,BorderLayout.CENTER);
        getContentPane().add(buttonPanel,BorderLayout.PAGE_END);
        pack();
        
        btnUpdate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtBusNo.getText() == null ||txtBusNo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Error?? BusNo can't be null", "Error", JOptionPane.DEFAULT_OPTION);
                    txtBusNo.requestFocus();
                    return;
                }
                if (txtRegNo.getText() == null || txtRegNo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Error?? RegNo can't be null", "Error",JOptionPane.DEFAULT_OPTION);
                    txtRegNo.requestFocus();
                    return;
                }
                if (txtModel.getText() == null ||txtModel.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Error?? Model Field is required", "Error",JOptionPane.DEFAULT_OPTION);
                    txtModel.requestFocus();
                    return;
                }
                if (txtCapacity.getText() == null || txtCapacity.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Error?? Enter bus capacity", "Error",JOptionPane.DEFAULT_OPTION);
                    txtCapacity.requestFocus();
                    return;
                }
                if (txtIns.getText() == null || txtIns.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Error?? Insurance status entry is required","Error", JOptionPane.DEFAULT_OPTION);
                    txtIns.requestFocus();
                    return;
                }
                try {
                    Statement statement = DBConnection.getDBConnection().createStatement();
                    {
                        String sql = "UPDATE Buses SET " +
                                "  Model              ='" + txtModel.getText() +
                                "',Capacity           ='" + txtCapacity.getText() +
                                "',DateBought         ='" + date_bought.getText() +
                                "',Insurance_Status   ='" + txtIns.getText() +
                                "',Date_Insured       ='" + date_ins.getText() +
                                "',Insurance_Expiry   ='" + date_expiry.getText() +
                                "' WHERE BusNo LIKE'" + txtBusNo.getText() + "'";
                        int result = statement.executeUpdate(sql);
                        dispose();
                    }
                } catch (SQLException sqlex) {
                    JOptionPane.showMessageDialog(null, "Error on updation","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });


        btnDelete.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                int DResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete Record?");
                if (DResult == JOptionPane.YES_OPTION) {
                    try {
                        Statement stmt = DBConnection.getDBConnection().createStatement();
                        if (!txtBusNo.equals("")) {
                            String query = ("DELETE  FROM Buses where BusNo ='" + txtBusNo.getText() + "'");
                            int result = stmt.executeUpdate(query);
                            if (result == 0) {
                                JOptionPane.showMessageDialog(null, "Record Deleted","DELETION", JOptionPane.DEFAULT_OPTION);
                            } else {
                                txtInfo.append("\nDeletion failed\n");
                                txtBusNo.setText("");
                                txtRegNo.setText("");
                                txtModel.setText("");
                                txtCapacity.setText("");
                                txtIns.setText("");
                            }
                            stmt.close();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Cannot delete record now, May be related with other tables"  ,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnClear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtBusNo.setText("");
                txtRegNo.setText("");
                txtModel.setText("");
                txtCapacity.setText("");

                txtIns.setText("");


            }
        });
        btnSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtBusNo.equals("")) {

                        Statement statement = DBConnection.getDBConnection().createStatement();
                        String query = ("SELECT * FROM Buses where BusNo ='" + txtBusNo.getText() + "'");

                        ResultSet rs = statement.executeQuery(query);
                        display(rs);
                        statement.close();
                    }
                } catch (SQLException sqlex) {
                    sqlex.printStackTrace();
                }



                setVisible(true);

            }
        });

        
    }//constructor closed

    public void display(ResultSet rs) {
        try {
            //rs.next();

            boolean recordNumber = rs.next();
            if (recordNumber) {
                regNo = rs.getString(1);

                busNo = rs.getString(2);

                model = rs.getString(3);
                capacity = rs.getString(4);
                db = rs.getString(5);

                is = rs.getString(6);
                ie = rs.getString(7);
                id = rs.getString(8);

                txtBusNo.setText(busNo);
                txtRegNo.setText(regNo);
                txtModel.setText(model);
                txtCapacity.setText(capacity);
                date_bought.setText(db);
                txtIns.setText(is);
                date_ins.setText(ie);
                date_expiry.setText(id);
            } else {
                JOptionPane.showMessageDialog(null, "Record Not found", "ERROR",
                        JOptionPane.DEFAULT_OPTION);
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
}//class closed
