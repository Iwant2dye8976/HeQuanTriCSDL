----BTL_HQT_CSDL_CUOI_KI
CREATE DATABASE QLyNhaSach;
GO

USE QLyNhaSach;
GO

-- Bảng Books (Thông tin Sách)
CREATE TABLE Books (
    BookID INT IDENTITY PRIMARY KEY,
    Title NVARCHAR(255) NOT NULL,
    Author NVARCHAR(255) NOT NULL,
    Category NVARCHAR(100) NOT NULL,
    Publisher NVARCHAR(255) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    StockQuantity INT NOT NULL
);

-- Bảng Customers (Khách hàng)
CREATE TABLE Customers (
    CustomerID INT IDENTITY PRIMARY KEY,
    FullName NVARCHAR(255) NOT NULL,
    PhoneNumber NVARCHAR(15) NOT NULL,
    Email NVARCHAR(255) NULL,
    Address NVARCHAR(500) NOT NULL
);

-- Bảng Employees (Nhân viên)
CREATE TABLE Employees (
    EmployeeID INT IDENTITY PRIMARY KEY,
    FullName NVARCHAR(255) NOT NULL,
    Position NVARCHAR(100) NOT NULL,
    PhoneNumber NVARCHAR(15) NOT NULL,
    Email NVARCHAR(255) NULL
);

-- Bảng Orders (Hóa đơn bán hàng)
CREATE TABLE Orders (
    OrderID INT IDENTITY PRIMARY KEY,
    CustomerID INT NOT NULL,
    EmployeeID INT NOT NULL,
    OrderDate DATETIME NOT NULL DEFAULT GETDATE(),
    TotalAmount DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

-- Bảng OrderDetails (Chi tiết hóa đơn)
CREATE TABLE OrderDetails (
    OrderDetailID INT IDENTITY PRIMARY KEY,
    OrderID INT NOT NULL,
    BookID INT NOT NULL,
    Quantity INT NOT NULL,
    UnitPrice DECIMAL(10, 2) NOT NULL,
    Subtotal AS (Quantity * UnitPrice),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

-- Bảng Suppliers (Nhà cung cấp)
CREATE TABLE Suppliers (
    SupplierID INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(255) NOT NULL,
    PhoneNumber NVARCHAR(15) NOT NULL,
    Email NVARCHAR(255) NULL,
    Address NVARCHAR(500) NOT NULL
);

-- Bảng StockReceipts (Phiếu nhập kho)
CREATE TABLE StockReceipts (
    ReceiptID INT IDENTITY PRIMARY KEY,
    SupplierID INT NOT NULL,
    EmployeeID INT NOT NULL,
    ReceiptDate DATETIME NOT NULL DEFAULT GETDATE(),
    TotalAmount DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

-- Bảng StockReceiptDetails (Chi tiết phiếu nhập kho)
CREATE TABLE StockReceiptDetails (
    ReceiptDetailID INT IDENTITY PRIMARY KEY,
    ReceiptID INT NOT NULL,
    BookID INT NOT NULL,
    Quantity INT NOT NULL,
    UnitCost DECIMAL(10, 2) NOT NULL,
    Subtotal AS (Quantity * UnitCost),
    FOREIGN KEY (ReceiptID) REFERENCES StockReceipts(ReceiptID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

INSERT INTO Books (Title, Author, Category, Publisher, Price, StockQuantity)
VALUES
('Kẻ Bắt Lúa Mạch', 'J.D. Salinger', 'Tiểu thuyết', 'Nhà xuất bản Trẻ', 250000, 50),
('Giết Con Chim Nhại', 'Harper Lee', 'Tiểu thuyết', 'Nhà xuất bản Văn học', 300000, 30),
('1984', 'George Orwell', 'Dystopian', 'Nhà xuất bản Thế giới', 220000, 40),
('Kiêu Hãnh và Định Kiến', 'Jane Austen', 'Lãng mạn', 'Nhà xuất bản Hội Nhà văn', 180000, 60),
('Đại Gia Gatsby', 'F. Scott Fitzgerald', 'Tiểu thuyết', 'Nhà xuất bản Nhã Nam', 250000, 25),
('Moby-Dick', 'Herman Melville', 'Phiêu lưu', 'Nhà xuất bản Thế Giới', 350000, 15),
('Chiến Tranh và Hòa Bình', 'Leo Tolstoy', 'Lịch sử', 'Nhà xuất bản Văn hóa', 500000, 10),
('Tội Ác và Hình Phạt', 'Fyodor Dostoevsky', 'Tiểu thuyết triết học', 'Nhà xuất bản Trẻ', 300000, 50),
('Chúa Tể Những Chiếc Nhẫn', 'J.R.R. Tolkien', 'Fantasy', 'Nhà xuất bản Kim Đồng', 250000, 35),
('Hobbit', 'J.R.R. Tolkien', 'Fantasy', 'Nhà xuất bản Kim Đồng', 220000, 20);


INSERT INTO Customers (FullName, PhoneNumber, Email, Address)
VALUES
('Nguyễn Thị Lan', '0912345678', 'lan.nguyen@example.com', '123 Đường 1, Hà Nội'),
('Trần Văn Nam', '0987654321', 'nam.tran@example.com', '456 Đường 2, Hồ Chí Minh'),
('Lê Hoàng Duy', '0901122334', 'duy.le@example.com', '789 Đường 3, Đà Nẵng'),
('Hoàng Minh Tuấn', '0911223344', 'tuan.hoang@example.com', '101 Đường 4, Hà Nội'),
('Phạm Minh Khuê', '0988776655', 'khue.pham@example.com', '202 Đường 5, Hồ Chí Minh'),
('Mai Thi Lan', '0909887766', 'lan.mai@example.com', '303 Đường 6, Hải Phòng'),
('Vũ Minh Quân', '0912233445', 'quan.vu@example.com', '404 Đường 7, Cần Thơ'),
('Đoàn Thị Lan Anh', '0988773344', 'anh.doan@example.com', '505 Đường 8, Huế'),
('Nguyễn Thị Ngọc', '0901124556', 'ngoc.nguyen@example.com', '606 Đường 9, Hà Nội'),
('Lương Minh Duy', '0988772233', 'duy.luong@example.com', '707 Đường 10, Hồ Chí Minh');


INSERT INTO Employees (FullName, Position, PhoneNumber, Email)
VALUES
('Lê Văn An', 'Quản lý', '0901234567', 'an.le@company.com'),
('Trần Thị Lan', 'Nhân viên bán hàng', '0902345678', 'lan.tran@company.com'),
('Nguyễn Minh Tuấn', 'Nhân viên bán hàng', '0903456789', 'tuan.nguyen@company.com'),
('Phan Thị Dung', 'Thu ngân', '0904567890', 'dung.phan@company.com'),
('Đặng Minh Khoa', 'Nhân viên kho', '0905678901', 'khoa.dang@company.com'),
('Hồ Quốc Duy', 'Quản lý', '0906789012', 'duy.ho@company.com'),
('Trần Kim Lan', 'Nhân viên bán hàng', '0907890123', 'lan.tran@company.com'),
('Bùi Minh Tuấn', 'Thu ngân', '0908901234', 'tuan.bui@company.com'),
('Nguyễn Thị Như', 'Nhân viên kho', '0909012345', 'nhu.nguyen@company.com'),
('Phan Minh Hoàng', 'Quản lý', '0901123456', 'hoang.phan@company.com');


INSERT INTO Orders (CustomerID, EmployeeID, OrderDate, TotalAmount)
VALUES
(1, 2, '2024-12-01', 1200000),
(2, 3, '2024-12-02', 1350000),
(3, 4, '2024-12-03', 800000),
(4, 5, '2024-12-04', 950000),
(5, 6, '2024-12-05', 700000),
(6, 7, '2024-12-06', 1500000),
(7, 8, '2024-12-07', 1700000),
(8, 9, '2024-12-08', 650000),
(9, 10, '2024-12-09', 1450000),
(10, 1, '2024-12-10', 1200000);

INSERT INTO OrderDetails (OrderID, BookID, Quantity, UnitPrice)
VALUES
(1, 1, 2, 250000),
(1, 2, 3, 300000),
(2, 3, 2, 220000),
(2, 4, 1, 180000),
(3, 5, 1, 250000),
(4, 6, 1, 350000),
(5, 7, 2, 500000),
(6, 8, 3, 300000),
(7, 9, 2, 250000),
(8, 10, 2, 250000);

INSERT INTO Suppliers (Name, PhoneNumber, Email, Address)
VALUES
('Nhà cung cấp Sách A', '0901234000', 'ncc1@example.com', '10 Đường Sách, Hà Nội'),
('Nhà cung cấp Sách B', '0902345000', 'ncc2@example.com', '20 Đường Sách, Hồ Chí Minh'),
('Nhà cung cấp Sách C', '0903456000', 'ncc3@example.com', '30 Đường Sách, Đà Nẵng'),
('Nhà cung cấp Sách D', '0904567000', 'ncc4@example.com', '40 Đường Sách, Hải Phòng'),
('Nhà cung cấp Sách E', '0905678000', 'ncc5@example.com', '50 Đường Sách, Cần Thơ'),
('Nhà cung cấp Sách F', '0906789000', 'ncc6@example.com', '60 Đường Sách, Huế'),
('Nhà cung cấp Sách G', '0907891000', 'ncc7@example.com', '70 Đường Sách, Hà Nội'),
('Nhà cung cấp Sách H', '0908902000', 'ncc8@example.com', '80 Đường Sách, Hồ Chí Minh'),
('Nhà cung cấp Sách I', '0909013000', 'ncc9@example.com', '90 Đường Sách, Đà Nẵng'),
('Nhà cung cấp Sách J', '0901124000', 'ncc10@example.com', '100 Đường Sách, Hải Phòng');


INSERT INTO StockReceipts (SupplierID, EmployeeID, ReceiptDate, TotalAmount)
VALUES
(1, 1, '2024-12-01', 1000000),
(2, 2, '2024-12-02', 1500000),
(3, 3, '2024-12-03', 900000),
(4, 4, '2024-12-04', 1200000),
(5, 5, '2024-12-05', 700000),
(6, 6, '2024-12-06', 2000000),
(7, 7, '2024-12-07', 2100000),
(8, 8, '2024-12-08', 1500000),
(9, 9, '2024-12-09', 2200000),
(10, 10, '2024-12-10', 2500000);


INSERT INTO StockReceiptDetails (ReceiptID, BookID, Quantity, UnitCost)
VALUES
(1, 1, 100, 100000),
(2, 2, 200, 150000),
(3, 3, 150, 120000),
(4, 4, 120, 130000),
(5, 5, 80, 140000),
(6, 6, 170, 160000),
(7, 7, 180, 180000),
(8, 8, 150, 200000),
(9, 9, 220, 190000),
(10, 10, 250, 250000);


