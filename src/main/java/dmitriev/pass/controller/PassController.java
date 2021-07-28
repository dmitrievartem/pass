package dmitriev.pass.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pass")
public class PassController {
    public List<Map<String, String>> passes = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "first text"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "second text"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "third text"); }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return passes;
    }
}
