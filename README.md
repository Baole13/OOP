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

### Yêu cầu chức năng

1. **Khách hàng**

   * Đăng nhập/đăng ký tài khoản.
   * Xem menu (tên, giá, mô tả, hình ảnh).
   * Chọn món, thêm ghi chú khẩu vị, đặt hàng.
   * Thanh toán bằng tiền mặt hoặc QR.
   * Nhận hóa đơn (giấy/PDF).
   * Đánh giá dịch vụ (tùy chọn).

2. **Nhân viên/Thu ngân**

   * Nhập order của khách và gửi đến pha chế.
   * Áp dụng khuyến mãi, giảm giá.
   * Xử lý thanh toán, in hóa đơn.
   * Theo dõi trạng thái order (**Mới → Đang làm → Hoàn thành**).
   * Cập nhật/hủy order theo yêu cầu khách.

3. **Quản lý/Admin**

   * Quản lý menu (thêm, sửa, xóa, phân loại, combo, topping).
   * Quản lý kho nguyên liệu.
   * Thống kê doanh thu, ca làm việc.
   * Quản lý nhân viên (tài khoản, phân quyền).
   
## Yêu cầu hệ thống

### 1. Khách hàng (Customer)

**Thông tin tài khoản**

* `id` : mã khách hàng (String)
* `name` : họ và tên (String)
* `username` : tên đăng nhập (String)
* `password` : mật khẩu (String, mã hóa)
* `phone` : số điện thoại (String)
* `email` : địa chỉ email (String)
* `gender` : giới tính (String)
* `dob` : ngày sinh (Date)
* `address` : địa chỉ giao hàng (String, tùy chọn)
* `created_at` : ngày tạo tài khoản (DateTime)

**Đơn hàng (Order)**

* `order_id` : mã đơn hàng (String)
* `customer_id` : mã khách hàng (String)
* `menu_items` : danh sách món (List<String>)
* `chosen_item` : món được chọn (String)
* `price` : đơn giá (Float)
* `quantity` : số lượng (Int/Long)
* `note` : ghi chú khẩu vị (String)
* `method` : phương thức thanh toán (String: cash/QR)
* `status` : trạng thái (String: Mới, Đang làm, Hoàn thành, Đã hủy)
* `created_at` : thời gian đặt hàng (DateTime)

### 2. Nhân viên / Thu ngân (Staff / Cashier)

**Thông tin nhân viên**

* `id` : mã nhân viên (String)
* `name` : họ và tên (String)
* `username` : tên đăng nhập (String)
* `password` : mật khẩu (String, mã hóa)
* `role` : vai trò (String: thu ngân, phục vụ, pha chế)
* `phone` : số điện thoại (String)
* `shift` : ca làm việc (String: sáng/chiều/tối)
* `salary` : mức lương (Float)
* `created_at` : ngày vào làm (DateTime)

**Chức năng xử lý order**

* Nhập order cho khách.
* Gửi order đến bộ phận pha chế.
* Tính tiền, áp dụng khuyến mãi/giảm giá.
* Ghi nhận phương thức thanh toán.
* Cập nhật order (hủy/sửa).
* In hóa đơn cho khách.

### 3. Quản lý / Chủ quán (Admin / Manager)

**Thông tin quản lý**

* `id` : mã quản lý (String)
* `name` : họ và tên (String)
* `username` : tên đăng nhập (String)
* `password` : mật khẩu (String, mã hóa)
* `phone` : số điện thoại (String)
* `email` : email (String)
* `role` : vai trò (Admin/Manager)

**Chức năng quản lý**

* **Quản lý Menu**

  * `menu_id` : mã sản phẩm (String)
  * `name` : tên món (String)
  * `description` : mô tả (String)
  * `price` : giá bán (Float)
  * `category` : loại sản phẩm (ví dụ: cafe, trà, topping)
  * `status` : trạng thái (còn bán/ngừng bán)
  * `image_url` : hình ảnh minh họa (String)





