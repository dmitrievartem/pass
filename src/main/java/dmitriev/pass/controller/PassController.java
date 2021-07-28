package dmitriev.pass.controller;

import dmitriev.pass.domain.Pass;
import dmitriev.pass.repo.PassRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public Pass getOne(@PathVariable("id") Pass pass) {
        return pass;
    }

    @PostMapping
    public Pass create(@RequestBody Pass pass) {
        return passRepo.save(pass);
    }

    @PutMapping("{id}")
    public Pass update(
            @PathVariable("id") Pass passFromDb,
            @RequestBody Pass pass
    ) {
        BeanUtils.copyProperties(pass, passFromDb,  "id");

        return passRepo.save(passFromDb);
    }

    @DeleteMapping("{id}")
    public void update(@PathVariable("id") Pass pass) {
        passRepo.delete(pass);
    }
}
