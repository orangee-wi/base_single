package org.orangee.base.single.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统权限表
 * </p>
 *
 * @author orangee
 * @since 2021-1-31 22:38:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysPermission对象", description="系统权限表")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "DI")
    private String id;

    @ApiModelProperty(value = "权限标识")
    private String permissionFlag;

    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    @ApiModelProperty(value = "父级权限标识")
    private String parentFlag;

    @ApiModelProperty(value = "是否菜单")
    private boolean menu;

    @ApiModelProperty(value = "前端组件")
    private String component;

    @ApiModelProperty(value = "排序")
    private int sort;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
