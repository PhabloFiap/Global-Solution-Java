package fiap.gs.marinho.gs_java_marinho.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="TB_GS_INCIDENTE")
@SequenceGenerator(name="incidente", sequenceName = "SQ_GS_INCIDENTE", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Incident {

    @Id
    @Column(name="id_incidente")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incidente")
    private Long id;

    @Column (name = "tipo", nullable = false)
    private String tipo;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private String lugar;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;




    public Incident(String tipo, String descricao, String lugar, LocalDateTime timestamp, User usuario) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.lugar = lugar;
        this.timestamp = timestamp;
        this.usuario = usuario;
    }


}
