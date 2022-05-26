-- user
INSERT INTO tbluser(uid,fullname,username,password,position) 
VALUES(?,?,?,?,?);


-- ncc
INSERT INTO tblncc(sid,name,address,phone,email,described) 
VALUES(?,?,?,?,?,?);

SELECT * FROM tblncc WHERE name LiKE "%key%";


-- truyen
INSERT INTO tbltruyen(tid,ten,loai,giathue,gianhap,soluong,mota) VALUES(?,?,?,?,?,?,?);

SELECT * FROM tbltruyen WHERE ten LIKE "%key%";



UPDATE tbltruyen
SET soluong = soluong + ?
WHERE tid = ?;


-- INSERT INTO tbltruyen(tid,ten,loai,giathue,gianhap,soluong,mota) VALUES("1","conan","trinh tham",45000,1000000,30,'test');

-- truyen duoc cung cap

INSERT INTO tbltruyenduoccungcap(soluong,gianhap,mota,tid,sid) VALUES(?,?,?,?,?);
-- INSERT INTO tbltruyenduoccungcap(soluong,gianhap,mota,tid,sid) VALUES(10,100000,'test',"1","1");

-- danh sach truyen duoc cung cap
SELECT t.tid,t.ten,t.loai,t.giathue,t.gianhap,t.soluong,t.mota,tdcc.gianhap,tdcc.soluong,tdcc.mota
FROM tblncc AS ncc
INNER JOIN tbltruyenduoccungcap AS tdcc ON ncc.sid = tdcc.sid
INNER JOIN tbltruyen AS t ON tdcc.tid = t.tid
WHERE ncc.sid = 1 AND t.ten LIKE '%%';

UPDATE tbltruyenduoccungcap AS tdcc
SET tdcc.soluong = ?
WHERE tdcc.tid = ? AND sid = ?;

UPDATE tbltruyenduoccungcap
SET soluong = soluong + 1
WHERE tid = "1";
-- luu hoa don
INSERT INTO tblhoadon(ngay,mota,sid,uid)
VALUES(?,?,?,?);
INSERT INTO tbltruyenduocnhap(soluong,gia,mota,tid,bid) 
VALUES(?,?,?,?,?);