<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Đăng nhập</title>
<div class="row justify-content-center">
  <div class="col-md-5">
    <h3 class="mb-3">Đăng nhập</h3>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
    <c:if test="${param.registered == 1}"><div class="alert alert-success">Kích hoạt tài khoản thành công, mời đăng nhập!</div></c:if>
    <form method="post" action="<c:url value='/login'/>">
      <div class="mb-3"><label class="form-label">Username</label>
        <input name="username" class="form-control" value="${username}" required></div>
      <div class="mb-3"><label class="form-label">Password</label>
        <input type="password" name="password" class="form-control" required></div>
      <button class="btn btn-primary">Đăng nhập</button>
      <a href="<c:url value='/register'/>" class="btn btn-link">Chưa có tài khoản? Đăng ký</a>
    </form>
  </div>
</div>
