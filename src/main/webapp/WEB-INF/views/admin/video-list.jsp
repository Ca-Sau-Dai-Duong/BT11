<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Quản lý Video</title>
<div class="d-flex justify-content-between mb-3">
  <h3>Quản lý Video</h3>
  <a href="<c:url value='/admin/videos/add'/>" class="btn btn-success">+ Thêm video</a>
</div>
<table class="table table-bordered table-hover align-middle">
  <thead class="table-dark">
    <tr><th>Mã</th><th>Poster</th><th>Tiêu đề</th><th>Category</th><th>Views</th><th>Share</th><th>Like</th><th>Active</th><th style="width:150px">Thao tác</th></tr>
  </thead>
  <tbody>
  <c:forEach var="v" items="${videos}">
    <tr>
      <td>${v.videoId}</td>
      <td><img src='<c:url value="${v.posterUrl}"/>' width="80" alt="poster"></td>
      <td>${v.title}</td>
      <td>${v.category.categoryname}</td>
      <td>${v.views}</td>
      <td>${v.shareCount}</td>
      <td>${v.likeCount}</td>
      <td>${v.active ? 'Có' : 'Không'}</td>
      <td>
        <a class="btn btn-sm btn-warning" href="<c:url value='/admin/videos/edit'><c:param name='id' value='${v.videoId}'/></c:url>">Sửa</a>
        <a class="btn btn-sm btn-danger" onclick="return confirm('Xóa video này (kèm share/like)?')"
           href="<c:url value='/admin/videos/delete'><c:param name='id' value='${v.videoId}'/><c:param name='page' value='${page}'/></c:url>">Xóa</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<!-- Phân trang 6 video / trang -->
<nav><ul class="pagination justify-content-center">
  <li class="page-item ${page == 1 ? 'disabled' : ''}"><a class="page-link" href="?page=${page - 1}">&laquo;</a></li>
  <c:forEach begin="1" end="${totalPages}" var="i">
    <li class="page-item ${i == page ? 'active' : ''}"><a class="page-link" href="?page=${i}">${i}</a></li>
  </c:forEach>
  <li class="page-item ${page == totalPages ? 'disabled' : ''}"><a class="page-link" href="?page=${page + 1}">&raquo;</a></li>
</ul></nav>
