package api;

import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.LoginRequest;
import dto.request.PasswordRequest;
import dto.request.cianuser.CianUserRequest;
import dto.request.cianuser.CianUserUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
public interface CianUserApi {


    @PermitAll
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllUsers(@RequestParam("page")
                           int page,
                           @RequestBody(required = false)
                           Map<String, String> columnsAndOrder,
                           @RequestParam("role") List<String> role,
                           @RequestParam("status") List<String> status);

    @PermitAll
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    CianUserResponse createNewCianUser(@Valid @RequestBody @NotNull CianUserRequest cianUserRequest);

    @PreAuthorize("hasAuthority('CLIENT') || hasAuthority('OWNER')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    CianUserResponse updateCianUserById(HttpServletRequest request, @Valid @RequestBody @NotNull CianUserUpdateRequest cianUserUpdateRequest);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('CLIENT') || hasAuthority('OWNER')")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    void deleteCianUserById(HttpServletRequest request, @RequestBody IdRequest idRequest);

    @PermitAll
    @GetMapping("/byId")
    @ResponseStatus(HttpStatus.OK)
    CianUserResponse getCianUserById(@RequestBody IdRequest idRequest);


    @PermitAll
    @GetMapping("/approveCianUser")
    @ResponseStatus(HttpStatus.OK)
    void approveCianUserById(@RequestParam String token);


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/bannedCianUser")
    @ResponseStatus(HttpStatus.OK)
    void bannedCianUserById(@RequestBody IdRequest idRequest);


    @PermitAll
    @PutMapping("/resetPassword")
    @ResponseStatus(HttpStatus.OK)
    void resetPasswordUser(@RequestBody PasswordRequest passwordAndToken);

    @PermitAll
    @PutMapping("/resetLogin")
    @ResponseStatus(HttpStatus.OK)
    void resetLoginUser(@Valid @RequestBody LoginRequest loginAndToken);


}
