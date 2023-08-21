package api;

import dto.IdRequest;
import dto.response.ChatRoomResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RequestMapping("/room")
public interface RoomApi {
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/notificationRoom")
    void createNotificationRoom(UUID userId, @RequestParam("name") String nameOfRoom);


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createChat")
    void createRoom(HttpServletRequest request, @RequestParam("name") String nameOfRoom);


    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/changeChatName")
    ChatRoomResponse changeRoomName(HttpServletRequest request, @RequestParam("newName") String newName, @RequestParam("id") String roomId);


    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    List<ChatRoomResponse> getAllRoomsOfUser(@RequestBody IdRequest idRequest);

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping()
    void deleteRoom(HttpServletRequest request, @RequestParam("id") String roomId);

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/deleteMember")
    void deleteMemberFromChat(HttpServletRequest request, @RequestParam("userId") UUID userId, @RequestParam("roomId") String roomId);

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/addMember")
    void addMemberToChat(HttpServletRequest request, @RequestParam("userId") UUID userId, @RequestParam("roomId") String roomId);
}
