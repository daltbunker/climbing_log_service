package com.climbing_log.model;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "area")
@EntityListeners(AuditingEntityListener.class)
@SqlResultSetMapping(
  name="areaPathMapping",
  classes = @ConstructorResult(
    targetClass = AreaPath.class,
    columns = {
      @ColumnResult(name = "id", type = Integer.class),
      @ColumnResult(name = "name", type = String.class),
      @ColumnResult(name = "path", type = String.class),
      @ColumnResult(name = "pathIds", type = String.class),
    }
  )
)
@NamedNativeQuery(name = "Area.findAllAreasWithPath",
        resultClass = AreaPath.class,
        resultSetMapping ="areaPathMapping",
        query = "WITH RECURSIVE area_path (id, name, path, pathIds) AS" +
                "(SELECT id, name, name as path, CONVERT(id, CHAR) as pathIds " +
                "FROM area " +
                "WHERE (? = 0 AND area_id IS NULL) OR area_id = ? " +
                "UNION ALL " +
                "SELECT a.id, a.name, CONCAT(ap.path, ' > ', a.name), CONCAT(ap.pathIds, ',', a.id) " +
                "FROM area_path AS ap JOIN area AS a " +
                "ON ap.id = a.area_id)" +
                "SELECT id, name, path, pathIds " +
                "FROM area_path " + 
                "WHERE name LIKE ?"
)
public class Area {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotNull
  private String name;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "area_id")
  @JsonIgnore
  private Area area;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Area getArea() {
    return area;
  }
  public void setArea(Area area) {
    this.area = area;
  }
}