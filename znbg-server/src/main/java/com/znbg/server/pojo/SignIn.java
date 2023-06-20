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

import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_signIn")
@ApiModel(value="SignIn对象", description="")
public class SignIn  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "eid")
    private Integer eid;

    @ApiModelProperty(value = "准时次数")
    private Integer number;

    @ApiModelProperty(value = "上次打卡日期")
    private LocalDate lastDate;

    @ApiModelProperty(value = "迟到次数")
    private Integer numberLate;

    @TableField(exist = false)
    @ApiModelProperty(value = "姓名")
    private String name;

    @TableField(exist = false)
    private Integer percentage;

}
