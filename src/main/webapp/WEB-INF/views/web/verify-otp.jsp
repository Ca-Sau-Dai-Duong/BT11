<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Xác thực OTP</title>
<div class="row justify-content-center">
  <div class="col-md-5">
    <h3 class="mb-3">Nhập mã OTP</h3>
    <p class="text-muted">Mã OTP gồm 6 số đã được gửi tới email <b>${sessionScope.pendingUser.email}</b> (hiệu lực 5 phút).</p>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
    <form method="post" action="<c:url value='/verify-otp'/>">
      <div class="mb-3"><input name="otp" class="form-control" maxlength="6" placeholder="______" required></div>
      <button class="btn btn-success">Kích hoạt tài khoản</button>
      <a href="<c:url value='/register'/>" class="btn btn-link">Đăng ký lại</a>
    </form>
  </div>
</div>
