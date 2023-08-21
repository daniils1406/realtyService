package api;

import dto.IdRequest;
import dto.request.MessageRequest;
import dto.request.MessageUpdateRequest;
import dto.request.NotificationRequest;
import dto.response.MessageResponse;
import dto.response.NotificationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping("/chat")
public interface MessageApi {

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    void sendMessage(HttpServletRequest request, @RequestBody MessageRequest messageRequest);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/api/sendNotification", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    void sendNotification(@RequestBody NotificationRequest notificationRequest);

    @PreAuthorize("isAuthenticated()")
    @PatchMapping()
    MessageResponse changeMessage(HttpServletRequest request, @RequestBody MessageUpdateRequest messageUpdateRequest);

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/deleteMessage")
    void deleteMessage(HttpServletRequest request, @RequestBody IdRequest messageId);

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/notifications")
    List<NotificationResponse> getAllNotificationsOfUser(HttpServletRequest request, @RequestParam String userId);

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/messages")
    List<MessageResponse> findChatMessages(@RequestParam("id") String roomId);

}
