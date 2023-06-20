package com.yzn.server.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_salary_emp")
@ApiModel(value="SalaryEmp对象", description="")
public class SalaryEmp {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "eid")
    private Integer eid;

    @ApiModelProperty(value = "工资")
    private Integer salaryEmployee;

    @ApiModelProperty(value = "日期")
    private LocalDate seDate;

    @TableField(exist = false)
    @ApiModelProperty(value = "姓名")
    private String name;



}
