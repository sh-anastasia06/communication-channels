package by.btk.commchannels.controller;

import by.btk.commchannels.DTO.directory.STypeSysTransDTO;
import by.btk.commchannels.service.directory.TypeSysTransServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TypeSysTransController {
    private final TypeSysTransServ  typeSysTransServ;

    @Autowired
    public TypeSysTransController(TypeSysTransServ typeSysTransServ) {
        this.typeSysTransServ = typeSysTransServ;
    }

    @GetMapping("/alltypest")
    public List<STypeSysTransDTO> getAllTypeSysTrans(@RequestParam(value = "filter", required = false) String filter){
        return typeSysTransServ.findAll(filter);
    }

    @PostMapping("/newtypest")
    public void createTypeSysTrans(@RequestBody STypeSysTransDTO dto){
        typeSysTransServ.insert(dto);
    }

    @PutMapping("/updatetypest")
    public void updateTypeSysTrans(@RequestBody STypeSysTransDTO dto){
        typeSysTransServ.update(dto);
    }
}
