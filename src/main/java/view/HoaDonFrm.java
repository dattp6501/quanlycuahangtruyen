package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.HoaDonDAO;
import controller.NCCDAO;
// import controller.TruyenDAO;
import controller.UserDAO;
import model.HoaDon;
import model.NCC;
import model.TruyenDuocNhap;
import model.User;

public class HoaDonFrm extends JFrame implements Action{
    private HoaDon hd;
    private JTextField txtNCC;
    private JTable tblTruyen;
    private JTextField txtTongTien;
    private JButton btnXacNhan;
    public HoaDonFrm(HoaDon hd){
        super("Hóa đơn");
        this.hd = hd;
        initUI();
    }
    private void initUI(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(1100, 650);
        this.setLocationRelativeTo(null);

        int sizetext = 15;
        JLabel lbltitle = new JLabel("HÓA ĐƠN NHẬP TRUYỆN", JLabel.CENTER);
        lbltitle.setBounds(0,20,this.getWidth(), 40);
        Font font = lbltitle.getFont();
        lbltitle.setFont(new Font(font.getName(),font.getStyle(),sizetext+10));
        this.add(lbltitle);

        JLabel lblNCC = new JLabel("Nhà cung cấp:");
        lblNCC.setFont(new Font(font.getName(),font.getStyle(),sizetext));
        lblNCC.setBounds(30, 100, 100, 40);
        this.add(lblNCC);

        txtNCC = new JTextField();
        setTxtNCC(hd.getNcc().getName());
        txtNCC.setFont(new Font(font.getName(),font.getStyle(),sizetext));
        txtNCC.setBounds(160, 100, 500, 30);
        txtNCC.setEditable(false);
        this.add(txtNCC);

        JLabel lbltitletbl = new JLabel("DANH SÁCH TRUYỆN",JLabel.CENTER);
        lbltitletbl.setFont(new Font(font.getName(),font.getStyle(),sizetext+5));
        lbltitletbl.setBounds(0, 150, this.getWidth(), 40);
        this.add(lbltitletbl);

        JScrollPane jsptbl = new JScrollPane();
        jsptbl.setBounds(30, 210, this.getWidth()-30*2, 300);
        tblTruyen = new JTable();
        String[] titiletbl = {"STT","Mã","Tên","Giá nhập","Số lượng","Thành tiền","Mô tả"};
        tblTruyen.setModel(new DefaultTableModel(null,titiletbl){
            boolean[] canEdit = new boolean[] {
                false, false, false, false, false, true, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblTruyen.setRowHeight(30);
        setDataTableTruyen(hd.getDs());
        jsptbl.setViewportView(tblTruyen);
        this.add(jsptbl);

        JLabel lblTongTien = new JLabel("Tổng tiền");
        lblTongTien.setFont(new Font(font.getName(),font.getStyle(),sizetext));
        lblTongTien.setBounds(30, 520, 100, 30);
        this.add(lblTongTien);

        txtTongTien = new JTextField();
        setTxtTongTien(hd.getTongtien());
        txtTongTien.setEditable(false);
        txtTongTien.setBounds(150, 520, 200, 30);
        this.add(txtTongTien);

        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.addActionListener(this);
        btnXacNhan.setFocusPainted(false);
        btnXacNhan.setBounds(900, 540, 100, 40);
        this.add(btnXacNhan);




        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });
    }
    //nha cung cap
    private void setTxtNCC(String ncc){
        txtNCC.setText(ncc);
    }
    //tong tien
    private void setTxtTongTien(float tongtien){
        txtTongTien.setText(String.format("%.2f",tongtien));
    }
    //bang truyen
    private void setDataTableTruyen(ArrayList<TruyenDuocNhap> ds){
        if(ds!=null){
            hd.setDs(ds);
        }
        DefaultTableModel model = (DefaultTableModel) tblTruyen.getModel();
        model.setRowCount(0);
        int stt = 1;
        for(TruyenDuocNhap t : hd.getDs()){
            model.addRow(new Object[]{
                stt++,t.getTruyen().getId(),t.getTruyen().getTen(),t.getGianhap(),t.getSoluong(),String.format("%.0f",t.getThanhtien()),t.getMota()
            });
        }

        for(int i=0; i<20; i++){
            model.addRow(new Object[]{
                null,null,null,null,null,null,null
            });
        }
    }
    // su kien nhan nut x
    private void closeWindow(){
        this.dispose();
        TimTruyenFrm timTruyenFrm = new TimTruyenFrm(hd);
        timTruyenFrm.setVisible(true);
    }
    // su kien nhan nut
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        if(btnClicked.equals(btnXacNhan)){
            btnXacNhanActionPerformed();
        }
    }
    private void btnXacNhanActionPerformed(){
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        if(!hoaDonDAO.saveHoaDon(hd)){
            JOptionPane.showMessageDialog(this, "Không lưu được hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Lưu thành công", "Thông báo", JOptionPane.DEFAULT_OPTION);
        // TruyenDAO truyenDAO = new TruyenDAO();
        // if(truyenDAO.updateSoLuongTruyen(hd.getDs(),hd.getNcc())){
        //     System.out.println("cap nhat so luong truyen thanh cong");
        // }
        this.dispose();
        QuanLyFrm quanLyFrm = new QuanLyFrm(hd.getUser());
        quanLyFrm.setVisible(true);
    }
    @Override
    public Object getValue(String key) {
        return null;
    }
    @Override
    public void putValue(String key, Object value) {
        
    }
    public static void main(String[] args) {
        HoaDon hd = new HoaDon();
        NCCDAO nccdao = new NCCDAO();
        NCC ncc = nccdao.searchNCC("sendo").get(0);
        hd.setNcc(ncc);

        UserDAO userDAO = new UserDAO();
        User u = new User();
        u.setUsername("dattp");
        u.setPassword("123456789");
        userDAO.checkLogin(u);

        hd.setUser(u);

        hd.setDs(new ArrayList<TruyenDuocNhap>());
        HoaDonFrm f = new HoaDonFrm(hd);
        f.setVisible(true);
    }
}
