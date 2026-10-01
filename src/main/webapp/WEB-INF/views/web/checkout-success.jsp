<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Đặt hàng thành công</title>
<div class="text-center py-5">
  <h3 class="text-success">Đặt hàng thành công!</h3>
  <p>Mã đơn hàng của bạn: <b>#${orderId}</b></p>
  <p>Hình thức thanh toán: Thanh toán khi nhận hàng (COD)</p>
  <a href="<c:url value='/orders'/>" class="btn btn-primary">Xem lịch sử đặt hàng</a>
  <a href="<c:url value='/home'/>" class="btn btn-outline-secondary">Tiếp tục mua sắm</a>
</div>
