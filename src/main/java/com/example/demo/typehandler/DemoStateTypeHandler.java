package com.example.demo.typehandler;

import com.example.demo.entity.State;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lorne
 * @date 2020/3/19
 * @description
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(value= State.class)
public class DemoStateTypeHandler extends BaseTypeHandler<State> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, State parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,parameter.getCode());
    }

    @Override
    public State getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return State.parser(rs.getInt(columnName));
    }

    @Override
    public State getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return State.parser(rs.getInt(columnIndex));
    }

    @Override
    public State getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return State.parser(cs.getInt(columnIndex));
    }
}
