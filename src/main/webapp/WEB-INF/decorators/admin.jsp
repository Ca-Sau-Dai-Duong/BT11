<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><decorator:title default="Admin"/></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <decorator:head/>
</head>
<body class="d-flex flex-column min-vh-100">
  <!-- HEADER (vai trò Admin) -->
  <nav class="navbar navbar-expand-lg navbar-dark" style="background:#8b1e2d">
    <div class="container">
      <a class="navbar-brand" href="<c:url value='/admin/home'/>">ADMIN</a>
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="<c:url value='/home'/>">Trang chủ</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value='/products'/>">Sản phẩm</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value='/admin/home'/>">Trang quản trị</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value='/admin/videos'/>">Quản lý Video</a></li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item"><span class="navbar-text me-3">Admin: ${sessionScope.user.fullname}</span></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value='/logout'/>">Đăng xuất</a></li>
      </ul>
    </div>
  </nav>

  <main class="container my-4 flex-grow-1">
    <decorator:body/>
  </main>

  <footer class="text-white text-center py-3 mt-auto" style="background:#8b1e2d">
    Họ tên: Huỳnh Anh Huy &nbsp;|&nbsp; MSSV: 22145150 &nbsp;|&nbsp; Mã đề: 03
  </footer>
</body>
</html>
