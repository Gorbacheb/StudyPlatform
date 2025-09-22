<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Tasks List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <style>
        .action-buttons {
            white-space: nowrap;
        }
        .table-hover tbody tr:hover {
            background-color: rgba(0, 0, 0, 0.075);
        }
    </style>
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
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>
            <c:choose>
                <c:when test="${not empty topicTitle}">Tasks for: ${topicTitle}</c:when>
                <c:otherwise>All Tasks</c:otherwise>
            </c:choose>
        </h2>
        <c:choose>
            <c:when test="${not empty topicId}">
                <a href="${pageContext.request.contextPath}/tasks?action=new&topicId=${topicId}" class="btn btn-primary">
                    <i class="bi bi-plus-circle"></i> New Task
                </a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/tasks?action=new" class="btn btn-primary">
                    <i class="bi bi-plus-circle"></i> New Task
                </a>
            </c:otherwise>
        </c:choose>
    </div>
    <c:if test="${not empty topicId}">
        <div class="alert alert-info d-flex justify-content-between align-items-center">
            <span>Showing tasks for topic: <strong>${topicTitle}</strong></span>
            <a href="${pageContext.request.contextPath}/tasks" class="btn btn-sm btn-outline-dark">Show All Tasks</a>
        </div>
    </c:if>
    <div class="card">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Topic</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <jsp:useBean id="tasks" scope="request" type="java.util.List"/>
                    <c:forEach var="task" items="${tasks}">
                        <tr>
                            <td>${task.id}</td>
                            <td>${task.title}</td>
                            <td>${task.description}</td>
                            <td>
                                <span class="badge status-badge
                                    <c:choose>
                                        <c:when test="${task.status == 'COMPLETED'}">bg-success</c:when>
                                        <c:when test="${task.status == 'IN_PROGRESS'}">bg-primary</c:when>
                                        <c:when test="${task.status == 'NOT_STARTED'}">bg-secondary</c:when>
                                        <c:otherwise>bg-danger</c:otherwise>
                                    </c:choose>">
                                        ${task.status}
                                </span>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/tasks?topicId=${task.topicId}"
                                   class="text-decoration-none">
                                        ${task.topicTitle}
                                </a>
                            </td>
                            <td class="action-buttons">
                                <a href="${pageContext.request.contextPath}/tasks?action=edit&id=${task.id}"
                                   class="btn btn-sm btn-outline-primary me-1"
                                   title="Edit task">
                                    <i class="bi bi-pencil"></i> Edit
                                </a>
                                <a href="${pageContext.request.contextPath}/tasks?action=delete&id=${task.id}"
                                   class="btn btn-sm btn-outline-danger"
                                   title="Delete task"
                                   onclick="return confirm('Are you sure you want to delete this task?')">
                                    <i class="bi bi-trash"></i> Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <c:if test="${empty tasks}">
                <div class="alert alert-info text-center">
                    <c:choose>
                        <c:when test="${not empty topicId}">
                            No tasks found for this topic. <a href="${pageContext.request.contextPath}/tasks?action=new&topicId=${topicId}">Create your first task</a>
                        </c:when>
                        <c:otherwise>
                            No tasks found. <a href="${pageContext.request.contextPath}/tasks?action=new">Create your first task</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>