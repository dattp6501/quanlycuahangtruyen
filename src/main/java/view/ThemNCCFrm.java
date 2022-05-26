package view;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.NCCDAO;

import java.awt.event.*;
import java.util.Date;
import java.awt.Font;
import java.awt.GridLayout;

import model.HoaDon;
import model.NCC;
import model.User;

public class ThemNCCFrm extends JFrame implements Action{
    private User u;
    private JTextField txtMa;
    private JTextField txtTen;
    private JTextField txtDiaChi;
    private JTextField txtEmail;
    private JTextField txtSDT;
    private JTextField txtMoTa;
    private JButton btnThem;
    public ThemNCCFrm(User u){
        super("Thêm nhà cung cấp");
        this.u = u;
        initUI();
    }
    private void initUI(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);


        JPanel pnlbl = new JPanel(new GridLayout(6,1,0,20));
        pnlbl.setBounds(20, 20, 70, 350);
        JLabel lblMa = new JLabel("Mã",JLabel.LEFT);
        Font font = lblMa.getFont();
        int sizeText = 14;
        lblMa.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblMa);
        JLabel lblTen = new JLabel("Tên",JLabel.LEFT);
        lblTen.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblTen);
        JLabel lblDiaChi = new JLabel("Địa chỉ",JLabel.LEFT);
        lblDiaChi.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblDiaChi);
        JLabel lblEmail = new JLabel("Email",JLabel.LEFT);
        lblEmail.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblEmail);
        JLabel lblSDT = new JLabel("SĐT",JLabel.LEFT);
        lblSDT.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblSDT);
        JLabel lblMoTa = new JLabel("Mô tả",JLabel.LEFT);
        lblMoTa.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblMoTa);
        this.add(pnlbl);

        JPanel pntxt = new JPanel(new GridLayout(6,1,0,20));
        pntxt.setBounds(140, 20, 330, 350);
        txtMa = new JTextField();
        txtMa.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtMa);
        txtTen = new JTextField();
        txtTen.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtTen);
        txtDiaChi = new JTextField();
        txtDiaChi.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtDiaChi);
        txtEmail = new JTextField();
        txtEmail.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtEmail);
        txtSDT = new JTextField();
        txtSDT.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtSDT);
        txtMoTa = new JTextField();
        txtMoTa.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtMoTa);
        this.add(pntxt);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(300, 410, 100, 30);
        btnThem.addActionListener(this);
        this.add(btnThem);






        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });
    }
    //
    private void closeWindow(){
        TimNCCFrm timNCCFrm = new TimNCCFrm(u);
        timNCCFrm.setVisible(true);
        this.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        if(btnClicked.equals(btnThem)){
            btnThemActionPerformed();
        }
    }
    private void btnThemActionPerformed(){
        String id = txtMa.getText();
        String name = txtTen.getText();
        String address = txtDiaChi.getText();
        String phone = txtSDT.getText();
        String email = txtEmail.getText();
        String described = txtMoTa.getText();
        if(name.equals("") || address.equals("") || phone.equals("") || email.equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "THông báo", JOptionPane.DEFAULT_OPTION);
            return;
        }
        NCC ncc = new NCC(id, name, address, phone, email, described);
        NCCDAO nccdao = new NCCDAO();
        if(!nccdao.addNCC(ncc)){
            System.out.println("khong them duoc");
            return;
        }
        System.out.println("them nha cung cap thanh cong");
        System.out.println(ncc);
        HoaDon hd = new HoaDon(1, "", u, ncc, new Date(), null, 0);
        TimTruyenFrm timTruyenFrm = new TimTruyenFrm(hd);
        timTruyenFrm.setVisible(true);
        this.dispose();
        reset();        
    }
    private void reset(){
        txtMa.setText("");
        txtTen.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtMoTa.setText("");
    }
    @Override
    public Object getValue(String key) {
        return null;
    }
    @Override
    public void putValue(String key, Object value) {
    }
    public static void main(String[] args) {
        ThemNCCFrm f = new ThemNCCFrm(new User("1","Trương Phúc Đạt","dattp","123456789","Quản lý"));
        f.setVisible(true);
    }
}
