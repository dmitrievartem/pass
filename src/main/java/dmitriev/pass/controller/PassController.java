package dmitriev.pass.controller;

import dmitriev.pass.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pass")
public class PassController {
    private int counter = 4;
    private List<Map<String, String>> passes = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "first text"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "second text"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "third text"); }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return passes;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getPass(id);
    }

    private Map<String, String> getPass(String id) {
        return passes.stream()
                .filter(pass -> pass.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> pass) {
        pass.put("id", String.valueOf(counter++));
        passes.add(pass);
        return pass;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> pass) {
        Map<String, String> passFromDb = getPass(id);

        passFromDb.putAll(pass);
        passFromDb.put("id", id);

        return passFromDb;
    }

    @DeleteMapping("{id}")
    public void update(@PathVariable String id) {
        Map<String, String> pass = getPass(id);

        passes.remove(pass);
    }
}
