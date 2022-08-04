package vn.cpa.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SYS_USER")
public class SysUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("username")
    @Column(name = "USERNAME")
    private String username;

    @JsonProperty("full_name")
    @Column(name = "FULLNAME")
    private String fullName;

    @JsonProperty("password")
    @Column(name = "PASSWORD")
    private String password;

    @JsonProperty("email")
    @Column(name = "EMAIL")
    private String email;

    @JsonProperty("phone_number")
    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    @JsonProperty("department_id")
    @Column(name = "DEPARTMENTID")
    private String departmentId;

    @JsonProperty("position_id")
    @Column(name = "POSITIONID")
    private String positionId;

    @JsonProperty("area_id")
    @Column(name = "AREAID")
    private String areaId;

    @JsonProperty("is_active")
    @Column(name = "ISACTIVE")
    private Integer isActive;

    @JsonProperty("is_deleted")
    @Column(name = "ISDELETED")
    private String isDeleted;


    @JsonProperty("work_store")
    @Column(name = "WORKSTOREID")
    private Long workStoreId;

}
