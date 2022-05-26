package view;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.TruyenDAO;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import model.HoaDon;
import model.Truyen;
import model.TruyenDuocCungCap;
import model.TruyenDuocNhap;

public class ThemTruyenFrm extends JFrame implements Action{
    private HoaDon hd;
    private JLabel lblMa;
    private JLabel lblLoai;
    private JLabel lblTen;
    private JLabel lblGiaNhap;
    private JLabel lblGiaThue;
    private JLabel lblSoLuong;
    private JLabel lblMoTa;
    private JTextField txtMa;
    private JTextField txtLoai;
    private JTextField txtTen;
    private JTextField txtGiaNhap;
    private JTextField txtGiaThue;
    private JTextField txtSoLuong;
    private JTextField txtMoTa;
    private JButton btnThem;
    public ThemTruyenFrm(HoaDon hd){
        super("Thêm truyện");
        this.hd = hd;
        initUI();
    }
    private void initUI(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(500,550);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        JPanel pnlbl = new JPanel(new GridLayout(7,1,0,20));
        pnlbl.setBounds(20, 20, 70, 400);
        lblMa = new JLabel("Mã",JLabel.LEFT);
        Font font = lblMa.getFont();
        int sizeText = 14;
        lblMa.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblMa);
        lblLoai = new JLabel("Loại",JLabel.LEFT);
        lblLoai.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblLoai);
        lblTen = new JLabel("Tên",JLabel.LEFT);
        lblTen.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblTen);
        lblGiaNhap = new JLabel("Giá nhập",JLabel.LEFT);
        lblGiaNhap.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblGiaNhap);
        lblGiaThue = new JLabel("Giá thuê",JLabel.LEFT);
        lblGiaThue.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblGiaThue);
        lblSoLuong = new JLabel("Số lượng",JLabel.LEFT);
        lblSoLuong.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblSoLuong);
        lblMoTa = new JLabel("Mô tả",JLabel.LEFT);
        lblMoTa.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pnlbl.add(lblMoTa);
        this.add(pnlbl);

        JPanel pntxt = new JPanel(new GridLayout(7,1,0,20));
        pntxt.setBounds(140, 20, 330, 400);
        txtMa = new JTextField();
        txtMa.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {txtMaKeyEvent();}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {txtMaKeyEvent();}
        });
        txtMa.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtMa);
        txtLoai = new JTextField();
        txtLoai.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtLoai);
        txtTen = new JTextField();
        txtTen.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtTen);
        txtGiaNhap = new JTextField();
        txtGiaNhap.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtGiaNhap);
        txtGiaThue = new JTextField();
        txtGiaThue.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtGiaThue);
        txtSoLuong = new JTextField("0");
        txtSoLuong.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        txtSoLuong.setEditable(false);
        pntxt.add(txtSoLuong);
        txtMoTa = new JTextField();
        txtMoTa.setFont(new Font(font.getName(),font.getStyle(),sizeText));
        pntxt.add(txtMoTa);
        this.add(pntxt);

        btnThem = new JButton("Thêm");
        btnThem.setFocusPainted(false);
        btnThem.setBounds(300, 460, 100, 30);
        btnThem.addActionListener(this);
        this.add(btnThem);

        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        if(btnClicked.equals(btnThem)){
            btnThemActionPerformed();
        }
    }
    private void btnThemActionPerformed(){
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        String loai = txtLoai.getText();
        float giathue = 0;
        float gianhap = 0;
        int sl = 0;
        TruyenDAO truyenDAO = new TruyenDAO();
        if(truyenDAO.checkTruyenDuocCungCap(ma, hd.getNcc().getId())){
            JOptionPane.showMessageDialog(this, "Truyện đã tồn tại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            try {
                truyenDAO.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            giathue = Float.parseFloat(txtGiaThue.getText());
            gianhap = Float.parseFloat(txtGiaNhap.getText());
            sl = Integer.parseInt(txtSoLuong.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Vui lòng nhập đúng thông tin ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String mota = txtMoTa.getText();
        if(ma.equals("") || ten.equals("") || loai.equals("")){
            JOptionPane.showMessageDialog(this,"Vui lòng nhập đầy đủ thông tin","Thông báo",JOptionPane.ERROR_MESSAGE);
            return;
        }
        Truyen truyen = new Truyen(ma,ten,loai,giathue,gianhap,sl,mota);
        TruyenDuocCungCap truyenDuocCungCap = new TruyenDuocCungCap(hd.getNcc(),truyen,sl,gianhap,mota);
        truyenDAO = new TruyenDAO();
        if(truyenDAO.addTruyen(truyenDuocCungCap)){
            System.out.println("them truyen thanh cong");
            TruyenDuocNhap truyenDuocNhap = new TruyenDuocNhap(truyen, mota, gianhap, 0, 0);
            hd.getDs().add(0,truyenDuocNhap);
            closeWindow();
        }else{
            System.out.println("khong them duoc truyen");
        }
    }
    private void closeWindow(){
        TimTruyenFrm timTruyenFrm = new TimTruyenFrm(this.hd);
        timTruyenFrm.setVisible(true);
        this.dispose();
    }
    @Override
    public Object getValue(String key) {
        return null;
    }
    @Override
    public void putValue(String key, Object value) {
    }
    // key event
    private void txtMaKeyEvent(){
        String key = txtMa.getText();
        TruyenDAO truyenDAO = new TruyenDAO();
        Truyen truyen = truyenDAO.getTruyen(key);
        if(truyen == null){
            txtLoai.setText("");
            txtTen.setText("");
            return;
        }
        txtLoai.setText(truyen.getLoai());
        txtTen.setText(truyen.getTen());
    }
    public static void main(String[] args) {
        ThemTruyenFrm f = new ThemTruyenFrm(null);
        f.setVisible(true);
    }
    
}
