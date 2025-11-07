
# OOP
Dự án bài tập lớn cho Môn Lập trình hướng đối tượng và Cơ sở dữ liệu
## Description: https://docs.google.com/document/d/1cNDeu2sTH9uSQ6RksYpEkOlDopm2nsHsBul-sA5ODKM/edit?usp=sharing


# Hệ thống Quản lý Quán Cafe/Trà Sữa

## Giới thiệu
Hệ thống Quản lý Quán Cafe/Trà Sữa được xây dựng nhằm:
* Tự động hóa quy trình **đặt món – thanh toán – quản lý**.
* Giúp **khách hàng** dễ dàng gọi món, theo dõi hóa đơn và phản hồi dịch vụ.
* Hỗ trợ **nhân viên/thu ngân** nhập order, xử lý thanh toán, theo dõi tiến độ order.
* Cho phép **quản lý/chủ quán** quản lý menu, nhân viên, kho nguyên liệu, phân quyền và thống kê doanh thu.

## Cấu trúc dự án

```
E:\Code\Java\BTN\OOP\
├── bin\                           
│   ├── ManagementView.class      # Giao diện quản lý
│   ├── CustomerView.class         # Giao diện khách hàng
│   ├── Customer.class             # Class khách hàng
│   ├── Order.class                # Class đơn hàng
│   ├── MenuItem.class             # Class món ăn
│   ├── Coffee.class               # Class cà phê (kế thừa MenuItem)
│   └── ... (các class khác)
├── Admin Manager\                 # Source code quản lý
│   ├── ManagementView.java        # Giao diện chính quản lý
│   ├── OrderManager.java          # Quản lý đơn hàng
│   ├── TableManager.java          # Quản lý bàn
│   ├── MenuManagement.java        # Quản lý thực đơn
│   ├── CustomerManagement.java    # Quản lý khách hàng
│   └── Table.java                 # Class bàn
├── *.java                         # 📄 Source code files
└── README.md                      # 📄 Tài liệu dự án
```
## Cách chạy chương trình
### 1. Chạy chương trình quản lý (Admin)
```bash
java -cp bin ManagementView
```
- **Mật khẩu admin:** `admin123`
### 2. Chạy chương trình khách hàng
```bash
java -cp bin CustomerView
```

## Yêu cầu chức năng
### Giao diện Quản lý (ManagementView)
**Đăng nhập:**
- Mật khẩu admin: `admin123`

**Menu quản lý:**
1. **Quản lý đơn hàng** - Xem, cập nhật trạng thái đơn hàng
2. **Quản lý bàn** - Quản lý trạng thái bàn (trống/có khách/đặt trước)
3. **Quản lý thực đơn** - Thêm, sửa, xóa món ăn
4. **Quản lý khách hàng** - Xem thông tin, thống kê khách hàng
5. **Trạng thái hệ thống** - Kiểm tra hoạt động hệ thống

### 🛒 Giao diện Khách hàng (CustomerView)
**Menu khách hàng:**
1. **Xem thực đơn** - Danh sách món ăn, giá cả
2. **Đặt hàng** - Chọn món, số lượng, loại dịch vụ (dine-in/takeaway)
3. **Xem giỏ hàng** - Kiểm tra đơn hàng hiện tại
4. **Thanh toán** - Xác nhận và thanh toán đơn hàng
5. **Đăng ký khách hàng** - Tạo tài khoản mới
6. **Đăng nhập** - Đăng nhập bằng email và số điện thoại
7. **Xem lịch sử đơn hàng** - Xem các đơn hàng đã đặt

##  Các Class chính

### Core Classes

#### 1. **Customer** - Khách hàng
- Thông tin cá nhân (tên, email, số điện thoại)
- Lịch sử đơn hàng
- Điểm thưởng (loyalty points)
- Quản lý đơn hàng

#### 2. **Order** - Đơn hàng
- Thông tin đơn hàng (ID, khách hàng, thời gian)
- Danh sách món ăn (OrderItem)
- Trạng thái đơn hàng (PENDING, CONFIRMED, PREPARING, READY, COMPLETED, CANCELLED)
- Loại dịch vụ (DINE_IN, TAKEAWAY)
- Tính toán tổng tiền (bao gồm thuế VAT 10%)

#### 3. **MenuItem** - Món ăn (Abstract Class)
- Thông tin cơ bản (ID, tên, giá, mô tả, loại)
- Tính giá (có thể override)
- Trạng thái khả dụng

#### 4. **Coffee** - Cà phê (Kế thừa MenuItem)
- Kích thước (Small, Medium, Large, Extra Large)
- Mức rang (Light, Medium, Dark)
- Tùy chọn (sữa, đường)
- Tính giá động dựa trên tùy chọn

#### 5. **Table** - Bàn
- Số bàn, sức chứa
- Trạng thái (AVAILABLE, OCCUPIED, RESERVED, OUT_OF_SERVICE)
- Quản lý đặt bàn

### Management Classes

#### 1. **ManagementView** - Giao diện chính quản lý
- Menu điều hướng chính
- Xác thực admin
- Tích hợp các module quản lý

#### 2. **OrderManager** - Quản lý đơn hàng
- Xem tất cả đơn hàng
- Xem đơn hàng đang xử lý
- Cập nhật trạng thái đơn hàng
- Tự động giải phóng bàn khi hoàn thành

#### 3. **TableManager** - Quản lý bàn
- Xem trạng thái bàn
- Thêm/xóa bàn
- Cập nhật trạng thái bàn
- Thống kê sử dụng bàn

#### 4. **MenuManagement** - Quản lý thực đơn
- Thêm/sửa/xóa món ăn
- Tìm kiếm món ăn
- Quản lý loại cà phê và món ăn

#### 5. **CustomerManagement** - Quản lý khách hàng
- Xem danh sách khách hàng
- Tìm kiếm khách hàng
- Cập nhật thông tin
- Thống kê khách hàng


#### **CustomerView** - Giao diện khách hàng
- Menu tương tác thân thiện
- Quản lý giỏ hàng
- Hệ thống đăng ký/đăng nhập
- Hỗ trợ cả dine-in và takeaway
- Quản lý bàn cho khách dine-in

