package com.example.studysystem.servlet;

import com.example.studysystem.dto.TopicDto;
import com.example.studysystem.service.TopicService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/topics")
public class TopicServlet extends HttpServlet {

    @Inject
    private TopicService topicService;

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
                deleteTopic(request, response);
                break;
            case "list":
            default:
                listTopics(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createTopic(request, response);
        } else if ("update".equals(action)) {
            updateTopic(request, response);
        }
    }

    private void listTopics(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<TopicDto> topics = topicService.findAll();
        request.setAttribute("topics", topics);
        request.getRequestDispatcher("/WEB-INF/topics.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/topics/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        TopicDto topic = topicService.findById(id)
                .orElseThrow(() -> new ServletException("Topic not found with id: " + id));

        request.setAttribute("topic", topic);
        request.getRequestDispatcher("/WEB-INF/views/topics/form.jsp").forward(request, response);
    }

    private void createTopic(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        TopicDto topicDto = new TopicDto();
        topicDto.setTitle(request.getParameter("title"));
        topicDto.setDescription(request.getParameter("description"));

        String estimatedHoursStr = request.getParameter("estimatedHours");
        if (estimatedHoursStr != null && !estimatedHoursStr.isEmpty()) {
            topicDto.setEstimatedHours(Integer.parseInt(estimatedHoursStr));
        }

        topicService.create(topicDto);
        response.sendRedirect(request.getContextPath() + "/topics");
    }

    private void updateTopic(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Long id = Long.parseLong(request.getParameter("id"));

        TopicDto topicDto = new TopicDto();
        topicDto.setId(id);
        topicDto.setTitle(request.getParameter("title"));
        topicDto.setDescription(request.getParameter("description"));

        String estimatedHoursStr = request.getParameter("estimatedHours");
        if (estimatedHoursStr != null && !estimatedHoursStr.isEmpty()) {
            topicDto.setEstimatedHours(Integer.parseInt(estimatedHoursStr));
        }

        topicService.update(id, topicDto);
        response.sendRedirect(request.getContextPath() + "/topics");
    }

    private void deleteTopic(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        topicService.delete(id);
        response.sendRedirect(request.getContextPath() + "/topics");
    }
}