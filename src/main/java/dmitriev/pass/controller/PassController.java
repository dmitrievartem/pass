package dmitriev.pass.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dmitriev.pass.View;
import dmitriev.pass.domain.Pass;
import dmitriev.pass.repo.PassRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("pass")
public class PassController {
    private final PassRepo passRepo;

    @Autowired
    public PassController(PassRepo passRepo) {
        this.passRepo = passRepo;
    }

    @GetMapping
    public List<Pass> list() {
        return passRepo.findAll();
    }

    @GetMapping("{guid}")
    public Pass getOne(@PathVariable(name = "guid", required = false) Pass pass) {
        checkGuid(pass);
        return pass;
    }

    @PostMapping
    @JsonView(View.Public.class)
    public Pass create(@RequestBody Pass pass) {
        return passRepo.save(pass);
    }

    @PutMapping("{guid}")
    public void update(
            @PathVariable(name = "guid", required = false) Pass passFromDb,
            @RequestBody Pass pass
    ) {
        checkGuid(passFromDb);
        BeanUtils.copyProperties(pass, passFromDb,  "guid");
        passRepo.save(passFromDb);
    }

    @DeleteMapping("{guid}")
    public void update(@PathVariable(name = "guid", required = false) Pass pass) {
        checkGuid(pass);
        passRepo.delete(pass);
    }

    @GetMapping("validate/{guid}")
    public void validate(@PathVariable(name = "guid", required = false) Pass pass) {
        checkGuid(pass);
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        if (today.isBefore(pass.getDateFrom()) || today.isAfter(pass.getDateTo())) {
            throw new ResponseStatusException(HttpStatus.GONE, "Pass is not valid");
        }
    }

    public void checkGuid(Pass pass) {
        if (pass == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "GUID not found");
        }
    }
}
