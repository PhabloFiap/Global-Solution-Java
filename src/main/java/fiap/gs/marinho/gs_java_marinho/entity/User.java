package fiap.gs.marinho.gs_java_marinho.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_GS_USUARIO")
@SequenceGenerator(name="usuario", sequenceName = "SQ_GS_USUARIO", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long id;

    @Column(name ="nm_usuario", nullable = false, unique = true)
    private String usuario;


    @Column(name="role", nullable = false) //role: O papel do usuário no sistema (por exemplo, Admin, Researcher, Public).
    private String role;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("usuario")
    private List<Incident> incidents = new ArrayList<>();
//    private Set<Incident> incidents = new HashSet<>();



//    public Set<Incident> getIncidents() {
//        return incidents;
//    }

//    public void setIncidents(Set<Incident> incidents) {
//        this.incidents = incidents;
//    }


    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }



    public User(String usuario, String role, List<Incident> incidents) {
        this.usuario = usuario;
        this.role = role;
        this.incidents = incidents;
    }
    //    public User(String usuario, String senha, String role, Set<Incident> incidents) {
//        this.usuario = usuario;
//        this.senha = senha;
//        this.role = role;
//        this.incidents = incidents;
//    }
}

