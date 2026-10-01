<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Trang chủ</title>
<c:forEach var="b" items="${blocks}">
  <!-- Câu 5: số video của category hiển thị trong ngoặc -->
  <h4 class="mt-4 border-bottom pb-2">${b.category.categoryname} (${b.total})</h4>
  <div class="row">
    <c:forEach var="v" items="${b.videos}">
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
              <input type="hidden" name="back" value="<c:url value='/home'/>">
              <input type="number" name="quantity" value="1" min="1" max="99" class="form-control form-control-sm" style="width:70px">
              <button class="btn btn-sm btn-primary flex-grow-1">Thêm vào giỏ</button>
            </form>
          </div>
        </div>
      </div>
    </c:forEach>
    <c:if test="${empty b.videos}"><p class="text-muted">Chưa có video.</p></c:if>
  </div>

  <c:if test="${b.totalPages > 1}">
    <nav>
      <ul class="pagination justify-content-center">
        <li class="page-item ${b.page == 1 ? 'disabled' : ''}">
          <a class="page-link" href="?${b.baseQuery}p${b.category.categoryId}=${b.page - 1}">&laquo;</a></li>
        <c:forEach begin="1" end="${b.totalPages}" var="i">
          <li class="page-item ${i == b.page ? 'active' : ''}">
            <a class="page-link" href="?${b.baseQuery}p${b.category.categoryId}=${i}">${i}</a></li>
        </c:forEach>
        <li class="page-item ${b.page == b.totalPages ? 'disabled' : ''}">
          <a class="page-link" href="?${b.baseQuery}p${b.category.categoryId}=${b.page + 1}">&raquo;</a></li>
      </ul>
    </nav>
  </c:if>
</c:forEach>
