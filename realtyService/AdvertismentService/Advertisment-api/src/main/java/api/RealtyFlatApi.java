package api;

import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.realty.IdAndRealtyStatusRequest;
import dto.request.realty.flat.RealtyFlatRequest;
import dto.request.realty.flat.RealtyFlatUpdateRequest;
import dto.response.realty.flat.RealtyFlatResponse;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/realty/flat")
public interface RealtyFlatApi {

    @PermitAll
    @GetMapping("/buy")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllFlatForBuying(@RequestParam int page,
                                   @RequestBody Map<String, String> columnsAndOrder,
                                   @RequestParam(value = "realtyType") String realtyType);

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/buy/agent")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllFlatForBuyingForAgent(HttpServletRequest request,
                                           @RequestParam int page,
                                           @RequestBody Map<String, String> columnsAndOrder,
                                           @RequestParam(value = "realtyType") String realtyType);

    @PermitAll
    @GetMapping("/rent")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllFlatForRent(@RequestParam int page,
                                 @RequestBody Map<String, String> columnsAndOrder,
                                 @RequestParam(value = "realtyType") String realtyType);

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/rent/agent")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllFlatForRentForAgent(HttpServletRequest request,
                                         @RequestParam int page,
                                         @RequestBody Map<String, String> columnsAndOrder,
                                         @RequestParam(value = "realtyType") String realtyType);


    @PreAuthorize("isAuthenticated()")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    RealtyFlatResponse createNewFlat(HttpServletRequest request, @Valid @RequestBody @NotNull RealtyFlatRequest flat);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('BUILDER') || hasAuthority('AGENCY') || hasAuthority('OWNER') || hasAuthority('AGENT')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    RealtyFlatResponse updateFlat(HttpServletRequest request, @Valid @NotNull @RequestBody RealtyFlatUpdateRequest newFlat);

    @PermitAll
    @GetMapping("/byId")
    @ResponseStatus(HttpStatus.OK)
    RealtyFlatResponse getFlatById(@RequestBody @NotNull IdRequest flatId);

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/setStatus")
    @ResponseStatus(HttpStatus.OK)
    void setStatusFlatById(HttpServletRequest request, @RequestBody @NotNull IdAndRealtyStatusRequest newStatusForFlat);


    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('OWNER')")
    @GetMapping("/admin/buy")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getFlatOfOwnerBuy(HttpServletRequest request,
                                 @RequestParam int page,
                                 @RequestBody Map<String, String> columnsAndOrder,
                                 @RequestParam(value = "realtyType") String realtyType,
                                 @RequestParam("id") UUID id);


    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('OWNER')")
    @GetMapping("/admin/rent")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getFlatOfOwnerRent(HttpServletRequest request,
                                  @RequestParam int page,
                                  @RequestBody Map<String, String> columnsAndOrder,
                                  @RequestParam(value = "realtyType") String realtyType,
                                  @RequestParam("id") UUID id);


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/interested")
    @ResponseStatus(HttpStatus.OK)
    void interestedInSomeRealty(HttpServletRequest request, @RequestParam("id") UUID realtyId);

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    void transferRealtyToAgent(HttpServletRequest request,
                               @RequestParam("realtyId") UUID realtyId,
                               @RequestParam("agentId") UUID agentId);

}
