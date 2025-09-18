package com.example.studysystem.servlet;

import com.example.studysystem.dto.TaskDto;
import com.example.studysystem.entity.TaskStatus;
import com.example.studysystem.service.TaskService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    @Inject
    private TaskService taskService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteTask(request, response);
                break;
            case "list":
            default:
                listTasks(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createTask(request, response);
        } else if ("update".equals(action)) {
            updateTask(request, response);
        }
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String topicIdParam = request.getParameter("topicId");
        List<TaskDto> tasks;

        if (topicIdParam != null && !topicIdParam.isEmpty()) {
            Long topicId = Long.parseLong(topicIdParam);
            tasks = taskService.findByTopicId(topicId);
            request.setAttribute("topicId", topicId);
        } else {
            tasks = taskService.findAll();
        }

        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/WEB-INF/views/tasks/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String topicIdParam = request.getParameter("topicId");
        if (topicIdParam != null && !topicIdParam.isEmpty()) {
            request.setAttribute("topicId", Long.parseLong(topicIdParam));
        }

        request.getRequestDispatcher("/WEB-INF/views/tasks/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        TaskDto task = taskService.findById(id)
                .orElseThrow(() -> new ServletException("Task not found with id: " + id));

        request.setAttribute("task", task);
        request.getRequestDispatcher("/WEB-INF/views/tasks/form.jsp").forward(request, response);
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(request.getParameter("title"));
        taskDto.setDescription(request.getParameter("description"));
        taskDto.setStatus(TaskStatus.valueOf(request.getParameter("status")));

        String deadlineStr = request.getParameter("deadline");
        if (deadlineStr != null && !deadlineStr.isEmpty()) {
            taskDto.setDeadline(LocalDateTime.parse(deadlineStr));
        }

        String topicIdParam = request.getParameter("topicId");
        if (topicIdParam != null && !topicIdParam.isEmpty()) {
            taskDto.setTopicId(Long.parseLong(topicIdParam));
        }

        taskService.create(taskDto);
        response.sendRedirect(request.getContextPath() + "/tasks?topicId=" + taskDto.getTopicId());
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Long id = Long.parseLong(request.getParameter("id"));

        TaskDto taskDto = new TaskDto();
        taskDto.setId(id);
        taskDto.setTitle(request.getParameter("title"));
        taskDto.setDescription(request.getParameter("description"));
        taskDto.setStatus(TaskStatus.valueOf(request.getParameter("status")));

        String deadlineStr = request.getParameter("deadline");
        if (deadlineStr != null && !deadlineStr.isEmpty()) {
            taskDto.setDeadline(LocalDateTime.parse(deadlineStr));
        }

        String topicIdParam = request.getParameter("topicId");
        if (topicIdParam != null && !topicIdParam.isEmpty()) {
            taskDto.setTopicId(Long.parseLong(topicIdParam));
        }

        taskService.update(id, taskDto);
        response.sendRedirect(request.getContextPath() + "/tasks?topicId=" + taskDto.getTopicId());
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Long id = Long.parseLong(request.getParameter("id"));
        TaskDto task = taskService.findById(id)
                .orElseThrow(() -> new ServletException("Task not found with id: " + id));

        taskService.delete(id);
        response.sendRedirect(request.getContextPath() + "/tasks?topicId=" + task.getTopicId());
    }
}