<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Giỏ hàng</title>
<h3 class="mb-3">Giỏ hàng của bạn</h3>
<c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
<c:if test="${param.added == '1'}"><div class="alert alert-success">Đã thêm vào giỏ hàng!</div></c:if>
<c:if test="${not empty param.error}"><div class="alert alert-danger">${param.error}</div></c:if>

<c:choose>
<c:when test="${empty cartItems}">
  <p class="text-muted">Giỏ hàng đang trống.</p>
  <a href="<c:url value='/products'/>" class="btn btn-outline-primary">Tiếp tục mua sắm</a>
</c:when>
<c:otherwise>
  <table class="table align-middle">
    <thead class="table-dark">
      <tr><th>Video</th><th>Đơn giá</th><th style="width:160px">Số lượng</th><th>Thành tiền</th><th></th></tr>
    </thead>
    <tbody>
    <c:set var="grandTotal" value="${0}"/>
    <c:forEach var="ci" items="${cartItems}">
      <c:set var="grandTotal" value="${grandTotal + ci.lineTotal}"/>
      <tr>
        <td class="d-flex align-items-center gap-2">
          <img src='<c:url value="${ci.video.posterUrl}"/>' width="60" style="object-fit:cover" alt="poster">
          <span>${ci.video.title} <small class="text-muted">(${ci.video.videoId})</small></span>
        </td>
        <td><fmt:formatNumber value="${ci.video.price}" type="number"/> đ</td>
        <td>
          <form method="post" action="<c:url value='/cart/update'/>" class="d-flex gap-1">
            <input type="hidden" name="cartItemId" value="${ci.cartItemId}">
            <input type="number" name="quantity" value="${ci.quantity}" min="1" max="99" class="form-control form-control-sm" style="width:80px">
            <button class="btn btn-sm btn-outline-secondary">Cập nhật</button>
          </form>
        </td>
        <td><fmt:formatNumber value="${ci.lineTotal}" type="number"/> đ</td>
        <td>
          <a class="btn btn-sm btn-outline-danger" onclick="return confirm('Xóa khỏi giỏ hàng?')"
             href="<c:url value='/cart/remove'><c:param name='id' value='${ci.cartItemId}'/></c:url>">Xóa</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div class="d-flex justify-content-between align-items-center">
    <h5>Tổng cộng: <fmt:formatNumber value="${grandTotal}" type="number"/> đ</h5>
    <a href="<c:url value='/checkout'/>" class="btn btn-success">Tiến hành đặt hàng</a>
  </div>
</c:otherwise>
</c:choose>
