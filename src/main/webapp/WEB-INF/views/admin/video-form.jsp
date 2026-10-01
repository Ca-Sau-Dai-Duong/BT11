<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>${isNew ? 'Thêm video' : 'Cập nhật video'}</title>
<h3>${isNew ? 'Thêm video' : 'Cập nhật video'}</h3>
<c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
<form method="post" action="<c:url value='/admin/videos/save'/>" class="row g-3">
  <input type="hidden" name="isNew" value="${isNew}">
  <div class="col-md-4"><label class="form-label">Mã video</label>
    <input name="videoId" class="form-control" value="${video.videoId}" ${isNew ? '' : 'readonly'} required></div>
  <div class="col-md-8"><label class="form-label">Tiêu đề</label>
    <input name="title" class="form-control" value="${video.title}" required></div>
  <div class="col-md-8"><label class="form-label">Poster (URL hoặc tên file trong assets/images, tối đa 50 ký tự)</label>
    <input name="poster" class="form-control" maxlength="50" value="${video.poster}"></div>
  <div class="col-md-4"><label class="form-label">Views</label>
    <input type="number" name="views" class="form-control" value="${empty video.views ? 0 : video.views}"></div>
  <div class="col-md-6"><label class="form-label">Category</label>
    <select name="categoryId" class="form-select">
      <c:forEach var="c" items="${categories}">
        <option value="${c.categoryId}" ${video.category.categoryId == c.categoryId ? 'selected' : ''}>${c.categoryname}</option>
      </c:forEach>
    </select></div>
  <div class="col-md-6 d-flex align-items-end">
    <div class="form-check"><input class="form-check-input" type="checkbox" name="active" id="active"
      ${isNew or video.active ? 'checked' : ''}><label class="form-check-label" for="active">Active</label></div></div>
  <div class="col-12"><label class="form-label">Mô tả</label>
    <textarea name="description" rows="4" class="form-control" maxlength="500">${video.description}</textarea></div>
  <div class="col-12">
    <button class="btn btn-primary">Lưu</button>
    <a href="<c:url value='/admin/videos'/>" class="btn btn-secondary">Hủy</a>
  </div>
</form>
