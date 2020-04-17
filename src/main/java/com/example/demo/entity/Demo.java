package com.example.demo.entity;



import com.codingapi.simplemybatis.tree.ITree;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "t_demo")
public class Demo implements ITree<Long> {

  @Id
  private Long id;

  private Long superId;

  private String name;

  @Column(name = "s_module")
  private String module;

  private String myName;

  private State state;

  private boolean isFlag;

  @Transient
  private String test;

}
