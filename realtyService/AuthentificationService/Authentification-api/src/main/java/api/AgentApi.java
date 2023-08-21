package api;

import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.cianuser.agent.AgentRequest;
import dto.request.cianuser.agent.AgentUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import dto.response.cianuser.agent.AgentAdminResponse;
import dto.response.cianuser.agent.AgentResponse;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RequestMapping("/agent")
public interface AgentApi {


    @PermitAll
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllUsers(@RequestParam("page")
                           int page,
                           @RequestBody(required = false)
                           Map<String, String> columnsAndOrder,
                           @RequestParam("experience") List<String> experience,
                           @RequestParam("status") List<String> status);

    @PermitAll
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    AgentResponse createNewAgent(@Valid @RequestBody @NotNull AgentRequest agentRequest);

    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    CianUserResponse updateAgentById(HttpServletRequest request, @Valid @RequestBody @NotNull AgentUpdateRequest updateRequest);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('AGENT')")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    void deleteAgentById(HttpServletRequest request, @RequestBody IdRequest idRequest);

    @PermitAll
    @GetMapping("/byId")
    @ResponseStatus(HttpStatus.OK)
    AgentResponse getAgentById(@RequestBody IdRequest agentId);


    @PreAuthorize("hasAuthority('AGENCY')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<AgentAdminResponse> getAgentsOfAgency(@RequestBody IdRequest agencyId);


}
