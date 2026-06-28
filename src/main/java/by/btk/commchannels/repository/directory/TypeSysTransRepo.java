package by.btk.commchannels.repository.directory;

import by.btk.commchannels.DTO.directory.STypeSysTransDTO;

import java.util.List;

public interface TypeSysTransRepo {
    List<STypeSysTransDTO> findAll(String filter);
    void insert(STypeSysTransDTO dto);
    void update(STypeSysTransDTO dto);
}
