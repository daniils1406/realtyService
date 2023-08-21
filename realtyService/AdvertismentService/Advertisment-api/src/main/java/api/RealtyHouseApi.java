package api;

import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.realty.IdAndRealtyStatusRequest;
import dto.request.realty.home.RealtyHomeRequest;
import dto.request.realty.home.RealtyHomeUpdateRequest;
import dto.response.realty.home.RealtyHomeResponse;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/realty/house")
public interface RealtyHouseApi {

    @PermitAll
    @GetMapping("/buy")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllHomeForBuying(@RequestParam int page,
                                   @RequestBody Map<String, String> columnsAndOrder);

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/buy/agent")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllHomeForBuyingForAgent(HttpServletRequest request,
                                           @RequestParam int page,
                                           @RequestBody Map<String, String> columnsAndOrder);

    @PermitAll
    @GetMapping("/rent")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllHomeForRent(@RequestParam int page,
                                 @RequestBody Map<String, String> columnsAndOrder);

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/rent/agent")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllHomeForRentForAgent(HttpServletRequest request,
                                         @RequestParam int page,
                                         @RequestBody Map<String, String> columnsAndOrder);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('BUILDER') || hasAuthority('AGENCY') || hasAuthority('OWNER') || hasAuthority('AGENT')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    RealtyHomeResponse createNewHome(HttpServletRequest request, @Valid @RequestBody @NotNull RealtyHomeRequest home);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('BUILDER') || hasAuthority('AGENCY') || hasAuthority('OWNER') || hasAuthority('AGENT')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    RealtyHomeResponse updateHome(HttpServletRequest request, @Valid @NotNull @RequestBody RealtyHomeUpdateRequest newHome);

    @PermitAll
    @GetMapping("/byId")
    @ResponseStatus(HttpStatus.OK)
    RealtyHomeResponse getFlatById(@RequestBody @NotNull IdRequest flatId);


    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/setStatus")
    @ResponseStatus(HttpStatus.OK)
    void setStatusHomeById(HttpServletRequest request, @RequestBody @NotNull IdAndRealtyStatusRequest newStatusForHome);


    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('OWNER')")
    @GetMapping("/admin/buy")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getHomeOfOwnerForBuying(HttpServletRequest request,
                                       @RequestParam int page,
                                       @RequestBody Map<String, String> columnsAndOrder,
                                       @RequestParam("id") UUID ownerId);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('OWNER')")
    @GetMapping("/admin/rent")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getHomeOfOwnerForRent(HttpServletRequest request,
                                     @RequestParam int page,
                                     @RequestBody Map<String, String> columnsAndOrder,
                                     @RequestParam("id") UUID ownerId);


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/interested")
    @ResponseStatus(HttpStatus.OK)
    void interestedInSomeRealty(HttpServletRequest request, UUID realtyId);


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    void transferRealtyToAgent(HttpServletRequest request,
                               @RequestParam("realtyId") UUID realtyId,
                               @RequestParam("agentId") UUID agentId);
}
