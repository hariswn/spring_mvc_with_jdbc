package com.tekicons.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.tekicons.beans.Emp;

public class EmpDao {
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Emp p) {
        String sql = "insert into employee(emp_name,salary,emp_address) values('" + p.getName() + "'," + p.getSalary() + ",'" + p.getAdddress() + "')";
        return template.update(sql);
    }

    public int update(Emp p) {
        String sql = "update employee set emp_name='" + p.getName() + "', salary=" + p.getSalary() + ",emp_address='" + p.getAdddress() + "' where emp_id=" + p.getId() + "";
        return template.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from employee where emp_id=" + id + "";
        return template.update(sql);
    }

    public Emp getEmpById(int id) {
        String sql = "select * from employee where emp_id=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Emp>(Emp.class));
    }

    public List<Emp> getEmployees() {
        return template.query("select * from employee", new RowMapper<Emp>() {
            public Emp mapRow(ResultSet rs, int row) throws SQLException {
                Emp e = new Emp();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setSalary(rs.getInt(4));
                e.setAdddress(rs.getString(3));
                return e;
            }
        });
    }
}  