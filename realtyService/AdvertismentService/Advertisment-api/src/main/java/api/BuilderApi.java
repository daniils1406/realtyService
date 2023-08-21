package api;

import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.builder.BuilderRequest;
import dto.request.builder.BuilderUpdateRequest;
import dto.response.builder.BuilderAdminWithComplexesResponse;
import dto.response.builder.BuilderResponse;
import dto.response.builder.BuilderWithComplexesResponse;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RequestMapping("/builder")
public interface BuilderApi {

    @PermitAll
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllBuilders(@RequestParam("page")
                              int page,
                              @RequestBody(required = false)
                              Map<String, String> columnsAndOrder);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('BUILDER')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    BuilderResponse createNewBuilder(@Valid @NotNull @RequestBody BuilderRequest builderData);

    @PreAuthorize("hasAuthority('BUILDER')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    BuilderResponse updateBuilderById(HttpServletRequest request, @Valid @NotNull @RequestBody BuilderUpdateRequest newBuilder);

    @PermitAll
    @GetMapping("/byId")
    @ResponseStatus(HttpStatus.OK)
    BuilderWithComplexesResponse getBuilderById(@RequestBody @NotNull IdRequest request);

    @PreAuthorize("hasAuthority('BUILDER')")
    @DeleteMapping()
    void deleteBuilderById(HttpServletRequest request, @RequestBody @NotNull IdRequest builderId);


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/approveBuilder")
    @ResponseStatus(HttpStatus.OK)
    void approveBuilderById(@RequestBody @NotNull IdRequest request);


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/getAll")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllBuildersByAdmin(@RequestParam("page")
                                     int page,
                                     @RequestParam(value = "status", required = false, defaultValue = "VERIFIED")
                                     List<String> status,
                                     @RequestBody(required = false)
                                     Map<String, String> columnsAndOrder);

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/byId")
    @ResponseStatus(HttpStatus.OK)
    BuilderAdminWithComplexesResponse getBuilderByIdByAdmin(@RequestBody @NotNull IdRequest request);


}
