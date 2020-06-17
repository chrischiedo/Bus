package bus;

/**
 *
 * @author chiedo
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Schedule extends JInternalFrame {

    private final JLabel busNumber;
    private final JLabel regNumber;
    private final JLabel routeNumber;
    private final JLabel routeName;
    private final JLabel driverNumber;
    private final JLabel driverName;
    private final JLabel departureTime;
    private final JLabel date;
    private final JLabel trip;
    private final JComboBox cboBusNo;
    private final JComboBox cboRouteNo;
    private final JComboBox cboRouteName;
    private final JComboBox cboDriverNo;
    private final JComboBox cboDriverName;
    private final JComboBox cboRegNo;
    private final JComboBox cboTrip;
    private final JTextField txtDepTime;
    private JTextField txtDate;
    private final JButton check;
    private final JButton schedule;
    private final JButton cancel;
    private final JButton btnPrint;
    private final DateButton sDate;
    int year;
    String is;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JPanel jPanel1;
    private final JPanel jPanel3;
    private final JPanel jPanel4;
    private final JPanel jPanel5;
    private static final JTextArea txtInfo = new JTextArea(15, 40);
    private Connection connection;
    private static String info;

    public Schedule() {
        super("Scheduling Process", false, true, false, true);
        busNumber = new JLabel("Bus Number ");
        regNumber = new JLabel("Reg Number ");
        routeNumber = new JLabel("Route Number");
        routeName = new JLabel("Route Name ");
        driverNumber = new JLabel("Driver Number");
        driverName = new JLabel("Driver Name");
        date = new JLabel("Date Scheduled");
        trip = new JLabel("Trip Number");
        departureTime = new JLabel("Departure Time");

        cboBusNo = new JComboBox();
        cboRegNo = new JComboBox();
        cboRouteNo = new JComboBox();
        cboRouteName = new JComboBox();
        cboDriverNo = new JComboBox();
        cboDriverName = new JComboBox();
        txtDepTime = new JTextField(10);
        cboTrip = new JComboBox();
        check = new JButton("View Shedules", new ImageIcon(ClassLoader.getSystemResource("images/viewschd.png")));
        schedule = new JButton("Schedule", new ImageIcon(ClassLoader.getSystemResource("images/schd.png")));
        cancel = new JButton("Cancel", new ImageIcon(ClassLoader.getSystemResource("images/exit.png")));
        btnPrint = new JButton("Print", new ImageIcon(ClassLoader.getSystemResource("images/print.png")));
        sDate = new DateButton();
        cboTrip.addItem("1");
        cboTrip.addItem("2");
        //Labels Settings
        busNumber.setFont(new Font("sansserif", Font.ITALIC, 14));
        regNumber.setFont(new Font("sansserif", Font.ITALIC, 14));
        routeNumber.setFont(new Font("sansserif", Font.ITALIC, 14));
        routeName.setFont(new Font("sansserif", Font.ITALIC, 14));
        driverNumber.setFont(new Font("sansserif", Font.ITALIC, 14));
        date.setFont(new Font("sansserif", Font.ITALIC, 14));
        trip.setFont(new Font("sansserif", Font.ITALIC, 14));
        departureTime.setFont(new Font("sansserif", Font.ITALIC, 14));
        driverName.setFont(new Font("sansserif", Font.ITALIC, 14));
        driverName.setForeground(Color.blue);
        departureTime.setForeground(Color.blue);
        busNumber.setForeground(Color.blue);
        regNumber.setForeground(Color.blue);
        routeNumber.setForeground(Color.blue);
        routeName.setForeground(Color.blue);
        driverNumber.setForeground(Color.blue);
        driverName.setForeground(Color.blue);
        date.setForeground(Color.blue);
        trip.setForeground(Color.blue);

        jPanel1 = new JPanel(new java.awt.GridLayout(9, 2));
        jPanel1.add(busNumber);
        jPanel1.add(cboBusNo);
        jPanel1.add(regNumber);
        jPanel1.add(cboRegNo);
        jPanel1.add(routeNumber);
        jPanel1.add(cboRouteNo);
        jPanel1.add(routeName);
        jPanel1.add(cboRouteName);
        jPanel1.add(driverNumber);
        jPanel1.add(cboDriverNo);
        jPanel1.add(driverName);
        jPanel1.add(cboDriverName);
        jPanel1.add(departureTime);
        jPanel1.add(txtDepTime);
        jPanel1.add(date);
        jPanel1.add(sDate);
        jPanel1.add(trip);
        jPanel1.add(cboTrip);

        cboRouteNo.addItem("Select");
        cboBusNo.addItem("Select");
        cboRouteName.addItem("Select");
        cboDriverNo.addItem("Select");
        cboDriverName.addItem("Select");
        cboRegNo.addItem("Select");
        //Labels Settings

        jPanel3 = new javax.swing.JPanel(new java.awt.FlowLayout());

        jPanel3.add(jPanel1);

        jPanel4 = new javax.swing.JPanel(new java.awt.FlowLayout());
//		jPanel4.add(Enable);
        jPanel4.add(check);
        jPanel4.add(schedule);
        jPanel4.add(cancel);
        jPanel4.add(btnPrint);
        setSize(550, 330);
        add(jPanel3);
        setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        setResizable(false);
        try {

            Statement s = DBConnection.getDBConnection().createStatement();
        } catch (Exception excp) {
            excp.printStackTrace();
            info = info + excp.toString();
        }
        setCbx();
        setCombo();
        setRoute();

        schedule.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                validator();
            }
        });
        
        check.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                ScheduleList frm=new ScheduleList();
                MDIWindow.desktop.add(frm);
                frm.setVisible(true);
                try{
                    frm.setSelected(true);
                }catch(Exception ex){}
            new ScheduleList().setVisible(true);
            }
        });
        btnPrint.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                ScheduleReport frm=new ScheduleReport();
                MDIWindow.desktop.add(frm);
                frm.setVisible(true);
                try{
                    frm.setSelected(true);
                }catch(Exception ex){}                
            }
        });
        cboBusNo.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                cboRegNo.setSelectedIndex(cboBusNo.getSelectedIndex());

            }
        });
        cboRegNo.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                cboBusNo.setSelectedIndex(cboRegNo.getSelectedIndex());

            }
        });

        cboRouteNo.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                cboRouteName.setSelectedIndex(cboRouteNo.getSelectedIndex());

            }
        });
        cboRouteName.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                cboRouteNo.setSelectedIndex(cboRouteName.getSelectedIndex());

            }
        });
        cboDriverName.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                cboDriverNo.setSelectedIndex(cboDriverName.getSelectedIndex());

            }
        });
        cboDriverNo.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                cboDriverName.setSelectedIndex(cboDriverNo.getSelectedIndex());

            }
        });
        cancel.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();

            }
        });




        jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

        jPanel5.add(jPanel3, BorderLayout.CENTER);
        jPanel5.add(jPanel4, BorderLayout.SOUTH);
        add(jPanel5);

    }

    private void setCbx() {
        try {
            ResultSet rst = DBConnection.getDBConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT Bus_RegNo,BusNo FROM Buses order by BusNo");

            while (rst.next()) {

                cboRegNo.addItem(rst.getString(1));
                cboBusNo.addItem(rst.getString(2));
            }
        } catch (Exception n) {
            n.printStackTrace();
        }
    }

    private void setRoute() {
        try {
            ResultSet rst = DBConnection.getDBConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT Route_No,RouteName FROM Route ");

            while (rst.next()) {
                cboRouteNo.addItem(rst.getString(1));
                cboRouteName.addItem(rst.getString(2));


            }
        } catch (Exception n) {
            n.printStackTrace();
        }
    }

    private void setCombo() {
        String dr;
        dr = "Driver";
        try {
            ResultSet rst = DBConnection.getDBConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT Emp.empNo, Emp.Sname, Emp.Fname, Emp.Lname, Emp.Designation FROM Emp WHERE Emp.Designation='Driver'");
            while (rst.next()) {
                cboDriverNo.addItem(rst.getString(1));
                cboDriverName.addItem(rst.getString(2));
            }
        } catch (Exception n) {
            n.printStackTrace();
        }


    }

    public void validator() {
        String SQL;        
        SQL = ("SELECT * FROM Validator WHERE Bus_No='" + cboBusNo.getSelectedItem() + "' AND Trip_No='" + cboTrip.getSelectedItem() + "'AND Date_Schedule='" + sDate.getText() + "'OR DriverNo='" + cboDriverNo.getSelectedItem() + "' AND Trip_No='" + cboTrip.getSelectedItem() + "'AND Date_Schedule='" + sDate.getText() + "'");
        try {
            Statement stmt = DBConnection.getDBConnection().createStatement();
            stmt.execute(SQL);
            ResultSet rs = stmt.getResultSet();
            boolean recordfound = rs.next();
            if (recordfound) {

                JOptionPane.showMessageDialog(null, "Either You are Trying Give a driver \n" +
                        " Another bus or giving one bus two \n" +
                        "Drivers at the same time.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                try {
                    if (cboRouteNo.getSelectedItem() == ("Select")) {
                        JOptionPane.showMessageDialog(null, "please choose route number", "INFORMATION",
                                JOptionPane.DEFAULT_OPTION);
                        return;
                    }
                    if (cboRouteName.getSelectedItem() == ("Select")) {
                        JOptionPane.showMessageDialog(null, "please Choose RouteName", "INFORMATION",
                                JOptionPane.DEFAULT_OPTION);
                        return;
                    }
                    if (cboBusNo.getSelectedItem() == ("Select")) {
                        JOptionPane.showMessageDialog(null, "please choose bus number", "INFORMATION",
                                JOptionPane.DEFAULT_OPTION);
                        return;
                    }
                    if (cboRegNo.getSelectedItem() == ("Select")) {
                        JOptionPane.showMessageDialog(null, "please choose registration number", "INFORMATION",
                                JOptionPane.DEFAULT_OPTION);
                        return;
                    }
                    if (cboDriverNo.getSelectedItem() == ("Select")) {
                        JOptionPane.showMessageDialog(null, "please choose driver number", "INFORMATION",
                                JOptionPane.DEFAULT_OPTION);
                        return;
                    }
                    if (cboDriverName.getSelectedItem() == ("Select")) {
                        JOptionPane.showMessageDialog(null, "please choose DriverName", "INFORMATION",
                                JOptionPane.DEFAULT_OPTION);
                        return;
                    }

                    Statement statement = DBConnection.getDBConnection().createStatement();
                    {
                        String temp = "INSERT INTO Schedules (Bus_No,Bus_RegNo, Route_No, Route_Name,empNo,Driver_Name,Date_Scheduled,Trip_No,Dept_Time) VALUES ('" +
                                cboBusNo.getSelectedItem() + "', '" +
                                cboRegNo.getSelectedItem() + "', '" +
                                cboRouteNo.getSelectedItem() + "', '" +
                                cboRouteName.getSelectedItem() + "', '" +
                                cboDriverNo.getSelectedItem() + "', '" +
                                cboDriverName.getSelectedItem() + "', '" +
                                sDate.getText() + "', '" +
                                cboTrip.getSelectedItem() + "', '" +
                                txtDepTime.getText() + "')";
                        int result = statement.executeUpdate(temp);
                        String temp2 = "INSERT INTO Validator(Bus_No,DriverNo,RouteNo,Date_Schedule,Trip_No)VALUES('" +
                                cboBusNo.getSelectedItem() + "', '" +
                                cboDriverNo.getSelectedItem() + "', '" +
                                cboRouteNo.getSelectedItem() + "' ,'" +
                                sDate.getText() + "' ,'" +
                                cboTrip.getSelectedItem() + "')";

                        int results = statement.executeUpdate(temp2);

                        JOptionPane.showMessageDialog(null, "Scheduling Succesfully done", "Sucess??",
                                JOptionPane.DEFAULT_OPTION);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex.toString());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
