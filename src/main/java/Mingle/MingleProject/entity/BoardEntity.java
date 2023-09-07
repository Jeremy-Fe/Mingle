package Mingle.MingleProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "BOARD")
public class BoardEntity {
    @Id
    @Column(name = "B_NUM", nullable = false)
    private Long bNum;

    @Column(name = "B_NAME", nullable = false, length = 100)
    private String bName;

    @OneToMany(mappedBy = "pBNum")
    private Set<PostEntity> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "piBNum")
    private Set<PostimgEntity> postImgs = new LinkedHashSet<>();

}