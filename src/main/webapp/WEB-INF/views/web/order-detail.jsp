<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Chi tiết đơn hàng #${order.orderId}</title>
<h3 class="mb-3">Chi tiết đơn hàng #${order.orderId}</h3>
<p><b>Trạng thái:</b> ${order.status.label}</p>
<p><b>Ngày đặt:</b> <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm"/></p>
<p><b>Người nhận:</b> ${order.receiverName} - ${order.receiverPhone}</p>
<p><b>Địa chỉ:</b> ${order.receiverAddress}</p>
<p><b>Thanh toán:</b> ${order.paymentMethod}</p>
<c:if test="${not empty order.note}"><p><b>Ghi chú:</b> ${order.note}</p></c:if>

<table class="table">
  <thead class="table-dark"><tr><th>Video</th><th>Đơn giá</th><th>SL</th><th>Thành tiền</th></tr></thead>
  <tbody>
  <c:forEach var="it" items="${order.items}">
    <tr>
      <td>${it.title}</td>
      <td><fmt:formatNumber value="${it.price}" type="number"/> đ</td>
      <td>${it.quantity}</td>
      <td><fmt:formatNumber value="${it.lineTotal}" type="number"/> đ</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<h5 class="text-end">Tổng cộng: <fmt:formatNumber value="${order.totalAmount}" type="number"/> đ</h5>
<a href="<c:url value='/orders'/>" class="btn btn-outline-secondary">&laquo; Về lịch sử đặt hàng</a>
