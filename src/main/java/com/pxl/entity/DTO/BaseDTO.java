package com.pxl.entity.DTO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDTO implements Serializable {

 /*   @TableField(value = "CREATE_BY",fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "UPDATE_BY",fill = FieldFill.UPDATE)
    private String updateBy;
*/
    @TableField(fill = FieldFill.INSERT)
    protected Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Date updateDate;
}
