package view;
import javax.swing.*;

import controller.UserDAO;
import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
public class DangNhapFrm extends JFrame implements Action{
    private static String font = "Time New Romem";
    private static int dodam = 15;
    private static int size = 18;
    private JLabel lblTitle;
    private JLabel lblUser;
    private JLabel lblPassword;
    private JTextField txtUser;
    private JPasswordField txtPassWord;
    private JButton btnLogin;
    public DangNhapFrm(){
        super("Đăng nhập");
        initUI();
    }
    private final void initUI(){
        this.setSize(370, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        // tieu de
        lblTitle = new JLabel("Đăng nhập",JLabel.CENTER);
        lblTitle.setFont(new Font(font,dodam,size+10));
        lblTitle.setBounds(0, 0, 360, 90);
        this.add(lblTitle);
        //phan username        
        lblUser = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("images/username.png")));
        // lblUser = new JLabel("username");
        this.add(lblUser);
        lblUser.setBounds(20,90,100,50);
        txtUser = new JTextField("dattp");
        txtUser.setBounds(120,100,185,30);
        txtUser.setFont(new Font(font,dodam,size));
        this.add(txtUser);
        // phan password
        lblPassword = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("images/password.png")));
        this.add(lblPassword);
        lblPassword.setBounds(20,155,100,50);
        txtPassWord = new JPasswordField("123456789");
        txtPassWord.setFont(new Font(font,dodam,size));
        this.add(txtPassWord);
        txtPassWord.setBounds(120,170,185,30);
        // nut login
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font(font,dodam,size));
        btnLogin.setFocusPainted(false);
        this.add(btnLogin);
        btnLogin.setBounds(110,260,150,50);
        btnLogin.setActionCommand("Đăng nhập");
        btnLogin.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Đăng nhập")){
            btnLoginActionPerformed();
        }
    }
    private void btnLoginActionPerformed(){
        UserDAO userDAO = new UserDAO();

        User u = new User("","",txtUser.getText(), new String(txtPassWord.getPassword()),"");
        if(!userDAO.checkLogin(u)){
            System.out.println("sai thong tin tai khoan");
            JOptionPane.showMessageDialog(this,"Sai thông tin tài khoản","Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(u.getPosition().toLowerCase().equals("quản lý")){
            QuanLyFrm quanLyFrm = new QuanLyFrm(u);
            this.dispose();
            quanLyFrm.setVisible(true);
        }else{
            System.out.println("không phải là quản lý");
        }
    }
    @Override
    public Object getValue(String key) {
        return null;
    }
    @Override
    public void putValue(String key, Object value) {
    }
}
