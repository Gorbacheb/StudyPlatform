<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${not empty topic}">Edit Topic</c:when>
            <c:otherwise>Create New Topic</c:otherwise>
        </c:choose>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title mb-0">
                        <c:choose>
                            <c:when test="${not empty topic}">Edit Topic</c:when>
                            <c:otherwise>Create New Topic</c:otherwise>
                        </c:choose>
                    </h4>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/topics" method="post">
                        <c:if test="${not empty topic}">
                            <input type="hidden" name="id" value="${topic.id}">
                            <input type="hidden" name="action" value="update">
                        </c:if>
                        <c:if test="${empty topic}">
                            <input type="hidden" name="action" value="create">
                        </c:if>
                        <div class="mb-3">
                            <label for="title" class="form-label">Title *</label>
                            <input type="text" class="form-control" id="title" name="title"
                                   value="${topic.title}" required maxlength="200">
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control" id="description" name="description"
                                      rows="3" maxlength="1000">${topic.description}</textarea>
                        </div>
                        <div class="mb-3">
                            <label for="estimatedHours" class="form-label">Estimated Hours</label>
                            <input type="number" class="form-control" id="estimatedHours" name="estimatedHours"
                                   value="${topic.estimatedHours}" min="0">
                        </div>
                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary">
                                <c:choose>
                                    <c:when test="${not empty topic}">Update Topic</c:when>
                                    <c:otherwise>Create Topic</c:otherwise>
                                </c:choose>
                            </button>
                            <a href="${pageContext.request.contextPath}/topics" class="btn btn-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>