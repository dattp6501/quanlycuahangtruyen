package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
// import javax.swing.Icon;
// import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.TruyenDAO;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.HoaDon;
import model.Truyen;
import model.TruyenDuocCungCap;
import model.TruyenDuocNhap;

public class TimTruyenFrm extends JFrame implements Action, MouseInputListener{
    private HoaDon hd;
    private ArrayList<TruyenDuocCungCap> dstruyencc = new ArrayList<>();
    private JPanel pnInforUser;
    private JLabel lblFullName;
    private JLabel lblPosition;
    private JButton btnThemTruyen;
    private JTable tblTruyenDuocNhap;
    private JTextField txtTimKiem;
    private JButton btnTimKiem;
    private JTable tblTruyen;
    private JButton btnSubmit;
    public TimTruyenFrm(HoaDon hd){
        super("Tìm truyện");
        this.hd = hd;
        initUI();
    }
    private void initUI(){
        this.setSize(1300,700);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        pnInforUser = new JPanel(new GridLayout(2,1));
        pnInforUser.setBorder(BorderFactory.createLineBorder(new Color(107,142,35), 1));
        pnInforUser.setBounds(1075, 0, 200, 60);
        lblFullName = new JLabel(hd.getUser().getFullname(), JLabel.CENTER);
        pnInforUser.add(lblFullName);
        lblFullName.setBounds(300, 0, 100, 30);
        lblPosition = new JLabel(hd.getUser().getPosition(),JLabel.CENTER);
        pnInforUser.add(lblPosition);
        lblPosition.setBounds(300, 30, 100, 40);
        this.add(pnInforUser);

        btnThemTruyen = new JButton("Thêm truyện");
        btnThemTruyen.setFocusPainted(false);
        btnThemTruyen.setBounds(50, 20, 150, 40);
        btnThemTruyen.addActionListener(this);
        this.add(btnThemTruyen);

        JLabel lbltitletbNhap = new JLabel("TRUYỆN ĐƯỢC NHẬP",JLabel.CENTER);
        Font font = lbltitletbNhap.getFont();
        int sizetitle = 20;
        lbltitletbNhap.setFont(new Font(font.getName(),font.getStyle(),sizetitle));
        lbltitletbNhap.setBounds(0,70,1300,40);
        this.add(lbltitletbNhap);

        JScrollPane scpTbTruyenDuocNhap  = new JScrollPane();
        scpTbTruyenDuocNhap.setBounds(10, 120, 1260, 200);
        tblTruyenDuocNhap = new JTable();
        tblTruyenDuocNhap.addMouseListener(this);
        tblTruyenDuocNhap.setRowHeight(30);
        String[] titletbTruyenDuocNhap = {"STT","Mã","Tên","Giá nhập","Số lượng","Thành tiền","Mô tả",""};
        tblTruyenDuocNhap.setModel(new DefaultTableModel(null,titletbTruyenDuocNhap){
            boolean[] canEdit = new boolean[] {
                false, false, false, false, true, true, true, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblTruyenDuocNhap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblTruyenDuocNhap.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblTruyenDuocNhap.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblTruyenDuocNhap.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblTruyenDuocNhap.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblTruyenDuocNhap.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblTruyenDuocNhap.getColumnModel().getColumn(5).setPreferredWidth(200);
        tblTruyenDuocNhap.getColumnModel().getColumn(6).setPreferredWidth(340);
        tblTruyenDuocNhap.getColumnModel().getColumn(7).setPreferredWidth(30);
        tblTruyenDuocNhap.getColumnModel().getColumn(7).setCellRenderer(new RendererImage());
        scpTbTruyenDuocNhap.setViewportView(tblTruyenDuocNhap);
        setDataTbTruyenDuocNhap(hd.getDs());
        this.add(scpTbTruyenDuocNhap);

        txtTimKiem = new JTextField();
        txtTimKiem.setBounds(80, 340, 1000, 30);
        this.add(txtTimKiem);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.setBounds(1110, 340, 100, 30);
        btnTimKiem.addActionListener(this);
        this.add(btnTimKiem);

        JScrollPane scpTbTruyen  = new JScrollPane();
        scpTbTruyen.setBounds(10, 390, 1260, 200);
        tblTruyen= new JTable();
        tblTruyen.setRowHeight(30);
        String[] titletbTruyen = {"STT","Mã","Tên","Loại","Giá nhập","Số lượng","Mô tả"};
        tblTruyen.setModel(new DefaultTableModel(null,titletbTruyen){
            boolean[] canEdit = new boolean[] {
                false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblTruyen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblTruyen.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblTruyen.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblTruyen.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblTruyen.getColumnModel().getColumn(3).setPreferredWidth(180);
        tblTruyen.getColumnModel().getColumn(4).setPreferredWidth(130);
        tblTruyen.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblTruyen.getColumnModel().getColumn(6).setPreferredWidth(380);
        tblTruyen.addMouseListener(this);
        TruyenDAO truyenDAO = new TruyenDAO();
        setDataTbTruyen(truyenDAO.seachTruyen(hd.getNcc(),""));
        scpTbTruyen.setViewportView(tblTruyen);
        setDataTbTruyenDuocNhap(hd.getDs());
        this.add(scpTbTruyen);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(1100, 620, 100, 30);
        btnSubmit.addActionListener(this);
        this.add(btnSubmit);


        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

    }
    //
    private void closeWindow(){
        TimNCCFrm timNCCFrm = new TimNCCFrm(hd.getUser());
        timNCCFrm.setVisible(true);
        this.dispose();
    }
    // bang truyen duoc nhap
    private void setDataTbTruyenDuocNhap(ArrayList<TruyenDuocNhap> ds){
        if(ds != null){
            hd.setDs(ds);;
        }
        DefaultTableModel model = (DefaultTableModel) tblTruyenDuocNhap.getModel();
        model.setRowCount(0);
        int stt = 1;
        for(TruyenDuocNhap t : hd.getDs()){
            model.addRow(new Object[]{
                stt++,t.getTruyen().getId(),t.getTruyen().getTen(),t.getGianhap(),t.getSoluong(),String.format("%.0f", t.getThanhtien()),t.getMota()
            });
        }
        // for(int i=0; i<20; i++){
        //     model.addRow(new Object[]{
        //         null,null,null,null,null,null,null
        //     });
        // }
    }
    // bang truyen cua nha cung cap
    private void setDataTbTruyen(ArrayList<TruyenDuocCungCap> ds){
        if(ds != null){
            dstruyencc = ds;
        }
        DefaultTableModel model = (DefaultTableModel) tblTruyen.getModel();
        model.setRowCount(0);
        int stt = 1;
            for(TruyenDuocCungCap t : dstruyencc){
                model.addRow(new Object[]{
                    stt++,t.getTruyen().getId(),t.getTruyen().getTen(),t.getTruyen().getLoai(),t.getGianhap(),t.getSoluong(),t.getMota()
                });
            }
        for(int i=0; i<20; i++){
            model.addRow(new Object[]{
                null,null,null,null,null,null,null
            });
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        if(btnClicked.equals(btnThemTruyen)){
            btnThemTruyenActionPerformed();
        }else if(btnClicked.equals(btnSubmit)){
            btnSubmitActionPerformed();
        }else if(btnClicked.equals(btnTimKiem)){
            btnTimKiemActionPerformed();
        }
    }
    private void btnThemTruyenActionPerformed(){
        // luu lai dach sach truyen duoc nhap hien thoi
        DefaultTableModel model = (DefaultTableModel) tblTruyenDuocNhap.getModel();
        for(int i=0; i<hd.getDs().size(); i++){
            int soluong = 0;
            float thanhtien = 0;
            String mota = model.getValueAt(i, 6).toString();
            try {
                soluong = Integer.parseInt(model.getValueAt(i,4).toString());
                thanhtien = Float.parseFloat(model.getValueAt(i, 5).toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng, thành tiền chỉ gồm các chữ số", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            hd.getDs().get(i).setSoluong(soluong);
            hd.getDs().get(i).setThanhtien(thanhtien);
            hd.getDs().get(i).setMota(mota);
        }


        ThemTruyenFrm themTruyenFrm = new ThemTruyenFrm(this.hd);
        themTruyenFrm.setVisible(true);
        this.dispose();
    }
    private void btnSubmitActionPerformed(){
        if(hd.getDs().size() <= 0){
            JOptionPane.showMessageDialog(this,"Chưa có truyện nào được nhập cả", "Thông báo",JOptionPane.DEFAULT_OPTION);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblTruyenDuocNhap.getModel();
        float tongtien = 0;
        for(int i=0; i<hd.getDs().size(); i++){
            int soluong = 0;
            double thanhtien = 0;
            String mota = model.getValueAt(i, 6).toString();
            try {
                soluong = Integer.parseInt(model.getValueAt(i,4).toString());
                if(soluong == 0){
                    tblTruyenDuocNhap.changeSelection(i,4, false, false);
                    String mess = "Mã "+hd.getDs().get(i).getTruyen().getId()+" chưa có số lượng cần nhập";
                    JOptionPane.showMessageDialog(this,mess, "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                thanhtien = Double.parseDouble(model.getValueAt(i, 5).toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng, thành tiền chỉ gồm các chữ số", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            hd.getDs().get(i).setSoluong(soluong);
            hd.getDs().get(i).setThanhtien(thanhtien);
            hd.getDs().get(i).setMota(mota);
            tongtien += thanhtien;
        }
        hd.setTongtien(tongtien);
        HoaDonFrm hoaDonFrm = new HoaDonFrm(hd);
        hoaDonFrm.setVisible(true);
        this.dispose();
    }
    private void btnTimKiemActionPerformed(){
        String key = txtTimKiem.getText();
        System.out.println(key);
        txtTimKiem.setText("");
        TruyenDAO truyenDAO = new TruyenDAO();
        setDataTbTruyen(truyenDAO.seachTruyen(hd.getNcc(), key));
    }
    @Override
    public Object getValue(String key) {
        return null;
    }
    @Override
    public void putValue(String key, Object value) {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        JTable tableClicked = (JTable) e.getSource(); 
        if(tableClicked.equals(tblTruyen)){
            if(e.getClickCount() != 2){
                return;
            }
            tblTruyenMouseEventDoubleClicked();
        }else if(tableClicked.equals(tblTruyenDuocNhap)){
            if(e.getClickCount() != 1){
                return;
            }
            if(tblTruyenDuocNhap.getSelectedColumn() != 7){
                return;
            }
            tblTruyenDuocNhapMouseEventDoubleClicked();
        }
        
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
    private void tblTruyenDuocNhapMouseEventDoubleClicked(){
        int row = tblTruyenDuocNhap.getSelectedRow();
        if(row >= hd.getDs().size()){
            return;
        }
        hd.getDs().remove(row);
        setDataTbTruyenDuocNhap(hd.getDs());
    }
    private void tblTruyenMouseEventDoubleClicked(){
        // luu lai danh sach cac truyen duoc chon hien thoi
        DefaultTableModel model = (DefaultTableModel) tblTruyenDuocNhap.getModel();
        for(int i=0; i<hd.getDs().size(); i++){
            int soluong = 0;
            float thanhtien = 0;
            String mota = (String) model.getValueAt(i, 6);
            try {
                soluong = Integer.parseInt(model.getValueAt(i,4).toString());
                thanhtien = Float.parseFloat(model.getValueAt(i, 5).toString());
            } catch (Exception e) {
                System.out.println(soluong+", "+thanhtien);
                JOptionPane.showMessageDialog(this, "Số lượng, thành tiền chỉ gồm các chữ số", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            hd.getDs().get(i).setSoluong(soluong);
            hd.getDs().get(i).setThanhtien(thanhtien);
            hd.getDs().get(i).setMota(mota);
        }
        // them truyen vua duoc chon vao danh sach nhap
        int row = tblTruyen.getSelectedRow();
        if(dstruyencc.size() <= row){
            return;
        }
        Truyen truyen = dstruyencc.get(row).getTruyen();
        String mota = dstruyencc.get(row).getMota();
        float gianhap = dstruyencc.get(row).getGianhap();
        int soluong = 0;
        TruyenDuocNhap truyenDuocNhap = new TruyenDuocNhap(truyen, mota, gianhap, soluong, 0);
        if(hd.getDs().contains(truyenDuocNhap)){
            return;
        }
        hd.getDs().add(0, truyenDuocNhap);
        setDataTbTruyenDuocNhap(hd.getDs());
    }








    public class RendererImage extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel lbl = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("images/thungrac.png")));
            return lbl;
        }
    }
}
