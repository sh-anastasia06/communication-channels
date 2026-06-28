package by.btk.commchannels.service.directory;

import by.btk.commchannels.DTO.directory.STypeSysTransDTO;
import by.btk.commchannels.repository.directory.TypeSysTransRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeSysTransServImpl implements TypeSysTransServ {
    private final TypeSysTransRepo typeSysTransRepo;

    public TypeSysTransServImpl(TypeSysTransRepo typeSysTransRepo) {
        this.typeSysTransRepo = typeSysTransRepo;
    }

    @Override
    public List<STypeSysTransDTO> findAll(String filter) {
        return typeSysTransRepo.findAll(filter);
    }

    @Override
    public void insert(STypeSysTransDTO dto) {
        if (dto.abbrevSt() == null || dto.abbrevSt().isBlank() ||
            dto.name() == null || dto.name().isBlank()) {
            throw new IllegalArgumentException("Все поля являются обязательными для заполнения");
        }

        String formattedAbbrevSt = dto.abbrevSt().trim().toUpperCase();
        String formattedName = dto.name().trim();

        if (formattedAbbrevSt.length() > 3) {
            throw new IllegalArgumentException("Поле 'Обозначение' не должно превышать 3 символов");
        }
        if (formattedName.length() > 20) {
            throw new IllegalArgumentException("Поле 'имя' не должно превышать 20 символов");
        }

        STypeSysTransDTO formattedDTO = new STypeSysTransDTO(formattedAbbrevSt, formattedName);

        typeSysTransRepo.insert(formattedDTO);
    }

    @Override
    public void update(STypeSysTransDTO dto) {
        if (dto.idTypeSt() == null) {
            throw new IllegalArgumentException("ID записи не может быть пустым");
        }
        if (dto.abbrevSt() == null || dto.abbrevSt().isBlank() ||
                dto.name() == null || dto.name().isBlank()) {
            throw new IllegalArgumentException("Все поля являются обязательными для заполнения");
        }

        String formattedAbbrevSt = dto.abbrevSt().trim().toUpperCase();
        String formattedName = dto.name().trim();

        if (formattedAbbrevSt.length() > 3) {
            throw new IllegalArgumentException("Поле 'Обозначение' не должно превышать 3 символов");
        }
        if (formattedName.length() > 20) {
            throw new IllegalArgumentException("Поле 'имя' не должно превышать 20 символов");
        }

        STypeSysTransDTO formattedDTO = new STypeSysTransDTO(dto.idTypeSt(), formattedAbbrevSt, formattedName);

        typeSysTransRepo.update(formattedDTO);
    }
}
