<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Thanh toán</title>
<h3 class="mb-3">Thanh toán đơn hàng</h3>
<c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

<div class="row">
  <div class="col-md-7">
    <table class="table">
      <thead class="table-dark"><tr><th>Video</th><th>SL</th><th>Thành tiền</th></tr></thead>
      <tbody>
      <c:set var="grandTotal" value="${0}"/>
      <c:forEach var="ci" items="${cartItems}">
        <c:set var="grandTotal" value="${grandTotal + ci.lineTotal}"/>
        <tr>
          <td>${ci.video.title}</td>
          <td>${ci.quantity}</td>
          <td><fmt:formatNumber value="${ci.lineTotal}" type="number"/> đ</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <h5 class="text-end">Tổng thanh toán: <fmt:formatNumber value="${grandTotal}" type="number"/> đ</h5>
  </div>
  <div class="col-md-5">
    <form method="post" action="<c:url value='/checkout/place'/>">
      <div class="mb-3"><label class="form-label">Họ tên người nhận</label>
        <input name="receiverName" class="form-control" value="${user.fullname}" required></div>
      <div class="mb-3"><label class="form-label">Số điện thoại</label>
        <input name="receiverPhone" class="form-control" value="${user.phone}" required></div>
      <div class="mb-3"><label class="form-label">Địa chỉ nhận hàng</label>
        <textarea name="receiverAddress" rows="3" class="form-control" required></textarea></div>
      <div class="mb-3"><label class="form-label">Ghi chú</label>
        <textarea name="note" rows="2" class="form-control"></textarea></div>
      <div class="mb-3">
        <label class="form-label">Phương thức thanh toán</label>
        <input class="form-control" value="Thanh toán khi nhận hàng (COD)" readonly>
      </div>
      <button class="btn btn-success w-100">Đặt hàng</button>
    </form>
  </div>
</div>
