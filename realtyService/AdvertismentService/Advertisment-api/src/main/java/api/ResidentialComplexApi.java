package api;


import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.residentialcomplex.ResidentialComplexRequest;
import dto.request.residentialcomplex.ResidentialComplexUpdateRequest;
import dto.response.residentialcomplex.ResidentialComplexAdminResponse;
import dto.response.residentialcomplex.ResidentialComplexResponse;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RequestMapping("/residentialComplex")
public interface ResidentialComplexApi {

    @PermitAll
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllResidentialComplex(@RequestParam("page") int page,
                                        @RequestBody(required = false) Map<String, String> columnsAndOrder);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('BUILDER') || hasAuthority('AGENCY')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ResidentialComplexResponse createNewResidentialComplex(@NotNull @RequestBody @Valid
                                                           ResidentialComplexRequest residentialComplexRequest);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('BUILDER') || hasAuthority('AGENCY')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    ResidentialComplexResponse updateResidentialComplexById(@RequestBody @NotNull @Valid
                                                            ResidentialComplexUpdateRequest residentialComplexRequest);

    @PermitAll
    @GetMapping("/byId")
    @ResponseStatus(HttpStatus.OK)
    ResidentialComplexResponse getResidentialComplexById(@RequestBody IdRequest residentialComplexId);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('BUILDER') || hasAuthority('AGENCY')")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    void deleteResidentialComplexById(@RequestBody IdRequest residentialComplexId);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/approveComplex")
    @ResponseStatus(HttpStatus.OK)
    void approveResidentialComplexById(@RequestBody IdRequest residentialComplexId);

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllResidentialComplexByAdmin(@RequestParam("page")
                                               int page,
                                               @RequestParam(value = "status", required = false,
                                                       defaultValue = "VERIFIED")
                                               List<String> status,
                                               @RequestBody(required = false) Map<String, String> columnsAndOrder);

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/byId")
    @ResponseStatus(HttpStatus.OK)
    ResidentialComplexAdminResponse getResidentialComplexByIdBuAdmin(@RequestBody IdRequest residentialComplexId);

}
