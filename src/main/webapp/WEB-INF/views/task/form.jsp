<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${not empty task}">Edit Task</c:when>
            <c:otherwise>Create New Task</c:otherwise>
        </c:choose>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">Task Management</a>
        <div class="navbar-nav">
            <a class="nav-link" href="${pageContext.request.contextPath}/topics">Topics</a>
            <a class="nav-link active" href="${pageContext.request.contextPath}/tasks">Tasks</a>
        </div>
    </div>
</nav>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title mb-0">
                        <i class="bi bi-card-checklist"></i>
                        <c:choose>
                            <c:when test="${not empty task}">Edit Task</c:when>
                            <c:otherwise>Create New Task</c:otherwise>
                        </c:choose>
                    </h4>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/tasks" method="post">
                        <c:if test="${not empty task}">
                            <input type="hidden" name="id" value="${task.id}">
                            <input type="hidden" name="action" value="update">
                        </c:if>
                        <c:if test="${empty task}">
                            <input type="hidden" name="action" value="create">
                        </c:if>
                        <div class="mb-3">
                            <label for="title" class="form-label">Title *</label>
                            <input type="text" class="form-control" id="title" name="title"
                                   value="${task.title}" required maxlength="200" placeholder="Enter task title">
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control" id="description" name="description"
                                      rows="4" maxlength="1000" placeholder="Enter task description">${task.description}</textarea>
                        </div>
                        <div class="mb-3">
                            <label for="status" class="form-label">Status *</label>
                            <select class="form-select" id="status" name="status" required>
                                <option value="NOT_STARTED"
                                        <c:if test="${task.status == 'NOT_STARTED'}">selected</c:if>>
                                    Not Started
                                </option>
                                <option value="IN_PROGRESS"
                                        <c:if test="${task.status == 'IN_PROGRESS'}">selected</c:if>>
                                    In Progress
                                </option>
                                <option value="COMPLETED"
                                        <c:if test="${task.status == 'COMPLETED'}">selected</c:if>>
                                    Completed
                                </option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="topicId" class="form-label">Topic ID *</label>
                            <input type="number" class="form-control" id="topicId" name="topicId"
                                   value="${not empty task ? task.topicId : topicId}" required min="1"
                                   placeholder="Enter topic ID">
                            <div class="form-text">Enter the ID of the topic this task belongs to</div>
                        </div>
                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-check-circle"></i>
                                <c:choose>
                                    <c:when test="${not empty task}">Update Task</c:when>
                                    <c:otherwise>Create Task</c:otherwise>
                                </c:choose>
                            </button>
                            <c:choose>
                                <c:when test="${not empty task}">
                                    <a href="${pageContext.request.contextPath}/tasks?topicId=${task.topicId}" class="btn btn-secondary">
                                        <i class="bi bi-x-circle"></i> Cancel
                                    </a>
                                </c:when>
                                <c:when test="${not empty topicId}">
                                    <a href="${pageContext.request.contextPath}/tasks?topicId=${topicId}" class="btn btn-secondary">
                                        <i class="bi bi-x-circle"></i> Cancel
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/tasks" class="btn btn-secondary">
                                        <i class="bi bi-x-circle"></i> Cancel
                                    </a>
                                </c:otherwise>
                            </c:choose>
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