import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Notesmanager{
    Notesmanager(){
        
    }
    
    public static ImageIcon bg=new ImageIcon("icons8-notes-300(@3x).png");
    static String head;
    static String body;
    String username;
    String lpassword;
    int flag;
    static Font fnt=new Font("serif",Font.BOLD,17);
    
    static Font fnt1=new Font("serif",Font.BOLD,16);
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_-+=[]{}|:;><',.?/~`";
    public int login(){
        JFrame f5=new JFrame("login");
        JButton bl= new JButton("Login");
       
        JLabel ml5=new JLabel(bg);
        JTextField tun=new JTextField();
        JPasswordField tup=new JPasswordField();
        JLabel ml6=new JLabel("USERNAME ");
        JLabel ml7=new JLabel("PASSWORD");
        tun.setFont(fnt);
        tup.setFont(fnt);
        JCheckBox showpassword=new JCheckBox("Show Password");
        showpassword.setBounds(620, 275, 150, 50);
        ml6.setBounds(370, 245, 100, 20);
        ml7.setBounds(370, 280, 100, 20);
        bl.setBounds(460, 400, 150, 50);
        tun.setBounds(450,240 , 170, 30);
        tup.setBounds(450, 280, 170, 30);
        tun.setOpaque(false);
        tup.setOpaque(false);
        bl.setOpaque(false);
        bl.setContentAreaFilled(false);
        f5.add(ml5);
        ml5.setLayout(null);
        ml5.add(showpassword);
        ml5.add(ml6);
        ml5.add(ml7);
        ml5.add(bl);
        ml5.add(tun);
        ml5.add(tup);
        showpassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(showpassword.isSelected()){
                    tup.setEchoChar((char)0);
                }
                else{
                    tup.setEchoChar('*');
                }
            }
        });
        
        bl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                username = tun.getText();
                lpassword = tup.getText();
                String sql = "select * from login";
                try{       
                    Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1/notes","root", "123456789"); 
                    PreparedStatement stmt=conn.prepareStatement(sql);
                    ResultSet rs=stmt.executeQuery();
                    while(rs.next()){
                        if(username.equals(rs.getString(1))){
                            flag =1;
                            if(lpassword.equals(rs.getString(2))){
                                f5.setVisible(false);
                                Window();
                                break;
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "wrong passsword");
                                tup.setText("");
                            }

                            }
                        
                    }
                    if(flag!=1){
                        JOptionPane.showMessageDialog(null, "USERNAME DOES NOT EXISTS");
                        tun.setText("");
                        tup.setText("");
                    }
                    conn.close();
                }
                catch(Exception ex){
                    System.out.println(ex);
                
                }  
                
            }
        });
        f5.setSize(1080,720);
        f5.setLocationRelativeTo(null);
        f5.setVisible(true);
        f5.setIconImage(bg.getImage());
        f5.setResizable(false);
        f5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return 0;
    }
    public static void Window(){
        JFrame f=new JFrame("Home");
        JButton b1 =new JButton("Generate New Password");
        JButton b2= new JButton("Open Notes");
        JButton b3= new JButton("Open Generated Passwords");
        JButton b4=new JButton("Create New Notes");
        b1.setBounds(670,250,250,50);
        b2.setBounds(160,350,250,50);
        b3.setBounds(670,350,250,50);
        b4.setBounds(160, 250, 250, 50);
        b1.setFont(fnt1);
        b2.setFont(fnt1);
        b3.setFont(fnt1);
        b4.setFont(fnt1);
        f.setFont(fnt1);
        JLabel ml=new JLabel(bg);
        f.add(ml);
        ml.setLayout(null);
        ml.add(b1);
        ml.add(b2);
        ml.add(b3);
        ml.add(b4);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window4();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    window2();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    window5();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                window3();
            }
        });

        // Add the ActionListener to the button
        f.setIconImage(bg.getImage());
        f.setSize(1080,720);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void window2() throws SQLException{
        int count =0;
                Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/notes","root", "123456789");
                JFrame f7=new JFrame("Notes");
                
                JLabel ml7=new JLabel(bg);
                JLabel jp =new JLabel();
                jp.setLayout(new GridLayout(5,5));
                jp.setSize(1050,620);
                
                try{
                    String sql = "select * from notes";
                    PreparedStatement stmt=con.prepareStatement(sql);
                    ResultSet rs=stmt.executeQuery();
                    String[] arr = new String[50];
                    String[] arr1=new String[50];

                    while(rs.next()){
                        arr[count]=rs.getString(1);
                        arr1[count]=rs.getString(2);
                        JButton b =new JButton(arr[count]);
                        jp.add(b);
                        
                        String text=arr1[count];
                        b.addActionListener(new ActionListener() {
                            
                            public void actionPerformed(ActionEvent e)
                            {
                                JOptionPane.showMessageDialog(null,""+text);

                            }
                        });
                        count++;
                        
                    }
                }
                catch(Exception ex){
                    System.out.println(ex);
                    
                }   
                
               
                
                ml7.add(jp);
                f7.add(ml7);
                f7.setSize(1080, 720);
                f7.setVisible(true);
                f7.setIconImage(bg.getImage());
                f7.setResizable(false);
                f7.setLocationRelativeTo(null);
                f7.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                con.close();
    }
            public static void window3(){
                
                JFrame f8=new JFrame("create notes");
                JButton bc= new JButton("create");
                    
                JLabel ml8=new JLabel(bg);
                JTextArea thead=new JTextArea(1,1);
                JTextArea tbody=new JTextArea(10,10);
                JLabel mlch=new JLabel("HEADING ");
                JLabel mlcb=new JLabel("BODY ");
                thead.setFont(new Font("Arial",Font.PLAIN,14));
                tbody.setFont(new Font("Arial",Font.PLAIN,12));
                
                mlch.setBounds(10, 10, 100, 20);
                mlcb.setBounds(450, 20, 100, 20);
                bc.setBounds(150, 130, 150, 50);
                thead.setBounds(110,10 , 250, 30);
                tbody.setBounds(500, 10, 500, 200);
                f8.add(ml8);
                ml8.setLayout(null);
                JScrollPane scrollPane = new JScrollPane(tbody);
                scrollPane.setBounds(500, 10, 500, 200);
                
                
                ml8.add(scrollPane);
                ml8.add(mlcb);
                ml8.add(mlch);
                ml8.add(bc);
                ml8.add(thead);
                bc.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        head = thead.getText();
                        body =tbody.getText();
                        String sql1="insert into notes values(?,?)";
                        try{
                            Connection con1 =DriverManager.getConnection("jdbc:mysql://127.0.0.1/notes","root", "123456789");
                            PreparedStatement stmt1=con1.prepareStatement(sql1);
                            stmt1.setString(1, head);
                            stmt1.setString(2,body);
                            stmt1.executeUpdate();
                            con1.close();
                            JOptionPane.showMessageDialog(null,"notes created succesfully");
                            thead.setText("");
                            tbody.setText("");
                        }
                        catch(Exception ex){

                        }
                }

                    
                });
                
                f8.setSize(1080,720);
                f8.setLocationRelativeTo(null);
                f8.setVisible(true);
                f8.setIconImage(bg.getImage());
                f8.setResizable(false);
                f8.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    }
            public static void window4(){
                JFrame f9=new JFrame("Password Generator");
                JButton bgp=new JButton("Generate Password");
                JButton bsp=new JButton("Save Password");
                JTextArea un=new JTextArea();
                JTextArea an=new JTextArea();
                JTextArea pd=new JTextArea();
                JLabel lun=new JLabel("USERNAME");
                JLabel lan=new JLabel("APPLICATON");
                JLabel lpd=new JLabel("PASSWORD");
                JLabel lbg=new JLabel(bg);
                bgp.setBounds(540, 350, 150, 50);
                bsp.setBounds(380, 350, 150, 50);
                un.setBounds(490,195,150,30);
                an.setBounds(490, 245, 150, 30);
                pd.setBounds(490, 295, 150, 30);
                lun.setBounds(410, 185, 150, 50);
                lan.setBounds(410, 235, 150, 50);
                lpd.setBounds(410, 285, 150, 50);
                bgp.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        String pswd=PasswordGenerator();
                        pd.setText(pswd);
                    }
                });
                bsp.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        String pwd = pd.getText();
                        String apn=an.getText();
                        String uname =un.getText();
                        String sql1="insert into pass values(?,?,?)";
                        try{
                            Connection con1 =DriverManager.getConnection("jdbc:mysql://127.0.0.1/notes","root", "123456789");
                            PreparedStatement stmt1=con1.prepareStatement(sql1);
                            stmt1.setString(1, apn);
                            stmt1.setString(2,uname);
                            stmt1.setString(3, pwd);
                            stmt1.executeUpdate();
                            con1.close();
                            JOptionPane.showMessageDialog(null,"password saved  succesfully");
                            pd.setText("");
                            an.setText("");
                            un.setText("");
                        }
                        catch(Exception ex){

                        }
                    }
                });             
                f9.add(lbg);
                lbg.setLayout(null);
                lbg.add(bgp);
                lbg.add(bsp);
                lbg.add(un);
                lbg.add(an);
                lbg.add(pd);
                lbg.add(lun);
                lbg.add(lan);
                lbg.add(lpd);

                f9.setIconImage(bg.getImage());
                f9.setSize(1080,720);
                f9.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f9.setVisible(true);
                f9.setResizable(false);
                f9.setLocationRelativeTo(null);
            }
            public static void window5() throws SQLException{
                int count1 =0;
                Connection con2=DriverManager.getConnection("jdbc:mysql://127.0.0.1/notes","root", "123456789");
                JFrame f10=new JFrame("Notes");
                
                JLabel ml10=new JLabel(bg);
                
                JLabel jp =new JLabel();
                jp.setLayout(new GridLayout(10,10));
                jp.setSize(1050,620);
                
                try{
                    String sql1 = "select * from pass";
                    PreparedStatement stmt=con2.prepareStatement(sql1);
                    ResultSet rs=stmt.executeQuery();
                    String[] arr2 = new String[50];
                    String[] arr3=new String[50];
                    String[] arr4=new String[50];

                    while(rs.next()){
                        arr2[count1]=rs.getString(1);
                        arr3[count1]=rs.getString(2);
                        arr4[count1]=rs.getString(3);
                        JButton bt =new JButton(arr2[count1]);
                        jp.add(bt);
                        String text1=arr2[count1];
                        String text2=arr3[count1];
                        String text3=arr4[count1];
                        bt.addActionListener(new ActionListener() {
                            
                            public void actionPerformed(ActionEvent e)
                            {
                                JOptionPane.showMessageDialog(null,"APPLICATION: "+text1+"\nUSERNAME: "+text2+"\nPASSWORD: "+text3);

                            }
                        });
                        count1++;
                        
                    }
                }
                catch(Exception ex){
                    System.out.println(ex);
                    
                }   
                ml10.add(jp);
                
                
                f10.add(ml10);
                f10.setSize(1080, 720);
                f10.setVisible(true);
                f10.setIconImage(bg.getImage());
                f10.setResizable(false);
                f10.setLocationRelativeTo(null);
                f10.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                con2.close();
            }
            public static String PasswordGenerator(){
                List<Character> password = new ArrayList<>();
                Random random = new Random();
                password.add(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));
                password.add(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));
                password.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
                password.add(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

                for (int i = 4; i < 13; i++) {
                    String chars = LOWER_CASE + UPPER_CASE + DIGITS + SPECIAL_CHARS;
                    password.add(chars.charAt(random.nextInt(chars.length())));
                }

                Collections.shuffle(password);
                StringBuilder sb = new StringBuilder();
                for (char c : password) {
                    sb.append(c);
                }
                return sb.toString();
            }
    public static void main (String args[]){
        Notesmanager n=new Notesmanager();
        n.login();
    }
   
}
