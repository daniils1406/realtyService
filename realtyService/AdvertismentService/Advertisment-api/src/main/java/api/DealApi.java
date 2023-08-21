package api;

import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.deal.DealRequest;
import dto.request.deal.DealUpdateRequest;
import dto.response.deal.DealResponse;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/deal")
public interface DealApi {

    @PermitAll
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    EntityPage getAllDeal(@RequestParam("page") int page,
                          @RequestParam(value = "status", required = false, defaultValue = "DONE")
                          List<String> status,
                          @RequestBody(required = false)
                          Map<String, String> columnsAndOrder);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    DealResponse createNewDeal(@Valid @NotNull @RequestBody DealRequest dealRequest);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    DealResponse updateDealById(@Valid @NotNull @RequestBody DealUpdateRequest dealRequest);

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/byId")
    @ResponseStatus(HttpStatus.OK)
    DealResponse getDealById(@RequestBody IdRequest dealId);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping()
    @ResponseStatus(HttpStatus.OK)
    void setNewStatus(@RequestParam("dealId") UUID dealId,
                      @RequestParam("status") String status);

}
