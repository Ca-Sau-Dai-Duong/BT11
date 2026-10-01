<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Sản phẩm</title>
<h4 class="mb-3">Tất cả video</h4>
<div class="row">
  <c:forEach var="v" items="${videos}">
    <div class="col-md-4 mb-3">
      <div class="card h-100">
        <a href="<c:url value='/video'><c:param name='id' value='${v.videoId}'/></c:url>">
          <img src='<c:url value="${v.posterUrl}"/>' class="card-img-top" style="height:200px;object-fit:cover" alt="poster">
        </a>
        <div class="card-body">
          <h6 class="card-title">Tiêu đề: ${v.title}</h6>
          <div>Mã video: ${v.videoId}</div>
          <div>Category name: ${v.category.categoryname}</div>
          <div>View: ${v.views}</div>
          <div>Share(${v.shareCount})</div>
          <div>Like(${v.likeCount})</div>
          <div class="fw-bold text-danger mt-1"><fmt:formatNumber value="${v.price}" type="number"/> đ</div>
          <form method="post" action="<c:url value='/cart/add'/>" class="d-flex gap-1 mt-2">
            <input type="hidden" name="videoId" value="${v.videoId}">
            <input type="hidden" name="back" value="<c:url value='/products'/>">
            <input type="number" name="quantity" value="1" min="1" max="99" class="form-control form-control-sm" style="width:70px">
            <button class="btn btn-sm btn-primary flex-grow-1">Thêm vào giỏ</button>
          </form>
        </div>
      </div>
    </div>
  </c:forEach>
</div>
<c:if test="${totalPages > 1}">
  <nav><ul class="pagination justify-content-center">
    <li class="page-item ${page == 1 ? 'disabled' : ''}"><a class="page-link" href="?page=${page - 1}">&laquo;</a></li>
    <c:forEach begin="1" end="${totalPages}" var="i">
      <li class="page-item ${i == page ? 'active' : ''}"><a class="page-link" href="?page=${i}">${i}</a></li>
    </c:forEach>
    <li class="page-item ${page == totalPages ? 'disabled' : ''}"><a class="page-link" href="?page=${page + 1}">&raquo;</a></li>
  </ul></nav>
</c:if>
