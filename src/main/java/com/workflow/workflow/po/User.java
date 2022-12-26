package com.workflow.workflow.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;

import lombok.Data;

@Data
@TableName("users")
public class User {
  @TableId(type = IdType.ASSIGN_UUID)
  private String id;

  @Unique
  @TableField
  private String username;
  @TableField
  private String password;
  @TableField
  private boolean accountNonExpired = true;
  @TableField
  private boolean accountNonLocked = true;
  @TableField
  private boolean credentialsNonExpired = true;
  @TableField
  private boolean enabled = true;

}
