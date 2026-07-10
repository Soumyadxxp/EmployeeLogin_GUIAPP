package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

class MainPanel extends JPanel
{
    private JLabel lblMsg1,lblCDate,lblMsg3,lblCTime,lblMsg5,lblMsg6;
    private JTextField txtUID;
    private JPasswordField txtPwd;
    private JButton btnSubmit,btnReset;
    private Timer time;
    private Connection con = null;
    private Statement smt = null;
    private ResultSet rst = null;
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h,int mode)
    {
        JLabel temp = new JLabel(cap);
        temp.setFont(new Font("Courier New",1,16));
        temp.setBounds(x,y,w,h);
        if(mode == 0)
        {
            temp.setOpaque(false);
            temp.setHorizontalAlignment(JLabel.LEFT);
        }
        else if(mode == 1)
        {
            temp.setOpaque(true);
            temp.setBackground(Color.WHITE);
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            temp.setHorizontalAlignment(JLabel.CENTER);
        }
        super.add(temp);
        return temp;
    }
    private JComponent makeTextBox(int x,int y,int w,int h,int mode)
    {
        JComponent temp = null;
        if(mode == 1)
            temp = new JTextField();
        else if(mode == 2)
            temp = new JPasswordField();
        temp.setFont(new Font("Courier New", 1, 18));
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        temp.setBounds(x,y,w,h);
        add(temp);
        return temp;
    }
    private JButton makeButton(String cap,int x,int y,int w,int h)
    {
        JButton temp = new JButton(cap);
        temp.setFont(new Font("Courier New", 1, 16));
        temp.setMargin(new Insets(0,0,0,0));
        temp.setBounds(x,y,w,h);
        temp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Object ob = e.getSource();
                    if(ob == btnSubmit)
                    {
                        String uid = txtUID.getText();
                        String pwd = txtPwd.getText();
                        String qry = "select EMPLOYEEID,FNAME,LNAME,DEPT,DESIG,PWD,PROPICT from employee where employeeid = '"+uid+"' and pwd = '"+pwd+"'";
                        rst = smt.executeQuery(qry);
                        if(rst.next())
                        {
                            String[] report = new String[8];
                            report[0] = rst.getString(1);
                            report[1] = rst.getString(2);
                            report[2] = rst.getString(3);
                            report[3] = rst.getString(4);
                            report[4] = rst.getString(5);
                            report[5] = rst.getString(7);
                            report[6] = lblCDate.getText();
                            report[7] = lblCTime.getText();
                            LoginStatusFrame frame = new LoginStatusFrame(report);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setTitle("Employee Login Status");
                            frame.setSize(550,300);
                            frame.setResizable(false);
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Employee Not Found");
                        }
                    }
                    else if(ob == btnReset)
                    {
                        txtUID.setText("");
                        txtPwd.setText("");
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        add(temp);
        return temp;
    }
    public MainPanel()
    {
        try
        {
            lblMsg1 = makeLabel("Date",20,20,50,30,0);
            lblCDate = makeLabel("",80,20,130,30,1);
            lblMsg3 = makeLabel("Time",220,20,50,30,0);
            lblCTime = makeLabel("",280,20,130,30,1);
            
            lblMsg5 = makeLabel("Enter Employee ID",20,70,180,30,0);
            txtUID = (JTextField)makeTextBox(210,70,200,30,1);
            txtUID.setHorizontalAlignment(JTextField.CENTER);
            lblMsg6 = makeLabel("Enter Password",20,120,180,30,0);
            txtPwd = (JPasswordField)makeTextBox(210,120,200,30,2);
            txtPwd.setHorizontalAlignment(JTextField.CENTER);
            txtPwd.setEchoChar('*');
            
            btnSubmit = makeButton("Submit",83,170,100,30);
            btnReset = makeButton("Reset",266,170,100,30);
            
            time = new Timer(500, act);
            time.setRepeats(true);
            time.start();
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HR","hr");
            smt = con.createStatement();
            DatabaseMetaData dbaseMeta = con.getMetaData();
            ResultSet rset = dbaseMeta.getTables("%","HR","EMPLOYEE",new String[]{"TABLE"});
            if(!rset.next())
            {
                String qry = "CREATE TABLE EMPLOYEE(EMPLOYEEID VARCHAR2(15) PRIMARY KEY,FNAME VARCHAR2(15),LNAME VARCHAR2(15),DEPT VARCHAR2(15),DESIG VARCHAR2(15),PWD VARCHAR2(15),PROPICT VARCHAR2(150))";
                smt.executeUpdate(qry);
                qry = "CREATE TABLE LOGIN(LOGINID CHAR(15) PRIMARY KEY,EMPLOYEEID VARCHAR2(15),LOGINTIME DATE)";
                smt.executeUpdate(qry);
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    ActionListener act = new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Date now = new Date();
            int dy = now.getDate();
            int mt = now.getMonth()+1;
            int yr = now.getYear()+1900;
            int hh = now.getHours();
            int mm = now.getMinutes();
            int ss = now.getSeconds();
            String dt = String.format("%02d-%02d-%4d", dy,mt,yr);
            String tm = String.format("%02d:%02d:%02d", hh,mm,ss);
            lblCDate.setText(dt);
            lblCTime.setText(tm);
        }
    };
}
class MainFrame extends JFrame
{
    private MainPanel panel;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setBackground(new Color(225,250,160));
        panel.setLayout(new BorderLayout());
        super.add(panel);
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 260);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Employee Login");
        frame.setResizable(false);
    }
}

