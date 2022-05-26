CREATE TABLE tbluser(
    uid VARCHAR(255) PRIMARY KEY,
    fullname VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    position VARCHAR(255)
);
-- supplier
CREATE TABLE tblncc(
    sid VARCHAR(255),
    name VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(10),
    email VARCHAR(255),
    described TEXT,
    CONSTRAINT khoa PRIMARY KEY(sid,phone,email)
);
-- truyen
CREATE TABLE tbltruyen(
    tid VARCHAR(255) PRIMARY KEY,
    ten VARCHAR(255),
    loai VARCHAR(255),
    giathue FLOAT,
    gianhap FLOAT,
    soluong INTEGER,
    mota TEXT
);


-- truyen duoc cung cap
CREATE TABLE tbltruyenduoccungcap(
    tid VARCHAR(255),
    sid VARCHAR(255),
    soluong INTEGER,
    gianhap FLOAT,
    mota TEXT,
    FOREIGN KEY (tid) REFERENCES tbltruyen(tid),
    FOREIGN KEY (sid) REFERENCES tblncc(sid)
);
-- hoa don
CREATE TABLE tblhoadon(
    bid INTEGER PRIMARY KEY AUTOINCREMENT,
    sid VARCHAR(255),
    uid VARCHAR(255),
    ngay DATE,
    mota TEXT,
    FOREIGN KEY (sid) REFERENCES tblncc(sid),
    FOREIGN KEY (uid) REFERENCES tbluser(uid)
);
-- truyen duoc nhap
CREATE TABLE tbltruyenduocnhap(
    tid VARCHAR(255),
    bid VARCHAR(255),
    soluong INTEGER,
    gia FLOAT,
    mota TEXT,
    FOREIGN KEY (tid) REFERENCES tbltruyen(tid),
    FOREIGN KEY (bid) REFERENCES tblhoadon(bid)
);
