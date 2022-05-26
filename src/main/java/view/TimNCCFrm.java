package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

import controller.NCCDAO;
import model.HoaDon;
import model.NCC;
import model.TruyenDuocNhap;
import model.User;

public class TimNCCFrm extends JFrame implements Action,MouseInputListener{
    private User u;
    private JLabel lblFullName;
    private JLabel lblPosition;
    private JButton btnThemNCC;
    private JTextField txtTimKiem;
    private JButton btnTimKiem;
    private JTable tblNCC;
    public TimNCCFrm(User u){
        super("Tìm nhà cung cấp");
        this.u = u;
        initUI();
    }
    private void initUI(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(1000, 650);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        boolean focusPainted = false;

        btnThemNCC = new JButton("Thêm NCC");
        btnThemNCC.setBounds(80,30, 150, 50);
        this.add(btnThemNCC);
        btnThemNCC.setFocusPainted(focusPainted);
        btnThemNCC.addActionListener(this);

        JPanel pnInforUser = new JPanel(new GridLayout(2,1));
        pnInforUser.setBorder(BorderFactory.createLineBorder(new Color(107,142,35), 1));
        pnInforUser.setBounds(780, 0, 200, 60);
        lblFullName = new JLabel(u.getFullname(), JLabel.CENTER);
        pnInforUser.add(lblFullName);
        lblFullName.setBounds(300, 0, 100, 30);
        lblPosition = new JLabel(u.getPosition(),JLabel.CENTER);
        pnInforUser.add(lblPosition);
        lblPosition.setBounds(300, 30, 100, 40);
        this.add(pnInforUser);

        txtTimKiem = new JTextField();
        txtTimKiem.setBounds(80,120,700,30);
        this.add(txtTimKiem);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBounds(800, 120, 100, 30);
        btnTimKiem.setFocusPainted(focusPainted);
        this.add(btnTimKiem);
        btnTimKiem.addActionListener(this);

        String[] titleTable = {"STT","Mã","Tên","Địa chỉ","Email","SĐT","Mô tả"};
        tblNCC = new JTable();
        tblNCC.setModel(new DefaultTableModel(null,titleTable){
            boolean[] canEdit = new boolean[] {
                false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblNCC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblNCC.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblNCC.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblNCC.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblNCC.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblNCC.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblNCC.getColumnModel().getColumn(5).setPreferredWidth(200);
        tblNCC.getColumnModel().getColumn(6).setPreferredWidth(260);
        tblNCC.setRowHeight(30);
        tblNCC.setFillsViewportHeight(false);
        tblNCC.addMouseListener(this);
        NCCDAO nccdao = new NCCDAO();
        setDataTableNCC(nccdao.searchNCC(""));
        JScrollPane scpTbNCC = new JScrollPane();
        scpTbNCC.setViewportView(tblNCC);
        scpTbNCC.setBounds(20, 170, 940, 400);
        this.add(scpTbNCC);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });
    }
    private void setDataTableNCC(ArrayList<NCC> ds){
        DefaultTableModel model = (DefaultTableModel) tblNCC.getModel();
        model.setRowCount(0);
        int stt = 1;
        for(NCC ncc: ds){
            model.addRow(new Object[]{
                stt++,ncc.getId(),ncc.getName(),ncc.getAddress(),ncc.getEmail(),ncc.getPhone(),ncc.getDescribed()
            });
        }
        for(int i=0; i<20; i++){
            model.addRow(new Object[]{
                null,null,null,null,null,null,null,null
            });
        }
    }
    //
    private void closeWindow(){
        QuanLyFrm quanLyFrm = new QuanLyFrm(u);
        quanLyFrm.setVisible(true);
        this.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnbtnClicked = (JButton)e.getSource();
        if(btnbtnClicked.equals(btnThemNCC)){
            btnThemNCCActionPerformed();
        }else if(btnbtnClicked.equals(btnTimKiem)){
            btnTimKiemActionPerformed();
        }
    }
    private void btnThemNCCActionPerformed(){
        ThemNCCFrm themNCCFrm = new ThemNCCFrm(u);
        themNCCFrm.setVisible(true);
        this.dispose();
    }
    private void btnTimKiemActionPerformed(){
        String key = txtTimKiem.getText();
        System.out.println(key);
        txtTimKiem.setText("");
        NCCDAO nccdao = new NCCDAO();
        setDataTableNCC(nccdao.searchNCC(key));
    }
    @Override
    public Object getValue(String key) {
        return null;
    }
    @Override
    public void putValue(String key, Object value) {
    }
    // chuot
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() != 2){
            return;
        }
        if(e.getSource().equals(tblNCC)){
            tblNCCMouseClicked();
        }
    }
    private void tblNCCMouseClicked(){
        int row = tblNCC.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblNCC.getModel();
        String id =  (String) model.getValueAt(row,1);
        String name = (String) model.getValueAt(row, 2);
        String address = (String) model.getValueAt(row, 3);
        String phone = (String) model.getValueAt(row, 4);
        String email = (String) model.getValueAt(row, 5);
        String described = (String) model.getValueAt(row, 6);
        if(id.equals("") || name.equals("") || address.equals("")){
            return;
        }
        NCC ncc = new NCC(id, name, address, phone, email, described);
        HoaDon hd = new HoaDon(0, "", u, ncc, new Date(), new ArrayList<TruyenDuocNhap>(), 0);
        TimTruyenFrm timTruyenFrm = new TimTruyenFrm(hd);
        timTruyenFrm.setVisible(true);
        this.dispose();
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}