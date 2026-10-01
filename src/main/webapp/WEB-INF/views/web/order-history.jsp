<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Lịch sử đặt hàng</title>
<h3 class="mb-3">Lịch sử đặt hàng</h3>

<!-- Lọc theo trạng thái -->
<form method="get" class="mb-3 d-flex gap-2 align-items-center">
  <label class="form-label mb-0">Trạng thái:</label>
  <select name="status" class="form-select" style="width:260px" onchange="this.form.submit()">
    <option value="">-- Tất cả --</option>
    <c:forEach var="s" items="${statuses}">
      <option value="${s}" ${selectedStatus == s ? 'selected' : ''}>${s.label}</option>
    </c:forEach>
  </select>
</form>

<c:choose>
<c:when test="${empty orders}">
  <p class="text-muted">Không có đơn hàng nào.</p>
</c:when>
<c:otherwise>
  <table class="table table-hover align-middle">
    <thead class="table-dark">
      <tr><th>Mã đơn</th><th>Ngày đặt</th><th>Tổng tiền</th><th>Trạng thái</th><th></th></tr>
    </thead>
    <tbody>
    <c:forEach var="o" items="${orders}">
      <tr>
        <td>#${o.orderId}</td>
        <td><fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy HH:mm"/></td>
        <td><fmt:formatNumber value="${o.totalAmount}" type="number"/> đ</td>
        <td>
          <c:choose>
            <c:when test="${o.status == 'HUY'}"><span class="badge bg-danger">${o.status.label}</span></c:when>
            <c:when test="${o.status == 'DA_GIAO'}"><span class="badge bg-success">${o.status.label}</span></c:when>
            <c:when test="${o.status == 'HOAN'}"><span class="badge bg-secondary">${o.status.label}</span></c:when>
            <c:otherwise><span class="badge bg-info text-dark">${o.status.label}</span></c:otherwise>
          </c:choose>
        </td>
        <td><a class="btn btn-sm btn-outline-primary" href="<c:url value='/orders/detail'><c:param name='id' value='${o.orderId}'/></c:url>">Chi tiết</a></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:otherwise>
</c:choose>
