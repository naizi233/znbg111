package com.yzn.server.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AreaEcharts对象", description="玫瑰图图表对象")
public class AreaEcharts {

    @ApiModelProperty(value = "类别")
    private String name;

    @ApiModelProperty(value = "金额")
    private Integer value;
}
