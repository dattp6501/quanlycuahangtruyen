package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.User;

public class QuanLyFrm extends JFrame implements Action{
    private User u;
    private JButton btnNhapTruyen;
    private JPanel pnInforUser;
    private JLabel lblFullName;
    private JLabel lblPosition;
    public QuanLyFrm(User u) {
        this.u = u;
        initUI();
    }
    private void initUI(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(520,300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        btnNhapTruyen = new JButton("Nhập truyện");
        btnNhapTruyen.setFocusPainted(false);
        this.add(btnNhapTruyen);
        btnNhapTruyen.setBounds(10,70,110, 40);
        btnNhapTruyen.addActionListener(this);

        pnInforUser = new JPanel(new GridLayout(2,1));
        pnInforUser.setBorder(BorderFactory.createLineBorder(new Color(107,142,35), 1));
        pnInforUser.setBounds(300, 0, 200, 60);
        lblFullName = new JLabel(u.getFullname(), JLabel.CENTER);
        pnInforUser.add(lblFullName);
        lblFullName.setBounds(300, 0, 100, 30);
        lblPosition = new JLabel(u.getPosition(),JLabel.CENTER);
        pnInforUser.add(lblPosition);
        lblPosition.setBounds(300, 30, 100, 40);
        this.add(pnInforUser);


        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });
    }
    //
    private void closeWindow() {
        DangNhapFrm dangNhapFrm = new DangNhapFrm();
        dangNhapFrm.setVisible(true);
        this.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        if(btnClicked.equals(btnNhapTruyen)){
            btnNhapTruyenActionPerformed();
        }
    }
    private void btnNhapTruyenActionPerformed(){
        TimNCCFrm timNCCFrm = new TimNCCFrm(u);
        timNCCFrm.setVisible(true);
        this.dispose();
    }
    @Override
    public Object getValue(String key) {
        
        return null;
    }
    @Override
    public void putValue(String key, Object value) {
        
    }
}
