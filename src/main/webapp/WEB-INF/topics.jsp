<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<jsp:useBean id="topics" scope="request" type="java.util.List"/>
<div class="row">
  <c:forEach items="${topics}" var="topic">
    <div class="col-md-4 mb-4">
      <div class="card h-100">
        <div class="card-body">
          <h5 class="card-title">${topic.title}</h5>
          <p class="card-text">${topic.description}</p>
          <c:if test="${topic.estimatedHours > 0}">
            <p class="card-text">
              <small class="text-muted">
                Estimated hours: ${topic.estimatedHours}
              </small>
            </p>
          </c:if>
        </div>
      </div>
    </div>
  </c:forEach>
</div>
</body>
</html>