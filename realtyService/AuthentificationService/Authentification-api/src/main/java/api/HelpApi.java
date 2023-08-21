package api;

import dto.request.cianuser.CianUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/help")
public interface HelpApi {

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/resetPassword")
    @ResponseStatus(HttpStatus.OK)
    void createTestMessageToResetPassword(@RequestBody CianUserRequest cianUserRequest);

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/resetLogin")
    @ResponseStatus(HttpStatus.OK)
    void createTestMessageToResetLogin(HttpServletRequest request, @RequestParam String newLogin);
}
