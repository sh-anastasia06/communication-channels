package by.btk.commchannels.service.directory;

import by.btk.commchannels.DTO.directory.STypeSysTransDTO;

import java.util.List;

public interface TypeSysTransServ {
    List<STypeSysTransDTO> findAll(String filter);
    void insert(STypeSysTransDTO dto);
    void update(STypeSysTransDTO dto);
}
