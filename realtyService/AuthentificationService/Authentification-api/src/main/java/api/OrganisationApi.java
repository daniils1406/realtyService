package api;

import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.cianuser.organisation.OrganisationRequest;
import dto.request.cianuser.organisation.OrganisationUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import dto.response.cianuser.organisation.OrganisationResponse;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/organisation")
public interface OrganisationApi {

    @PermitAll
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllUsers(@RequestParam("page")
                           int page,
                           @RequestBody(required = false)
                           Map<String, String> columnsAndOrder,
                           @RequestParam("status") List<String> status);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    OrganisationResponse createNewOrganisation(@Valid @RequestBody @NotNull OrganisationRequest organisationRequest);

    @PreAuthorize("hasAuthority('AGENCY') || hasAuthority('BUILDER')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    CianUserResponse updateOrganisationById(HttpServletRequest request, @Valid @RequestBody @NotNull OrganisationUpdateRequest organisationUpdateRequest);

    @PreAuthorize("hasAuthority('AGENCY') || hasAuthority('BUILDER')")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    void deleteOrganisationById(HttpServletRequest request, @RequestBody IdRequest idRequest);

    @PermitAll
    @GetMapping("/byId")
    @ResponseStatus(HttpStatus.OK)
    OrganisationResponse getOrganisationById(@RequestBody IdRequest idRequest);

    @PreAuthorize("hasAuthority('AGENCY')")
    @PostMapping("/agent/hire")
    @ResponseStatus(HttpStatus.OK)
    void hireWorker(HttpServletRequest request, @RequestBody IdRequest idRequest);

    @PreAuthorize("hasAuthority('AGENCY')")
    @PostMapping("/agent/dismiss")
    @ResponseStatus(HttpStatus.OK)
    void dismissWorker(HttpServletRequest request, @RequestBody IdRequest idRequest);


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/agent/level")
    @ResponseStatus(HttpStatus.OK)
    void newAgentLevel(@RequestParam("agentId") UUID agentId, @RequestParam("agentLevel") String agentLevel);
}
