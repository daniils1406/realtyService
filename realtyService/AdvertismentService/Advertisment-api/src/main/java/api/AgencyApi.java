package api;

import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.agency.AgencyRequest;
import dto.request.agency.AgencyUpdateRequest;
import dto.response.agency.AgencyAdminResponse;
import dto.response.agency.AgencyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RequestMapping("/agency")
public interface AgencyApi {

    @Operation(summary = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = EntityPage.class))
            })
    })
    @PermitAll
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllAgency(@Parameter(description = "") @RequestParam("page") int page,//описываем конкртеное поле и его значение
                            @RequestParam(value = "level", required = false, defaultValue = "PRO") List<String> level,
                            @RequestBody(required = false) Map<String, String> columnsAndOrder);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('AGENCY')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    AgencyResponse createNewAgency(HttpServletRequest request, @Valid @NotNull @RequestBody AgencyRequest agencyRequest);

    @PreAuthorize("hasAuthority('AGENCY')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    AgencyResponse updateAgencyById(HttpServletRequest request,@Valid @NotNull @RequestBody AgencyUpdateRequest agencyRequest);

    @PermitAll
    @GetMapping("/byId")
    @ResponseStatus(HttpStatus.OK)
    AgencyResponse getAgencyById(@RequestBody IdRequest agencyId);

    @PreAuthorize("hasAuthority('AGENCY')")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    void deleteAgencyById(HttpServletRequest request,@RequestBody IdRequest agencyId);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/approveAgency")
    @ResponseStatus(HttpStatus.OK)
    void approveAgencyById(@RequestBody IdRequest agencyId);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateLevel")
    @ResponseStatus
    void updateLevelById(@RequestBody IdRequest request, @NotNull @RequestParam("newLevel") String newLevel);


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/getAll")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllAgencyByAdmin(@RequestParam("page")
                                   int page,
                                   @RequestParam(value = "status", required = false, defaultValue = "VERIFIED")
                                   List<String> status,
                                   @RequestParam(value = "level", required = false, defaultValue = "PRO")
                                   List<String> level,
                                   @RequestBody(required = false)
                                   Map<String, String> columnsAndOrder);

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/byId")
    @ResponseStatus(HttpStatus.OK)
    AgencyAdminResponse getAgencyByIdByAdmin(@RequestBody IdRequest agencyId);

}
