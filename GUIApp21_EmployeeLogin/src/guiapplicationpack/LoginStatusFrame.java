
package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

class LoginStatusPanel extends JPanel
{
    private JLabel lblProPict;
    private Connection con = null;
    private Statement smt = null;
    private String[] report = null;
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h,int mode)
    {
        JLabel temp = new JLabel(cap);
        if(mode == 0)
        {
            temp.setOpaque(true);
            temp.setBackground(Color.BLUE);
            temp.setForeground(Color.WHITE);
            temp.setFont(new Font("vewdana",1,20));
            temp.setHorizontalAlignment(JLabel.CENTER);
            Border b1 = BorderFactory.createLineBorder(Color.RED, 3);
            Border b2 = BorderFactory.createLineBorder(Color.WHITE, 2);
            Border b3 = BorderFactory.createCompoundBorder(b1, b2);
            temp.setBorder(b3);
        }
        else
        {
            temp.setFont(new Font("Courier New",1,16));
            if(mode == 2) temp.setForeground(Color.BLUE);
        }
        super.add(temp);
        temp.setBounds(x,y,w,h);
        return temp;
    }
    public LoginStatusPanel(String[] report)
    {
        try
        {
            File imgpath = new File(report[5]);
            BufferedImage bimg = ImageIO.read(imgpath);
            Image scaled = bimg.getScaledInstance(120,180,Image.SCALE_SMOOTH);
            Icon picon = new ImageIcon(scaled);
            
            lblProPict = new JLabel(picon);
            lblProPict.setBounds(10,70,120,180);
            lblProPict.setOpaque(true);
            Border br = BorderFactory.createLineBorder(Color.BLACK, 2);
            lblProPict.setBorder(br);
            add(lblProPict);
            
            makeLabel("Login Status",        10, 10,520,50,0);
            makeLabel("Employee ID    : ",   140, 70,170,30,1);
            makeLabel(report[0],                 330, 70,230,30,2);
            makeLabel("First Name     : ",   140,100,170,30,1);
            makeLabel(report[1],                 330,100,230,30,2);
            makeLabel("Last Name      : ",   140,130,170,30,1);
            makeLabel(report[2],                 330,130,230,30,2);
            makeLabel("Department     : ",   140,160,170,30,1);
            makeLabel(report[3],                 330,160,230,30,2);
            makeLabel("Designation    : ",   140,190,170,30,1);
            makeLabel(report[4],                 330,190,230,30,2);
            makeLabel("Login Date/Time: ",   140,220,170,30,1);
            makeLabel(report[6]+" "+report[7],   330,220,230,30,2);
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HR","hr");
            smt = con.createStatement();
            String[] cdate = report[6].split("-");
            String[] ctime = report[7].split(":");
            String logid = "'"+cdate[0]+cdate[1]+cdate[2]+"-"+ctime[0]+ctime[1]+ctime[2]+"'";
            String logdt = "'"+report[6]+" "+report[7]+"'";
            String qry = "INSERT INTO LOGIN VALUES("+logid+",'"+report[0]+"',to_date("+logdt+",'DD-MM-YYYY HH24:MI:SS'))";
            smt.executeUpdate(qry);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
public class LoginStatusFrame extends JFrame
{
    private LoginStatusPanel panel = null;
    public LoginStatusFrame(String[] report)
    {
        panel = new LoginStatusPanel(report);
        panel.setBackground(new Color(220,250,200));
        panel.setLayout(new BorderLayout());
        super.add(panel);
    }
}

