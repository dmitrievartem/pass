package dmitriev.pass.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dmitriev.pass.View;
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

    @GetMapping("{guid}")
    public Pass getOne(@PathVariable("guid") Pass pass) {
        return pass;
    }

    @PostMapping
    @JsonView(View.Public.class)
    public Pass create(@RequestBody Pass pass) {
        return passRepo.save(pass);
    }

    @PutMapping("{guid}")
    public void update(
            @PathVariable("guid") Pass passFromDb,
            @RequestBody Pass pass
    ) {
        BeanUtils.copyProperties(pass, passFromDb,  "guid");
        passRepo.save(passFromDb);
    }

    @DeleteMapping("{guid}")
    public void update(@PathVariable("guid") Pass pass) {
        passRepo.delete(pass);
    }

//    @GetMapping("validate/{guid}")
//    public Pass validate(@PathVariable("guid") Pass pass) {
//        return pass;
//    }
}
