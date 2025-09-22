<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <title>Topics List</title>
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
      <a class="nav-link active" href="${pageContext.request.contextPath}/topics">Topics</a>
      <a class="nav-link" href="${pageContext.request.contextPath}/tasks">Tasks</a>
    </div>
  </div>
</nav>

<div class="container mt-4">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Topics</h2>
    <a href="${pageContext.request.contextPath}/topics?action=new" class="btn btn-primary">
      <i class="bi bi-plus-circle"></i> New Topic
    </a>
  </div>
  <div class="card">
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <thead class="table-dark">
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Estimated Hours</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <jsp:useBean id="topics" scope="request" type="java.util.List"/>
          <c:forEach var="topic" items="${topics}">
            <tr>
              <td>${topic.id}</td>
              <td>${topic.title}</td>
              <td>${topic.description}</td>
              <td>
                <c:if test="${not empty topic.estimatedHours}">
                  ${topic.estimatedHours} hours
                </c:if>
                <c:if test="${empty topic.estimatedHours}">
                  <span class="text-muted">Not specified</span>
                </c:if>
              </td>
              <td class="action-buttons">
                <a href="${pageContext.request.contextPath}/topics?action=edit&id=${topic.id}"
                   class="btn btn-sm btn-outline-primary me-1"
                   title="Edit topic">
                  <i class="bi bi-pencil"></i> Edit
                </a>
                <a href="${pageContext.request.contextPath}/topics?action=delete&id=${topic.id}"
                   class="btn btn-sm btn-outline-danger me-1"
                   title="Delete topic"
                   onclick="return confirm('Are you sure you want to delete this topic? All tasks associated with this topic will also be deleted.')">
                  <i class="bi bi-trash"></i> Delete
                </a>
                <a href="${pageContext.request.contextPath}/tasks?topicId=${topic.id}"
                   class="btn btn-sm btn-outline-info"
                   title="View tasks for this topic">
                  <i class="bi bi-list-task"></i> Tasks
                </a>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>

      <c:if test="${empty topics}">
        <div class="alert alert-info text-center">
          No topics found. <a href="${pageContext.request.contextPath}/topics?action=new">Create your first topic</a>
        </div>
      </c:if>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>