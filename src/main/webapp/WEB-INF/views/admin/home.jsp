<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Trang chủ Admin</title>
<h3>Chào mừng ${sessionScope.user.fullname} đến trang quản trị</h3>
<div class="row mt-4">
  <div class="col-md-4"><div class="card text-bg-primary"><div class="card-body">
    <h5>Tổng số video</h5><h2>${totalVideos}</h2></div></div></div>
  <div class="col-md-4"><div class="card text-bg-success"><div class="card-body">
    <h5>Tổng số category</h5><h2>${totalCategories}</h2></div></div></div>
</div>
<a href="<c:url value='/admin/videos'/>" class="btn btn-danger mt-4">Quản lý Video</a>
