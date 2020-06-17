package bus;

/**
 *
 * @author chiedo
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class NewDriverEntry extends JInternalFrame {

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JLabel employeeNumber;
    private final JLabel surname;
    private final JLabel firstName;
    private final JLabel lastName;
    private final JLabel gender;
    private final JLabel designation;
    private final JLabel telephone;
    private final JLabel lblEmployeePic;
    private final JLabel email;
    private final JLabel address;
    private final JLabel dateOfBirth;
    private final JTextField txtEmployeeNumber;
    private final JTextField txtSurname;
    private final JTextField txtFirstName;
    private final JTextField txtLastName;
    private final JTextField txtDesignation;
    private final JTextField txtTelephone;
    private final JTextField txtEmail;
    private final JTextField txtAddress;
    private final JButton jButton1;
    private final JButton jButton2;
    private final JButton addPic;
    private final JButton clear;
    private JButton next;
    private final JPanel jPanel1;
    private final JPanel pics;
    private final JPanel jPanel3;
    private final JPanel jPanel4;
    private final JPanel jPanel5;
    private final DateButton dob;
    private final JComboBox cboGender;
    private static final JTextArea txtInfo = new JTextArea(15, 40);
    // private Connection dbconn;
    private Connection connection;
    private static String info;
    final JFileChooser fc = new JFileChooser();
    String getPicture;

    public NewDriverEntry() {

        super("Add New Driver",false,true,false,true);
        setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
        employeeNumber = new JLabel("Employee Number ");
        surname = new JLabel("Surname ");
        firstName = new JLabel("First Name ");
        lastName = new JLabel("Last Name ");
        gender = new JLabel("Gender ");
        designation = new JLabel("Designation ");
        telephone = new JLabel("Telephone Number");
        email = new JLabel("E-mail Address");
        address = new JLabel("Address");
        dateOfBirth = new JLabel("DOB");
        lblEmployeePic = new JLabel(new ImageIcon(ClassLoader.getSystemResource("images/defaultpic.png")));
        txtEmployeeNumber = new JTextField(10);
        txtSurname = new JTextField(10);
        txtFirstName = new JTextField(10);
        txtLastName = new JTextField(10);
        cboGender = new JComboBox();
        txtDesignation = new JTextField(10);
        txtTelephone = new JTextField(10);
        txtEmail = new JTextField(10);
        txtAddress = new JTextField(10);

        jButton1 = new JButton("Add Record", new ImageIcon(ClassLoader.getSystemResource("images/addnew.png")));
        jButton2 = new JButton("Cancel", new ImageIcon(ClassLoader.getSystemResource("images/exit.png")));
        clear = new JButton("Clear", new ImageIcon(ClassLoader.getSystemResource("images/clear.png")));
        addPic = new JButton("Select pic");
        dob = new DateButton();
        dob.setForeground(Color.red);

        pics = new JPanel();
        pics.setPreferredSize(new Dimension(150, 250));
        pics.add(lblEmployeePic);
        pics.add(addPic);

        jPanel1 = new JPanel(new java.awt.GridLayout(10, 2));
        jPanel1.setPreferredSize(new Dimension(400, 250));
        jPanel1.add(employeeNumber);
        jPanel1.add(txtEmployeeNumber);
        jPanel1.add(surname);
        jPanel1.add(txtSurname);
        jPanel1.add(firstName);
        jPanel1.add(txtFirstName);
        jPanel1.add(lastName);
        jPanel1.add(txtLastName);
        jPanel1.add(gender);
        jPanel1.add(cboGender);

        jPanel1.add(dateOfBirth);
        jPanel1.add(dob);
        jPanel1.add(telephone);
        jPanel1.add(txtTelephone);
        jPanel1.add(email);
        jPanel1.add(txtEmail);
        jPanel1.add(address);
        jPanel1.add(txtAddress);
        jPanel1.add(designation);
        jPanel1.add(txtDesignation);

        jPanel4 = new JPanel();
        jPanel4.add(jButton1);
        jPanel4.add(jButton2);
        jPanel4.add(clear);

        jPanel3 = new JPanel();
        jPanel3.add(jPanel1);
        jPanel3.add(pics);
        jPanel3.add(jPanel4);
        add(jPanel3);
        setSize(400, 250);
        setResizable(false);
        cboGender.addItem("Male");
        cboGender.addItem("Female");
        setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        try {
            Statement s = DBConnection.getDBConnection().createStatement();
        } catch (Exception excp) {
            excp.printStackTrace();
        }
        generator();
        txtSurname.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {

                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    e.consume();
                }
            }
        });
        txtFirstName.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {

                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    e.consume();
                }
            }
        });
        txtLastName.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {

                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    e.consume();
                }
            }
        });


        txtDesignation.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    txtDesignation.requestFocus();
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    e.consume();
                }
            }
        });
        txtTelephone.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent e) {
                JTextField textField =
                        (JTextField) e.getSource();
                String content = textField.getText();
                if (content.length() != 0) {
                    try {
                        Integer.parseInt(content);
                    } catch (NumberFormatException nfe) {
                        getToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Invalid data entry", "Error",JOptionPane.DEFAULT_OPTION);
                        textField.requestFocus();
                        txtTelephone.setText("");
                    }
                }
            }
        });
        jButton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtEmployeeNumber.getText() == null ||
                        txtEmployeeNumber.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Employee Number",
                            "Error", JOptionPane.DEFAULT_OPTION);
                    txtEmployeeNumber.requestFocus();
                    return;
                }

                if (txtSurname.getText() == null ||
                        txtSurname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Employee Surname ",
                            "Error", JOptionPane.DEFAULT_OPTION);
                    txtSurname.requestFocus();
                    return;
                }

                if (txtFirstName.getText() == null ||
                        txtFirstName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enetr Employee First Name",
                            "Error", JOptionPane.DEFAULT_OPTION);
                    txtFirstName.requestFocus();
                    return;
                }

                if (txtLastName.getText() == null ||
                        txtLastName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enetr Employee Last Name",
                            "Error", JOptionPane.DEFAULT_OPTION);
                    txtLastName.requestFocus();
                    return;
                }
                if (txtTelephone.getText() == null ||
                        txtTelephone.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter telphone number",
                            "Error", JOptionPane.DEFAULT_OPTION);
                    txtTelephone.requestFocus();
                    return;
                }
                if (txtEmail.getText() == null ||
                        txtEmail.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter E-mail address",
                            "Error", JOptionPane.DEFAULT_OPTION);
                    txtEmail.requestFocus();
                    return;
                }
                if (txtAddress.getText() == null ||
                        txtAddress.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Address","Error", JOptionPane.DEFAULT_OPTION);
                    txtAddress.requestFocus();
                    return;
                }

                if (txtDesignation.getText() == null ||
                        txtDesignation.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Employee designation","Error", JOptionPane.DEFAULT_OPTION);
                    txtDesignation.requestFocus();
                    return;
                }

                try {
                    Statement statement = DBConnection.getDBConnection().createStatement();
                    {
                        String temp = "INSERT INTO Emp (EmpNo, Sname, Fname, Lname, Gender,DOB,Designation,Telephone,E_Mail,Address) VALUES ('" +
                                txtEmployeeNumber.getText() + "', '" +
                                txtSurname.getText() + "', '" +
                                txtFirstName.getText() + "', '" +
                                txtLastName.getText() + "', '" +
                                cboGender.getSelectedItem() + "', '" +
                                dob.getText() + "', '" +
                                txtDesignation.getText() + "', '" +
                                txtTelephone.getText() + "', '" +
                                txtEmail.getText() + "', '" +
                                txtAddress.getText() + "')";
                        try {
                            lblEmployeePic.setIcon(new ImageIcon("Employees/" + txtEmployeeNumber.getText() + ".png"));
                        } catch (Exception p) {
                        }
                        int result = statement.executeUpdate(temp);
                        String[] ObjButtons = {"Yes", "No"};
                        int PromptResult = JOptionPane.showOptionDialog(null, "Record succesfully added.Do you want to add another?",
                                "tobiluoch", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                        if (PromptResult == 0) {
                            generator();

                            txtSurname.setText("");
                            txtFirstName.setText("");
                            txtLastName.setText("");

                            txtDesignation.setText("");
                            txtTelephone.setText("");
                            txtEmail.setText("");

                            txtAddress.setText("");

                        } else {
                            new Employee().setVisible(true);
                            setVisible(false);

                        }
                    }

                } catch (SQLException sqlex) {
                    sqlex.printStackTrace();


                }
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                setVisible(true);
                dispose();
            }
        });
        addPic.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        clear.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {

                txtSurname.setText("");
                txtFirstName.setText("");
                txtLastName.setText("");
                txtDesignation.setText("");
                txtTelephone.setText("");
                txtEmail.setText("");
                txtAddress.setText("");

            }
        });

        jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

        jPanel5.add(jPanel3, BorderLayout.CENTER);
        jPanel5.add(jPanel4, BorderLayout.SOUTH);
        getContentPane().add(jPanel5);
        pack();

    }

    private void generator() {

        try {
            ResultSet rst = DBConnection.getDBConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT empNo FROM Emp");
            txtEmployeeNumber.setText("1000");
            while (rst.next()) {
                String s;
                int number = rst.getInt(1);
                number = number + 1;

                s = "" + number;
                txtEmployeeNumber.setText(s);

            }
        } catch (Exception n) {
            JOptionPane.showMessageDialog(null,"Error on operation");
        }
    }

    private void openFile() {
        int returnVal = fc.showOpenDialog(NewDriverEntry.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File dialog = fc.getSelectedFile();
            getPicture = dialog.getPath();
            lblEmployeePic.setIcon(new ImageIcon(getPicture));
        }
    }
}
