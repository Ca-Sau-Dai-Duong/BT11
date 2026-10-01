<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Đăng ký</title>
<div class="row justify-content-center">
  <div class="col-md-6">
    <h3 class="mb-3">Đăng ký tài khoản</h3>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
    <form method="post" action="<c:url value='/register'/>">
      <div class="mb-3"><label class="form-label">Username *</label>
        <input name="username" class="form-control" value="${param.username}" required></div>
      <div class="mb-3"><label class="form-label">Password *</label>
        <input type="password" name="password" class="form-control" required></div>
      <div class="mb-3"><label class="form-label">Họ tên</label>
        <input name="fullname" class="form-control" value="${param.fullname}"></div>
      <div class="mb-3"><label class="form-label">Email * (nhận mã OTP)</label>
        <input type="email" name="email" class="form-control" value="${param.email}" required></div>
      <div class="mb-3"><label class="form-label">Số điện thoại</label>
        <input name="phone" class="form-control" value="${param.phone}"></div>
      <button class="btn btn-primary">Đăng ký &amp; nhận OTP</button>
      <a href="<c:url value='/login'/>" class="btn btn-link">Đã có tài khoản?</a>
    </form>
  </div>
</div>
