package by.btk.commchannels.repository.directory;

import by.btk.commchannels.DTO.directory.STypeSysTransDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeSysTransRepoImpl implements TypeSysTransRepo {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public TypeSysTransRepoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<STypeSysTransDTO> findAll(String filter) {
        StringBuilder sql = new StringBuilder("SELECT id_type_st, abbrev_st, name FROM public.s_type_sys_trans WHERE 1=1 ");
        MapSqlParameterSource params = new MapSqlParameterSource();

        if (filter != null && !filter.isBlank()) {
            sql.append("AND CAST(id_type_st AS TEXT) ILIKE :filter ")
                    .append(" OR abbrev_st ILIKE :filter ")
                    .append(" OR name ILIKE :filter ");

            params.addValue("filter", "%" + filter.trim() + "%");
        }

        sql.append("ORDER BY id_type_st ASC");

        return jdbcTemplate.query(sql.toString(), params, (rs, rowNum) -> new STypeSysTransDTO(
                rs.getInt("id_type_st"),
                rs.getString("abbrev_st"),
                rs.getString("name")
        ));
    }

    @Override
    public void insert(STypeSysTransDTO dto) {
        String sql = "INSERT INTO public.s_type_sys_trans (abbrev_st, name) VALUES (:abbrev, :name)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("abbrev", dto.abbrevSt())
                .addValue("name", dto.name());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void update(STypeSysTransDTO dto) {
        String sql = "UPDATE public.s_type_sys_trans SET abbrev_st = :abbrev, name = :name WHERE id_type_st = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("abbrev", dto.abbrevSt())
                .addValue("name", dto.name())
                .addValue("id", dto.idTypeSt());

        jdbcTemplate.update(sql, params);
    }
}
