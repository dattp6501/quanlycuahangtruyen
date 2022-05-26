package com.dattp;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// import controller.UserDAO;
// import model.User;
import view.DangNhapFrm;


public class App {
    public static void main( String[] args ){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        DangNhapFrm f = new DangNhapFrm();
        f.setVisible(true);
        // App.themUser();
    }
    // public static void themUser(){
    //     User u = new User("5", "Phạm Thanh Tùng", "tungpt", "123456789", "Quản lý");
    //     UserDAO userDAO = new UserDAO();
    //     if(userDAO.addUser(u)){
    //         System.out.println("Thêm user thành công");
    //     }else{
    //         System.out.println("Lỗi! không thêm được user");
    //     }

    // }
}