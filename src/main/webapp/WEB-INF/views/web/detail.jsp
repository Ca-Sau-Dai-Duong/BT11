<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>${video.title}</title>
<div class="row">
  <div class="col-md-5">
    <img src='<c:url value="${video.posterUrl}"/>' class="img-fluid rounded" alt="poster">
  </div>
  <div class="col-md-7">
    <h4>Tiêu đề: ${video.title}</h4>
    <p class="mb-1"><b>Mã video:</b> ${video.videoId}</p>
    <p class="mb-1"><b>Category name:</b> ${video.category.categoryname}</p>
    <p class="mb-1"><b>View:</b> ${video.views}</p>
    <p class="mb-1"><b>Share(${video.shareCount})</b></p>
    <p class="mb-3"><b>Like(${video.likeCount})</b></p>
    <p>${video.description}</p>
    <p class="fs-4 fw-bold text-danger">Giá: <fmt:formatNumber value="${video.price}" type="number"/> đ</p>
    <form method="post" action="<c:url value='/cart/add'/>" class="d-flex gap-2 mb-3" style="max-width:300px">
      <input type="hidden" name="videoId" value="${video.videoId}">
      <input type="hidden" name="back" value="<c:url value='/video'><c:param name='id' value='${video.videoId}'/></c:url>">
      <input type="number" name="quantity" value="1" min="1" max="99" class="form-control">
      <button class="btn btn-primary">Thêm vào giỏ</button>
    </form>
    <a href="<c:url value='/home'/>" class="btn btn-outline-secondary">&laquo; Về trang chủ</a>
  </div>
</div>
